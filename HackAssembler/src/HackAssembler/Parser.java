package HackAssembler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Parser {
    static PrintStream out = System.out;

    // parsed and cleaned substrings
    public String comp = "default";
    public String dest = "default";
    public String jump = "default";
    public String Addr = "default";
    public String instType = "";


    public void Parse(String line){
        if(line.contains("//")){    //get rid of comments
            line = line.substring(0,line.indexOf("//"));
        }
        if(line.startsWith("@")){
            Addr = line.substring(1);
            instType = "A";
            return;
        }
        String[] insTokens = line.split("[=;]"); //split the string by the delimiters = ;

        for(String token : insTokens){
            token = token.trim();   //get rid fo whitespace
        }

        instType = "C";
        if(line.contains("=") && line.contains(";")){       //different combinations ie. M=D+1;JGT M;JGT ...
            dest = insTokens[0];
            comp = insTokens[1];
            jump = insTokens[2];
        }
        else if(line.contains("=") && !line.contains(";")){
            dest = insTokens[0];
            comp = insTokens[1];
            jump = "DNE";
        }
        else if(line.contains(";") && !line.contains("=")){
            dest = "DNE";
            comp = insTokens[0];
            jump = insTokens[1];
        }
    }
}
