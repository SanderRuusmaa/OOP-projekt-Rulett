import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;

public class Reeglid extends Application {
    public void start(Stage pealava){
        BorderPane aken = new BorderPane();

        //REEGLITE TEKST
        Text tekst = new Text(30, 50 , "Mänguks on rulett. Ruleti mängu põhimõte seisneb selles, et kasutaja panustab \nkas punase, musta või rohelise peale." +
                " Juhul kui ruletiratas langeb kasutaja poolt \nvalitud värvile, siis mängija panus musta ja punase värvivaliku puhul \nduubeldatakse ja rohelise puhul" +
                " 14-kordistatakse. Tõenäosused vastavatele \nvärvidele on 18/37, 18/37 ja 1/37. Mäng saab läbi siis, kui kasutaja vajutab \nlahku nupulue või kastuaja " +
                "bilanss läheb alla nulli. Enne mängu algust \npeab kasutaja ka sisestama rahasumma, millega ta mängu alustab. \n Teie logid salvestatakse faili nimega logid.txt");
        tekst.setFont(Font.font("Times New Roman", 20));
        tekst.setFill(Color.LIGHTYELLOW);
        tekst.setTextAlignment(TextAlignment.CENTER);

        //TAGASI NUPP
        HBox hbox = new HBox();
        Button tagasi = new Button("Tagasi");
        tagasi.setStyle("-fx-font-size: 2.3em;-fx-text-fill: #e6dc70; -fx-background-color: #1d4d20; -fx-border-color: #e6dc70; -fx-border-width: 2px; -fx-border-radius: 5px");

        tagasi.setOnMouseEntered(event -> {
            tagasi.setStyle("-fx-font-size: 2.3em;-fx-text-fill: #ff8066; -fx-background-color: #29752e; -fx-border-color: #ff8066; -fx-border-width: 2px; -fx-border-radius: 5px;");
        });
        tagasi.setOnMouseExited(event -> {
            tagasi.setStyle("-fx-font-size: 2.3em;-fx-text-fill: #e6dc70; -fx-background-color: #1d4d20; -fx-border-color: #e6dc70; -fx-border-width: 2px; -fx-border-radius: 5px");
        });

        tagasi.setOnMouseClicked(event -> {
            PeaMenu peaMenu = new PeaMenu();
            try {
                peaMenu.start(pealava);
            } catch (IOException e) {
                System.out.println("Puuduvad vajalikud pildifailid");
            }
        });

        //ASJADE PAIGUTAMINE AKNASSE
        hbox.getChildren().add(tagasi);
        hbox.setAlignment(Pos.CENTER);
        aken.setStyle("-fx-background-color: #338738");
        aken.getChildren().add(tekst);
        aken.setBottom(hbox);

        //STSEEN
        Scene stseen = new Scene(aken, 700, 300);
        pealava.setTitle("Reeglid");
        pealava.setResizable(false);
        pealava.setScene(stseen);
        pealava.show();
    }
}
