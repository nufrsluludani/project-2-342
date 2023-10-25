import javafx.application.Application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Queue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;



public class BaccaratGame extends Application {

	ArrayList<Card> playerHand = new ArrayList<>();
	ArrayList<Card> bankerHand = new ArrayList<>();
	BaccaratDealer theDealer;
	BaccaratGameLogic gameLogic = new BaccaratGameLogic();
	double currentBet;
	double totalWinnings = 0;
	int counter = 0;
	String YourBet = "";
	String WhoWon = "";
	ArrayList<String> gameHistory = new ArrayList<>();
	ListView<String> displayWins = new ListView<>();

	private Timeline cardAnimation;

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

		play.setOnAction(e -> {
			try {
				primaryStage.setScene(bettingScreen(primaryStage));
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
			primaryStage.show();
		});


//		primaryStage.setScene();
//		primaryStage.show();
	}

	public Scene bettingScreen(Stage primaryStage) throws Exception {

		// textfield
		TextField placeHolder = new TextField();
		placeHolder.setFont(new Font("Serif", 36));
		placeHolder.setPrefSize(200,100);

		// Label
		Label betInfoLabel = new Label("Place your bet and choose a side:");
		betInfoLabel.setFont(new Font("Serif", 26));
		betInfoLabel.setTextFill(Color.BLACK);
		VBox bid = new VBox(betInfoLabel, placeHolder);
		bid.setAlignment(Pos.CENTER);

		// buttons
		Button play = new Button();
		play.setPrefSize(300,200);
		play.setText("PLAY");
		play.setFont((Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50)));

