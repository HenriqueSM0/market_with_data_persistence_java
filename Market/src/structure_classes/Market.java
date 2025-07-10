package structure_classes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Market {
    String IDs = "src\\files\\ids.txt";
    String KEY = "src\\files\\key.txt";
    String MARKET_NAME = "src\\files\\market_name.txt";
    String NAMEs = "src\\files\\names.txt";
    String PRICEs = "src\\files\\prices.txt";
    String QTDs = "src\\files\\qtds.txt";
    String SELLs = "src\\files\\sells.txt";
    String MONEY_TRANSATIONS = "src\\files\\money_transations.txt";
    String CURRENT_MONEY = "src\\files\\current_money.txt";
    private String MONEY_TRANSATIONS_NUMS = "src\\files\\money_trans_nums.txt";

    ArrayList<Integer> Product_ids_array = new ArrayList<>();
    ArrayList<String> Product_names_array = new ArrayList<>();
    ArrayList<Double> Product_prices_array = new ArrayList<>();
    ArrayList<Integer> Product_qtds_array = new ArrayList<>();
    ArrayList<String> Product_sells_array = new ArrayList<>();
    ArrayList<String> Money_transations_array = new ArrayList<>();
    private ArrayList<Double> Money_transations_nums_array = new ArrayList<>();

    int current_index;
    boolean matched_key = false;
    double current_money;

    public String get_name () {
        return File_functions.get_line(MARKET_NAME, 1);
    }

    public double get_current_money () {
        return Double.parseDouble(File_functions.get_text(CURRENT_MONEY));
    }

    private boolean set_name (Scanner SC) {
        String name, answer;
        System.out.println("Type a name for market : ");
        name = SC.nextLine();
        System.out.println("Sure " + name + " ?");
        System.out.println("1 - OK :");
        System.out.println("Else - Cancel :");
        answer = SC.nextLine();
        if (answer.equals("1")) {
            File_functions.overwrite_file(MARKET_NAME, name);
            return true;
        } 
        System.out.println("1 - Try again :");
        System.out.println("Else - Exit :");
        answer = SC.nextLine();
        if (answer.equals("1")) set_name(SC);
        return false;
    }

    private boolean set_password_key (Scanner SC) {
        String key, answer;
        System.out.println("Type a key for market acess :");
        key = SC.nextLine();
        System.out.println("Sure " + "*".repeat(key.length()) + " ?");
        System.out.println("1 - OK :");
        System.out.println("Else - Cancel :");
        answer = SC.nextLine();
        if (answer.equals("1")) {
            File_functions.overwrite_file(KEY, key);
            return true;
        } 
        System.out.println("1 - Try again :");
        System.out.println("Else - Exit :");
        answer = SC.nextLine();
        if (answer.equals("1")) set_password_key(SC);
        return false;
    }

    private boolean match_password_key (Scanner SC) {
        if (matched_key) return true;
        String key_match;
        System.out.println("Type the key :");
        key_match = SC.nextLine();
        if (key_match.equals(File_functions.get_text(KEY))) {
            matched_key = true;
            return true;
        }
        System.out.println("1 - Try again :");
        System.out.println("Else - Exit :");
        key_match = SC.nextLine();
        if (key_match.equals("1")) match_password_key(SC);
        return false;
    }

    private boolean product_exists_in_stock (String product_name) {
        for (String name : Product_names_array) {
            if (name.equals(product_name)) return true;
        }
        return false;
    }

    private String set_valid_name_guarantee (Scanner SC) {
        boolean first_time = true;
        String name = "";
        System.out.println("Type a name for product : ");
        while (first_time || product_exists_in_stock(name)) {
            if (!first_time) System.out.println("Already exists in stock a product with name : " + name + " !\nTry again : ");
            name = SC.nextLine();
            first_time = false;
        }
        return name;
    }

    private boolean valid_price (Double price) {
        if (price > 0.0) return true;
        return false;
    }

    private Double set_valid_price_guarantee (Scanner SC) {
        boolean first_time = true;
        Double price = 0.0;
        System.out.println("Type a price for product : ");
        while (first_time || !valid_price(price)) {
            if (!first_time) System.out.println("Invalid price !\nTry again : ");
            price = Double.parseDouble(SC.nextLine());
            first_time = false;
        }
        return price;
    }

    private boolean valid_qtd (int qtd) {
        if (qtd > 0) return true;
        return false;
    }

    private int set_valid_qtd_guarantee (Scanner SC) {
        boolean first_time = true;
        int qtd = 0;
        System.out.println("Type a qtd for product : ");
        while (first_time || !valid_qtd(qtd)) {
            if (!first_time) System.out.println("Invalid price !\nTry again : ");
            qtd = Integer.parseInt(SC.nextLine());
            first_time = false;
        }
        return qtd;
    }

    private boolean create_market (Scanner SC) {
       System.out.println("---- Create Market !!! ----");
       File_functions.overwrite_file(IDs, "");
       File_functions.overwrite_file(NAMEs, "");
       File_functions.overwrite_file(PRICEs, "");
       File_functions.overwrite_file(QTDs, "");
       File_functions.overwrite_file(SELLs, "");
       File_functions.overwrite_file(MONEY_TRANSATIONS, "");
       File_functions.overwrite_file(MONEY_TRANSATIONS_NUMS, "");
       File_functions.overwrite_file(CURRENT_MONEY, "0.00");
       if (!set_name(SC)) return false;
       if (!set_password_key(SC)) return false;
       return true;
    }

    private void intialize_market () {
        System.out.println("Loading data ...");
        File_functions.copy_ints_to_arraylist(IDs, Product_ids_array);
        File_functions.copy_strings_to_arraylist(NAMEs, Product_names_array);
        File_functions.copy_doubles_to_arraylist(PRICEs, Product_prices_array);
        File_functions.copy_ints_to_arraylist(QTDs, Product_qtds_array);
        File_functions.copy_strings_to_arraylist(SELLs, Product_sells_array);
        File_functions.copy_strings_to_arraylist(MONEY_TRANSATIONS, Money_transations_array);
        File_functions.copy_doubles_to_arraylist(MONEY_TRANSATIONS_NUMS, Money_transations_nums_array);
        current_money = get_current_money();
        current_index = Product_ids_array.size();
        System.out.println("Loading complete !");
    }

    private void update_market () {
        System.out.println("Exporting data ...");
        File_functions.copy_ints_to_file(Product_ids_array, IDs);
        File_functions.copy_strings_to_file(Product_names_array, NAMEs);
        File_functions.copy_doubles_to_file(Product_prices_array, PRICEs);
        File_functions.copy_ints_to_file(Product_qtds_array, QTDs);
        File_functions.copy_strings_to_file(Product_sells_array, SELLs);
        File_functions.overwrite_file(CURRENT_MONEY, String.format(Locale.US, "%.2f", current_money));
        File_functions.copy_strings_to_file(Money_transations_array, MONEY_TRANSATIONS);
        File_functions.copy_doubles_to_file(Money_transations_nums_array, MONEY_TRANSATIONS_NUMS);
        current_index = Product_ids_array.size();
        System.out.println("Exporting complete !");
    }

    public Market (Scanner SC) {
        if (get_name().equals("")) create_market(SC);
        intialize_market();
    }

    public boolean create_product (Scanner SC) {
        String name, answer;
        Double price = 0.0;
        System.out.println("---- Create Product !!! ----");
        if (!match_password_key(SC)) return false;
        name = set_valid_name_guarantee(SC);
        System.out.println("Sure " + name + " ?");
        System.out.println("1 - OK :");
        System.out.println("Else - Cancel :");
        answer = SC.nextLine();
        if (answer.equals("1")) {
            price = set_valid_price_guarantee(SC);
            System.out.println("Sure " + price + " ?");
            System.out.println("1 - OK :");
            System.out.println("Else - Cancel :");
            answer = SC.nextLine();
            if (answer.equals("1")) {
                Product_ids_array.add(current_index + 1);
                Product_names_array.add(name);
                Product_prices_array.add(price);
                Product_qtds_array.add(0);
                update_market();
                System.out.println("Succeed add of : " + name + " | RS " + price);
                return true;
            } 
            System.out.println("1 - Try again :");
            System.out.println("Else - Cancel :");
            answer = SC.nextLine();
            if (answer.equals("1")) create_product(SC);
            return false;
        } 
        return false;
    }

    public boolean add_qtd_of_product (Scanner SC) {
        System.out.println("---- Add qtd of product !!! ----");
        int id, qtd;
        String answer;
        if (!match_password_key(SC)) return false;
        print_stock(SC);
        System.out.println("Type one of these ids to add qtd. Or not to cancel :");
        id = Integer.parseInt(SC.nextLine());
        if ((id >= 1) && (id <= current_index)) {
            qtd = set_valid_qtd_guarantee(SC);
            System.out.println("Sure " + qtd + " ?");
            System.out.println("1 - OK :");
            System.out.println("Else - Cancel :");
            answer = SC.nextLine();
            if (answer.equals("1")) {
                int current_qtd;
                current_qtd = Product_qtds_array.get(id - 1) + qtd;
                Product_qtds_array.set(id - 1, current_qtd);
                update_market();
                System.out.println("Succeed add " + qtd + " of " + Product_names_array.get(id - 1));
                return true;
            }
            System.out.println("1 - Try again :");
            System.out.println("Else - Cancel :");
            answer = SC.nextLine();
            if (answer.equals("1")) add_qtd_of_product(SC);
            return false;
        }
        return false;
    }

    public boolean print_stock (Scanner SC) {
        int i;
        if (!match_password_key(SC)) return false;
        System.out.println("id | name | price | qtd");
        for (i = 0; i < current_index; i++) {
            System.out.println(Product_ids_array.get(i) + " | " + Product_names_array.get(i) + " | " + Product_prices_array.get(i) + " | " + Product_qtds_array.get(i));
        }
        return true;
    }

    public boolean sell (Scanner SC) {
        int current_qtd, id = 0, qtd;
        String answer;
        boolean end_sell = false;
        double sell_price = 0.0;
        System.out.println("---- Sell Products !!! ----");
        if (!match_password_key(SC)) return false;
        ArrayList<Integer> qtds_of_sell = new ArrayList<>(); 
        ArrayList<Integer> ids_of_sell = new ArrayList<>(); 
        while (!end_sell) {
            print_stock(SC);
            System.out.println("Type one of these ids to add in kart :");
            System.out.println("Type 0 to cancel whole sell");
            System.out.println("Other to end sell");
            id = Integer.parseInt(SC.nextLine());
            if ((id >= 1) && (id <= current_index)) {
                qtd = set_valid_qtd_guarantee(SC);
                if (Product_qtds_array.get(id - 1) < qtd) {
                    System.out.println("Out of stock !!!");
                } else {
                    System.out.println("Sure " + qtd + " ?");
                    System.out.println("1 - OK :");
                    System.out.println("Else - Cancel :");
                    answer = SC.nextLine();
                    if (answer.equals("1")) {
                        qtds_of_sell.add(qtd);
                        ids_of_sell.add(id);
                        current_qtd = Product_qtds_array.get(id - 1) - qtd;
                        Product_qtds_array.set(id - 1, current_qtd);
                        sell_price = sell_price + qtd * Product_prices_array.get(id - 1);
                        System.out.println("Added : " + Product_names_array.get(id - 1) + " | qtd : " + qtd);
                        System.out.println("Total price : RS " + sell_price);
                    }
                }
            }
            if ((id < 1) || (id > current_index)) end_sell = true;
        }
        if (ids_of_sell.size() == 0) return false;
        System.out.println("Informations of buy :");
        int i = 0;
        for (Integer ID : ids_of_sell) {
            System.out.println(Product_names_array.get(ID - 1) + " | " + qtds_of_sell.get(i));
            i++;
        }
        System.out.println("Total price : RS " + sell_price);
        Product_qtds_array.clear();
        File_functions.copy_ints_to_arraylist(QTDs, Product_qtds_array);
        if (id == 0) return false;
        i = 0;
        for (Integer ID : ids_of_sell) {
            current_qtd = Product_qtds_array.get(ID - 1) - qtds_of_sell.get(i);
            Product_qtds_array.set(ID - 1, current_qtd);
            i++;
        }
        Product_sells_array.add(LocalDateTime.now().toString() + " | " + ids_of_sell.toString() + " --- " + qtds_of_sell.toString() + " | RS " + sell_price);
        current_money = current_money + sell_price;
        Money_transations_array.add(LocalDateTime.now().toString() + " | RS " + sell_price);
        Money_transations_nums_array.add(sell_price);
        update_market();
        return true;
    }

    public boolean print_sells (Scanner SC) {
        if (!match_password_key(SC)) return false;
        System.out.println("year | month | day | ids of products | qtds of products | full price");
        for (String sell_info : Product_sells_array) {
            System.out.println(sell_info);
        }
        return true;
    }

    public void destroy_market (Scanner SC) {
        System.out.println("DANGER !!!");
        System.out.println("Destroying market !!!");
        System.out.println("This will erase all data of " + get_name() + " !!!");
        String answer;
        System.out.println("1 - OK :");
        System.out.println("Else - Cancel :");
        answer = SC.nextLine();
        if (answer.equals("1")) {
            System.out.println("Erasing name file !!!");
            File_functions.overwrite_file(MARKET_NAME, "");
            System.out.println("Your program will close !!");
            return;
        }
        System.out.println("Operation canceled !!!");
    }
}