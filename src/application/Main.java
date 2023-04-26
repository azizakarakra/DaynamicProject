package application;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Main extends Application {

	private Stage stage;
	Pair moves[][];
	int i = 0, l = 0, k = 0, j = 0, temp;
	int counter1 = 0, counter2 = 0;
	StringBuilder sb1 = new StringBuilder();
	StringBuilder sb2 = new StringBuilder();
	static ArrayList<Integer> arr = new ArrayList<>();
	static ArrayList<Integer> arr1 = new ArrayList<>();

	private void Switch(Scene scene) {
		this.stage.setScene(scene);
	}

	@Override
	public void start(Stage primaryStage) {
		try {

			this.stage = primaryStage;

			firstScene().getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Switch(firstScene());
			primaryStage.setMaximized(true);
			stage.setMaximized(true);
			stage.setMinWidth(stage.getWidth());
			stage.setMinHeight(stage.getHeight());
			stage.show();
			stage.setTitle("Algorithm Project-1");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Scene thirdScene() {
// TODO Auto-generated method stub
		Group root = new Group();

		String[][] s = new String[moves.length][moves.length];
		for (int i = 0; i < moves.length; ++i) {
			for (int j = 0; j < moves[i].length; ++j) {
				s[i][j] = moves[i][j].toString(); 
			}
		}
		TableView<String[]> tv = new TableView<>();
		tv.setLayoutX(50);
		tv.setPrefSize(800, 600);
		ObservableList<String[]> data = FXCollections.observableArrayList();
		data.addAll(Arrays.asList(s));

		////////////////////////

		for (int i = 0; i < s.length; i++) {
			final int colNo = i ;
			TableColumn<String[], String> tc = new TableColumn(Integer.toString(i));
			tc.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(CellDataFeatures<String[], String> p) {
					return new SimpleStringProperty((p.getValue()[colNo]));
				}
			});
			tc.setPrefWidth(90);
			tv.getColumns().add(tc);
		}
		tv.setItems(data);

		///////////////////////

		Button buttonBack = new Button("<--Back");
		buttonBack.setLayoutX(50);
		buttonBack.setLayoutY(630);
		buttonBack.setMinSize(150, 30);
		buttonBack.setFont(new Font(STYLESHEET_CASPIAN, 20));

		buttonBack.setOnAction(e -> {

			if (buttonBack.isArmed()) {

				Switch(secondeScene());
				// stage.setScene(secondeScene());
				stage.setMaximized(true);
				stage.setFullScreen(true);
				stage.show();

			}

		});

		root.getChildren().addAll(tv, buttonBack);
		return new Scene(root);
	}

	private Scene secondeScene() {
// TODO Auto-generated method stub
		k = 0;
		i = 0;
		j = arr.size() - 1; // until end array
		counter1 = counter2 = 0;
		Group root = new Group();

		Label label1 = new Label();
		label1.setLayoutX(50);
		label1.setLayoutY(150);
		label1.setFont(new Font(20));

		Label label2 = new Label();
		label2.setLayoutX(800);
		label2.setLayoutY(150);
		label2.setFont(new Font(20));

		sb1 = new StringBuilder();
		sb2 = new StringBuilder();

		label1.setText(sb1.toString());
		label2.setText(sb2.toString());

		Button totable = new Button("Next-->");
		totable.setLayoutX(1100);
		totable.setLayoutY(550);
		totable.setMinSize(150, 30);
		totable.setFont(new Font(STYLESHEET_CASPIAN, 20));

		Label label3 = new Label();
		label3.setLayoutX(100);
		label3.setLayoutY(50);
		label3.setFont(new Font(20));
		label3.setText(arr1.toString());

		Label label4 = new Label();
		label4.setLayoutX(100);
		label4.setLayoutY(100);
		label4.setFont(new Font(20));
		label4.setText("counter1 = " + Integer.toString(counter1));

		Label label5 = new Label();
		label5.setLayoutX(1000);
		label5.setLayoutY(100);
		label5.setFont(new Font(20));
		label5.setText(arr1.toString());
		label5.setText("counter2 = " + Integer.toString(counter2));

		totable.setOnAction(e -> {
			if (arr1.size() == 0) {
				Switch(thirdScene());
				stage.setMaximized(true);
				stage.setFullScreen(true);
				stage.show();
			} else {
				printSequence(arr, moves);
				arr1.remove(arr1.indexOf(temp));
				++k;
				label1.setText(sb1.toString());
				label2.setText(sb2.toString());
				label3.setText(arr1.toString());
				label4.setText("counter1 = " + Integer.toString(counter1));
				label5.setText("counter2 = " + Integer.toString(counter2));

			}

		});

		Button buttonBack = new Button("<--Back");
		buttonBack.setLayoutX(5);
		buttonBack.setLayoutY(550);
		buttonBack.setMinSize(150, 30);
		buttonBack.setFont(new Font(STYLESHEET_CASPIAN, 20));

		buttonBack.setOnAction(e -> {

			if (buttonBack.isArmed()) {

				Switch(firstScene());

				stage.setMaximized(true);
				stage.setFullScreen(true);
				stage.show();

			}

		});

		root.getChildren().addAll(label1, label2, totable, label3, label4, label5, buttonBack);
		return new Scene(root);
	}

	private Scene firstScene() {
// TODO Auto-generated method stub
		i = 0;// initialization
		l = 0;
		arr = new ArrayList<>();
		arr1 = new ArrayList<>();
		Group root = new Group();
		Label label1 = new Label("Enter Coins:");
		label1.setLayoutX(190);
		label1.setLayoutY(150);
		label1.setFont(new Font(25));
		Button addButton = new Button("Add");
		addButton.setLayoutX(650);
		addButton.setLayoutY(150);
		addButton.setMinSize(150, 30);
		addButton.setFont(new Font(STYLESHEET_CASPIAN, 20));

		TextField txt1 = new TextField();
		txt1.setLayoutX(350);
		txt1.setLayoutY(156);

		Label arrayLabel = new Label();
		arrayLabel.setLayoutX(200);
		arrayLabel.setLayoutY(300);
		arrayLabel.setFont(new Font(25));
		////////////////////////////////////////////

		addButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (isInt(txt1.getText().trim())) { //if isInt return true
					arr.add(Integer.parseInt(txt1.getText().trim()));
					arr1.add(Integer.parseInt(txt1.getText().trim()));
					arrayLabel.setText(arr.toString());
				} else {
					//if isInt return false
					arrayLabel.setText("Unvalid Entery!");
				}
				txt1.clear();

			}
		});

		//////////////////////////////////////////

		Button buttonStart = new Button("Start->");
		buttonStart.setLayoutX(1100);
		buttonStart.setLayoutY(550);
		buttonStart.setMinSize(150, 30);
		buttonStart.setFont(new Font(STYLESHEET_CASPIAN, 20));

		Label botomLabel = new Label();
		botomLabel.setLayoutX(400);
		botomLabel.setLayoutY(650);
		botomLabel.setFont(new Font(25));

		///////////////////////////////////////

		buttonStart.setOnAction(e -> {
			moves = new Pair[arr.size()][arr.size()];
			if (arr.size() % 2 == 0 && arr.size() > 0) {
				for (i = 0; i < moves.length; i++) { // initialization
					for (int j = 0; j < moves[i].length; j++) {
						moves[i][j] = new Pair();// 0
					}
				}

				for (int i = 0; i < arr.size(); i++) {
					moves[i][i].first = arr.get(i); // (i,0) if i have 1 coin
					moves[i][i].second = 0;
					// to track the sequence of moves
					moves[i][i].index = i;
				}
				i = 0;
				l = 0;
				moves = findMoves(arr);

				////////////////////////
				k = 0;
				Switch(secondeScene());
				stage.setMaximized(true);
				stage.setFullScreen(true);
				stage.show();
				botomLabel.setText("");
			} else if (arr.size() % 2 != 0) {
				botomLabel.setText("The array size should be even!");
			} else {
				botomLabel.setText("The array is Empty!");

			}

		});

		//////////////////////////////////////

		Button buttonlogOut = new Button("Log Out");
		buttonlogOut.setLayoutX(5);
		buttonlogOut.setLayoutY(550);
		buttonlogOut.setMinSize(150, 30);
		buttonlogOut.setFont(new Font(STYLESHEET_CASPIAN, 20));

		buttonlogOut.setOnAction(e -> {
			if (buttonlogOut.isArmed()) {
				Platform.exit(); //to exit from scene
			}
		});

		Button clear = new Button("Clear");
		clear.setLayoutX(900);
		clear.setLayoutY(150);
		clear.setMinSize(150, 30);
		clear.setFont(new Font(STYLESHEET_CASPIAN, 20));

		clear.setOnAction(e -> {
			arr.remove(arr.size() - 1);
			arr1.remove(arr1.size() - 1);
			arrayLabel.setText(arr.toString());

		});

		root.getChildren().addAll(label1, addButton, buttonStart, txt1, arrayLabel, botomLabel, buttonlogOut, clear);
		return new Scene(root);
	}

	public static boolean isInt(String txt) {
		try {
			Integer.parseInt(txt);//from string to int 
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	///////////////////////////////////////////////////////

	public Pair[][] findMoves(ArrayList<Integer> arr) {

		for (int l = 2; l <= arr.size(); l++) { // L represent the number of coins
			for (int i = 0; i <= arr.size() - l; i++) {
				int j = i + l - 1;
				if (arr.get(i) + moves[i + 1][j].second > moves[i][j - 1].second + arr.get(j)) {
					moves[i][j].first = arr.get(i) + moves[i + 1][j].second;
					moves[i][j].second = moves[i + 1][j].first;
					moves[i][j].index = i;
				} else {
					moves[i][j].first = arr.get(j) + moves[i][j - 1].second;
					moves[i][j].second = moves[i][j - 1].first;
					moves[i][j].index = j;
				}
			}
		}
		return moves;
	}

	//////////////////////////////////////////////////////////

	public void printSequence(ArrayList<Integer> arr, Pair moves[][]) {
//method to select coins

		int step; //for print

		if (k < arr.size()) { // k num of plays 
			step = moves[i][j].index;
			temp = arr.get(step);

			// this is the value of pick and its index
			if (k % 2 == 0) {

				counter1 += temp;
				sb1.append("user1 => value: " + arr.get(step) + " " + "index: " + step + " \n\n");
			} else {
				counter2 += temp;

				sb2.append("user2 => value: " + arr.get(step) + " " + "index: " + step + " \n\n");
			}

			if (step == i) { //step forward
				i = i + 1;

			} else {
				j = j - 1; //step backward
			}
		}

	}
	/////////////////////////////////////////////////////////////////

	public static void main(String[] args) {
		launch(args);
	}
}