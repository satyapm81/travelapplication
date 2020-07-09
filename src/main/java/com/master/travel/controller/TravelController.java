package com.master.travel.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.master.travel.service.TravelService;

@RestController
public class TravelController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TravelController.class);


	@Autowired
	TravelService service;

	@GetMapping("/connected")
	public String getResult(@RequestParam(value="origin")  String origin ,@RequestParam(value="destination")  String destination  ) throws IOException{

		String status= "no";
		String city1 = null, city2 = null;
		
		if(!StringUtils.isBlank(origin)){
			city1 =origin;
		}
		
		
		if(!StringUtils.isBlank(destination)){
			city2 =destination;
		}

		LOGGER.info( "origin : "+city1 + ", destination: "+city2);
		
		if(city1!=null && city2!=null){
		 status = service.process(city1, city2);
		}
		return status;
	}



}
