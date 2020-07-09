package com.master.travel.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.master.travel.controller.TravelController;

@Service
public class TravelService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TravelService.class);

	@Autowired
	ResourceLoader resourceLoader;

	HashMap<String,String> map = new HashMap<String, String>();

	public String process(String city1 , String city2  ) throws IOException{


		String status= "no";

		if(city1!=null){
			city1= city1.trim();
		}

		if(city2!=null){
			city2= city2.trim();
		}


		LOGGER.info( "city1 : "+city1 + ", city2: "+city2);

		Resource resource = resourceLoader.getResource("classpath:city.txt");
		File file = resource.getFile();

		BufferedReader br = new BufferedReader(new FileReader(file));

		//Read File Content
		String content = new String(Files.readAllBytes(file.toPath()));
		LOGGER.info("file Content \n" +content);


		String line =  null;

		while((line=br.readLine())!=null){ 			

			String str[] = line.split(",");			
			for(int i=0;i<str.length;i++){				
				map.put(str[0], str[1]);
			}

			//Caase 1 and Case3
			if((line.indexOf(city1) !=-1 && line.indexOf(city2) != -1) || (line.indexOf(city2) !=-1 && line.indexOf(city1) != -1)){				
				status  = "yes";
			}


		}
		br.close();


		String value1="";//value
		String value2="";//	value
		String KEY="";//used for Key value pair checking

		String value3="";//value
		String value4=""; //value
		String value5 = "";//key
		for ( String key : map.keySet() ) {			
			KEY = key;
			if(key.equalsIgnoreCase(city1)){
				value1= map.get(key);
				if(value1.equalsIgnoreCase(city2)){					
					status  = "yes";
				}
			}

				
			value3= map.get(KEY);		
			value4= getValueFromKey(map, value3);			

			if(value4 !=null){
				if( value4.trim().equalsIgnoreCase(city1)){

					value5 = getKeyFromValue(map,value3);
					//then

					if( value5.equalsIgnoreCase(city2) ){

						status= "yes";
					}

				}else if( value4.trim().equalsIgnoreCase(city2)){

					value5 = getKeyFromValue(map,value3);
					//then

					if( value5.equalsIgnoreCase(city1) ){					
						status= "yes";
					}

				}

			}
		}
		
		LOGGER.info( "service status : "+status);

		return status;

	}

	private String getValueFromKey(HashMap<String, String> map, String value) {
		String v = "";
		v=map.get(value.trim());			
		return v;
	}

	public static String getKeyFromValue(HashMap<String, String> map, Object value) {
		for (String o : map.keySet()) {
			if (map.get(o).equals(value)) {
				return o;
			}
		}
		return null;
	}		

}
