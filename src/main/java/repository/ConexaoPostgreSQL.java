package repository;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import io.github.cdimascio.dotenv.Dotenv;
 
public class ConexaoPostgreSQL {
    private String host;
    private String username;
    private String password;
    private String dbname;
    private String port;
    private Dotenv dotenv = Dotenv.configure().load();
    
    
    public ConexaoPostgreSQL() {
        this.host = dotenv.get("POSTGRES_HOST");
        this.dbname = dotenv.get("POSTGRES_DBNAME");
        this.username = dotenv.get("POSTGRES_USERNAME");
        this.password = dotenv.get("POSTGRES_PASSWORD");
        this.port = dotenv.get("POSTGRES_PORT");
    }

    public Connection getConexao() {
        String url = "jdbc:postgresql://" + this.host + ":" + this.port + "/" + this.dbname;
        try {
            System.out.println("Show de bola! Estou 'connect'");
            return DriverManager.getConnection(url, this.username, this.password);
        } catch (SQLException ex) {
            System.out.println("Deu xabum na conexao!");
            Logger.getLogger(ConexaoPostgreSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     
}