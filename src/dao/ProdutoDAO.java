package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Produto;

public class ProdutoDAO {
    
    private BancoDados db;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public ProdutoDAO() {
        db = new BancoDados();
    }
    
    public void inserir(Produto produto) {
        db.conectar();
        String sql = "INSERT INTO TB_PRODUTOS (PRO_NOME, PRO_PRECO, PRO_ESTOQUE) VALUES (?, ?, ?)";
        try {
            ps = db.getConexao().prepareStatement(sql);
            ps.setString(1, produto.getNome());
            ps.setFloat(2, produto.getPreco());
            ps.setInt(3, produto.getEstoque());
            ps.executeUpdate();
        } catch(SQLException error) {
            System.out.println("ERRO: " + error.toString());
        }
        db.desconectar();
    }
    
    public void atualizar(Produto produto) {
        db.conectar();
        String sql = "UPDATE TB_PRODUTOS SET PRO_NOME = ?, PRO_PRECO = ?, PRO_ESTOQUE = ? WHERE PRO_ID = ?";
        try {
            ps = db.getConexao().prepareStatement(sql);
            ps.setString(1, produto.getNome());
            ps.setFloat(2, produto.getPreco());
            ps.setInt(3, produto.getEstoque());
            ps.setInt(4, produto.getId());
            ps.executeUpdate();
        } catch(SQLException error) {
            System.out.println("ERRO: " + error.toString());
        }
        db.desconectar();
    }
    
    public void excluir(Produto produto) {
        db.conectar();
        String sql = "DELETE FROM TB_PRODUTOS WHERE PRO_ID = ?";
        try {
            ps = db.getConexao().prepareStatement(sql);
            ps.setInt(1, produto.getId());
            ps.executeUpdate();
        } catch(SQLException error) {
            System.out.println("ERRO: " + error.toString());
        }
        db.desconectar();
    }
    
    public List<Produto> buscarTodos() {
        db.conectar();
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM TB_PRODUTOS";
        try {
            ps = db.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("PRO_ID"));
                produto.setNome(rs.getString("PRO_NOME"));
                produto.setPreco(rs.getFloat("PRO_PRECO"));
                produto.setEstoque(rs.getInt("PRO_ESTOQUE"));
                produtos.add(produto);
            }
            return produtos;
        } catch(SQLException error) {
            System.out.println("ERRO: " + error.toString());
        }
        db.desconectar();
        return null;
    }
    
    public Produto buscarPorId(int id) {
        db.conectar();
        Produto produto = new Produto();
        String sql = "SELECT * FROM TB_PRODUTOS WHERE PRO_ID = ?";
        try {
            ps = db.getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {   
                produto.setId(rs.getInt("PRO_ID"));
                produto.setNome(rs.getString("PRO_NOME"));
                produto.setPreco(rs.getFloat("PRO_PRECO"));
                produto.setEstoque(rs.getInt("PRO_ESTOQUE"));
                return produto;
            }
        } catch(SQLException error) {
            System.out.println("ERRO: " + error.toString());
        }
        db.desconectar();
        return null;
    }
    
}
