package view;

import dao.ClienteDAO;
import dao.ProdutoDAO;
import dao.VendaDAO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import model.Cliente;
import model.Produto;
import model.Venda;

public class Main {
    
    public static void main(String[] args) {
        
        for (Venda venda : new VendaDAO().buscarTodos()) {
            System.out.println(venda);
        }
//        System.out.println(new VendaDAO().buscarTodos());
        
    }
    
}
