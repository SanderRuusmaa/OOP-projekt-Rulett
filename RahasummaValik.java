import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class RahasummaValik extends Application {
    private int algneRaha;

    public void start(Stage pealava){
        BorderPane aken = new BorderPane();
        HBox sisestage = new HBox();

        //RAHASUMMA SISESTUSE VÄLI
        TextField rahakast = new TextField();
        rahakast.setPrefColumnCount(15);
        rahakast.setPrefHeight(30);
        rahakast.setPrefWidth(100);
        rahakast.setFont(Font.font("Times New Roman", 25));
        rahakast.setStyle("-fx-text-fill: #046618");
        rahakast.setAlignment(Pos.CENTER);
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        Label sisestuseTekst = new Label("Sisestage rahasumma:");
        sisestuseTekst.setStyle("-fx-font-family: Rockwell; -fx-font-size: 25;-fx-text-fill: #e6dc70;");
        rahakast.setPrefWidth(100);
        hBox.getChildren().addAll(rahakast);

        //VEATEKST
        Text veaTekst = new Text();
        veaTekst.setFill(Color.DARKRED);

        //EDASI NUPP
        HBox hbox = new HBox();
        Button edasi = new Button("Edasi");
        edasi.setStyle("-fx-font-size: 2.3em;-fx-text-fill: #e6dc70; -fx-background-color: #1d4d20; -fx-border-color: #e6dc70; -fx-border-width: 2px; -fx-border-radius: 5px");

        edasi.setOnMouseEntered(event -> {
            edasi.setStyle("-fx-font-size: 2.3em;-fx-text-fill: #ff8066; -fx-background-color: #29752e; -fx-border-color: #ff8066; -fx-border-width: 2px; -fx-border-radius: 5px;");
        });
        edasi.setOnMouseExited(event -> {
            edasi.setStyle("-fx-font-size: 2.3em;-fx-text-fill: #e6dc70; -fx-background-color: #1d4d20; -fx-border-color: #e6dc70; -fx-border-width: 2px; -fx-border-radius: 5px");
        });

        edasi.setOnMouseClicked(event -> {
            try {
                veaTekst.setText("");
                algneRaha = Integer.parseInt(rahakast.getText());
                if (algneRaha <= 0) {
                    // jääb catch plokki kinni
                    throw new IllegalArgumentException();
                }

                Mang mäng = new Mang();
                mäng.setRahaAlguses(algneRaha);
                mäng.start(pealava);

            } catch (IOException e) {
                System.out.println("Puuduvad vajalikud pildifailid");
            } catch (IllegalArgumentException d){
                veaTekst.setText("Sisestage positiivne täisarv!");
            }
        });

        //ASJADE PAIGUTAMINE AKNASSE
        hbox.getChildren().add(edasi);
        hbox.setAlignment(Pos.CENTER);
        sisestage.getChildren().add(sisestuseTekst);
        sisestage.setAlignment(Pos.TOP_CENTER);
        hBox.getChildren().add(veaTekst);

        aken.setStyle("-fx-background-color: #338738");
        aken.setCenter(hBox);
        hBox.setAlignment(Pos.CENTER);
        aken.setTop(sisestage);
        aken.setBottom(hbox);

        //STSEEN
        Scene stseen = new Scene(aken, 300, 200);
        pealava.setTitle("Rahasumma sisestus");
        pealava.setResizable(false);
        pealava.setScene(stseen);
        pealava.show();
    }
}