		Button banker = new Button();
		banker.setPrefSize(150,150);
		banker.setText("Banker");
		banker.setFont((Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20)));

		Button player = new Button();
		player.setPrefSize(150,150);
		player.setText("Player");
		player.setFont((Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20)));

		Button draw = new Button();
		draw.setPrefSize(150,150);
		draw.setText("Draw");
		draw.setFont((Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20)));

		//hBox
		HBox container = new HBox(player, draw, banker);
		container.setAlignment(Pos.CENTER);
		//VBox
		VBox all = new VBox(bid, container);


		// placing bets
		banker.setOnAction(e ->{
			YourBet += "Banker";
			player.setDisable(true);
			draw.setDisable(true);
		});

		player.setOnAction(e ->{
			YourBet += "Player";
			banker.setDisable(true);
			draw.setDisable(true);
		});

		draw.setOnAction(e ->{
			YourBet += "Draw";
			player.setDisable(true);
			banker.setDisable(true);
		});

		play.setOnAction(e -> {
			currentBet = Double.parseDouble(placeHolder.getText());
			try {
				primaryStage.setScene(gameScreen(primaryStage));
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
			primaryStage.show();
			startCardAnimation();
		});

		// borderpane
		BorderPane bpane = new BorderPane();

		// properties
		bpane.setBottom(play);
		bpane.setCenter(all);
		BorderPane.setAlignment(play, Pos.CENTER);
		BorderPane.setAlignment(all, Pos.CENTER);
		BorderPane.setAlignment(bid, Pos.CENTER);

		return new Scene(bpane, 700, 700);
	}



	// GAME SCREEN
	public Scene gameScreen(Stage primaryStage) throws Exception{
		//display wins properties
		displayWins.setPrefSize(100,100);


		// Label for current bet and total hand
		Label currentBetLabel = new Label("Current Bet: " + currentBet + " Bones");
		currentBetLabel.setFont(new Font("Serif", 25));
		currentBetLabel.setTextFill(Color.BLACK);

		Label totalHand = new Label("Total Hand: " + totalWinnings);
		totalHand.setFont(new Font("Serif", 25));
		totalHand.setTextFill(Color.BLACK);

		HBox topOfScreen = new HBox(currentBetLabel,totalHand);
		topOfScreen.setSpacing(50);
		topOfScreen.setAlignment(Pos.CENTER);

		// BANKER
		// generate hand and deck
		theDealer = new BaccaratDealer();
		theDealer.generateDeck(); // generate deck
		//ArrayList<Card> bankerHand = new ArrayList<Card>(); // hand holds two cards
		bankerHand.clear();
		bankerHand = theDealer.dealHand(); // deal hand to banker

		// test code
//		Card firstCard = new Card("heart", 3);
//		Card secondCard = new Card("spade", 1);
//		bankerHand.add(firstCard);
//		bankerHand.add(secondCard);

		// make cards
		Label BankerC1 = new Label("Test");
		Label BankerC2 = new Label("Test");
		Label BankerC3 = new Label("*Extra Dealed Cards Go Here*");
		BankerC1.setTextFill(Color.WHITE);
		BankerC2.setTextFill(Color.WHITE);
		BankerC3.setTextFill(Color.WHITE);
		VBox Banker = new VBox(BankerC1,BankerC2,BankerC3);
		Banker.setAlignment(Pos.CENTER);

		// Player
		// generate hand and deck
		//ArrayList<Card> playerHand = new ArrayList<Card>(); // hand holds two cards
		playerHand.clear();
		playerHand = theDealer.dealHand(); // deal hand to player

		// test code
//		Card firstCard = new Card("heart", 4);
//		Card secondCard = new Card("spade", 4);
//		playerHand.add(firstCard);
//		playerHand.add(secondCard);

		// background
		Image background = new Image("background.jpg");
		BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);

		// make cards
		Label PlayerC1 = new Label("Test");
		Label PlayerC2 = new Label("Test");
		Label PlayerC3 = new Label("*Extra Dealed Cards Go Here*");
		PlayerC1.setTextFill(Color.WHITE);
		PlayerC2.setTextFill(Color.WHITE);
		PlayerC3.setTextFill(Color.WHITE);
		VBox Player = new VBox(PlayerC1,PlayerC2,PlayerC3);
		Player.setAlignment(Pos.CENTER);

		//label to announce drawing of third card
		Label thirdCard = new Label();
		thirdCard.setTextFill(Color.WHITE);

		// create HBox to hold cards
		HBox cards = new HBox(Player,thirdCard, Banker);
		cards.setAlignment(Pos.CENTER);
		cards.setSpacing(100);

		//Border pane set up
		BorderPane bpane = new BorderPane();
		bpane.setBackground(new Background(new BackgroundImage(background,
				BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER,
				bSize)));
		bpane.setCenter(cards);
		bpane.setTop(topOfScreen);
		bpane.setBottom(displayWins);
		BorderPane.setAlignment(currentBetLabel, Pos.CENTER);
		BorderPane.setAlignment(cards,Pos.CENTER);


		ArrayList<Card> finalPlayerHand = playerHand;
		ArrayList<Card> finalPlayerHand1 = playerHand;
		ArrayList<Card> finalBankerHand = bankerHand;
		ArrayList<Card> finalBankerHand1 = bankerHand;
		cardAnimation = new Timeline(
				new KeyFrame(Duration.seconds(1), e -> displayCard(PlayerC1, finalPlayerHand.get(0))),
				new KeyFrame(Duration.seconds(2), e -> displayCard(BankerC1, finalBankerHand.get(0))),
				new KeyFrame(Duration.seconds(3), e -> displayCard(PlayerC2, finalPlayerHand1.get(1))),
				new KeyFrame(Duration.seconds(4), e -> displayCard(BankerC2, finalBankerHand1.get(1)))
		);



		WhoWon += gameLogic.whoWon(bankerHand, playerHand);
		cardAnimation.setOnFinished(event -> {
			Timeline delayTimeline = new Timeline( // delay
					new KeyFrame(Duration.seconds(2), e -> {
						ArrayList<Card> finalPlayerHand2 = finalPlayerHand;
						if (gameLogic.evaluatePlayerDraw(finalPlayerHand)) {
							thirdCard.setText("Drawing third card");
							finalPlayerHand2.add(theDealer.drawOne());
							PlayerC3.setText(finalPlayerHand2.get(2).getSuite() + " " + finalPlayerHand2.get(2).getValue());
						}

						if (gameLogic.evaluateBankerDraw(finalBankerHand, null)) {
							if (gameLogic.handTotal(finalBankerHand) <= 2) {
								thirdCard.setText("Drawing third card");
								finalBankerHand.add(theDealer.drawOne());
								BankerC3.setText(finalBankerHand.get(2).getSuite() + " " + finalBankerHand.get(2).getValue());

							} else if (gameLogic.handTotal(finalBankerHand) <= 6 && finalPlayerHand.size() == 3) {
								thirdCard.setText("Drawing third card");
								finalBankerHand.add(theDealer.drawOne());
								BankerC3.setText(finalBankerHand.get(2).getSuite() + " " + finalBankerHand.get(2).getValue());
							}
						}
					}),
					new KeyFrame(Duration.seconds(5), e -> {
						try {
							primaryStage.setScene(endScreen(primaryStage));
						} catch (Exception ex) {
							throw new RuntimeException(ex);
						}
						primaryStage.show();
					})
			);

			delayTimeline.setCycleCount(1);
			delayTimeline.play();
		});

		cardAnimation.setCycleCount(1);
		cardAnimation.play();

		return new Scene(bpane, 700,700);
	}

	public Scene endScreen(Stage primaryStage) throws Exception {
		BorderPane bpane = new BorderPane();

		Font boldFont = Font.font("Serif", FontWeight.BOLD, 50);

		Label resultLabel = new Label("Winner: " + WhoWon);
		resultLabel.setFont(boldFont);
		resultLabel.setTextFill(Color.BLACK);

		// show players choice
		Label choice = new Label("You bet on: " + YourBet);
		choice.setFont(new Font("Serif", 24));
		choice.setTextFill(Color.BLACK);

		Label betInfoLabel = new Label();
		betInfoLabel.setFont(new Font("Serif", 24));
		betInfoLabel.setTextFill(Color.BLACK);

		String winnerHistory;
		// Set the text for the betInfoLabel based on the outcome
		if (YourBet.equals("Banker") && WhoWon.equals("Banker")) {
			betInfoLabel.setText("You bet on Banker and won!");
			totalWinnings += currentBet;
			winnerHistory = "Congrats";
		}
		else if (YourBet.equals("Player") && WhoWon.equals("Player")) {
			betInfoLabel.setText("You bet on Player and won!");
			totalWinnings += currentBet;
			winnerHistory = "Congrats";
		}
		else if (YourBet.equals("Draw") && WhoWon.equals("Draw")) {
			betInfoLabel.setText("You bet on Draw and won!");
			totalWinnings += currentBet*8;
			winnerHistory = "Congrats";
		}
		else {
			betInfoLabel.setText("You bet on " + YourBet + " and lost.");
			winnerHistory = "Sorry";
		}
		displayWins.getItems().add("Player Total: " + Integer.toString(gameLogic.handTotal(playerHand)) + " Banker Total: " + Integer.toString(gameLogic.handTotal(bankerHand)) + "\n" + gameLogic.whoWon(bankerHand,playerHand) + " wins\n" + winnerHistory + ", " + betInfoLabel.getText() + "\n");
		Button optionsButton = new Button("Options");
		optionsButton.setPrefSize(100, 80);
		optionsButton.setOnAction(e -> openOptionsTab(primaryStage));

		// play another round button
		Button anotherRound = new Button("Play another round");
		anotherRound.setPrefSize(200, 100);
		anotherRound.setOnAction(e -> {
			try {
				YourBet = "";
				WhoWon = "";
				primaryStage.setScene(bettingScreen(primaryStage));
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
			primaryStage.show();
		} );

		VBox labelsBox = new VBox(choice, resultLabel, betInfoLabel,anotherRound);
		labelsBox.setAlignment(Pos.CENTER);

		bpane.setCenter(labelsBox);

		HBox optionsBox = new HBox(optionsButton);
		optionsBox.setAlignment(Pos.TOP_RIGHT);
		bpane.setTop(optionsBox);

		return new Scene(bpane, 700, 700);
	}


	private void openOptionsTab(Stage primaryStage) {
		Stage optionsStage = new Stage();
		optionsStage.setTitle("Options");
		optionsStage.initModality(Modality.APPLICATION_MODAL);
		optionsStage.initOwner(primaryStage);

		// exit button
		Button exitButton = new Button("Exit");
		exitButton.setOnAction(e -> System.exit(0));

		// restart button
		Button restartButton = new Button("Fresh Start");
		restartButton.setOnAction(e -> {
			YourBet = "";
			WhoWon = "";
			totalWinnings = 0;
			displayWins.getItems().clear();
			optionsStage.close();
			try {
				primaryStage.setScene(bettingScreen(primaryStage));
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
			primaryStage.show();
		});


		// hbox to hold buttons
		HBox buttonBox = new HBox(exitButton, restartButton);
		buttonBox.setAlignment(Pos.CENTER);

		VBox optionsLayout = new VBox(buttonBox);
		optionsLayout.setAlignment(Pos.CENTER);

		Scene optionsScene = new Scene(optionsLayout, 400, 300);
		optionsStage.setScene(optionsScene);

		optionsStage.showAndWait();
	}


	private void displayCard(Label label, Card card) {
		label.setText(card.getSuite() + " " + card.getValue());
	}

	private void startCardAnimation() {
		cardAnimation.setCycleCount(1);
		cardAnimation.play();
	}

}