import java.util.InputMismatchException;
import java.util.Scanner;

public class CarRental {

    final int N = 5;
    String [] customerName = new String [N];
    int [] daysNum = new int [N];
    boolean [] specialOffer = new boolean[N];
    double [] charge = new double[N];
    String mostSevenDays = "";
    String leastSevenDays = "";

    public void inputData() {

        Scanner scanner = new Scanner(System.in);

        System.out.printf("             Welcome to used CarRental calculator.%n%n");
        for(int i = 0; i < N; i++){

            System.out.print("Enter customer name " + (i + 1) + ": ");
            customerName[i] = scanner.next();

            System.out.print("Enter the numbers of days: ");
            while (true){
                try {
                    if(scanner.hasNextInt()){
                        daysNum[i] = scanner.nextInt();
                        if (daysNum [i] <= 0 || daysNum[i] > 365){
                            throw new NumberFormatException(" Error: you can't rent a car for " + daysNum[i] + " day/days.");
                        } else break;
                    } else throw new NumberFormatException("Error: entered data is not a number.");
                } catch (NumberFormatException e){
                    System.out.print(e + " Please, enter the data again ");
                }
            }

            System.out.print("Enter yes or no to indicate special offer: ");
            while (true){
                try{
                    String a = scanner.next();
                    if (a.equals("yes") || a.equals("no")) {
                        if (a.equals("yes")) {
                        specialOffer[i] = true;
                            break;
                        } else break;
                    } else throw new NumberFormatException(" Error: you entered incorrect data. ");
                } catch (NumberFormatException e) {
                    System.out.print(e + " Please, enter the data again ");
                }
            }
            charge[i] = calculateRental(daysNum[i], specialOffer[i]);
            System.out.printf("      The rental of car hire from " + customerName[i] + " is $%.2f %n", charge[i]);
            System.out.println("--------------------------------------------------------");


            if (daysNum[i] >= 7) {
                mostSevenDays += "*";
            } else leastSevenDays += "*";
        }
    }

    public double calculateRental(int daysNum, boolean specialOffer){
        if (!specialOffer) {
            if (daysNum >= 1 && daysNum <= 5) {
                return daysNum * 25.0;
            }
            if (daysNum >=6 && daysNum <= 10) {
                return daysNum * 22.5;
            } else return daysNum * 21.0;
        } else {
            if (daysNum >= 1 && daysNum <= 5) {
                return daysNum * 25.0 * 0.90;
            }
            if (daysNum >=6 && daysNum <= 10) {
                return daysNum * 22.5 * 0.90;
            } else return daysNum * 21.0 * 0.90;
        }
    }


    public void displayInfo(){

        double maxCharge = charge[0];
        int max = 0;
        double minCharge = charge[0];
        int min = 0;

        System.out.printf("%n%n             Summary of Car Rentals%n");
        System.out.println("===============================================================");
        System.out.println("    Name           Days           SpecialOffer      Charge");
        System.out.printf("---------------------------------------------------------------%n%n");
        for(int i = 0; i < N; i++){
           System.out.printf("%-19s%-15d%-17s $%.2f%n", customerName[i], daysNum[i], specialOfferYesOrNo(specialOffer[i]), charge[i]);

           if (maxCharge < charge[i]) {
               maxCharge = charge[i];
               max = i;
           }
           if (minCharge > charge[i]){
               minCharge = charge[i];
               min = i;
           }
        }
        System.out.printf("%n---------------------------------------------------------------%n");
        System.out.printf("The customer spending most rental is " + customerName[max] + " $%.2f%n%n", charge[max]);
        System.out.printf("The customer spending least rental is " + customerName[min] + " $%.2f%n%n", charge[min]);
        System.out.println("The rental days < 7: " + leastSevenDays);
        System.out.println("The rental days >=7: " + mostSevenDays);
    }

    public String specialOfferYesOrNo (boolean specialOffer){
        if(!specialOffer) {
            return "yes";
        } else return "no";
    }

}
