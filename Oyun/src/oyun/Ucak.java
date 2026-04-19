
package oyun;
public class Ucak extends Hava {
   
    public Ucak(int dayaniklilik, int seviyePuani, int vurus, String sinif, String altSinif, int karaVurusAvantaji) {
        // Üst sınıf Hava'nın constructor'ını çağırarak gerekli parametreleri iletmek
        super(dayaniklilik, seviyePuani, vurus, sinif, altSinif, karaVurusAvantaji);
    }
    
    @Override
    public String getKartAdi() {
        return "Ucak";
    }
    @Override
    public void DurumGuncelle(int dayaniklilik, int seviyePuani) {
        this.dayaniklilik = dayaniklilik;
        this.seviyePuani = seviyePuani;
    }

}
