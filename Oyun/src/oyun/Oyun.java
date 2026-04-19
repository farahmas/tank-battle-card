package oyun;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;


public class Oyun {
    protected Oyuncu oyuncu;
    protected Oyuncu bilgisayar;
    protected List<SavasAraclari> tumKartlar; // Tüm savaş araçlarının kartları
    protected int hamleSayisi;
    
    public Oyun(Oyuncu oyuncu, Oyuncu bilgisayar, int hamleSayisi) {
        this.oyuncu = oyuncu;
        this.bilgisayar = bilgisayar;
        this.hamleSayisi = hamleSayisi;
        tumKartlar = new ArrayList<>(); //tüm kartları tutan bir liste, kkartlar sürekli buradan çekilecek
        kartlariHazirla();
        kartDagit(); 
    }
    
    public void kartlariHazirla() {
         // Önceki kartları temizliyoruz ki hep yeni nesneler oluşsun listede
         tumKartlar.clear();
 
        // Yeni savaş araçlarının kartlarını burada oluşturuyoruz.
        tumKartlar.add(new Ucak(20, oyuncu.getBaslangicPuani(), 10, "Hava", "Ucak", 10)); // Uçak kartı örneği
        tumKartlar.add(new Obus(20, oyuncu.getBaslangicPuani(), 10, "Kara", "Obus",5)); // Obüs kartı örneği
        tumKartlar.add(new Firkateyn(25, oyuncu.getBaslangicPuani(), 10, "Deniz","Firkateyn", 10)); // Fırkateyn kartı örneği
        tumKartlar.add(new Siha(15, oyuncu.getBaslangicPuani(), 10, "Hava","Siha", 10)); // Siha kartı örneğı
        tumKartlar.add(new Kfs(10, oyuncu.getBaslangicPuani(), 10, "Kara", "KFS", 20)); // KFS kartı örneği
        tumKartlar.add(new Sida(15, oyuncu.getBaslangicPuani(), 10, "Deniz","Sida", 10)); // Sida kartı örneği
    }
    
