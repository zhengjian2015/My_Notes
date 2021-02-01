package com.copy.cf;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TreeView;

public class Controller {
	
	@FXML
    private Label labelDirectory;
    @FXML
    private Label labelError;
    @FXML
    private TreeView<Node> tree;
    @FXML
    private ProgressIndicator progress;
    
    public void browse() {
    	
    }
}
