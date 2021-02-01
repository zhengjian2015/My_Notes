package com.copy.weather;

public interface WeatherSupplier {
	
	  Weather get(String cityName);
}
