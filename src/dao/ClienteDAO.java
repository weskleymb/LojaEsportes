package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;

public class ClienteDAO {

    private BancoDados db;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public ClienteDAO() {
        db = new BancoDados();
    }

    public void inserir(Cliente cliente) {
        db.conectar();
        String sql = "INSERT INTO TB_CLIENTES (CLI_CPF, CLI_NOME, CLI_NASCIMENTO) VALUES (?, ?, ?)";
        try {
            ps = db.getConexao().prepareStatement(sql);
            ps.setString(1, cliente.getCpf());
            ps.setString(2, cliente.getNome());
            ps.setString(3, new SimpleDateFormat("yyyy-MM-dd").format(cliente.getNascimento()));
            ps.executeUpdate();
        } catch(SQLException error) {
            System.out.println("ERRO: " + error.toString());
        } finally {
            db.desconectar();
        }
    }
    
    public void atualizar(Cliente cliente) {
        db.conectar();
        String sql = "UPDATE TB_CLIENTES SET CLI_NOME = ?, CLI_NASCIMENTO = ? WHERE CLI_CPF = ?";
        try {
            ps = db.getConexao().prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(cliente.getNascimento()));
            ps.setString(3, cliente.getCpf());
            ps.executeUpdate();
        } catch(SQLException error) {
            System.out.println("ERRO: " + error.toString());
        }
        db.desconectar();
    }
    
    public void excluir(Cliente cliente) {
        db.conectar();
        String sql = "DELETE FROM TB_CLIENTES WHERE CLI_CPF = ?";
        try {
            ps = db.getConexao().prepareStatement(sql);
            ps.setString(1, cliente.getCpf());
            ps.executeUpdate();
            System.out.println("EXCLUIDO");
        } catch(SQLException error) {
            System.out.println("ERRO: " + error.toString());
        }
        db.desconectar();
    }
    
    public List<Cliente> buscarTodos() {
        db.conectar();
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM TB_CLIENTES";
        try {
            ps = db.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("CLI_ID"));
                cliente.setCpf(rs.getString("CLI_CPF"));
                cliente.setNome(rs.getString("CLI_NOME"));
                cliente.setNascimento(rs.getDate("CLI_NASCIMENTO"));
                clientes.add(cliente);
            }
            return clientes;
        } catch(SQLException error) {
            System.out.println("ERRO: " + error.toString());
        } finally {
            db.desconectar();
        }
        return null;
    }
    
    public Cliente buscarPorCpf(String cpf) {
        db.conectar();
        String sql = "SELECT * FROM TB_CLIENTES WHERE CLI_CPF = ?";
        try {
            ps = db.getConexao().prepareStatement(sql);
            ps.setString(1, cpf);
            rs = ps.executeQuery();
            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("CLI_ID"));
                cliente.setCpf(rs.getString("CLI_CPF"));
                cliente.setNome(rs.getString("CLI_NOME"));
                cliente.setNascimento(rs.getDate("CLI_NASCIMENTO"));
                return cliente;
            }
        } catch(SQLException error) {
            System.out.println("ERRO: " + error.toString());
        }
        db.desconectar();
        return null;
    }
    
    public Cliente buscarPorId(int id) {
        db.conectar();
        String sql = "SELECT * FROM TB_CLIENTES WHERE CLI_ID = ?";
        try {
            ps = db.getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("CLI_ID"));
                cliente.setCpf(rs.getString("CLI_CPF"));
                cliente.setNome(rs.getString("CLI_NOME"));
                cliente.setNascimento(rs.getDate("CLI_NASCIMENTO"));
                db.desconectar();
                return cliente;
            }
        } catch(SQLException error) {
            System.out.println("ERRO: " + error.toString());
        } finally {
            db.desconectar();
        }
        return null;
    }
    
    public List<Cliente> buscarPorNome(String nome) {
        db.conectar();
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM TB_CLIENTES WHERE CLI_NOME LIKE ?";
        try {
            ps = db.getConexao().prepareStatement(sql);
            ps.setString(1, "%" + nome + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("CLI_ID"));
                cliente.setCpf(rs.getString("CLI_CPF"));
                cliente.setNome(rs.getString("CLI_NOME"));
                cliente.setNascimento(rs.getDate("CLI_NASCIMENTO"));
                clientes.add(cliente);
            }
            return clientes;
        } catch(SQLException error) {
            System.out.println("ERRO: " + error.toString());
        }
        db.desconectar();
        return null;
    }
    
}
