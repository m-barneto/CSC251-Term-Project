package carlot;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
//Preprocesser

public class CarLotFX extends Application {
    public static void main(String[] args) {
        launch(args);
    }
//Launchs JavaFx
    final HBox hb = new HBox();
    CarLot lot = new CarLot();
//Member Variables
    @Override
	public void start(Stage stage) {
		stage.setWidth(700);
	//Creates Stage
		TableView<Car> carView = new TableView<Car>();
	//Creates TableView to hold cars data
		final ObservableList<Car> data = FXCollections.observableArrayList();

		TableColumn<Car, String> column1 = new TableColumn<>("ID");

		column1.setCellValueFactory(new PropertyValueFactory<>("id"));

		TableColumn<Car, Integer> column2 = new TableColumn<>("Mileage");

		column2.setCellValueFactory(new PropertyValueFactory<>("mileage"));

		TableColumn<Car, Integer> column3 = new TableColumn<>("MPG");

		column3.setCellValueFactory(new PropertyValueFactory<>("mpg"));

		TableColumn<Car, Double> column4 = new TableColumn<>("Cost");

		column4.setCellValueFactory(new PropertyValueFactory<>("cost"));

		TableColumn<Car, Double> column5 = new TableColumn<>("Sales Prices");

		column5.setCellValueFactory(new PropertyValueFactory<>("salesPrice"));

		TableColumn<Car, Double> column6 = new TableColumn<>("Sold");

		column6.setCellValueFactory(new PropertyValueFactory<>("sold"));
	//Creates columns for each data type
		carView.getColumns().addAll(column1, column2, column3, column4, column5, column6);
	//Adds the columns to the TableView carView
		data.add(new Car("test1", 10000, 30, 12500.0D, 17500.0D));
		data.add(new Car("test2", 12000, 20, 12000.0D, 15000.0D));
		carView.setItems(data);
	//Adds Cars to the table
		final TextField addId = new TextField();
		addId.setPromptText("ID");
		addId.setMaxWidth(column1.getPrefWidth());
		final TextField addMileage = new TextField();
		addMileage.setMaxWidth(column2.getPrefWidth());
		addMileage.setPromptText("Mileage");
		final TextField addMPG = new TextField();
		addMPG.setMaxWidth(column3.getPrefWidth());
		addMPG.setPromptText("MPG");
		final TextField addCost = new TextField();
		addCost.setMaxWidth(column4.getPrefWidth());
		addCost.setPromptText("Cost");
		final TextField addPrice = new TextField();
		addPrice.setMaxWidth(column5.getPrefWidth());
		addPrice.setPromptText("Price");
	//Creates TextFields for the different car data
		final Button addButton = new Button("Add");
		addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				Car car = data.stream().filter(c -> c.getId().equals(addId.getText())).findAny().orElse(null);

				if (car == null) {
					data.add(new Car(addId.getText(), Integer.parseInt(addMileage.getText()),
							Integer.parseInt(addMPG.getText()), Double.parseDouble(addCost.getText()),
							Double.parseDouble(addPrice.getText())));
				} else {
					Alert alert = new Alert(AlertType.WARNING, "There is already a car with this ID", ButtonType.OK);
					alert.showAndWait();
				}

				addId.clear();
				addMileage.clear();
				addMPG.clear();
				addCost.clear();
				addPrice.clear();
			}
		});
	//Creates Button to add a new car to the table from the TextFields and then clears the TextFields

		final Button sellButton = new Button("Sell");
		sellButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				Car car = data.stream().filter(c -> c.getId().equals(addId.getText())).findAny().orElse(null);
				if (car != null) {
					car.sellCar(Double.parseDouble(addPrice.getText()));

				} else {
					Alert alert = new Alert(AlertType.WARNING, "There is no car with this ID", ButtonType.OK);
					alert.showAndWait();
				}

				carView.refresh();
				carView.setItems(data);

				addId.clear();
				addMileage.clear();
				addMPG.clear();
				addCost.clear();
				addPrice.clear();
			}
		});
	//Creates a Button that Updates the car at the indicated id to indicate that it is sold, the price sold at, and the profit from selling it, and then clears the TextFields
		final Button profButton = new Button("Profit");
		profButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				
				Car car = data.stream().filter(c -> c.getId().equals(addId.getText())).findAny().orElse(null);
				if (car == null) {
					Alert alert = new Alert(AlertType.WARNING, "There is no car with this ID", ButtonType.OK);
					alert.showAndWait();
				} else {
					if (!car.isSold()) {
						Alert alert = new Alert(AlertType.WARNING, "This car is not sold", ButtonType.OK);
						alert.showAndWait();
					} else {
						Alert alert = new Alert(AlertType.NONE, "Profit: " + car.getProfit(), ButtonType.OK);
						alert.showAndWait();
					}

				}

				addId.clear();
				addMileage.clear();
				addMPG.clear();
				addCost.clear();
				addPrice.clear();
			}
		});
	//Creates a Button that creates an alert giving the profit of the car with the id in the Id TextField
		carView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal != null) {
				addId.setText(newVal.getId());
				addMileage.setText(Integer.toString(newVal.getMileage()));
				addMPG.setText(Integer.toString(newVal.getMpg()));
				addCost.setText(Double.toString(newVal.getCost()));
				addPrice.setText(Double.toString(newVal.getSalesPrice()));
			}
		});
	//Adds the Data of a selected car to the its Matching TextField
		hb.getChildren().addAll(addId, addMileage, addMPG, addCost, addPrice, addButton, sellButton, profButton);
		hb.setSpacing(3);
	//Adds the buttons to the HBox
		final VBox vbox = new VBox(carView);
	//Creates a vBox and adds the TableView carView to it
		vbox.getChildren().addAll(hb);
	//Adds the HBox to the VBox
		Scene scene = new Scene(vbox);
	//Creates a Scene with the VBox
		scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
	//Stylesheet for JavaFX
		stage.setTitle("GROUP 1 CARLOT");
	//Title for JavaFX
		stage.setScene(scene);
	//adds the scene to the stage
		stage.show();
	//Displays the Stage
	}

}
