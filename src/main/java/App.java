import java.sql.Connection;

import persistencia.ConexaoPostgreSQL;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Connection connection = new ConexaoPostgreSQL().getConexao();
    }
}
