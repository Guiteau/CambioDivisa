package dad.javafx.cambioDivisa;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CambioDivisa extends Application {

	private Divisa euro = new Divisa("Euro", 1.0);
	private Divisa libra = new Divisa("Libra", 0.8873);
	private Divisa yen = new Divisa("Yen", 133.59);
	private Divisa dolar = new Divisa("Dólar", 1.2007);

	private Divisa[] conjuntoDivisas = { euro, libra, dolar, yen };

	private TextField upperTextField;
	private TextField bottomTextField;
	private Button cambiarButton;
	private ComboBox<Divisa> upperComboBox;
	private ComboBox<Divisa> bottomComboBox;

	@Override
	public void start(Stage primaryStage) throws Exception {

		cambiarButton = new Button("Cambiar");

		upperTextField = new TextField("0");
		upperTextField.setPrefWidth(60);
		upperTextField.setAlignment(Pos.CENTER_LEFT);

		bottomTextField = new TextField("0");
		bottomTextField.setPrefWidth(60);
		bottomTextField.setAlignment(Pos.CENTER_LEFT);
		bottomTextField.setEditable(false);

		upperComboBox = new ComboBox<Divisa>();
		upperComboBox.getItems().addAll(conjuntoDivisas);
		upperComboBox.getSelectionModel().selectFirst();
		bottomComboBox = new ComboBox<Divisa>();
		bottomComboBox.getItems().addAll(conjuntoDivisas);
		bottomComboBox.getSelectionModel().select(3);

		HBox upperHBox = new HBox(5, upperTextField, upperComboBox);
		upperHBox.setAlignment(Pos.CENTER);
		HBox bottomHBox = new HBox(5, bottomTextField, bottomComboBox);
		bottomHBox.setAlignment(Pos.CENTER);
		HBox cambiarButtonHBox = new HBox(5, cambiarButton);
		cambiarButtonHBox.setAlignment(Pos.CENTER);

		cambiarButton.setOnAction(e -> onCambiarAction(e));

		VBox root = new VBox(5, upperHBox, bottomHBox, cambiarButtonHBox);
		root.setAlignment(Pos.CENTER);

		Scene scene = new Scene(root, 320, 200);

		primaryStage.setTitle("Cambio de divisa");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public void onCambiarAction(ActionEvent e) {

		Double resultado, resultadoRedondeado;
		Alert alert;
		ButtonType aceptarButton = new ButtonType("Aceptar", ButtonBar.ButtonData.OK_DONE);

		if (!upperTextField.getText().matches("[0-9]+") || Double.parseDouble(upperTextField.getText()) < 0) {

			alert = new Alert(AlertType.ERROR, "El número introducido no es válido.", aceptarButton);
			alert.setTitle("Cambio de divisa");
			alert.setHeaderText("Error");

			alert.showAndWait();
			
			upperTextField.setText("0");
			bottomTextField.setText("0");

		} else {

			resultado = Divisa.fromTo(upperComboBox.getSelectionModel().getSelectedItem(),
					bottomComboBox.getSelectionModel().getSelectedItem(), Double.parseDouble(upperTextField.getText()));
			
			resultadoRedondeado = (double)Math.round(resultado*1000.0) / 1000.0;

			bottomTextField.setText(Double.toString(resultadoRedondeado));
		}

	}

	public static void main(String[] args) {

		launch(args);

	}

}
