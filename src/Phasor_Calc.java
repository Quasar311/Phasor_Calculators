import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;




public class Phasor_Calc {
    //how to take inputs from the command line
    public static final int NUM_CHOICES = 10;
    public static ArrayList<Phasor> phas = new ArrayList<>();
    public static ArrayList<Cartesian> cart = new ArrayList<>();

    public static void main(String[] args){
        int choice = menu();

        while(choice > NUM_CHOICES || choice <= 0)
        {
          System.out.println("please try again:");
          choice = menu();
        }

        

    }

    public static Cartesian phas_to_cart(Phasor p){


        return new Cartesian(1, 1, 1);
    }








    private static int menu(){
        Scanner scan = new Scanner(System.in);
        System.out.println("MENU\n=====================");
        //choices
        System.out.println("Please choose one from the options below:\n");
        System.out.println("1 - add new phasor");
        System.out.println("2 - Polar to Cartesian");


        //current system data
        System.out.println("Here is the information about all of the stored phasors");
        for(int i = 0; i < phas.size(); i++){
            System.out.println("phasor: " + (++i) + ", Magnitude: " + phas.get(i).mag + ", Angle: " + phas.get(i).ang);
        }
        System.out.println("Here is the information about all of the stored caresian corrds");
        for(int i = 0; i < phas.size(); i++){
            //fix this---------------------------
            System.out.println("phasor: " + (++i) + ", Amplitude: " + cart.get(i).amp + ", Phae: " + cart.get(i).phase + ", Frequency: " + cart.get(i).freq);
        }

        int ret = scan.nextInt();
        System.out.flush();
        return ret;

    }
}
/* 
class Phasor{
    int mag;
    int ang;
    
    public Phasor(int m, int a){
        mag = m;
        ang = a;
    }
}

class Cartesian{
    int amp;
    int phase;
    int freq;
    
    public Cartesian(int a, int p, int f){
        amp = a;
        phase = p;
        freq = f;
    }
     
}

*/