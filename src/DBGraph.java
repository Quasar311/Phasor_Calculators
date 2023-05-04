import java.util.Scanner;

public class DBGraph {
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args){
        System.out.println("is this an RL or RC circuit, 1 for RL, 0 for RC");
        int input = scan.nextInt();
        if(intput==0){
            System.out.println("capacitor");
            System.out.println("What is the capacitance?");
            double cap = scan.nextDouble();
            
        }

        }
        else{
            System.out.println("inductor");
        }


    }
    

}
