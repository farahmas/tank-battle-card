
package oyun;

public class Obus extends Kara {

   
    public Obus(int dayaniklilik, int seviyePuani, int vurus, String sinif, String altSinif, int denizVurusAvantaji) {
        super(dayaniklilik, seviyePuani, vurus, sinif, altSinif, denizVurusAvantaji);
    }
    @Override
    public String getKartAdi() {
        return "Obus";
    }
    @Override
    public void DurumGuncelle(int dayaniklilik, int seviyePuani) {
        this.dayaniklilik = dayaniklilik;
        this.seviyePuani = seviyePuani;
    }
}


