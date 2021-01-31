package com.copy.weather;

public final class Weather {
	
	private String cityName;
	private double temp;
	
	
	public Weather(String cityName, double temp) {
		super();
		this.cityName = cityName;
		this.temp = temp;
	}


	public String getCityName() {
		return cityName;
	}


	public void setCityName(String cityName) {
		this.cityName = cityName;
	}


	public double getTemp() {
		return temp;
	}


	public void setTemp(double temp) {
		this.temp = temp;
	}
	
	

}
