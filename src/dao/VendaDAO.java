package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Produto;
import model.Venda;

public class VendaDAO {

    private BancoDados db;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public VendaDAO() {
        db = new BancoDados();
    }
    
    public void inserir(Venda venda) {
        db.conectar();
        String sql = "INSERT INTO TB_VENDAS ";
        sql += "(VEN_CLI_ID, VEN_DATA, VEN_VALOR) ";
        sql += "VALUES (?, ?, ?)";
        try {
            ps = db.getConexao().prepareStatement(sql);
            ps.setInt(1, venda.getCliente().getId());
            ps.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            ps.setFloat(3, venda.getValor());
            ps.executeUpdate();
            int id_venda = ultimaVenda();
            for (Produto produto : venda.getProdutos()) {
                int id_produto = produto.getId();
                sql = "INSERT INTO TB_ITENS_VENDAS ";
                sql += "(IDV_VEN_ID, IDV_PRO_ID) ";
                sql += "VALUES (?, ?)";
                ps = db.getConexao().prepareStatement(sql);
                ps.setInt(1, id_venda);
                ps.setInt(2, id_produto);
                ps.executeUpdate();
            }
        } catch(SQLException error) {
            System.out.println("Erro: " + error.toString());
        }
        db.desconectar();
    }
    
    public Venda buscarPorId(int id) {
        db.conectar();
        try {
            Venda venda = new Venda();
            String sql = "SELECT * FROM TB_VENDAS WHERE VEN_ID = ?";
            ps = db.getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) { 
               venda.setId(rs.getInt("VEN_ID"));
               venda.setCliente(new ClienteDAO().buscarPorId(rs.getInt("VEN_CLI_ID")));
               venda.setData(rs.getDate("VEN_DATA"));
               venda.setValor(rs.getFloat("VEN_VALOR"));
            }
            sql = "SELECT * FROM TB_ITENS_VENDAS WHERE IDV_VEN_ID = ?";
            ps = db.getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            List<Produto> produtos = new ArrayList<>();
            while(rs.next()) {
                produtos.add(new ProdutoDAO().buscarPorId(rs.getInt("IDV_PRO_ID")));
            }
            venda.setProdutos(produtos);
            db.desconectar();
            return venda;
        } catch(SQLException error) {
            System.out.println("Erro: " + error.toString());
        } finally {
            db.desconectar();
        }
        return null;
    }
    
    public List<Venda> buscarTodos() {
        //db.conectar();
        try {
            List<Venda> vendas = new ArrayList<>();
            String sql = "SELECT * FROM TB_VENDAS";
            ps = db.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                vendas.add(new VendaDAO().buscarPorId(rs.getInt("VEN_ID")));
            }
            db.desconectar();
            return vendas;
        } catch(SQLException error) {
            System.out.println("Erro: " + error);
        } finally {
            db.desconectar();
        }
        return null;
    }
    
    private int ultimaVenda() {
        db.conectar();
        String sql = "SELECT VEN_ID FROM TB_VENDAS ";
        sql += "ORDER BY VEN_ID DESC LIMIT 1";
        try {
            ps = db.getConexao().prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("VEN_ID");
            }
        } catch(SQLException error) {
            System.out.println("Erro2: " + error.toString());
        }
        db.desconectar();
        return 0;
    }
    
    
    
}
