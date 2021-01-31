package com.copy.weather;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainController {
	
	 @FXML
	 private TextField fieldSearch;

	 @FXML
	 private Label labelInfo;
	 
	 public void onSearch() {
	       System.out.println(fieldSearch.getText());
	    }

}
