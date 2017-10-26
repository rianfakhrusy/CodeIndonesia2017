import java.util.*;
import java.io.*;

public class Nopalindrom {
    static String filename = "A-small-attempt1";
    //static String filename = "A-large";
    //static String filename = "input";
    static String inname = filename + ".in";    
    static String outname = filename + ".out"; 
    
    private static int[] FungsiPembatas(char[] pola)
    {
        int[] FungsiPembatas = new int[pola.length]; //Membuat array seukuran panjang pola
        FungsiPembatas[0] = 0; //Inisialisasi untuk panjang=1
        int lPola = pola.length; //Panjang pola
        int i = 1;
        int j = 0;
        while (i < lPola)
        {
            if (pola[j] == pola[i])
            {
                // Kasus jika karakter cocok
                FungsiPembatas[i] = j + 1;
                i++;
                j++;
            }
            else
            if (j > 0)
            {
                // Melanjutkan proses pencocokan
                j = FungsiPembatas[j - 1];
            }
            else
            {
                //Kasus jika tidak ada karakter yang cocok
                FungsiPembatas[i] = 0;
                i++;
            }
        }
        return FungsiPembatas;
    }

    /// <summary>
    /// Algoritma String Matching dengan KMP
    /// </summary>
    /// <param name="teks"> String yang akan dicari indeks kemunculand dari substring pola </param>
    /// <param name="pola"> String yang akan dicari indeks kemunculannya di teks </param>
    /// <returns> Indeks kemunculan pola di teks jika ada, -1 jika tidak ditemukan </returns>
    public static int StringMatch(char[] teks, char[] pola)
    {
        int PanjangTeks = teks.length;// Panjang teks
        int PanjangPola = pola.length;// Panjang pola
        int[] FungsiBatas = FungsiPembatas(pola); //Menghitung fungsi pembatas
        int i = 0; int j = 0;
        while (i < PanjangTeks)
        {
            if (pola[j] == teks[i])
            {
                if (j == PanjangPola - 1)
                {
                    // Kasus jika Panjang Pola sama dengan panjang kecocokan
                    // Kembalikan indeks kecocokan
                    return i - PanjangPola + 1;
                }
                i++;
                j++;
            }
            else
            if (j > 0)
            {
                // Kasus jika karakter tidak cocok pada karakter ke-2 hingga terakhir
                j = FungsiBatas[j - 1];
            }
            else
            {
                // Kasus jika sama sekali tidak ada karakter yang cocok
                i++;
            }
        }
        return -1;
    }
    
    public static String[] getMatrixDiagonal(String[] grid) {
        StringBuilder builder = new StringBuilder();
        for (String s : grid) {
            builder.append(s);
        }
        String matrixString = builder.toString();

        int wordLength = grid[0].length();
        int numberOfWords = grid.length;
        List<String> list = new ArrayList<String>();


        if (wordLength > 0) {
            int[] indexes = new int[numberOfWords];

            indexes[0] = matrixString.length() - wordLength;
            for (int i = 1; i < numberOfWords; i++) {
                indexes[i] = indexes[i - 1] - wordLength;
            }

            int wordCount = numberOfWords + wordLength - 1;

            for (int i = 0; i < wordCount; i++) {
                builder.delete(0, builder.length());
                for (int j = 0; (j <= i) && (j < numberOfWords); j++) {
                    if (indexes[j] < wordLength * (wordCount - i)) {
                        char c = matrixString.charAt(indexes[j]);
                        builder.append(c);
                        indexes[j]++;
                    }
                }
                String s = builder.reverse().toString();
                list.add(s);
            }
        }

        return list.toArray(new String[list.size()]);
    }
    
