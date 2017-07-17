package sample;

import javafx.event.ActionEvent;
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
    int a = 0
    String rjesenje;
    @FXML
    public Button novo;
    @FXML
    public Line vjesalo1;
    @FXML
    public Line vjesalo2;
    @FXML
    public Line vjesalo3;
    @FXML
    public Line vjesalo4;
    @FXML
    public Line ruka1;
    @FXML
    public Line ruka2;
    @FXML
    public Line noga1;
    @FXML
    public Line noga2;
    @FXML
    public Circle glava;
    @FXML
    public Button submit;
    @FXML
    public TextField slovo;
    @FXML
    public Label rijec;
    @Override


    public void initialize(URL location, ResourceBundle resources) {

        novo.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextInputDialog dialog = new TextInputDialog("");
                dialog.setTitle("Text Input Dialog");
                dialog.setContentText("Please enter your word:");

// Traditional way to get the response value.
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    System.out.println("Your name: " + result.get());
                    String string = "";
                    for (int i=0;i<result.get().length();i++){
                        string+="_  ";


                    }

                    rijec.setText(string);
                    System.out.println("r3223f");
                }

// The Java 8 way to get the response value (with lambda expression).
                result.ifPresent(name -> System.out.println("Your name: " + name));



            }
        });
    }
}
