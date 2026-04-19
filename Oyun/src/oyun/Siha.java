
package oyun;
public class Siha extends Hava {

    private final int denizVurusAvantaji;
  
    public Siha(int dayaniklilik, int seviyePuani, int vurus, String sinif, String altSinif, int karaVurusAvantaji) {
        super(dayaniklilik, seviyePuani, vurus, sinif, altSinif, karaVurusAvantaji);
        this.denizVurusAvantaji = 10; 
    }

    public int getSihaDenizVurusAvantaji() {
        return denizVurusAvantaji;
    }
  
    @Override
    public String getKartAdi() {
        return "Siha";
    }
    @Override
    public void DurumGuncelle(int dayaniklilik, int seviyePuani) {
       this.dayaniklilik = dayaniklilik;
       this.seviyePuani = seviyePuani;
    }
}
