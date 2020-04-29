import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class PeaMenu extends Application {
    public void start(Stage pealava) throws IOException {
        BorderPane aken = new BorderPane();

        //PILTIDE INPUTSTREAMID
        FileInputStream fis1 = new FileInputStream("C:\\Users\\georg\\OneDrive\\Desktop\\mustruut-gradle\\src\\main\\java\\oop\\zetoon.png");
        FileInputStream fis2 = new FileInputStream("C:\\Users\\georg\\OneDrive\\Desktop\\mustruut-gradle\\src\\main\\java\\oop\\zetoon1.png");
        FileInputStream fis3 = new FileInputStream("C:\\Users\\georg\\OneDrive\\Desktop\\mustruut-gradle\\src\\main\\java\\oop\\zetoon2.png");
        FileInputStream fisLogo = new FileInputStream("C:\\Users\\georg\\OneDrive\\Desktop\\mustruut-gradle\\src\\main\\java\\oop\\logo.png");

        //LOGO
        Image logo = new Image(fisLogo);
        ImageView logoVaade = new ImageView(logo);


        //IKOONID
        Image ikoon1 = new Image(fis1);
        ImageView ikooniVaade1 = new ImageView(ikoon1);
        ikooniVaade1.setFitHeight(60);
        ikooniVaade1.setFitWidth(55);

        Image ikoon2 = new Image(fis2);
        ImageView ikooniVaade2 = new ImageView(ikoon2);
        ikooniVaade2.setFitHeight(60);
        ikooniVaade2.setFitWidth(55);

        Image ikoon3 = new Image(fis3);
        ImageView ikooniVaade3 = new ImageView(ikoon3);
        ikooniVaade3.setFitHeight(60);
        ikooniVaade3.setFitWidth(55);

        //NUPUD
        HBox hbox = new HBox();
        HBox logoBox = new HBox();

        Button alusta = new Button("Alusta", ikooniVaade1);
        alusta.setStyle("-fx-font-size: 2.3em;-fx-text-fill: #e6dc70; -fx-background-color: #1d4d20; -fx-border-color: #e6dc70; -fx-border-width: 2px; -fx-border-radius: 5px");

        Button reeglid = new Button("Reeglid", ikooniVaade2);
        reeglid.setStyle("-fx-font-size: 2.3em; -fx-text-fill: #e6dc70; -fx-background-color: #1d4d20; -fx-border-color: #e6dc70; -fx-border-width: 2px; -fx-border-radius: 5px");

        Button lahku = new Button("Lahku", ikooniVaade3);
        lahku.setStyle("-fx-font-size: 2.3em; -fx-text-fill: #e6dc70; -fx-background-color: #1d4d20; -fx-border-color: #e6dc70; -fx-border-width: 2px; -fx-border-radius: 5px");

        //====================MOUSE HANDLERID NUPPUDELE====================

        //ALUSTA
        alusta.setOnMouseEntered(event -> {
            alusta.setStyle("-fx-font-size: 2.3em;-fx-text-fill: #ff8066; -fx-background-color: #29752e; -fx-border-color: #ff8066; -fx-border-width: 2px; -fx-border-radius: 5px");
        });
        alusta.setOnMouseExited(event -> {
            alusta.setStyle("-fx-font-size: 2.3em;-fx-text-fill: #e6dc70; -fx-background-color: #1d4d20; -fx-border-color: #e6dc70; -fx-border-width: 2px; -fx-border-radius: 5px");
        });

        alusta.setOnMouseClicked(event -> {
            RahasummaValik rahasummaSisestamine = new RahasummaValik();
            rahasummaSisestamine.start(pealava);
        });


        //REEGLID NUPP
        reeglid.setOnMouseEntered(event -> {
            reeglid.setStyle("-fx-font-size: 2.3em;-fx-text-fill: #ff8066; -fx-background-color: #29752e; -fx-border-color: #ff8066; -fx-border-width: 2px; -fx-border-radius: 5px;");
        });
        reeglid.setOnMouseExited(event -> {
            reeglid.setStyle("-fx-font-size: 2.3em;-fx-text-fill: #e6dc70; -fx-background-color: #1d4d20; -fx-border-color: #e6dc70; -fx-border-width: 2px; -fx-border-radius: 5px");
        });

        reeglid.setOnMouseClicked(event -> {
            Reeglid reeglidAken = new Reeglid();
            reeglidAken.start(pealava);
        });

        //LAHKU NUPP
        lahku.setOnMouseEntered(event -> {
            lahku.setStyle("-fx-font-size: 2.3em;-fx-text-fill: #ff8066; -fx-background-color: #29752e; -fx-border-color: #ff8066; -fx-border-width: 2px; -fx-border-radius: 5px");
        });
        lahku.setOnMouseExited(event -> {
            lahku.setStyle("-fx-font-size: 2.3em;-fx-text-fill: #e6dc70; -fx-background-color: #1d4d20; -fx-border-color: #e6dc70; -fx-border-width: 2px; -fx-border-radius: 5px");
        });
        lahku.setOnMouseClicked(event ->{
            pealava.close();
        });

        aken.setStyle("-fx-background-color: #338738");

        hbox.getChildren().addAll(alusta, reeglid, lahku);
        hbox.setAlignment(Pos.CENTER);

        logoBox.getChildren().add(logoVaade);
        logoBox.setAlignment(Pos.CENTER);

        aken.setTop(logoBox);
        aken.setCenter(hbox);


        //STSEEN
        Scene stseen = new Scene(aken, 700, 420);

        pealava.setTitle("Main menu");
        pealava.setResizable(true);
        pealava.setMinWidth(750);
        pealava.setMinHeight(450);
        pealava.setScene(stseen);
        pealava.show();

        fis1.close();
        fis2.close();
        fis3.close();
        fisLogo.close();

    }
}
