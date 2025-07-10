package use_classes;

import java.util.Scanner;

import structure_classes.Market;

public class Main {

    public static void main(String[] args) {
        Scanner SC = new Scanner(System.in);
        String answer;
        boolean work = true;
        Market M = new Market(SC);
        System.out.println("Welcome to " + M.get_name() + "!!!");
        while (work) {
            System.out.println("What would you like to do :");
            System.out.println("1 - Create product :");
            System.out.println("2 - Add qtd. of product :");
            System.out.println("3 - Sell products :");
            System.out.println("4 - See stock :");
            System.out.println("5 - See sells :");
            System.out.println("Else - EXIT :");
            answer = SC.nextLine();
            if (answer.equals("1")) M.create_product(SC);
            else if (answer.equals("2")) M.add_qtd_of_product(SC);
            else if (answer.equals("3")) M.sell(SC);
            else if (answer.equals("4")) M.print_stock(SC);
            else if (answer.equals("5")) M.print_sells(SC);
            else if (answer.equals(M.get_name())) {
                M.destroy_market(SC);
                work = false;
            }
            else work = false;
        }
        SC.close();
    }
}
