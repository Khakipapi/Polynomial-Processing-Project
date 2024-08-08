/*
 *Jose Reyes
 * Project 2
 * 11/12/2022
 * UMGC
 * CMSC 350
 */
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    private static List<Polynomial> polyList = new ArrayList<>();

    //main here
    public static void main(String[] args) {
        processPolyList();
    }

    public static ArrayList<String> fromFile() {
//Create ArrayList and JFileChooser
        ArrayList<String> expressionList = new ArrayList<>();
        JFileChooser chooser = new JFileChooser();
//Show both directories and files
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
//use current directory
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        int response = chooser.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION){
            File file = chooser.getSelectedFile();
            try {
                Scanner fileIn = new Scanner(file);
                if (file.isFile()){
                    while (fileIn.hasNextLine()){
                        String expression = fileIn.nextLine();
                        expressionList.add(expression);
                    }
                }
            }catch (NoSuchElementException nse){
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"File is empty!");
            }catch(FileNotFoundException fne){
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"File is not found!");
            }
        }
        return expressionList;
    }
    /*
method: checkWeakOrder
determines if a list is in weak order
*/
    public static boolean checkWeakOrder( List<Polynomial> polyList){

        boolean isWeakOrder = true;
        Polynomial previous = polyList.get(polyList.size()-1);
        for(int i = polyList.size()-2; i > 0; i--){

            if (previous.compareExponents(polyList.get(i)) < 0){
                isWeakOrder = false;
            }
        }
        return isWeakOrder;
    }

    public static void processPolyList(){
        try {
            ArrayList<String> a = fromFile();
            for (String element : a) {
                Polynomial p = new Polynomial(element);
                System.out.println(p);
                polyList.add(p);
            }
        }catch (InvalidPolynomialSyntax ex){
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),ex.getMessage());
        }
        /* Call to check sorted for the Strong order check */
        System.out.println("Strong Ordered: " + OrderedList.checkSorted(polyList));
        /* Check for Weak order (exponents only) */
        System.out.println("Weak Ordered: " + checkWeakOrder(polyList));
    }
}