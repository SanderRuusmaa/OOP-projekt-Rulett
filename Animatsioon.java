import javafx.animation.RotateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Animatsioon{

    //ERINEVAD ANIMATSIOONID RATTALE (VÕIDUVÄRV ON TEADA ENNE ANIMATSIOONI MÄNGIMIST)
    public void mustaVõit(ImageView ratasVaade){
        if(Math.random() > 0.5){
            RotateTransition rt = new RotateTransition(Duration.millis(6000), ratasVaade);
            rt.setByAngle(1040);
            rt.setCycleCount(1);
            rt.setAutoReverse(false);

            rt.play();
        } else{
            RotateTransition rt = new RotateTransition(Duration.millis(6000), ratasVaade);
            rt.setByAngle(1110);
            rt.setCycleCount(1);
            rt.setAutoReverse(false);

            rt.play();
        }
    }

    public void punaseVõit(ImageView ratasVaade){
        if(Math.random() > 0.5){
            RotateTransition rt = new RotateTransition(Duration.millis(6000), ratasVaade);
            rt.setByAngle(1157);
            rt.setCycleCount(1);
            rt.setAutoReverse(false);

            rt.play();
        } else{
            RotateTransition rt = new RotateTransition(Duration.millis(6000), ratasVaade);
            rt.setByAngle(1195);
            rt.setCycleCount(1);
            rt.setAutoReverse(false);

            rt.play();
        }
    }

    public void roheliseVõit(ImageView ratasVaade){
        RotateTransition rt = new RotateTransition(Duration.millis(6000), ratasVaade);
        rt.setByAngle(1079);
        rt.setCycleCount(1);
        rt.setAutoReverse(false);

        rt.play();
    }
}
