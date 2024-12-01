package HackAssembler;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import static java.util.Map.Entry;


public class Code {
    static PrintStream out = System.out;

    Map<String, String> binComp = Map.ofEntries(
            Map.entry("0","0101010"),
            Map.entry("1","0111111"),
            Map.entry("-1","0111010"),
            Map.entry("D","0001100"),
            Map.entry("A","0110000"),
            Map.entry("M","1110000"),
            Map.entry("!D","0001101"),
            Map.entry("!A","0110001"),
            Map.entry("!M","1110001"),
            Map.entry("-D","0001111"),
            Map.entry("-A","0110011"),
            Map.entry("-M","1110011"),
            Map.entry("D+1","0011111"),
            Map.entry("A+1","0110111"),
            Map.entry("M+1","1110111"),
            Map.entry("D-1","0001110"),
            Map.entry("A-1","0110010"),
            Map.entry("M-1","1110010"),
            Map.entry("D+A","0000010"),
            Map.entry("D+M","1000010"),
            Map.entry("D-A","0010011"),
            Map.entry("D-M","1010011"),
            Map.entry("A-D","0000111"),
            Map.entry("M-D","1000111"),
            Map.entry("D&A","0000000"),
            Map.entry("D&M","1000000"),
            Map.entry("D|A","0010101"),
            Map.entry("D|M","1010101")
    );
     Map<String,String> binDest = Map.ofEntries(
            Map.entry("DNE","000"),
            Map.entry("M","001"),
            Map.entry("D","010"),
            Map.entry("MD","011"),
            Map.entry("A","100"),
            Map.entry("AM","101"),
            Map.entry("AD","110"),
            Map.entry("AMD","111")
    );
     Map<String,String> binJump = Map.ofEntries(
            Map.entry("DNE","000"),
            Map.entry("JGT","001"),
            Map.entry("JEQ","010"),
            Map.entry("JGE","011"),
            Map.entry("JLT","100"),
            Map.entry("JNE","101"),
            Map.entry("JLE","110"),
            Map.entry("JMP","111")
    );

    public String comp(String token){
        String intermediate = binComp.get(token); //intermediate value calculated for debugging purposes, otherwise redundant
        return intermediate;
   }
    public String dest(String token){
        String intermediate = binDest.get(token);
        return intermediate;
   }
    public String jump(String token){
        String intermediate = binJump.get(token);
        return intermediate;
   }
    public String addr(String token, HashMap<String,String> map){

        if(!isNumeric(token)){
           String intermediate = map.get(token);
           int numValue = Integer.parseInt(intermediate);
           intermediate = Integer.toBinaryString(numValue);
           intermediate = "0".repeat(15-intermediate.length()) + intermediate;
           return intermediate;
       }
       else { //TODO: promenne nefunguji - jak je rozlisovat?
           String num = Integer.toBinaryString(Integer.parseInt(token, 10));
           num = "0".repeat(15 - num.length()) + num;
           return num;
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