    public static char[][] transpose (char[][] array) {
        if (array == null || array.length == 0)//empty or unset array, nothing do to here
            return array;

        int width = array.length;
        int height = array[0].length;

        char[][] array_new = new char[height][width];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                array_new[y][x] = array[x][y];
            }
        }
        return array_new;
    }
    
    public static String[] convertToStringArray(char[][] strArr){
        String[] ret = new String[strArr.length];
        for(int i=0; i<strArr.length; i++){
            ret[i] = String.valueOf(strArr[i]);
        }
        return ret;
    }
        
    public static void main(String[] args){
        try{
            //Scanner in = new Scanner(new BufferedReader(new FileReader(inname)));
            Scanner in = new Scanner(System.in);
            BufferedWriter out = new BufferedWriter(new FileWriter(outname));
            int t = in.nextInt();
            in.nextLine();
            for (int cas = 1; cas <= t; cas++){
                int r = in.nextInt();
                int c = in.nextInt();in.nextLine();
                char[][] grid = new char[r][c];
                for (int i=0;i<r;i++)
                    grid[i] = in.nextLine().toCharArray();
                
                char[] theWord = in.nextLine().toCharArray();
                
                //for (int i=0;i<r;i++)
                    //System.out.println(grid[i]);
                //System.out.println(theWord);
                
                int counter = 0;
                
                for (int i=0;i<r;i++){
                    char[] theGrid = grid[i];
                    //System.out.println("grid= " + String.valueOf(theGrid));
                    int rtemp = StringMatch(theGrid,theWord);
                    while (rtemp!=-1) {
                        rtemp = StringMatch(theGrid,theWord);
                        if (rtemp==-1)
                            break;
                        theGrid = String.valueOf(theGrid).substring(rtemp+1).toCharArray();
                        //System.out.println("grid= " + String.valueOf(theGrid));
                        counter++;
                    } 
                }
                
                for (int i=0;i<r;i++){
                    String temp = new StringBuilder(String.valueOf(grid[i])).reverse().toString();
                    char[] theGrid = temp.toCharArray();
                    //System.out.println("grid= " + String.valueOf(theGrid));
                    int rtemp = StringMatch(theGrid,theWord);
                    while (rtemp!=-1) {
                        rtemp = StringMatch(theGrid,theWord);
                        if (rtemp==-1)
                            break;
                        theGrid = String.valueOf(theGrid).substring(rtemp+1).toCharArray();
                        //System.out.println("grid= " + String.valueOf(theGrid));
                        counter++;
                    } 
                }
                
                char[][] gridrev = transpose(grid);
                
                for (int i=0;i<c;i++){
                    char[] theGrid = gridrev[i];
                    //System.out.println("grid= " + String.valueOf(theGrid));
                    int rtemp = StringMatch(theGrid,theWord);
                    while (rtemp!=-1) {
                        rtemp = StringMatch(theGrid,theWord);
                        if (rtemp==-1)
                            break;
                        theGrid = String.valueOf(theGrid).substring(rtemp+1).toCharArray();
                        //System.out.println("grid= " + String.valueOf(theGrid));
                        counter++;
                    } 
                }
                
                for (int i=0;i<c;i++){
                    String temp = new StringBuilder(String.valueOf(gridrev[i])).reverse().toString();
                    char[] theGrid = temp.toCharArray();
                    //System.out.println("grid= " + String.valueOf(theGrid));
                    int rtemp = StringMatch(theGrid,theWord);
                    while (rtemp!=-1) {
                        rtemp = StringMatch(theGrid,theWord);
                        if (rtemp==-1)
                            break;
                        theGrid = String.valueOf(theGrid).substring(rtemp+1).toCharArray();
                        //System.out.println("grid= " + String.valueOf(theGrid));
                        counter++;
                    } 
                }
                
                String[] grids = convertToStringArray(grid);
                
                
                String[] downright = getMatrixDiagonal(grids);
                
                for (int i=0;i<downright.length;i++){
                    char[] theGrid = downright[i].toCharArray();
                    System.out.println("grid= " + String.valueOf(theGrid));
                    int rtemp = StringMatch(theGrid,theWord);
                    while (rtemp!=-1) {
                        rtemp = StringMatch(theGrid,theWord);
                        if (rtemp==-1)
                            break;
                        theGrid = String.valueOf(theGrid).substring(rtemp+1).toCharArray();
                        System.out.println("grid= " + String.valueOf(theGrid));
                        counter++;
                    } 
                }
                
                /*
                String[] gridsrev = convertToStringArray(gridrev);
                String[] downleft = getMatrixDiagonal(gridsrev);
                
                for (int i=0;i<downleft.length;i++){
                    char[] theGrid = downleft[i].toCharArray();
                    System.out.println("grid= " + String.valueOf(theGrid));
                    int rtemp = StringMatch(theGrid,theWord);
                    while (rtemp!=-1) {
                        rtemp = StringMatch(theGrid,theWord);
                        if (rtemp==-1)
                            break;
                        theGrid = String.valueOf(theGrid).substring(rtemp+1).toCharArray();
                        System.out.println("grid= " + String.valueOf(theGrid));
                        counter++;
                    } 
                }*/
                
                /*
                for( int k = 0 ; k < c ; k++ ) {
                    String word = "";
                    for( int j = 0 ; j <= k ; j++ ) {
                        int i = k - j;
                        word += grid[i][j];
                    }
                    
                    char[] theGrid = word.toCharArray();
                    
                    System.out.println("grid= " + String.valueOf(theGrid));
                    int rtemp = StringMatch(theGrid,theWord);
                    while (rtemp!=-1) {
                        rtemp = StringMatch(theGrid,theWord);
                        if (rtemp==-1)
                            break;
                        theGrid = String.valueOf(theGrid).substring(rtemp+1).toCharArray();
                        System.out.println("grid= " + String.valueOf(theGrid));
                        counter++;
                    } 
                }
                
                for( int k = c - 2 ; k >= 0 ; k-- ) {
                    String word = "";
                    for( int j = 0 ; j <= k ; j++ ) {
                        int i = k - j;
                        word += grid[r - j - 1][c - i - 1];
                    }
                    
                    char[] theGrid = word.toCharArray();
                    
                    System.out.println("grid= " + String.valueOf(theGrid));
                    int rtemp = StringMatch(theGrid,theWord);
                    while (rtemp!=-1) {
                        rtemp = StringMatch(theGrid,theWord);
                        if (rtemp==-1)
                            break;
                        theGrid = String.valueOf(theGrid).substring(rtemp+1).toCharArray();
                        System.out.println("grid= " + String.valueOf(theGrid));
                        counter++;
                    } 
                }*/
                
                System.out.print("Case #" + cas + ": " + counter + "\n");
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
