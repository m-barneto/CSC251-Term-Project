import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CarView extends Application {
	final HBox hb = new HBox();
	CarLot lot = new CarLot();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		stage.setWidth(700);

		TableView<Car> carView = new TableView<Car>();

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

		TableColumn<Car, Double> column7 = new TableColumn<>("Profit");

		column7.setCellValueFactory(new PropertyValueFactory<>("profit"));

		carView.getColumns().addAll(column1, column2, column3, column4, column5, column6, column7);
		data.add(new Car("test1", 10000, 30, 12500.0D, 17500.0D));
		data.add(new Car("test2", 12000, 20, 12000.0D, 15000.0D));
		carView.setItems(data);

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

		final Button addButton = new Button("Add");
		addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				for (Car car : data) {
					if (car.getId() == addId.getText()) {
						Alert alert = new Alert(AlertType.WARNING, "There is already a car with that ID",
								ButtonType.OK);
						alert.showAndWait();
					} else {

						data.add(new Car(addId.getText(), Integer.parseInt(addMileage.getText()),
								Integer.parseInt(addMPG.getText()), Double.parseDouble(addCost.getText()),
								Double.parseDouble(addPrice.getText())));

						addId.clear();
						addMileage.clear();
						addMPG.clear();
						addCost.clear();
						addPrice.clear();
					}
				}
			}
		});

		final Button sellButton = new Button("Sell");
		sellButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				
				for (Car car: data) {
					if (car.getId()  == addId.getText())
						car.setId("test4");
				}
				/*for (Car car : data) {
					if (car.getId() == addId.getText())
						car.sellCar(Double.parseDouble(addPrice.getText()));
				}**/
				carView.setItems(FXCollections.observableArrayList(data));

				addId.clear();
				addMileage.clear();
				addMPG.clear();
				addCost.clear();
				addPrice.clear();
			}
		});

		final Button profButton = new Button("Profit");
		profButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				addId.clear();
				addMileage.clear();
				addMPG.clear();
				addCost.clear();
				addPrice.clear();
			}
		});

		carView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal != null) {
				addId.setText(newVal.getId());
				addMileage.setText(Integer.toString(newVal.getMileage()));
				addMPG.setText(Integer.toString(newVal.getMpg()));
				addCost.setText(Double.toString(newVal.getCost()));
				addPrice.setText(Double.toString(newVal.getSalesPrice()));
			}
		});

		hb.getChildren().addAll(addId, addMileage, addMPG, addCost, addPrice, addButton, sellButton, profButton);
		hb.setSpacing(3);

		final VBox vbox = new VBox(carView);

		vbox.getChildren().addAll(hb);

		Scene scene = new Scene(vbox);

		stage.setScene(scene);

		stage.show();
	}

}
