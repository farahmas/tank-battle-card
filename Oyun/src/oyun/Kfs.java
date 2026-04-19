package oyun;
public class Kfs extends Kara {

    private final int havaVurusAvantaji;

    public Kfs(int dayaniklilik, int seviyePuani, int vurus, String sinif, String altSinif, int denizVurusAvantaji) {
        super(dayaniklilik, seviyePuani, vurus, sinif, altSinif, denizVurusAvantaji);
        this.havaVurusAvantaji = 20; 
    }

   
    public int getKfsHavaVurusAvantaji() {
        return havaVurusAvantaji;
    }
    
    @Override
    public String getKartAdi() {
        return "Kfs";
    }

    @Override
    public void DurumGuncelle(int dayaniklilik, int seviyePuani) {
      this.dayaniklilik = dayaniklilik;
      this.seviyePuani = seviyePuani;
    }
}

