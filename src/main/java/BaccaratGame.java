import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class BaccaratGame extends Application {

	ArrayList<Card> playerHand;
	ArrayList<Card> bankerHand;
	BaccaratDealer theDealer;
	BaccaratGameLogic gameLogic;
	double currentBet;
	double totalWinnings;

	public double evaluateWinnings(){
		return 1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Welcome to JavaFX");

		// components
		Image Mario = new Image("thumbsup.png");
		ImageView img = new ImageView((Mario));
		Button imgHolder = new Button();
		Button play = new Button();
		HBox menu = new HBox(play, img);


		// image properties
		img.setFitHeight(300);
		img.setFitWidth(300);
		img.setPreserveRatio(true);
		imgHolder.setGraphic(img);

		// button properties
		play.setPrefSize(300,200);
		play.setText("PLAY");
		play.setFont((Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50)));

		// menu properties
		menu.setAlignment(Pos.CENTER);

		Scene playMenu = new Scene(menu, 700,700);
		primaryStage.setScene(playMenu);
		primaryStage.show();

		play.setOnAction(e -> {primaryStage.setScene(gameScreen());
			primaryStage.show();
		});


//		primaryStage.setScene();
//		primaryStage.show();
	}



	// GAME SCREEN
	public Scene gameScreen(){
		// BANKER
		// generate hand and deck
		ArrayList<Card> bankerHand = new ArrayList<>(); // hand holds two cards
		theDealer = new BaccaratDealer();
		theDealer.generateDeck(); // generate deck
		bankerHand = theDealer.dealHand(); // deal hand to hand

		// make cards
		Button Banker = new Button();

		// banker card properties
		Banker.setDisable(true);
		Banker.setFont((Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20)));

		// set text inside cards
		Banker.setText(bankerHand.get(0).getSuite() + " " + bankerHand.get(0).getValue() + "\n" + bankerHand.get(1).getSuite() + " " +bankerHand.get(1).getValue());


		// Player
		// generate hand and deck
		ArrayList<Card> playerHand = new ArrayList<>(); // hand holds two cards
		theDealer = new BaccaratDealer();
		theDealer.generateDeck(); // generate deck
		playerHand = theDealer.dealHand(); // deal hand to hand

		// make cards
		Button Player = new Button();

		// banker card properties
		Player.setDisable(true);
		Player.setFont((Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20)));

		// set text inside cards
		Player.setText(playerHand.get(0).getSuite() + " " + playerHand.get(0).getValue() + "\n" + playerHand.get(1).getSuite() + " " + playerHand.get(1).getValue());

		// create HBox to hold cards
		HBox cards = new HBox(Banker, Player);


		//Align HBox
		cards.setAlignment(Pos.CENTER);
		cards.setSpacing(200);

		Banker.setPrefSize(200,200);
		Player.setPrefSize(200,200);

		return new Scene(cards, 700,700);
	}
}
