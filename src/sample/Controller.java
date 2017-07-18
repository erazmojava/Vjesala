package sample;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public Button new_word;
    @FXML
    public Label rijec;

    //skriveno
    @FXML
    public Label unesena_rijec;
    @FXML
    public Button check;
    @FXML
    public TextField slovo;
    @FXML
    public Circle glava;
    @FXML
    public Line tijelo;
    @FXML
    public Line desna_ruka;
    @FXML
    public Line lijeva_ruka;
    @FXML
    public Line desna_noga;
    @FXML
    public Line lijeva_noga;
    @FXML
    public Label poruka;
    @FXML
    public Button result;
    //Za brojenje grešaka
    int brojac;

    //Reset image to default
    public void reset() {
        glava.setOpacity(0.1);
        tijelo.setOpacity(0.1);
        desna_ruka.setOpacity(0.1);
        lijeva_ruka.setOpacity(0.1);
        desna_noga.setOpacity(0.1);
        lijeva_noga.setOpacity(0.1);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Enter word to begin game
        alert();
        //Limit Field "slovo" to 1 char
        addTextLimiter(slovo, 1);
//Set text to poruka
        poruka.setText("Ukupno imate 6 života!");
        //Button New Word
        new_word.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                alert();
                reset();
                brojac = 0;
                poruka.setText("");
            }
        });

        //Button Check
        check.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                checkChar();
            }
        });

        //Button Result
        result.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                enterResult();
            }
        });
    }

    public void checkChar() {
        poruka.setText("");
        if (slovo.getText().equals("")) {
            poruka.setText("Molimo, unesite neko slovo!");
            return;
        }
        if (slovo.getText().matches("[0-9.]")) {
            poruka.setText("Molimo, unesite neko slovo a ne broj!");
            return;
        }

        if (!unesena_rijec.getText().contains(slovo.getText())) {
            brojac();

        }

        for (int i = 0; i < unesena_rijec.getText().length(); i++) {
            //add new _ to string. Lenght from inut alert

            if (unesena_rijec.getText().charAt(i) == slovo.getText().charAt(0)) {
                StringBuilder str = new StringBuilder(rijec.getText());
                str.setCharAt(i, slovo.getText().charAt(0));
                rijec.setText(String.valueOf(str));

            }



        }
        //Reset "slovo" input
        slovo.setText("");
        //Uporedi unesenu riječ i trenutnu riječ
        if (rijec.getText().equals(unesena_rijec.getText())) {
            pobjeda();
        }


    }


    public void alert() {
        TextInputDialog dialog = new TextInputDialog("ERAZMO");
        dialog.setTitle("Enter word - Start Vjesala Game");
        dialog.setHeaderText("Prije početka igre unesite traženu riječ!");
        dialog.setContentText("Please enter word:");


// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();

        //if OK
        if (result.isPresent()) {
            String string = "";
            //Save input Word to hidden textbox
            unesena_rijec.setText(result.get().toUpperCase());


            for (int i = 0; i < result.get().length(); i++) {
                //add new _ to string. Lenght from inut alert
                string += "-";

            }
            //add string _ to "rijec"
            rijec.setText(string);
        }

        //if Cancel press
        else {
            Platform.exit();
            // cancel might have been pressed.
        }

// The Java 8 way to get the response value (with lambda expression).
        //     result.ifPresent(name -> System.out.println(name));

    }


    public void enterResult() {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Vjesala Game - Enter Word");
        dialog.setHeaderText("Unesite konačan rezultat!");
        dialog.setContentText("Please enter word:");


// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();

        //if OK
        if (result.isPresent()) {
            String s = "";
            s = result.get().toUpperCase();
            //Save input Word to hidden textbox
            if (unesena_rijec.getText().toUpperCase().equals(s)) {
                pobjeda();

            } else {
                izgubljeno();

            }


        }

        //if Cancel press
        else {
            return;
            // cancel might have been pressed.
        }

// The Java 8 way to get the response value (with lambda expression).
        //     result.ifPresent(name -> System.out.println(name));

    }

    //Limiter rijeci
    public static void addTextLimiter(final TextField slovo, final int maxLength) {
        slovo.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (slovo.getText().length() > maxLength) {
                    String s = slovo.getText().substring(0, maxLength);
                    slovo.setText(s);
                }
            }
        });
    }

    //slovo to UperCase
    public void toUper(KeyEvent event) {
        slovo.textProperty().addListener((ov, oldValue, newValue) -> {
            slovo.setText(newValue.toUpperCase());
        });

    }

    //Brojac pogresnih unosa
    public void brojac() {
        brojac++;
        // test brojaca
        // System.out.println(brojac);

        if (brojac == 1) {
            glava.setOpacity(100);
            poruka.setText("Imate još 5 života!");
        }
        if (brojac == 2) {
            tijelo.setOpacity(100);
            poruka.setText("Imate još 4 života!");
        }
        if (brojac == 3) {
            desna_ruka.setOpacity(100);
            poruka.setText("Imate još 3 života!");
        }
        if (brojac == 4) {
            lijeva_ruka.setOpacity(100);
            poruka.setText("Imate još 2 života!");
        }
        if (brojac == 5) {
            desna_noga.setOpacity(100);
            poruka.setText("Imate još 1 život!");
        }
        if (brojac == 6) {
            lijeva_noga.setOpacity(100);
            izgubljeno();
        }
        if (brojac > 6) {
            brojac = 0;
        }
    }

    //Pusti zvuk ako je pobjeda ili izgubljeno
    public static void sound(String file) {
        String musicFile = file;     // For example
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    public void pobjeda() {
        poruka.setText("Pobijedili ste!");
        sound("win.mp3");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Igra je završena");
        alert.setHeaderText("Čestitamo, pobijedili ste!");
        alert.setContentText("Želite li započeti igru ponovo?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... user chose OK
            alert();
            reset();
            brojac = 0;
            poruka.setText("");
        } else {
            Platform.exit();
            // ... user chose CANCEL or closed the dialog
        }
    }

    public void izgubljeno() {
        poruka.setText("Izgubili ste!");
        sound("lost.mp3");
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Igra je završena");
        alert.setHeaderText("Nažalost, izgubili ste! Više sreće drugi put.");
        // alert.setContentText("Započnite novu igru!");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... user chose OK
            alert();
            reset();
            brojac = 0;
            poruka.setText("");
        } else {
            Platform.exit();
            // ... user chose CANCEL or closed the dialog
        }
    }
}

