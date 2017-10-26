import java.util.*;
import java.io.*;

public class Multiplication {
    static String filename = "A-small-attempt1";
    //static String filename = "A-large";
    //static String filename = "input";
    static String inname = filename + ".in";    
    static String outname = filename + ".out"; 
    
    public static void main(String[] args){
        try{
            //Scanner in = new Scanner(new BufferedReader(new FileReader(inname)));
            Scanner in = new Scanner(System.in);
            BufferedWriter out = new BufferedWriter(new FileWriter(outname));
            int t = in.nextInt();
            in.nextLine();
            for (int cas = 1; cas <= t; cas++){
                int start = in.nextInt();
                int fin = in.nextInt();
                int div = in.nextInt();
                
                int cnt = 0;
                for (int i=start;i<=fin;i++){
                    if (i%div==0)
                        cnt++;
                }
                
                System.out.print("Case #" + cas + ": " + cnt + "\n");
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
