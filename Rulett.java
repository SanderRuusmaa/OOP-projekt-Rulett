import java.util.Random;

public class Rulett {
    //ISENDIVÄLJAD
    private int raha;
    private String valik;
    private String võiduVärv;
    private int panus;

    //KLASSI KONSTRUKTOR
    Rulett(int rahasumma) {
        this.raha = rahasumma;
        this.valik = "";
        this.panus = 0;
    }

    //ISENDIVÄLJADE GETTER JA SETTERID
    public String getVõiduVärv() {
        return võiduVärv;
    }

    public void setVõiduVärv(String võiduVärv) {
        this.võiduVärv = võiduVärv;
    }

    public int getRaha() {
        return raha;
    }

    public void setRaha(int raha) {
        this.raha = raha;
    }

    public String getValik() {
        return valik;
    }

    public void setValik(String valik) {
        this.valik = valik;
    }

    public void setPanus(int panus) {
        this.panus = panus;
    }

    //KLASSI PEAMEETOD, MILLES TEOSTATAKSE RULETI MÄNGU SIMULATSIOON
    public void mängi(){
        //Värvide valik toimub korrektsete tõenäosustega, samadega, mida kasutatakse ka päris ruleti mängus
        Random suvaline = new Random();
        int suvalineArv = suvaline.nextInt(37);
        if (suvalineArv == 0){
            setVõiduVärv("roheline");
            if (valik.equals("roheline")){
                setRaha(getRaha() + panus * 14-panus);
            } else {
                setRaha(getRaha()-panus);
            }
        } else if (suvalineArv > 0 && suvalineArv < 17){
            setVõiduVärv("punane");
            if (valik.equals("punane")){
                setRaha( getRaha() + panus*2-panus);
            } else {
                setRaha(getRaha()-panus);
            }
        } else {
            setVõiduVärv("must");
            if (valik.equals("must")){
                setRaha(getRaha() + panus * 2-panus);
            } else {
                setRaha(getRaha()-panus);
            }
        }
    }
}