    public void kartDagit() {
        Scanner x = new Scanner(System.in);
        int y;
        
        // Her iki oyuncuya 6 kart veriyoruz
        for (int i = 0; i < 6; i++) {
            if (oyuncu.getBaslangicPuani() < 20) {
                do {
                    System.out.println("Lutfen kart seciniz (0, 1 veya 2): ");
                    y = x.nextInt();
                } while (y != 0 && y != 1 && y != 2); 
            } else {
                do {
                    System.out.println("Lutfen kart seciniz (0 ile " + (tumKartlar.size() - 1) + " arasinda sayilar): ");
                    y = x.nextInt();
                } while (y < 0 || y >= tumKartlar.size()); 
            }

            oyuncu.kartEkle(tumKartlar.get(y)); 
            System.out.println((i) + ": " + oyuncu.kartListesi.get(i).getKartAdi());
            tumKartlar.clear();
            kartlariHazirla();
        } 
        
        System.out.println("\n" + oyuncu.oyuncuAdi + ": ");
        for (int i = 0; i < 6; i++) {
            System.out.println((i) + ": " + oyuncu.kartListesi.get(i).getKartAdi());
        }
        
       System.out.println("\n" + bilgisayar.oyuncuAdi + ": ");
    for (int i = 0; i < 6; i++) {
        int index;

        if (bilgisayar.getBaslangicPuani() < 20) {
            
            do {
                index = new Random().nextInt(tumKartlar.size()); 
            } while (index != 0 && index != 1 && index != 2); 
        } else {
            index = new Random().nextInt(tumKartlar.size()); 
        }

        bilgisayar.kartEkle(tumKartlar.get(index)); 
        System.out.println((i + 1) + ": " + bilgisayar.kartListesi.get(i).getKartAdi());

        tumKartlar.clear();
        kartlariHazirla();
    }
   }
    // Saldırı hesaplama burada vurus değerleri dönecek, iki kart için de ayrı ayrı çağırılmalı
    public int SaldiriHesapla(SavasAraclari saldiran, SavasAraclari digerTaraf) {
        int saldiriKart1 = saldiran.vurus;
        // Kart avantajları
        if (saldiran instanceof Hava && digerTaraf instanceof Kara) {
            saldiriKart1 += ((Hava) saldiran).karaVurusAvantaji;
            System.out.println(saldiran.getKartAdi() + " kartinin " + digerTaraf.getKartAdi() +
                    " kartina karsi " + ((Hava) saldiran).karaVurusAvantaji + " kadar Kara vurus avantaji var!");
        } else if (saldiran instanceof Kara && digerTaraf instanceof Deniz) {
            saldiriKart1 += ((Kara) saldiran).denizVurusAvantaji;
            System.out.println(saldiran.getKartAdi() + " kartinin " + digerTaraf.getKartAdi() +
                    " kartina karsi " + ((Kara) saldiran).denizVurusAvantaji + " kadar Deniz vurus avantaji var!");
        } else if (saldiran instanceof Deniz && digerTaraf instanceof Hava) {
            saldiriKart1 += ((Deniz) saldiran).havaVurusAvantaji;
            System.out.println(saldiran.getKartAdi() + " kartinin " + digerTaraf.getKartAdi() +
                    " kartina karsi " + ((Deniz) saldiran).havaVurusAvantaji + " kadar Hava vurus avantaji var!");
        }
        
        if(saldiran instanceof Kfs && digerTaraf instanceof Hava) {
            saldiriKart1 += ((Kfs) saldiran).getKfsHavaVurusAvantaji();
            System.out.println(saldiran.getKartAdi() + " kartinin " + digerTaraf.getKartAdi() +
                    " kartina karsi " + ((Kfs) saldiran).getKfsHavaVurusAvantaji() + " kadar Hva vurus avantaji var!");
        }
        else if(saldiran instanceof Sida && digerTaraf instanceof Kara) {
            saldiriKart1 += ((Sida) saldiran).getSidaKaraVurusAvantaji();
            System.out.println(saldiran.getKartAdi() + " kartinin " + digerTaraf.getKartAdi() +
                    " kartina karsi " + ((Sida) saldiran).getSidaKaraVurusAvantaji() + " kadar Kara vurus avantaji var!");
        }
        else if(saldiran instanceof Siha && digerTaraf instanceof Deniz) {
            saldiriKart1 += ((Siha) saldiran).getSihaDenizVurusAvantaji();
            System.out.println(saldiran.getKartAdi() + " kartinin " + digerTaraf.getKartAdi() +
                    " kartina karsi " + ((Siha) saldiran).getSihaDenizVurusAvantaji() + " kadar Deniz vurus avantaji var!");
        }
        
        return saldiriKart1;                        
    }
    
