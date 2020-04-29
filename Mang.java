import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Mang extends Application {
    //GLOBAALNE MUUTUJA
    private int rahaAlguses;

    //SETTER RAHALE ALGUSES
    public void setRahaAlguses(int summa){
        rahaAlguses = summa;
    }

    //LUUAKSE UUS RULETT TÜÜPI ISEND, MILLELE ANTAKSE PARAMEETRIKS RAHA ALGUSES
    Rulett mängija = new Rulett(rahaAlguses);

    public void start(Stage pealava) throws IOException {
        BorderPane aken = new BorderPane();

        //=======FAIL========
        FileWriter bw = new FileWriter("C:\\Users\\georg\\OneDrive\\Desktop\\mustruut-gradle\\src\\main\\java\\oop\\logid.txt");
        String euroopaAeg = "dd.MM.yyyy HH:mm:ss";
        DateTimeFormatter euroopaAeg2 = DateTimeFormatter.ofPattern(euroopaAeg);
        bw.write("Ruleti mäng algas: " + LocalDateTime.now().format(euroopaAeg2) + "\n");

        //=============PILDID=======================
        //Kahjuks ei suutnud programmi saada tööle ilma absolute path-i ette andmiseta. Katsetasime üle viie erineva meetodi ja variandi, ent mitte ühelgil korral ei õnnestunud pilte avada ilma absolute path-ita.
        FileInputStream fis = new FileInputStream("C:\\Users\\georg\\OneDrive\\Desktop\\mustruut-gradle\\src\\main\\java\\oop\\ruletiratas.png");
        FileInputStream fisNool = new FileInputStream("C:\\Users\\georg\\OneDrive\\Desktop\\mustruut-gradle\\src\\main\\java\\oop\\kolmnurk.png");
        FileInputStream fisVõit = new FileInputStream("C:\\Users\\georg\\OneDrive\\Desktop\\mustruut-gradle\\src\\main\\java\\oop\\võiduEmoji.png");
        FileInputStream fisKaotus = new FileInputStream("C:\\Users\\georg\\OneDrive\\Desktop\\mustruut-gradle\\src\\main\\java\\oop\\kaotuseEmoji.png");
        FileInputStream fisPunane = new FileInputStream("C:\\Users\\georg\\OneDrive\\Desktop\\mustruut-gradle\\src\\main\\java\\oop\\kinnitus.png");
        FileInputStream fisRoheline = new FileInputStream("C:\\Users\\georg\\OneDrive\\Desktop\\mustruut-gradle\\src\\main\\java\\oop\\kinnitusRoheline.png");
        FileInputStream fisMust = new FileInputStream("C:\\Users\\georg\\OneDrive\\Desktop\\mustruut-gradle\\src\\main\\java\\oop\\kinnitusMust.png");

        //========RATAS, NOOL ja EMOJID=======
        Animatsioon anim = new Animatsioon();

        Image nool = new Image(fisNool);
        ImageView noolVaade = new ImageView(nool);

        Image ratas = new Image(fis);
        ImageView ratasVaade = new ImageView(ratas);

        Image emojiVõit = new Image(fisVõit);
        ImageView emojiVõidud = new ImageView(emojiVõit);
        emojiVõidud.setFitHeight(280);
        emojiVõidud.setFitWidth(330);
        emojiVõidud.setX(20);
        emojiVõidud.setY(80);

        Image emojiKaotus = new Image(fisKaotus);
        ImageView emojiKaotused= new ImageView(emojiKaotus);
        emojiKaotused.setFitHeight(450);
        emojiKaotused.setFitWidth(450);
        emojiKaotused.setX(-30);
        emojiKaotused.setY(40);
        //====================

        //=============================Kinnituse nupud=============================
        Image kinnituseLogoMust = new Image(fisMust);
        Image kinnituseLogoRoheline = new Image(fisRoheline);
        Image kinnituseLogoPunane = new Image(fisPunane);

        ImageView kinnituseLogoVaadePunane = new ImageView(kinnituseLogoPunane);
        ImageView kinnituseLogoVaadeRoheline = new ImageView(kinnituseLogoRoheline);
        ImageView kinnituseLogoVaadeMust = new ImageView(kinnituseLogoMust);

        Button kinnitusPunane = new Button("", kinnituseLogoVaadePunane);
        kinnitusPunane.setStyle("-fx-border-color: #338738;-fx-background-color: #338738; -fx-border-width: 1px; -fx-border-radius: 5px;");

        Button kinnitusRoheline = new Button("", kinnituseLogoVaadeRoheline);
        kinnitusRoheline.setStyle("-fx-border-color: #338738;-fx-background-color: #338738; -fx-border-width: 1px; -fx-border-radius: 5px;");

        Button kinnitusMust = new Button("", kinnituseLogoVaadeMust);
        kinnitusMust.setStyle("-fx-border-color: #338738;-fx-background-color: #338738; -fx-border-width: 1px; -fx-border-radius: 5px;");
        //===========================================================================

        //==================RAHASUMMA LAHTER===================
        TextField rahakast = new TextField();
        rahakast.setPrefColumnCount(15);
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        Label sisestuseTekst = new Label("Sisestage panus:");
        sisestuseTekst.setStyle("-fx-font-size: 1.6em;-fx-text-fill: #e6dc70; -fx-font-family: 'Times New Roman'");
        rahakast.setPrefWidth(100);
        hBox.getChildren().addAll(sisestuseTekst,rahakast, kinnitusPunane, kinnitusMust, kinnitusRoheline);

        mängija.setRaha(rahaAlguses);
        Text bilanss = new Text(0, 50, "Teie bilanss: " + mängija.getRaha() + "€");
        bilanss.setFont(Font.font("Times New Roman", 20));
        bilanss.setFill(Color.LIGHTYELLOW);
        aken.getChildren().add(bilanss);

        Text veaTekst = new Text(0, 80, "");
        veaTekst.setFont(Font.font("Times New Roman", 20 ));

        //==============================NUPULE VAJUTUSE EVENT HANDLERID=======================

        //PUNANE
        kinnitusPunane.setOnMouseEntered(event -> {
            kinnitusPunane.setStyle("-fx-border-color: #ff8066;-fx-background-color: #338738; -fx-border-width: 1px; -fx-border-radius: 5px;");
        });

        kinnitusPunane.setOnMouseExited(event -> {
            kinnitusPunane.setStyle("-fx-border-color: #338738;-fx-background-color: #338738; -fx-border-width: 1px; -fx-border-radius: 5px;");
        });

        kinnitusPunane.setOnMouseClicked(event -> {
            try{
                veaTekst.setText("");
                int panus = Integer.parseInt(rahakast.getText());
                if (panus > mängija.getRaha() || panus <= 0){
                    throw new IllegalArgumentException();
                }
                mängija.setPanus(panus);
                mängija.setValik("punane");

                mängija.mängi();
                rahaAlguses = mängija.getRaha();
                String võit = mängija.getVõiduVärv();

                ratasVaade.setRotate(0);

                if(võit.equals("roheline")){
                    anim.roheliseVõit(ratasVaade);
                } else if (võit.equals("punane")){
                    anim.punaseVõit(ratasVaade);
                } else{
                    anim.mustaVõit(ratasVaade);
                }

                PauseTransition voit = new PauseTransition(Duration.millis(6500));
                PauseTransition kaotus = new PauseTransition(Duration.millis(6500));

                ScaleTransition st = new ScaleTransition(Duration.millis(500), emojiVõidud);
                st.setByX(1.2f);
                st.setByY(1.2f);
                st.setCycleCount(4);
                st.setAutoReverse(true);

                RotateTransition rt = new RotateTransition(Duration.millis(1000), emojiKaotused);
                rt.setByAngle(1079);
                rt.setCycleCount(1);
                rt.setAutoReverse(false);

                voit.setOnFinished(event1 ->
                {bilanss.setText("Teie bilanss: " + rahaAlguses + "€");
                aken.getChildren().add(emojiVõidud);
                st.play();
                });

                kaotus.setOnFinished(event2 ->
                {bilanss.setText("Teie bilanss: " + rahaAlguses + "€");
                aken.getChildren().add(emojiKaotused);
                rt.play();
                });

                if(võit.equals(mängija.getValik())){
                    voit.play();
                }else{
                    kaotus.play();
                    if (mängija.getRaha() <= 0){
                        Text tekst1 = new Text(20, 190, "RAHA SAI OTSA!" +
                                "\nMÄNG LÕPPEB!");
                        tekst1.setFont(Font.font("Tmes New Roman", 90));
                        tekst1.setTextAlignment(TextAlignment.CENTER);
                        tekst1.setFill(Color.RED);
                        tekst1.setStyle("-fx-border-width: 10px; -fx-border-color: black");

                        FadeTransition ft = new FadeTransition(Duration.millis(1000), tekst1);
                        ft.setFromValue(1.0);
                        ft.setToValue(0);
                        ft.setCycleCount(4);
                        ft.setAutoReverse(true);

                        PauseTransition lõpp = new PauseTransition(Duration.millis(11000));
                        lõpp.play();

                        PauseTransition lõpp1 = new PauseTransition(Duration.millis(6500));
                        lõpp1.play();

                        lõpp1.setOnFinished(event1 ->
                        {
                            aken.getChildren().add(tekst1);
                            ft.play();
                        });

                        lõpp.setOnFinished(event1 ->
                        {
                            pealava.close();
                        });

                        bw.write("Mäng lõppes: " + LocalDateTime.now().format(euroopaAeg2) + ".");
                        bw.close();
                    }
                }

                aken.getChildren().remove(emojiVõidud);
                aken.getChildren().remove(emojiKaotused);

            } catch(NumberFormatException e){
                veaTekst.setFill(Color.RED);
                veaTekst.setText("Vigane sisestus, sisestage täisarv!");
            } catch(IllegalArgumentException e){
                veaTekst.setFill(Color.RED);
                veaTekst.setText("Ebasobiv panus!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });



        //ROHELINE
        kinnitusRoheline.setOnMouseEntered(event -> {
            kinnitusRoheline.setStyle("-fx-border-color: #ff8066;-fx-background-color: #338738; -fx-border-width: 1px; -fx-border-radius: 5px;");
        });

        kinnitusRoheline.setOnMouseExited(event -> {
            kinnitusRoheline.setStyle("-fx-border-color: #338738;-fx-background-color: #338738; -fx-border-width: 1px; -fx-border-radius: 5px;");
        });

        kinnitusRoheline.setOnMouseClicked(event -> {
            try{
                veaTekst.setText("");
                int panus = Integer.parseInt(rahakast.getText());
                if (panus > mängija.getRaha() || panus <= 0){
                    throw new IllegalArgumentException();
                }
                mängija.setPanus(panus);
                mängija.setValik("roheline");

                mängija.mängi();
                rahaAlguses = mängija.getRaha();
                String võit = mängija.getVõiduVärv();

                ratasVaade.setRotate(0);

                if(võit.equals("roheline")){
                    anim.roheliseVõit(ratasVaade);
                } else if (võit.equals("punane")){
                    anim.punaseVõit(ratasVaade);
                } else{
                    anim.mustaVõit(ratasVaade);
                }

                PauseTransition voit = new PauseTransition(Duration.millis(6500));
                PauseTransition kaotus = new PauseTransition(Duration.millis(6500));

                ScaleTransition st = new ScaleTransition(Duration.millis(500), emojiVõidud);
                st.setByX(1.2f);
                st.setByY(1.2f);
                st.setCycleCount(4);
                st.setAutoReverse(true);

                RotateTransition rt = new RotateTransition(Duration.millis(1000), emojiKaotused);
                rt.setByAngle(1079);
                rt.setCycleCount(1);
                rt.setAutoReverse(false);

                voit.setOnFinished(event1 ->
                {bilanss.setText("Teie bilanss: " + rahaAlguses + "€");
                    aken.getChildren().add(emojiVõidud);
                    st.play();
                });

                kaotus.setOnFinished(event2 ->
                {bilanss.setText("Teie bilanss: " + rahaAlguses + "€");
                    aken.getChildren().add(emojiKaotused);
                    rt.play();
                });

                if(võit.equals(mängija.getValik())){
                    voit.play();
                }else{
                    kaotus.play();
                    if (mängija.getRaha() <= 0){
                        Text tekst1 = new Text(20, 190, "RAHA SAI OTSA!" +
                                "\nMÄNG LÕPPEB!");
                        tekst1.setFont(Font.font("Tmes New Roman", 90));
                        tekst1.setTextAlignment(TextAlignment.CENTER);
                        tekst1.setFill(Color.RED);
                        tekst1.setStyle("-fx-border-width: 10px; -fx-border-color: black");

                        FadeTransition ft = new FadeTransition(Duration.millis(1000), tekst1);
                        ft.setFromValue(1.0);
                        ft.setToValue(0);
                        ft.setCycleCount(4);
                        ft.setAutoReverse(true);

                        PauseTransition lõpp = new PauseTransition(Duration.millis(11000));
                        lõpp.play();

                        PauseTransition lõpp1 = new PauseTransition(Duration.millis(6500));
                        lõpp1.play();

                        lõpp1.setOnFinished(event1 ->
                        {
                            aken.getChildren().add(tekst1);
                            ft.play();
                        });

                        lõpp.setOnFinished(event1 ->
                        {
                            pealava.close();
                        });
                        bw.write("Mäng lõppes: " + LocalDateTime.now().format(euroopaAeg2) + ".");
                        bw.close();
                    }
                }

                aken.getChildren().remove(emojiVõidud);
                aken.getChildren().remove(emojiKaotused);

            } catch(NumberFormatException e){
                veaTekst.setFill(Color.RED);
                veaTekst.setText("Vigane sisestus, sisestage täisarv!");
            } catch(IllegalArgumentException e){
                veaTekst.setFill(Color.RED);
                veaTekst.setText("Ebasobiv panus!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //MUST

        kinnitusMust.setOnMouseEntered(event -> {
            kinnitusMust.setStyle("-fx-border-color: #ff8066;-fx-background-color: #338738; -fx-border-width: 1px; -fx-border-radius: 5px;");
        });

        kinnitusMust.setOnMouseExited(event -> {
            kinnitusMust.setStyle("-fx-border-color: #338738;-fx-background-color: #338738; -fx-border-width: 1px; -fx-border-radius: 5px;");
        });

        kinnitusMust.setOnMouseClicked(event -> {
            try{
                veaTekst.setText("");
                int panus = Integer.parseInt(rahakast.getText());
                if (panus > mängija.getRaha() || panus <= 0){
                    throw new IllegalArgumentException();
                }
                mängija.setPanus(panus);
                mängija.setValik("must");

                mängija.mängi();
                rahaAlguses = mängija.getRaha();
                String võit = mängija.getVõiduVärv();

                ratasVaade.setRotate(0);

                if(võit.equals("roheline")){
                    anim.roheliseVõit(ratasVaade);
                } else if (võit.equals("punane")){
                    anim.punaseVõit(ratasVaade);
                } else{
                    anim.mustaVõit(ratasVaade);
                }

                PauseTransition voit = new PauseTransition(Duration.millis(6500));
                PauseTransition kaotus = new PauseTransition(Duration.millis(6500));

                ScaleTransition st = new ScaleTransition(Duration.millis(500), emojiVõidud);
                st.setByX(1.2f);
                st.setByY(1.2f);
                st.setCycleCount(4);
                st.setAutoReverse(true);

                RotateTransition rt = new RotateTransition(Duration.millis(1000), emojiKaotused);
                rt.setByAngle(1079);
                rt.setCycleCount(1);
                rt.setAutoReverse(false);

                voit.setOnFinished(event1 ->
                {bilanss.setText("Teie bilanss: " + rahaAlguses + "€");
                    aken.getChildren().add(emojiVõidud);
                    st.play();
                });

                kaotus.setOnFinished(event2 ->
                {bilanss.setText("Teie bilanss: " + rahaAlguses + "€");
                    aken.getChildren().add(emojiKaotused);
                    rt.play();
                });

                if(võit.equals(mängija.getValik())){
                    voit.play();
                }else{
                    kaotus.play();
                    if (mängija.getRaha() <= 0){
                        Text tekst1 = new Text(20, 190, "RAHA SAI OTSA!" +
                                "\nMÄNG LÕPPEB!");
                        tekst1.setFont(Font.font("Tmes New Roman", 90));
                        tekst1.setTextAlignment(TextAlignment.CENTER);
                        tekst1.setFill(Color.RED);
                        tekst1.setStyle("-fx-border-width: 10px; -fx-border-color: black");

                        FadeTransition ft = new FadeTransition(Duration.millis(1000), tekst1);
                        ft.setFromValue(1.0);
                        ft.setToValue(0);
                        ft.setCycleCount(4);
                        ft.setAutoReverse(true);

                        PauseTransition lõpp = new PauseTransition(Duration.millis(11000));
                        lõpp.play();

                        PauseTransition lõpp1 = new PauseTransition(Duration.millis(6500));
                        lõpp1.play();

                        lõpp1.setOnFinished(event1 ->
                        {
                            aken.getChildren().add(tekst1);
                            ft.play();
                        });

                        lõpp.setOnFinished(event1 ->
                        {
                            pealava.close();
                        });
                        bw.write("Mäng lõppes: " + LocalDateTime.now().format(euroopaAeg2) + ".");
                        bw.close();
                    }
                }

                aken.getChildren().remove(emojiVõidud);
                aken.getChildren().remove(emojiKaotused);

            } catch(NumberFormatException e){
                veaTekst.setFill(Color.RED);
                veaTekst.setText("Vigane sisestus, sisestage täisarv!");
            } catch(IllegalArgumentException e){
                veaTekst.setFill(Color.RED);
                veaTekst.setText("Ebasobiv panus!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //========================================================================

        aken.getChildren().add(veaTekst);
        aken.setStyle("-fx-background-color: #338738");
        aken.setTop(hBox);


        //RATTA JA NOOLE KAASA LIIKUMINE EKRAANI SUURUSE MUUTMISEL
        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> {
            ratasVaade.setX(pealava.getWidth()-420);
            ratasVaade.setY(pealava.getHeight()-450);
            noolVaade.setX(pealava.getWidth()-235);
            noolVaade.setY(pealava.getHeight()-450);
        };
        pealava.widthProperty().addListener(stageSizeListener);
        pealava.heightProperty().addListener(stageSizeListener);
        aken.getChildren().addAll(ratasVaade,noolVaade);

        //STSEEN
        Scene stseen = new Scene(aken, 700, 450);
        pealava.setTitle("Ruletimäng");
        pealava.setResizable(true);
        pealava.setMinHeight(500);
        pealava.setMinWidth(750);
        pealava.setScene(stseen);
        pealava.show();
    }
}