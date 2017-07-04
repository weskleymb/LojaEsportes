package view;

import dao.ClienteDAO;
import dao.ProdutoDAO;
import dao.VendaDAO;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import model.Cliente;
import model.Produto;
import model.Venda;

public class Main {
    
    public static void main(String[] args) {
        
        Cliente cliente = new ClienteDAO().buscarPorCpf("99988877766");
        //System.out.println(cliente);
        
        List<Produto> produtos = new ArrayList<>();
        
        produtos.add(new ProdutoDAO().buscarPorId(1));
        produtos.add(new ProdutoDAO().buscarPorId(3));
        produtos.add(new ProdutoDAO().buscarPorId(4));
        
        //System.out.println(produtos);
        
        Venda venda = new Venda();
        
        venda.setCliente(cliente);
        venda.setProdutos(produtos);
        
        venda.setValor(500);
        
        //System.out.println(venda);
        
        new VendaDAO().inserir(venda);
        
    }
    
}