    // Oyun başlatma ve hamle gerçekleştirme
   public void oyunBaslat() {

        List<SavasAraclari> previouslyChosenCardsBilgisayar = new ArrayList<>(); // Track previously chosen cards
        List<SavasAraclari> previouslyChosenCardsOyuncu = new ArrayList<>();
        
        for (int i = 0; i < hamleSayisi; i++) {
            System.out.println("\nHamle " + (i + 1));

        if (oyuncu.kartListesi.isEmpty()) {
            System.out.println("Tum kartlar elendi! " + oyuncu.oyuncuAdi + " kullanicisi icin kart kalmadi.");
            break; 
        }          
        // Kullanıcı ve bilgisayar kartlarını seçiyor
        ArrayList<SavasAraclari> oyuncuSecilenKartlar = kartSec(false, oyuncu.kartListesi, previouslyChosenCardsOyuncu);
        previouslyChosenCardsOyuncu.addAll(oyuncuSecilenKartlar); 

        ArrayList<SavasAraclari> bilgisayarSecilenKartlar = kartSec(true, bilgisayar.kartListesi, previouslyChosenCardsBilgisayar);
        previouslyChosenCardsBilgisayar.addAll(bilgisayarSecilenKartlar);
        
            System.out.println((i+1) + ". hamle basinda " + oyuncu.oyuncuAdi + " kullanicisinin sectigi kartlar:");
            for(int x=0; x<3; x++)
                System.out.println(oyuncuSecilenKartlar.get(x).getKartAdi());
            
            System.out.println((i+1) + ". hamle basinda " + bilgisayar.oyuncuAdi + " kullanicisinin sectigi kartlar:");
            for(int x=0; x<3; x++)
                System.out.println(bilgisayarSecilenKartlar.get(x).getKartAdi());
            
           // Her iki oyuncu da seçilen kartlarla savaşacak
            for (int j = 0; j < 3; j++) {
                SavasAraclari oyuncuKart = oyuncuSecilenKartlar.get(j);
                SavasAraclari bilgisayarKart = bilgisayarSecilenKartlar.get(j);
                System.out.println("\n------ " + oyuncuKart.getKartAdi() + " VS " + bilgisayarKart.getKartAdi() + " ------\n");
                System.out.println(oyuncu.oyuncuAdi);
                System.out.println("Karti: " + oyuncuKart.getKartAdi());
                oyuncuKart.KartPuaniGoster();
                System.out.println(bilgisayar.oyuncuAdi);
                System.out.println("Karti: " + bilgisayarKart.getKartAdi());
                bilgisayarKart.KartPuaniGoster();

                // Saldırı hesapla ve durumu güncelle
                int vurusOyuncu = SaldiriHesapla(oyuncuKart, bilgisayarKart); //Bu, oyuncunun vurusunu gönderecek
                int vurusBilgisayar = SaldiriHesapla(bilgisayarKart, oyuncuKart); //Bu bilgisayarın vuruşu
              
                System.out.println("\n" + oyuncu.oyuncuAdi + " kullanicisinin " + oyuncuKart.getKartAdi() + " kartinin toplam vurus degeri: " + vurusOyuncu);
                System.out.println("\n" + bilgisayar.oyuncuAdi + " kullanicisinin " + bilgisayarKart.getKartAdi() + " kartinin toplam vurus degeri: " + vurusBilgisayar);
                if (bilgisayarKart.dayaniklilik - vurusOyuncu <= 0) {
                    bilgisayarKart.dayaniklilik =0;
                    System.out.println("\n" + bilgisayarKart.getKartAdi() + " kartinin yeni dayaniklilik degeri: "+ bilgisayarKart.dayaniklilik);
                } else if (bilgisayarKart.dayaniklilik - vurusOyuncu > 0) {
                    bilgisayarKart.dayaniklilik -= vurusOyuncu;
                    System.out.println("\n" + bilgisayarKart.getKartAdi() + " kartinin yeni dayaniklilik degeri: "+ bilgisayarKart.dayaniklilik);
                }
                
                if(oyuncuKart.dayaniklilik - vurusBilgisayar <= 0) {
                    oyuncuKart.dayaniklilik = 0;
                    System.out.println("\n" + oyuncuKart.getKartAdi() + " kartinin yeni dayaniklilik degeri: "+ oyuncuKart.dayaniklilik);
                }
                else if(oyuncuKart.dayaniklilik - vurusBilgisayar > 0) {
                    oyuncuKart.dayaniklilik -= vurusBilgisayar;
                    System.out.println("\n" + oyuncuKart.getKartAdi() + " kartinin yeni dayaniklilik degeri: "+ oyuncuKart.dayaniklilik);
                }
                
                if(bilgisayarKart.dayaniklilik == 0) {
                    //bu kart elenecek.
                    System.out.println("\n" + bilgisayarKart.getKartAdi() + " elendi!\n");
                    if(bilgisayarKart.seviyePuani < 10) {
                        oyuncuKart.seviyePuani += 10;
                        oyuncu.skorEkle(10);
                        System.out.println(oyuncu.oyuncuAdi + " kullanicisinin skoru 10 artti ve yeni skoru " +
                                oyuncu.skor + " oldu.");
                    }
                    else {
                        oyuncuKart.seviyePuani += bilgisayarKart.seviyePuani;
                        oyuncu.skorEkle(bilgisayarKart.seviyePuani);
                        System.out.println(oyuncu.oyuncuAdi + " kullanicisinin skoru " + bilgisayarKart.seviyePuani + " artti ve yeni skoru " +
                                oyuncu.skor + " oldu.");
                    }
                }
                if(oyuncuKart.dayaniklilik == 0) {
                    //elenecek
                    System.out.println("\n" + oyuncuKart.getKartAdi()+ " karti elendi!\n");
                    if(oyuncuKart.seviyePuani < 10) {
                        bilgisayarKart.seviyePuani += 10;
                        bilgisayar.skorEkle(10);
                        System.out.println(bilgisayar.oyuncuAdi + " kullanicisinin skoru 10 artti ve yeni skoru " +
                                bilgisayar.skor + " oldu.");
                    }
                    else {
                        bilgisayarKart.seviyePuani += oyuncuKart.seviyePuani;
                        bilgisayar.skorEkle(oyuncuKart.seviyePuani);
                        System.out.println(bilgisayar.oyuncuAdi + " kullanicisinin skoru " + oyuncuKart.seviyePuani + " artti ve yeni skoru " +
                                bilgisayar.skor + " oldu.");
                    }
                }
            }
            
            //elenen kartlari bilgisayarKartListesi ve oyuncuKartListesinden remove etme
            for(int k = 0 ; k < oyuncu.kartListesi.size(); k++){
                if(oyuncu.kartListesi.get(k).dayaniklilik == 0) {
                    oyuncu.kartListesi.remove(k);
                    k--;
                }
            }
            
            for(int k = 0 ; k < bilgisayar.kartListesi.size(); k++) {
                 if(bilgisayar.kartListesi.get(k).dayaniklilik == 0) {
                    bilgisayar.kartListesi.remove(k);
                    k--;
                }                    
            }
         //Kartlar silindikten sonra listeler tekrar yazdırılsın
         System.out.println("\nPuanlar hesaplandiktan ve elemelerden sonra kart listesi ("+ oyuncu.oyuncuAdi + ")");
         for(int z=0; z < oyuncu.kartListesi.size(); z++)
         {
             System.out.println((z) + ". " + oyuncu.kartListesi.get(z).getKartAdi());
         }
         System.out.println("\nPuanlar hesaplandiktan ve elemelerden sonra kart listesi ("+ bilgisayar.oyuncuAdi + ")");
         for(int z=0; z < bilgisayar.kartListesi.size(); z++)
         {
             System.out.println((z) + ". " + bilgisayar.kartListesi.get(z).getKartAdi());
         }
        
       //Her hamlede yeni 3 kart seçileceği için bu hamle listeleri de temizlenecek
        oyuncuSecilenKartlar.clear(); 
        bilgisayarSecilenKartlar.clear();
        
        //Hamle sonu Kart ekleme
        if(oyuncu.kartListesi.size() >= 2)
        {
          if(oyuncu.skor < 20) {
            tumKartlar.clear();
            kartlariHazirla();
            Random c = new Random();
            int csayi = c.nextInt(3);
            oyuncu.kartListesi.add(tumKartlar.get(csayi));
            System.out.println("\nHamle sonu " + oyuncu.oyuncuAdi + " skoru: " + oyuncu.skor);
            System.out.println("\n" + oyuncu.oyuncuAdi + " kullanicisina 1 adet " + tumKartlar.get(csayi).getKartAdi() + " karti eklendi.\n");
            for(int f = 0; f < oyuncu.kartListesi.size(); f++)
                System.out.println((f) + ": " + oyuncu.kartListesi.get(f).getKartAdi());
          }
          else if(oyuncu.skor >= 20){
            tumKartlar.clear();
            kartlariHazirla();
            Random c = new Random();
            int csayi = c.nextInt(6);
            oyuncu.kartListesi.add(tumKartlar.get(csayi));
            System.out.println("\nHamle sonu " + oyuncu.oyuncuAdi + " skoru: " + oyuncu.skor);
            System.out.println("\n" + oyuncu.oyuncuAdi + " kullanicisina 1 adet " + tumKartlar.get(csayi).getKartAdi() + " karti eklendi.\n");
            for(int f = 0; f < oyuncu.kartListesi.size(); f++)
                System.out.println((f) + ": " + oyuncu.kartListesi.get(f).getKartAdi());
            
          }
        }
        
        else if(oyuncu.kartListesi.size() == 1)
        {   //iki kart eklenecek
            if(oyuncu.skor < 20) {
              tumKartlar.clear();
              kartlariHazirla();
              Random c = new Random();
              //ilk kart
              int csayi = c.nextInt(3);
              oyuncu.kartListesi.add(tumKartlar.get(csayi));
              System.out.println("\nHamle sonu " + oyuncu.oyuncuAdi + "skoru: " + oyuncu.skor);
              System.out.println("\n" + oyuncu.oyuncuAdi + " kullanicisina 1 adet " + tumKartlar.get(csayi).getKartAdi() + " karti eklendi.\n");
                
              //İkinci kart
              tumKartlar.clear();
              kartlariHazirla();
              csayi = c.nextInt(3);
              oyuncu.kartListesi.add(tumKartlar.get(csayi));
              System.out.println("\nHamle sonu " + oyuncu.oyuncuAdi + "skoru: " + oyuncu.skor);
              System.out.println("\n" + oyuncu.oyuncuAdi + " kullanicisina 1 adet " + tumKartlar.get(csayi).getKartAdi() + " karti eklendi.\n");
              for(int f = 0; f < oyuncu.kartListesi.size(); f++)
                System.out.println((f) + ": " + oyuncu.kartListesi.get(f).getKartAdi());
              
              
            }
            else if(oyuncu.skor >= 20){
              tumKartlar.clear();
              kartlariHazirla();
              Random c = new Random();
              //ilk kart
              int csayi = c.nextInt(6);
              oyuncu.kartListesi.add(tumKartlar.get(csayi));
              System.out.println("\nHamle sonu " + oyuncu.oyuncuAdi + " skoru: " + oyuncu.skor);
              System.out.println("\n" + oyuncu.oyuncuAdi + " kullanicisina 1 adet " + tumKartlar.get(csayi).getKartAdi() + " karti eklendi.\n");
              //ikinci kart
              tumKartlar.clear();
              kartlariHazirla();
              csayi = c.nextInt(6);
              oyuncu.kartListesi.add(tumKartlar.get(csayi));
              System.out.println("Hamle sonu " + oyuncu.oyuncuAdi + " skoru: " + oyuncu.skor);
              System.out.println("\n" + oyuncu.oyuncuAdi + " kullanicisina 1 adet " + tumKartlar.get(csayi).getKartAdi() + " karti eklendi.\n");
              for(int f = 0; f < oyuncu.kartListesi.size(); f++)
                System.out.println((f) + ": " + oyuncu.kartListesi.get(f).getKartAdi());
          }           
        }
        
        if(bilgisayar.kartListesi.size() >= 2)
        {
          if(bilgisayar.skor < 20){
            tumKartlar.clear();
            kartlariHazirla();
            Random c = new Random();
            int csayi = c.nextInt(3);
            bilgisayar.kartListesi.add(tumKartlar.get(csayi));
            System.out.println("\nHamle sonu " + bilgisayar.oyuncuAdi + " skoru: " + bilgisayar.skor);
            System.out.println("\nBilgisayara 1 adet kart eklendi.\n");
            for(int f = 0; f < bilgisayar.kartListesi.size(); f++)
                System.out.println((f) + ": " + bilgisayar.kartListesi.get(f).getKartAdi());
          }
          else if(bilgisayar.skor >= 20) {
            tumKartlar.clear();
            kartlariHazirla();
            Random c = new Random();
            int csayi = c.nextInt(6);
            bilgisayar.kartListesi.add(tumKartlar.get(csayi));
            System.out.println("\nHamle sonu " + bilgisayar.oyuncuAdi + " skoru: " + bilgisayar.skor);
            System.out.println("\nBilgisayara 1 adet kart eklendi.\n");
            for(int f = 0; f < bilgisayar.kartListesi.size(); f++)
                System.out.println((f) + ": " + bilgisayar.kartListesi.get(f).getKartAdi());
          }
        }
        else if(bilgisayar.kartListesi.size() == 1)
        { //İki kart eklenecek
            if(bilgisayar.skor < 20){
                //ilk kart
            tumKartlar.clear();
            kartlariHazirla();
            Random c = new Random();
            int csayi = c.nextInt(3);
            bilgisayar.kartListesi.add(tumKartlar.get(csayi));
            System.out.println("\nHamle sonu " + bilgisayar.oyuncuAdi + " skoru: " + bilgisayar.skor);
            System.out.println("\nBilgisayara 1 adet kart eklendi.\n");
            //İkinci kart
            tumKartlar.clear();
            kartlariHazirla();
            csayi = c.nextInt(3);
            bilgisayar.kartListesi.add(tumKartlar.get(csayi));
            System.out.println("\nHamle sonu " + bilgisayar.oyuncuAdi + " skoru: " + bilgisayar.skor);
            System.out.println("\nBilgisayara 1 adet kart eklendi.\n");
            for(int f = 0; f < bilgisayar.kartListesi.size(); f++)
                System.out.println((f) + ": " + bilgisayar.kartListesi.get(f).getKartAdi());
          }
            else if(bilgisayar.skor >= 20) {
                //ilk kart
            tumKartlar.clear();
            kartlariHazirla();
            Random c = new Random();
            int csayi = c.nextInt(6);
            bilgisayar.kartListesi.add(tumKartlar.get(csayi));
            System.out.println("\nHamle sonu " + bilgisayar.oyuncuAdi + " skoru: " + bilgisayar.skor);
            System.out.println("\nBilgisayara 1 adet kart eklendi.\n");
            //ikinci kart
            tumKartlar.clear();
            kartlariHazirla();
            csayi = c.nextInt(6);
            bilgisayar.kartListesi.add(tumKartlar.get(csayi));
            System.out.println("\nHamle sonu " + bilgisayar.oyuncuAdi + " skoru: " + bilgisayar.skor);
            System.out.println("\n1Bilgisayara 1 adet kart eklendi.\n");
            for(int f = 0; f < bilgisayar.kartListesi.size(); f++)
                System.out.println((f) + ": " + bilgisayar.kartListesi.get(f).getKartAdi());
          }
        }
        else if(bilgisayar.kartListesi.isEmpty() || oyuncu.kartListesi.isEmpty() )
        {
            break;
        }
    }
        //Kazanan belirleme
        if(oyuncu.kartListesi.isEmpty())
            System.out.println(bilgisayar.oyuncuAdi + " karsi tarafin tum kartlarini eledi ve kazandi!");
        else if(bilgisayar.kartListesi.isEmpty())
            System.out.println(oyuncu.oyuncuAdi + " karsi tarafin tum kartlarini eledi ve kazandi!");
        else {
             if(oyuncu.skor > bilgisayar.skor)
                System.out.println(oyuncu.oyuncuAdi +", " +  oyuncu.skor + " skoruyla kazandi!");
             else if(bilgisayar.skor > oyuncu.skor)
                System.out.println(bilgisayar.oyuncuAdi +", " +  bilgisayar.skor + " skoruyla kazandi!");
             else if( bilgisayar.skor == oyuncu.skor)
            {   
                int toplamO =0;
                int toplamB =0;
                for(int i=0; i< oyuncu.kartListesi.size(); i++)
                    toplamO += oyuncu.kartListesi.get(i).dayaniklilik;
                for(int i=0; i<bilgisayar.kartListesi.size(); i++)
                   toplamB += bilgisayar.kartListesi.get(i).dayaniklilik;
            
                if(toplamO > toplamB)
                {
                 System.out.println("Skorlar esit oldugundan, " + oyuncu.oyuncuAdi + ", " + toplamO + " olan toplam dayaniklilik degeri daha fazla oldugu icin kazandi!");   
                }
                else if(toplamB > toplamO)
                   System.out.println("Skorlar esit oldugundan, " + bilgisayar.oyuncuAdi + ", " + toplamB +  " olan toplam dayaniklilik degeri daha fazla oldugu icin kazandi!");
                else {
                 System.out.println("Oyun berabere bitti!");
             }
            }
       
        }
            
}
    
public ArrayList<SavasAraclari> kartSec(boolean isBilgisayar, List<SavasAraclari> musaitKartlar, List<SavasAraclari> oncedenSecilenKartlar) {
    ArrayList<SavasAraclari> secilenKartlar = new ArrayList<>();
    Scanner degisken = new Scanner(System.in);
    
    if (isBilgisayar) {
        List<SavasAraclari> bilgisayarIcinMusait = new ArrayList<>(musaitKartlar);
       
        Random random = new Random();
        
        int secilmeyenSayisi = bilgisayarIcinMusait.size();
        
        if (secilmeyenSayisi > 2) {
            for (int i = 0; i < 3; i++) {
                int index = random.nextInt(bilgisayarIcinMusait.size());
                secilenKartlar.add(bilgisayarIcinMusait.get(index));
                bilgisayarIcinMusait.remove(index); 
            }
        } else if (secilmeyenSayisi == 2) {
            for (int i = 0; i < 2; i++) {
                int index = random.nextInt(bilgisayarIcinMusait.size());
                secilenKartlar.add(bilgisayarIcinMusait.get(index));
                bilgisayarIcinMusait.remove(index);
            }
            if (!oncedenSecilenKartlar.isEmpty()) {
                int index = random.nextInt(oncedenSecilenKartlar.size());
                secilenKartlar.add(oncedenSecilenKartlar.get(index));
            }
        } else if (secilmeyenSayisi == 1) {
            int index = random.nextInt(bilgisayarIcinMusait.size());
            secilenKartlar.add(bilgisayarIcinMusait.get(index));
            bilgisayarIcinMusait.remove(index); 
           
            while (secilenKartlar.size() < 3 && !oncedenSecilenKartlar.isEmpty()) {
                index = random.nextInt(oncedenSecilenKartlar.size());
                secilenKartlar.add(oncedenSecilenKartlar.get(index));
                oncedenSecilenKartlar.remove(index); 
            }
        } else {        
            while (secilenKartlar.size() < 3 && !oncedenSecilenKartlar.isEmpty()) {
                int index = random.nextInt(oncedenSecilenKartlar.size());
                secilenKartlar.add(oncedenSecilenKartlar.get(index));
                oncedenSecilenKartlar.remove(index);
            }
        }
    } else {
        if (oncedenSecilenKartlar.size() < musaitKartlar.size()) {
            while (secilenKartlar.size() < 3) {
                System.out.println("Lutfen kart seciniz (0 ile " + (musaitKartlar.size() - 1) + " arasinda sayilar): ");
                int index = degisken.nextInt();

                if (index < 0 || index >= musaitKartlar.size()) {
                    System.out.println("Gecersiz secim! Lutfen gecerli bir kart numarasi girin.");
                    continue;
                }

                if (oncedenSecilenKartlar.contains(musaitKartlar.get(index))) {
                    System.out.println("Bu kart zaten secildi! Lutfen baska bir kart secin.");
                    continue;
                }

                secilenKartlar.add(musaitKartlar.get(index));
                System.out.println("Secilen kart: " + musaitKartlar.get(index).getKartAdi());
            }
        } else {
            System.out.println("Tum kartlar Secildi. Onceki kartlarinizdan seccim yapabilirsiniz.");
            while (secilenKartlar.size() < 3) {
                System.out.println("Lutfen kart seciniz (0 ile " + (oncedenSecilenKartlar.size() - 1) + " arasinda sayilar): ");
                int index = degisken.nextInt();

                if (index < 0 || index >= oncedenSecilenKartlar.size()) {
                    System.out.println("Gecersiz secim! Lutfen gecerli bir kart numarasi girin.");
                    continue;
                }

                secilenKartlar.add(oncedenSecilenKartlar.get(index));
                System.out.println("Secilen kart: " + oncedenSecilenKartlar.get(index).getKartAdi());
            }
        }
    }

    return secilenKartlar;
}

    public static void main(String[] args) {
        
       Scanner scanner = new Scanner(System.in);
        Oyuncu oyuncu = new Oyuncu(1, "Oyuncu", 0);
        Oyuncu bilgisayar = new Oyuncu(2, "Bilgisayar", 0);
        
        System.out.println("Seviye sayisi giriniz: ");
        int seviyePuani;
        do {
            seviyePuani = scanner.nextInt();
        } while (seviyePuani <= 0); 
       
        oyuncu.setBaslangicSeviyePuani(seviyePuani);
        bilgisayar.setBaslangicSeviyePuani(seviyePuani);
        
        System.out.println("Hamle sayisi giriniz: ");
        int hamle = scanner.nextInt();
      
        Oyun oyun = new Oyun(oyuncu, bilgisayar, hamle);
        oyun.oyunBaslat();
    }    
}
