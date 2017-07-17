package sample;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public Button Check;
    @FXML
    public TextField slovo;
    @FXML
    public Button NEW;
    @FXML
    public Label rijec;
    @FXML
    public Circle glava;
    @FXML
    public Line desnaRuka;
    @FXML
    public Line lijevaRuka;
    @FXML
    public Line desnaNoga;
    @FXML
    public Line lijevaNoga;


    EventHandler handleNEW = new EventHandler() {
        @Override
        public void handle(Event event) {
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("Text Input Dialog");
            dialog.setHeaderText("Look, a Text Input Dialog");
            dialog.setContentText("Please enter the word:");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                String string = "";
                for (int i = 0; i < result.get().length(); i++) {
                    string += "_  ";

                }
                rijec.setText(string);
            }


        }
    }

        ;
        EventHandler handleCheck = new EventHandler() {
            @Override
            public void handle(Event event) {
            }
        };
        EventHandler handleSlovo = new EventHandler() {
            @Override
            public void handle(Event event) {
            }
        };


        @Override
        public void initialize(URL location, ResourceBundle resources) {
            NEW.setOnAction(handleNEW);
            Check.setOnAction(handleCheck);
            slovo.setOnAction(handleSlovo);
        }


    }
