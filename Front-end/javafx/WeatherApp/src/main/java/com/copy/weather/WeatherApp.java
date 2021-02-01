package com.copy.weather;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WeatherApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		//发现fxml，访问不到要根据 比如访问/com.copay.weather/ui.fxml
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ui.fxml"));
		
		loader.setControllerFactory(t -> new MainController(new MockWeatherProvider()));
		
		Parent root = loader.load();
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
	
	 public static void main(String[] args) {
	        launch(args);
	 }

}
