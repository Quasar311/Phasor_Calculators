import java.lang.Math;


public class Trans {
    

    public static void main(String[] args){
        double initialV = 0;
        double R = 0;
        double C = 0;
        double delta_V = 0;
        double delta_t = 0;
        double V_final = initialV - delta_V*(Math.exp(-delta_t/(R*C)));
        System.out.println(V_final);
    }
}
