package com.copy.weather;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainController {
	
	 @FXML
	 private TextField fieldSearch;

	 @FXML
	 private Label labelInfo;
	 
	 
	 private WeatherService service = new WeatherService();
	 
	 
	 private WeatherSupplier weatherSupplier;
	 
	 public MainController(WeatherSupplier supplier) {
	        weatherSupplier = supplier;
	    }
	 
	 public void onSearch() {
	       System.out.println(fieldSearch.getText());
	       service.cityName = fieldSearch.getText();
	       System.out.println("11111111111111111111111");
	       service.restart();
	    }
	 
	 private class WeatherService extends Service<Weather> {
		 
		private String cityName;

		@Override
		protected Task<Weather> createTask() {
			// TODO Auto-generated method stub
			System.out.println("init.....");
			Task<Weather> result = new Task<Weather>() {

				@Override
				protected Weather call() throws Exception {
					// TODO Auto-generated method stub
					System.out.println("22222222222222222222222");
					return weatherSupplier.get(cityName);
				}
				
				@Override
                protected void succeeded() {
                    Weather weather = getValue();

                    labelInfo.setText(weather.getCityName() + ": " + weather.getTemp());
                }

                @Override
                protected void failed() {
                    Throwable error = getException();

                    labelInfo.setText("Error: " + error);
                }
                
				
			};
			return result;
		}

		public WeatherService() {
			super();
			System.out.println("countor.....");
			
		}
		
		
		 
		 
	 }

}
