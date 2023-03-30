import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;




public class Phasor_Calc {
    //how to take inputs from the command line
    public static final int NUM_CHOICES = 11;
    public static ArrayList<Phasor> phas = new ArrayList<>();
    public static ArrayList<Cartesian> cart = new ArrayList<>();

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        boolean loop = true;
        while(loop){
            int choice = menu(scan);

            while(choice > NUM_CHOICES || choice < 0)
            {
            System.out.println("please try again:\n");
            choice = menu(scan);
            }

            switch(choice){
                case 0:
                    loop = false;
                    break;
                case 1:
                    input_phasor(scan);
                    break;
                case 2:
                    input_cart(scan);
                    break;
                case 3:
                    phas.clear();
                    cart.clear();
                    break;
                case 4:
                    add_points_from_menu(scan);
                    break;
                case 5:
                    input_phasor_degrees(scan);
                    break;
                case 6:
                    multiply_points_from_menu(scan);
                    break;
                case 7:
                    divide_points_from_menu(scan);
                    break;
                case 8:
                    subtract_points_from_menu(scan);
                    break;
                case 9:
                    impedance_parrallel_menu(scan);
                    break;
                case 10:
                    cap_impede(scan);
                    break;
                case 11:
                    induc_impede(scan);
                    break;

            }

        }    
        scan.close();
    }

    public static void cap_impede(Scanner scan){
        System.out.println("what is the omega value:");
        double omega = scan.nextDouble();
        System.out.println("What is the value of the capacitance:");
        double j_val = scan.nextDouble();
        cart.add(new Cartesian(0, 1/(omega * j_val)));

        add_phasor_from_input(cart.size()-1);
    }

    public static void induc_impede(Scanner scan){
        System.out.println("what is the omega value:");
        double omega = scan.nextDouble();
        System.out.println("What is the value of the Inductance:");
        double j_val = scan.nextDouble();
        cart.add(new Cartesian(0, (omega * j_val)));

        add_phasor_from_input(cart.size()-1);
    }

    public static void impedance_parrallel_menu(Scanner scan){
        System.out.println("what is the first point that you would like to combine:");
        int p1 = scan.nextInt();
        System.out.println("What is the second point that you would like to combine:");
        int p2 = scan.nextInt();

        Phasor numerator = new Phasor(phas.get(p1).mag * phas.get(p2).mag, phas.get(p1).ang + phas.get(p2).ang);
        double[] denom = cart_phas(cart.get(p1).real + cart.get(p2).real, cart.get(p1).imag + cart.get(p2).imag);
        Phasor denominator = new Phasor(denom[0], denom[1]);
        divide_points(numerator, denominator);
    }

    public static void subtract_points_from_menu(Scanner scan){
        System.out.println("what is the first point that you would like to subtract:");
        int p1 = scan.nextInt();
        System.out.println("What is the second point that you would like to subtract:");
        int p2 = scan.nextInt();
        subtract_points(cart.get(p1) , cart.get(p2));
    }

    public static void subtract_points(Cartesian p1, Cartesian p2){
        double r = p1.real - p2.real;
        double i = p1.imag - p2.imag;
        cart.add(new Cartesian(r, i));
        add_phasor_from_input(cart.size() - 1);
    }

    public static void multiply_points_from_menu(Scanner scan){//working here
        System.out.println("what is the first point that you would like to multiply:");
        int p1 = scan.nextInt();
        System.out.println("What is the second point that you would like to multiply:");
        int p2 = scan.nextInt();
        multiply_points(phas.get(p1) , phas.get(p2));
    
    }

    public static void multiply_points(Phasor p1, Phasor p2){
        double new_mag = p1.mag * p2.mag;
        double new_ang = p1.ang + p2.ang;
        if(new_ang >= 2*Math.PI){
            new_ang %= 2 * Math.PI;
        }
        phas.add(new Phasor(new_mag, new_ang));
        add_cart_from_input(phas.size()-1);
    }

    public static void divide_points_from_menu(Scanner scan){//working here
        System.out.println("what is the first point that you would like to divide:");
        int p1 = scan.nextInt();
        System.out.println("What is the second point that you would like to divide:");
        int p2 = scan.nextInt();
        divide_points(phas.get(p1) , phas.get(p2));
    
    }
   
    public static void divide_points(Phasor p1, Phasor p2){
        double new_mag = p1.mag / p2.mag;
        double new_ang = p1.ang - p2.ang;
        if(new_ang >= 2*Math.PI){
            new_ang %= 2 * Math.PI;
        }
        phas.add(new Phasor(new_mag, new_ang));
        add_cart_from_input(phas.size()-1);
    }
    



    public static void input_phasor_degrees(Scanner scan){
        System.out.println("Please input the phasor magnitude:");
        double magn = scan.nextDouble();
        scan.nextLine();
        System.out.println("Please input the phasor angle in degrees:");
        double angl = scan.nextDouble();
        scan.nextLine();
        phas.add(new Phasor(magn, Math.toRadians(angl)));
        add_cart_from_input(phas.size()-1);
    }

    public static void add_points_from_menu(Scanner scan){
        System.out.println("Please input the number of the first point:");
        int p1 = scan.nextInt();
        System.out.println("Please input the number of the second point:");
        int p2 = scan.nextInt();
        add_points_phasor(cart.get(p1), cart.get(p2));
    }

    public static void add_points_phasor(Cartesian p1, Cartesian p2){
        double r = p1.real + p2.real;
        double i = p1.imag + p2.imag;
        cart.add(new Cartesian(r, i));
        add_phasor_from_input(cart.size() - 1);
    }

    private static void input_phasor(Scanner scan){

        System.out.println("Please input the phasor magnitude:");
        double magn = scan.nextDouble();
        scan.nextLine();
        System.out.println("Please input the phasor angle:");
        double angl = scan.nextDouble();
        scan.nextLine();
        phas.add(new Phasor(magn, angl));
        add_cart_from_input(phas.size()-1);
        return;
    }

    private static void add_cart_from_input(int phas_pos){
        double[] new_coords = phas_cart(phas.get(phas_pos).mag, phas.get(phas_pos).ang);
        cart.add(new Cartesian(new_coords[0], new_coords[1]));
    }

    public static double[] phas_cart(double r, double theta){ //assuming radians
        double x = r * Math.cos(theta);
        double y = r * Math.sin(theta);
        double[] ret = {x, y};
        return ret;
    }

    private static void input_cart(Scanner in){

        System.out.println("Please input the cartesian real component:");
        double r = in.nextDouble();
        in.nextLine();
        System.out.println("Please input the cartesian imaginary component:");
        double i = in.nextDouble();
        in.nextLine();
        cart.add(new Cartesian(r, i));

        add_phasor_from_input(cart.size()-1);
        return;
    }

    private static void add_phasor_from_input(int cart_pos){
        double[] new_coords = cart_phas(cart.get(cart_pos).real, cart.get(cart_pos).imag);
        phas.add(new Phasor(new_coords[0], new_coords[1]));
    }

    public static double[] cart_phas(double x, double y){     //assuming radians
        double r = Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
        double theta = 0;
        if(x ==0){
            if(y > 0){
                theta = Math.PI / 2;
            }
            else if(y < 0){
                theta = Math.PI * 3 / 2;
            }
        }
        else if(y == 0){
            if(x > 0){
                theta = 0;
            }
            else if(x < 0){
                theta = Math.PI;
            }
        }
        else if(x > 0){
            if(y >= 0){
                theta = Math.atan2(Math.abs(y), Math.abs(x));
            }
            else{
                theta = Math.PI * 3/2 +  Math.atan2(Math.abs(y), Math.abs(x));
            }
        }
        else{
            theta = Math.atan2(Math.abs(y), Math.abs(x));
            if(y >= 0){
                theta = Math.PI - theta;
            }
            else{
                theta = Math.PI + theta;
            }
        }
        //should work from there
        double[] ret = {r, theta};
        return ret;
    }


    private static int menu(Scanner scan){
        System.out.println("MENU\n=====================");
        //choices
        System.out.println("Please choose one from the options below:\n");
        System.out.println("0 - exit");
        System.out.println("1 - add new phasor");
        System.out.println("2 - add new cartesian");
        System.out.println("3 - clear");
        System.out.println("4 - add two points");
        System.out.println("5 - add new phasor with degrees");
        System.out.println("6 - multiply two points");
        System.out.println("7 - divide two points");
        System.out.println("8 - subtract two points");
        System.out.println("9 - combine impedances in parrallel");
        System.out.println("10 - get impedance for capacitor");
        System.out.println("11 - get impedance for an inductor");


        //current system data
        System.out.println("Here is the information about all of the stored phasors");
        for(int i = 0; i < phas.size(); i++){
            System.out.println("phasor: " + (i) + ", Magnitude: " + phas.get(i).mag + ", Angle(rad): " + phas.get(i).ang + ", Angle(deg): " +  Math.toDegrees(phas.get(i).ang));
        }
        System.out.println("\nHere is the information about all of the stored caresian coords"); 
        for(int i = 0 ; i < phas.size(); i++){
            //fix this---------------------------
            System.out.println("Coords: " + (i) + ", real component: " + cart.get(i).real + ", imaginary: " + cart.get(i).imag);
        }

        int ret = scan.nextInt();
        System.out.flush();
        

        return ret;

    }
}

