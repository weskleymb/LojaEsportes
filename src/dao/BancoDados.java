package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BancoDados {

    private static BancoDados singleton = new BancoDados();
    
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String DATABASE = "DB_LOJA";
    private final String URL = "jdbc:mysql://localhost:3306/" + DATABASE;
    private final String USER = "root";
    private final String PASSWORD = "senac";
    private Connection connection = null;
    
    public BancoDados getInstance() {
        return singleton;
    }
    
    public Connection getConexao() {
        conectar();
        return connection;
    }
    
    public void conectar() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("CONECTADO");
        } catch (ClassNotFoundException error) {
            System.out.println("DRIVER NÃO ENCONTRADO");
            System.out.println("ERRO: " + error.toString());
        } catch (SQLException error) {
            System.out.println("PROBLEMAS COM A CONEXÃO");
            System.out.println("ERRO: " + error.toString());
        }
    }
    
    public void desconectar() {
//        try {
//            connection.close();
//            System.out.println("DESCONECTADO");
//        } catch (SQLException error) {
//            System.out.println("PROBLEMAS COM A DESCONEXÃO");
//            System.out.println("ERRO: " + error.toString());
//        }
    }
    
}
