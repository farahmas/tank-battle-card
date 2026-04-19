
package oyun;

public class Firkateyn extends Deniz {

    public Firkateyn(int dayaniklilik, int seviyePuani, int vurus, String sinif, String altSinif, int havaVurusAvantaji) {
        super(dayaniklilik, seviyePuani, vurus, sinif, altSinif, havaVurusAvantaji);
    }
    @Override
    public void DurumGuncelle(int yenDayaniklilik, int yeniSeviyePuani) {
      this.dayaniklilik = yenDayaniklilik;
      this.seviyePuani = yeniSeviyePuani;
    }
    @Override
    public String getKartAdi() {
        return "Firkateyn";
    }
}