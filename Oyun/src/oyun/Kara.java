
package oyun;
public abstract class Kara extends SavasAraclari {

    protected String altSinif;              
    protected int denizVurusAvantaji;    
    
    public Kara(int dayaniklilik, int seviyePuani, int vurus, String sinif, String altSinif, int denizVurusAvantaji) {
        // Üst sınıfın constructor'ını çağırıyoruz
        super(dayaniklilik, seviyePuani, vurus, sinif);
        this.altSinif = altSinif;
        this.denizVurusAvantaji = denizVurusAvantaji;
    }
}

