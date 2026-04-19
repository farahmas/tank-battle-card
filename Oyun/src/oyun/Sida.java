package oyun;
public class Sida extends Deniz {
    private final int karaVurusAvantaji;
    
    public Sida(int dayaniklilik, int seviyePuani, int vurus, String sinif, String altSinif, int havaVurusAvantaji) {
        // Hava sınıfının constructor'ını çağırıyoruz
        super(dayaniklilik, seviyePuani, vurus, sinif, altSinif, havaVurusAvantaji);
        this.karaVurusAvantaji = 10;  // Sabit bir değer, yani her Siha nesnesi için 10 olacak
    }
    
    public int getSidaKaraVurusAvantaji() {
        return karaVurusAvantaji;
    }
    
    @Override
    public String getKartAdi() {
        return "Sida";
    }
    @Override
    public void DurumGuncelle(int dayaniklilik, int seviyePuani) {
      this.dayaniklilik = dayaniklilik;
      this.seviyePuani = seviyePuani;
    }
}

