package codeindonesia;

public class CodeIndonesia {
    public static void main(String[] args) {
        String[] grid = {"SUGAR", 
                 "GLASS", 
                 "MOUSE"};

        for( int k = 0; k < grid.length; k++ )
        {   
            StringBuffer buffer = new StringBuffer( );

            for( int i = 0; i < grid.length
                        && i+k < grid[0].length( ); i++ )
            {
                buffer.append( grid[i].charAt(i+k) );
            }

            System.out.println( buffer.toString() );
        }
    }
}
