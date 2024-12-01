package HackAssembler;
import java.io.*;
import java.text.ParseException;
import java.util.Scanner;

public class Main {
    static PrintStream out = System.out;

    static Parser parser= new Parser();
    static Code code= new Code();


    public static void main(String[] args) {
        String output = "";
        try {
            String filepath = "C:/Users/Ondra/Documents/nand2tetris/assembler/" + "testprog/" + args[0] + ".asm";
            File file = new File(filepath);
            out.println(filepath);
            BufferedWriter writer = new BufferedWriter(new FileWriter(args[0] + ".hack", true));
            Scanner fileRead = new Scanner(file);
            while (fileRead.hasNextLine()) {
                String line = fileRead.nextLine();

                if(!line.isEmpty() && !line.startsWith("//")) {
                    parser.Parse(line);
                    if(parser.instType.equals("A")){
                        output = "0" + code.addr(parser.Addr);
                    }
                    else if(parser.instType.equals("C")){
                        output = "111" + code.comp(parser.comp) + code.dest(parser.dest) + code.jump(parser.jump);
                        out.println("TEST:");


                    }
                    writer.write(output);
                    writer.newLine();

                }
            }
            writer.close();
            fileRead.close();
        } catch (IOException e) {
            System.out.println("File not found");
        }


    }
}

