package com.master.travel.model;

public class Cities {
	
	public String city1;	
	public String city2;
	
	
	public Cities() {
	}
	
	public Cities(String city1, String city2) {
		super();
		this.city1 = city1;
		this.city2 = city2;
	}


	public String getCity1() {
		return city1;
	}


	public void setCity1(String city1) {
		this.city1 = city1;
	}


	public String getCity2() {
		return city2;
	}


	public void setCity2(String city2) {
		this.city2 = city2;
	}

	@Override
	public String toString() {
		return "Cities [city1=" + city1 + ", city2=" + city2 + "]";
	}	
	

}
