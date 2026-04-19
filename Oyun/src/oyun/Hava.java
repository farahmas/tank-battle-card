
package oyun;
public abstract class Hava extends SavasAraclari {

    // Özellikler
    protected String altSinif;             
    protected int karaVurusAvantaji;   

    // Constructor
    public Hava(int dayaniklilik, int seviyePuani, int vurus, String sinif, String altSinif, int karaVurusAvantaji) {
  
        super(dayaniklilik, seviyePuani, vurus, sinif);
        this.altSinif = altSinif;
        this.karaVurusAvantaji = karaVurusAvantaji;
    }
}

