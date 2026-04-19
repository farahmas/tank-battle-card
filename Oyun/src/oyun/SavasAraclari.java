
package oyun;
public abstract class SavasAraclari {
    
    protected int dayaniklilik;   
    protected int seviyePuani;    
    protected int vurus;         
    protected String sinif;       
    protected int secilmeSayisi;
    
    public SavasAraclari(int dayaniklilik, int seviyePuani, int vurus, String sinif) {
        this.dayaniklilik = dayaniklilik;
        this.seviyePuani = seviyePuani;
        this.vurus = vurus;
        this.sinif = sinif;
    }

    public void KartPuaniGoster() {
        System.out.println("Dayaniklilik: " + dayaniklilik);
        System.out.println("Seviye Puani: " + seviyePuani);
        System.out.println("Vurus: " + vurus);
        System.out.println();
        
    }

    public abstract String getKartAdi(); // Alt sınıflarda implement edilecek
    public abstract void DurumGuncelle(int dayaniklilik, int seviyePuani);
}
