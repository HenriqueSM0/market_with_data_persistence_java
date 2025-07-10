package structure_classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public abstract class File_functions {
    
    public static boolean print_file (String file_path) {
        try (BufferedReader BR = new BufferedReader(new FileReader(file_path))) {
            String line;
            while ((line = BR.readLine()) != null) {
                System.out.println(line);
            }
            BR.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean overwrite_file (String file_path, String new_text) {
        try (BufferedWriter BW = new BufferedWriter(new FileWriter(file_path))) {
            BW.write(new_text);
            BW.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String get_text (String file_path) {
        try (BufferedReader BR = new BufferedReader(new FileReader(file_path))) {
            String line, text = "";
            int num_of_line = 0;
            while ((line = BR.readLine()) != null) {
                num_of_line++;
                text = text + line;
                if (num_of_line != num_of_lines(file_path))  text = text + "\n";
            }
            BR.close();
            return text;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static boolean copy_ints_to_arraylist (String file_path, ArrayList<Integer> AR_INTS) {
        try (BufferedReader BR = new BufferedReader(new FileReader(file_path))) {
            String line;
            while ((line = BR.readLine()) != null) {
                AR_INTS.add(Integer.parseInt(line));
            }
            BR.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean copy_doubles_to_arraylist (String file_path, ArrayList<Double> AR_DOUBLES) {
        try (BufferedReader BR = new BufferedReader(new FileReader(file_path))) {
            String line;
            while ((line = BR.readLine()) != null) {
                AR_DOUBLES.add(Double.parseDouble(line));
            }
            BR.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean copy_strings_to_arraylist (String file_path, ArrayList<String> AR_STRS) {
        try (BufferedReader BR = new BufferedReader(new FileReader(file_path))) {
            String line;
            while ((line = BR.readLine()) != null) {
                AR_STRS.add(line);
            }
            BR.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean copy_strings_to_file (ArrayList<String> AR_STRS, String file_path) {
        String text = "";
        int i;
        for (i = 0; i < AR_STRS.size(); i++) {
            text = text + AR_STRS.get(i);
            if (i < AR_STRS.size() - 1) text = text + "\n";
        }
        return overwrite_file(file_path, text);
    }

    public static boolean copy_doubles_to_file (ArrayList<Double> AR_DOUBLES, String file_path) {
        String text = "";
        int i;
        for (i = 0; i < AR_DOUBLES.size(); i++) {
            text = text + String.format(Locale.US, "%.2f", AR_DOUBLES.get(i));
            if (i < AR_DOUBLES.size() - 1) text = text + "\n";
        }
        return overwrite_file(file_path, text);
    }

    public static boolean copy_ints_to_file (ArrayList<Integer> AR_INTS, String file_path) {
        String text = "";
        int i;
        for (i = 0; i < AR_INTS.size(); i++) {
            text = text + AR_INTS.get(i).toString();
            if (i < AR_INTS.size() - 1) text = text + "\n";
        }
        return overwrite_file(file_path, text);
    }

    public static int num_of_lines (String file_path) {
        try (BufferedReader BR = new BufferedReader(new FileReader(file_path))) {
            @SuppressWarnings("unused")
            String line;
            int num_of_lines = 0;
            while ((line = BR.readLine()) != null) {
                num_of_lines++;
            }
            BR.close();
            return num_of_lines;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static String get_line (String file_path, int num_of_line) {
        try (BufferedReader BR = new BufferedReader(new FileReader(file_path))) {
            String line;
            int num_of_current_line = 0;
            while ((line = BR.readLine()) != null) {
                num_of_current_line++;
                if (num_of_current_line == num_of_line) {
                    return line;
                }
            }
            BR.close();
            return "";
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int num_of_line (String file_path, String searched_line) {
        try (BufferedReader BR = new BufferedReader(new FileReader(file_path))) {
            String line;
            int num = 0;
            while ((line = BR.readLine()) != null) {
                num++;
                if (line.equals(searched_line)) {
                    return num;
                }
            }
            BR.close();
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
