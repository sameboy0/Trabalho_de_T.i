
package magica;
import  java.sql.Connection;
import  java.sql.DriverManager;

public class Conexao {

    public Connection getConexao(){
        try{
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/trabalho_ti",
                    "root",
                    "");
            return conn;
        }catch(Exception e){
            System.out.println("Erro de conexão: " + e.getMessage());
            return null;
        }

    }
    public static void main(String[] args) {
       
        }
    }

    

