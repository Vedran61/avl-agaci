import java.util.Scanner;

public class main {

    public static void main(String[] args) 
    {
        Avl_agaci avl = new Avl_agaci();
                
        avl.ekle("a");
        avl.ekle("b");
        avl.ekle("c");
        avl.ekle("d");
        avl.ekle("e");
        avl.ekle("f");
        avl.ekle("g");
        avl.ekle("h");
        
        String girdi,eleman;
        int el_sil;
        boolean sart = true; //Boolean olarak şart atanır.
        Scanner keyboard = new Scanner(System.in);
        
        while(sart)
        {
            System.out.println("\nYapmak istediğiniz işlemi yazınız"
                + "\nEkleme için 'ekle' yazınız."
                + "\nSilme için 'sil' yazınız."
                + "\nAtama için 'ata' yazınız."
                + "\nÇıkış için 'x' yazınız.\n");
            
            girdi = keyboard.nextLine();
            
            switch(girdi)
            {
                case "ekle":
                    System.out.println("Eklenecek elemanı giriniz: ");
                    eleman = keyboard.nextLine();
                    avl.ekle(eleman);
                    break;
                case "sil":
                    System.out.println("Silinecek elemanı giriniz: ");
                    el_sil = keyboard.nextInt();
                    avl.sil(el_sil);
                    break;
                case "ata":
                    /*
                    String atadgm = avl.ata();
                    System.out.println("Ata düğüm:",avl.ata());
                    break;
                    */
                case "x":
                    System.exit(0);
                    break;
                default:
                    System.out.println(">>> Lütfen geçerli bir işlem belirtiniz. <<<");
                    break;
            }
        } 
    }  
}
