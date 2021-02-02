package com.copy.hangman;

import java.util.HashMap;

import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HangmanMain extends Application {

	private static final int APP_W = 900;
	private static final int APP_H = 500;
	private static final Font DEFAULT_FONT = new Font("Courier", 36);

	/**
	 * K - characters [A..Z] and '-' V - javafx.scene.Text representation of K
	 */
	private HashMap<Character, Text> alphabet = new HashMap<Character, Text>();

	/**
	 * Is game playable
	 */
	private SimpleBooleanProperty playable = new SimpleBooleanProperty();

	/**
	 * Current score
	 */
	private SimpleIntegerProperty score = new SimpleIntegerProperty();
	
	//private HangmanImage hangman = new HangmanImage();

	public Parent createContent() {
		HBox rowLetters = new HBox();
		rowLetters.setAlignment(Pos.CENTER);

		Button btnAgain = new Button("NEW GAME");
		btnAgain.disableProperty().bind(playable);
		// btnAgain.setOnAction(event -> startGame());

		HBox rowAlphabet = new HBox(5);
		rowAlphabet.setAlignment(Pos.CENTER);
		for (char c = 'A'; c <= 'Z'; c++) {
			Text t = new Text(String.valueOf(c));
			t.setFont(DEFAULT_FONT);
			alphabet.put(c, t);
			rowAlphabet.getChildren().add(t);
		}

		HBox rowHangman = new HBox(10);
		// layout
		HBox row1 = new HBox();
		HBox row3 = new HBox();
		row1.setAlignment(Pos.CENTER);
		row3.setAlignment(Pos.CENTER);
		for (int i = 0; i < 20; i++) {
			row1.getChildren().add(new Letter(' '));
			row3.getChildren().add(new Letter(' '));
		}

		Text textScore = new Text();
		textScore.textProperty().bind(score.asString().concat(" Points"));

		VBox vBox = new VBox(10, btnAgain, textScore);
		// vertical layout
		vBox.getChildren().addAll(row1, rowLetters, row3, rowAlphabet, rowHangman);
		return vBox;

	}

	private static class Letter extends StackPane {
		private Rectangle bg = new Rectangle(40, 60);
		private Text text;

		public Letter(char letter) {
			bg.setFill(letter == ' ' ? Color.DARKSEAGREEN : Color.WHITE);
			bg.setStroke(Color.BLUE);

			text = new Text(String.valueOf(letter).toUpperCase());
			text.setFont(DEFAULT_FONT);
			text.setVisible(false);

			setAlignment(Pos.CENTER);
			getChildren().addAll(bg, text);
		}

		public boolean isEqualTo(char other) {
			return text.getText().equals(String.valueOf(other).toUpperCase());
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Scene scene = new Scene(createContent());

		primaryStage.setResizable(false);
		primaryStage.setWidth(APP_W);
		primaryStage.setHeight(APP_H);
		primaryStage.setTitle("Hangman");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
