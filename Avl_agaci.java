import java.util.*;
import java.io.*;

public class Avl_agaci 
{   
    public class Dugum
    {
        Dugum sol;
        Dugum sag;
        int yukseklik;
        String veri;
        String item;
        int deger;
        int sayi;
        
        public Dugum(String veri)
        {
            this.veri = veri;
            this.yukseklik = 1; //En az 1 olacak
            this.sag = null; //Sol ve Sağ Başlangıçta boş
            this.sol = null;
        }
    }
    public Dugum kok = null;
    public int deger = 0;
    
    public boolean isEmpty() //Boş olu olmadığını kontrol eden metod.
    {
        if(kok != null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
   
    public int yukseklik(Dugum X) //Yükseklik kontrolü
    {
        if(X == null) //X yoksa..
        { 
            return 0;  //Yükseklik 0'dır.
        }
        return X.yukseklik; //Yükseklik döndürülür
    }
    
    public Dugum sagaGonder(Dugum a) //Sağa yerleştirmeyi sağlar.
    {
        Dugum b = a.sol;
        Dugum z = b.sag;
        
        b.sag = a;
        a.sol = z;
        
        a.yukseklik = Math.max(yukseklik(a.sol), yukseklik(a.sag))+1;
        b.yukseklik = Math.max(yukseklik(b.sol), yukseklik(b.sag))+1;
        
        return b;
    }
    
    public Dugum solaGonder(Dugum b) //Sola yerleştirmeyi sağlar.
    {
        Dugum a = b.sag;
        Dugum z = a.sol;
        
        a.sol = b;
        b.sag = z;
        
        b.yukseklik = Math.max(yukseklik(b.sol), yukseklik(b.sag))+1;
        a.yukseklik = Math.max(yukseklik(a.sol), yukseklik(a.sag))+1;
        
        return a;
    }
    
    public Dugum enKucuk(Dugum dugum) //En küçük düğümü bulur
    {
        Dugum suanki = dugum;
        
        while(suanki.sol != null)
        {
            suanki = suanki.sol;
        }
        return suanki;
    }
    
    public Dugum enBuyuk(Dugum dugum) //En büyük düğümü bulur -- PROGRAMDA KULLANILMADI!
    {
        Dugum suanki = dugum;
        
        while(suanki.sag != null)
        {
            suanki = suanki.sag;
        }
        return suanki;
    }
    
    public int dengele(Dugum X) //Dengeleme faktörü, dengeleme işlemi.
    {
        if(X == null)
        {
            return 0;
        }
        return yukseklik(X.sol)-yukseklik(X.sag);
    }
    
    public Dugum dugumAra(Dugum dugum, String veri) //Düğüm arar, buna bağlı silme işlemimiz vardır.
    {
        /* Eğer değer, kökün değerinden küçükse kökün solundaki değer silinir. 
        Eğer değilse sağındaki değer silinir. Şartlar sağlanmazsa başka bir if else 
        döngüsüne girer. Sol kök null değilse, sol kök tempe atılır, 
        eğer sol kök null ise sağ kök tempe atılır. Eğer temp “null” ise, kök “null” olur 
        ve 2. İf else döngüsündeki if sonucunda temp=”null” işlemi gerçekleşir. 
        Hiçbir olumlu döngüye girmez ise temp, sağ tarafın en küçüğüne atanır. 
        Temp ile kök değerleri eşitlenir, kökün sağından temp değeri silinir.
        */
        if(dugum == null) //Eğer düğüm yoksa..
        {
            return dugum; //Düğüm döndürülür => null.
        }
        if(dugum.veri.toLowerCase().compareTo(veri.toLowerCase())>0)
        {
            return dugumAra(dugum.sol, veri);
        }
        else if(dugum.veri.toLowerCase().compareTo(veri.toLowerCase())<0)
        {
            return dugumAra(dugum.sag, veri);
        }
        return dugum;
    }
    
    public void ekle(String eleman) //Void olarak ekleme metodu, mainden direk eklemeyi sağlar.
    {
        ekle(kok,eleman);
    }
    
    public Dugum ekle(Dugum dugum, String veri) //Ekle metodu.
    {
        /*
        Eğer düğüm “null” ise, düğüm türünden bir yeniDugum tanımlanır ve veri eklenir. 
        Daha sonra oluşan bu veri, yeniDugum değişkeni olarak atanır. 
        Eğer düğüm verisi 0’dan büyükse sola, küçükse sola eklenir. 
        Düğüm yüksekliği özel Math fonksiyonuyla hesaplanır, denge faktörü kontrol edilir. 
        Denge faktörü durumuna göre sağa veya sola düğüm gönderme işlemi yapılır. 
        En sonda düğüm döndürülür.
        */
        
        if(dugum == null)
        {
            Dugum yeniDugum = new Dugum(veri);
            return yeniDugum;
        }
        if(dugum.veri.toLowerCase().compareTo(veri.toLowerCase())>0)
        {
            dugum.sol = ekle(dugum.sol, veri);
        }
        else if(dugum.veri.toLowerCase().compareTo(veri.toLowerCase())<0)
        {
            dugum.sag = ekle(dugum.sag, veri);
        }   
        
        dugum.yukseklik = Math.max(yukseklik(dugum.sol), yukseklik(dugum.sag))+1;
        
        int dengele = dengele(dugum);
        
        if (dengele > 1 && veri.toLowerCase().compareTo(dugum.sol.veri.toLowerCase()) < 0)
        {
            return sagaGonder(dugum);
        }
        if (dengele < -1 && veri.toLowerCase().compareTo(dugum.sag.veri.toLowerCase()) > 0)
        {
            dugum.sol = solaGonder(dugum.sol);
            return sagaGonder(dugum);
        }
        if (dengele < -1 && veri.toLowerCase().compareTo(dugum.sol.veri.toLowerCase()) < 0)
        {
            dugum.sag = sagaGonder(dugum.sag);
            return solaGonder(dugum); 
        }
        return dugum;
    }
    
    public void sil(int el_sil) //Void olarak silme metodu, mainden direk silmeyi sağlar.
    {
        sil(kok,el_sil);
    }
    
    public Dugum sil(Dugum kok, int deger) //Silme metodu.
    {
        /*
        Eğer kök “null” ise kök döndürür.
        Eğer değer, kökün değerinden küçükse kökün solundaki değer silinir. 
        Eğer değilse sağındaki değer silinir. Şartlar sağlanmazsa başka bir 
        if else döngüsüne girer. Sol kök null değilse, sol kök tempe atılır, 
        eğer sol kök null ise sağ kök tempe atılır. Eğer temp “null” ise, 
        kök “null” olur ve 2. İf else döngüsündeki if sonucunda temp=”null” 
        işlemi gerçekleşir. Hiçbir olumlu döngüye girmez ise temp, sağ 
        tarafın en küçüğüne atanır. Temp ile kök değerleri eşitlenir, kökün 
        sağından temp değeri silinir.
        */
        
        if (kok == null)
        {
            return kok;
        }   
        if ( deger < kok.deger )
        {
            kok.sol = sil(kok.sol, deger);
        }
        else if( deger > kok.deger)
        {
            kok.sag = sil(kok.sag, deger);
        } 
        else 
        {
            if( (kok.sol == null) || (kok.sag == null) ) 
            {
                Dugum temp;
                if (kok.sol != null)
                {
                    temp = kok.sol;
                }    
                else
                {
                    temp = kok.sag;
                }
                if(temp == null) 
                {
                    temp = kok;
                    kok = null;
                }
                else
                {
                    kok = temp; 
                }
                temp = null;
            }
            else 
            {
                Dugum temp = enKucuk(kok.sag);
                kok.deger = temp.deger;
                kok.sag = sil(kok.sag, temp.deger);
            }      
        }
        return kok;
    }
    
    public Dugum ata(Dugum kok, Dugum dugum) //Atama metodu
    {
        /*
        Kök ve düğüm “null” ise, “null” döndürür. Yani ata düğüm yok demektir. 
        Kökün sağı “null” değilse ve kökün sağ değeri düğüm değerine eşitse 
        veya kökün sağı null değilse ve kökün sol değeri düğüm değerine eşitse 
        kök döndürülür. Bu şartlar sağlanmazsa; bulunan düğüm kökün sağının 
        atasına eşitlenir. Eğer bulunan yoksa yani “null” ise, sola eşitlenir. 
        Bulunan değeri döndürülür.
        */
        
        if(kok == null || dugum == null) //Eğer kök veya düğüm yoksa
        {
            return null; //null döner
        }
        else if((kok.sag != null && kok.sag.deger == dugum.deger) || 
                (kok.sol != null && kok.sol.deger == dugum.deger)) 
            /*kökün sağı yoksa ve sağ değer düğüm değerine eşitse veya
            kökün solu yoksa ve sol değer düğüm değerine eşitse...
            */
        {
            return kok; //kök döndürülür.
        }
        else
        {
            Dugum bulunan = ata(kok.sag, dugum); //Bulunan, kökün sağının ata değeri..
            
            if(bulunan == null) //bulunan null ise..
            {
                bulunan = ata(kok.sol, dugum); //Bulunan, kökün solunun ata değeri..
            }
            return bulunan; //bulunan döndrülür.
        }
    }   
}



