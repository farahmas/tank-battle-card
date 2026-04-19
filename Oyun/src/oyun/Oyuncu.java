package oyun;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Oyuncu {
    protected int oyuncuID;
    protected int skor;
    protected String oyuncuAdi;
    protected ArrayList<SavasAraclari> kartListesi; //Oyuncunun elinde bulunan kartlar 
    private int baslangicSeviyePuani; // Girilecek başlangıç puanı için
    
    
    
    public Oyuncu(int oyuncuID, String oyuncuAdi, int skor) {
        this.oyuncuID = oyuncuID;
        this.oyuncuAdi = oyuncuAdi;
        this.skor = skor;
        this.kartListesi = new ArrayList<>();
        
    }
    
    public Oyuncu(){
        this.oyuncuID = -1;
        this.oyuncuAdi = "Gizemli Oyuncu";
        this.skor = 0;
        this.kartListesi = new ArrayList<>();
    }
    
    public void SkorGoster() {
        System.out.println(oyuncuAdi + " Skoru: " + skor);
    }
    
    public void kartEkle(SavasAraclari kart) {
        kartListesi.add(kart);
    }
    
    
    public void skorEkle(int eklenenSkor) {
        skor += eklenenSkor;
    }
    
    public void setBaslangicSeviyePuani(int seviyePuani) {
        this.baslangicSeviyePuani = seviyePuani; 
        for (SavasAraclari kart : kartListesi) {
            kart.seviyePuani = seviyePuani; 
        }
    }

    public int getBaslangicPuani() {
        return baslangicSeviyePuani; 
    }
}
