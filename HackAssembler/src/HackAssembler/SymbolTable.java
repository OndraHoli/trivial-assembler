package HackAssembler;
import java.io.*;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SymbolTable {

    public HashMap<String, String> symbols = new HashMap<String,String>(); //resulting map of symbols used in translation

    void setSymbols(HashMap<String, String> symbols) {
        this.symbols = symbols;
        symbols.put("R0","0");
        symbols.put("R1","1");
        symbols.put("R2","2");
        symbols.put("R3","3");
        symbols.put("R4","4");
        symbols.put("R5","5");
        symbols.put("R6","6");
        symbols.put("R7","7");
        symbols.put("R8","8");
        symbols.put("R9","9");
        symbols.put("R10","10");
        symbols.put("R11","11");
        symbols.put("R12","12");
        symbols.put("R13","13");
        symbols.put("R14","14");
        symbols.put("R15","15");
        symbols.put("SCREEN","16384");
        symbols.put("KBD","24576");
        symbols.put("SP","0");
        symbols.put("LCL","1");
        symbols.put("ARG","2");
        symbols.put("THIS","3");
        symbols.put("THAT","4");
    }

    public void analyzeSymbols(File file) {

        setSymbols(symbols);
        ArrayList<String> storedValues = new ArrayList<String>();
        ArrayList<String> storedLabels = new ArrayList<String>();
        ArrayList<Integer> labelLines = new ArrayList<Integer>();

        try{
            int lineCount = 0;
            Scanner fileRead = new Scanner(file);
            while(fileRead.hasNextLine()){
                String line = fileRead.nextLine();
                line = line.trim();
                if(!line.startsWith("(") && !line.startsWith("//") && !line.isBlank()){
                    lineCount++;
                }
                if(line.startsWith("@") && !isNumeric(line.substring(1)) && !symbols.containsKey(line.substring(1)) && !storedValues.contains(line.substring(1))){
                    storedValues.add(line.substring(1));
                }
                else if(line.startsWith("(")){
                    storedLabels.add(line.substring(1,line.length()-1));
                    labelLines.add(lineCount);
                }
            }
        }catch (IOException e){
            System.out.println("File not found");
        }
        int inc = 1;
        int varValue;
        for(String value : storedValues){
            if(storedLabels.contains(value)){
                symbols.put(value, labelLines.get(storedLabels.indexOf(value)).toString());
                System.out.println("TEST");
            }
            else{
                varValue = 15+inc;
                symbols.put(value, Integer.toString(varValue));
                inc++;
            }
        }

    }
    boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
