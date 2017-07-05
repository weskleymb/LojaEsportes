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
        
        Venda venda = new VendaDAO().buscarPorId(5);
        
        System.out.println(venda.getData().get(Calendar.DATE));
        
    }
    
}
