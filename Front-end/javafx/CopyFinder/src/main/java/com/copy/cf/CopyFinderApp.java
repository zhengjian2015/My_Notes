package com.copy.cf;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CopyFinderApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ui.fxml"));
		Parent root = loader.load();
		// loader.<Controller>getController().setModel(new Model(getHostServices()));

		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("CopyFinder 1.0.0");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
