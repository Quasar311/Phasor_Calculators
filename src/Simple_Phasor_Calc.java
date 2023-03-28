

public class Simple_Phasor_Calc{


    public static void main(String[] args){
        int mag = 1;
        int ang = 1;
        Phasor a = new Phasor(mag, ang);
        int amp = 1;
        int phase = 1;
        int freq = 1;
        Cartesian c = new Cartesian(amp, phase, freq);

        Cartesian_To_Polar(c);
        

    }


    public static void Cartesian_To_Polar(Cartesian c){
        System.out.println("the mag is: " + c.amp + ", the angle is: " + c.phase + ".");
    }

    public static void Polar_To_Cartesian(Phasor p){
        
    }
}