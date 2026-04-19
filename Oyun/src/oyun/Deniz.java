
package oyun;


public abstract class Deniz extends SavasAraclari {
    protected String altSinif;              // Deniz aracının alt sınıfı mesela Firkateyn
    protected int havaVurusAvantaji;    

    public Deniz(int dayaniklilik, int seviyePuani, int vurus, String sinif, String altSinif, int havaVurusAvantaji) {
        super(dayaniklilik, seviyePuani, vurus, sinif);
        this.altSinif = altSinif;
        this.havaVurusAvantaji = havaVurusAvantaji;
    }
}

