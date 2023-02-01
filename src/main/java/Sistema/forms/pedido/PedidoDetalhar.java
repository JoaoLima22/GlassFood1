package Sistema.forms.pedido;

import Sistema.models.*;
import Sistema.repository.ClienteDAO;
import Sistema.repository.LancheDAO;
import Sistema.repository.PizzaDAO;
import Sistema.repository.ProdutoDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PedidoDetalhar extends JFrame{
    private JPanel painel;
    private JPanel panelBotons;
    private JList listaCar;
    private JButton voltarButton;
    private JLabel idLabel;
    private JLabel valorLabel;
    private JLabel ObservacaoLabel;
    private JLabel nomeLabel;
    private JLabel enderecoLabel;
    private DefaultListModel DLM;

    public PedidoDetalhar(PedidoForm pedidoForm, Pedido pedido){
        setContentPane(painel); //define o frame
        setTitle("Glass Food");     // define o título da pag
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setMinimumSize(new Dimension(1300, 700));
        ImageIcon img = new ImageIcon("C:\\Users\\João Vitor\\eclipse-workspace\\GlassFood1\\src\\main\\resources\\icon.png");
        setIconImage(img.getImage());
        //Entrada nos labels e na Tabela
        idLabel.setText(String.valueOf(pedido.getCod_ped()));
        Cliente cliente = new Cliente();
        cliente = ClienteDAO.selectById(pedido.getCod_cli());
        valorLabel.setText(String.valueOf(pedido.getValor()));
        ObservacaoLabel.setText(pedido.getObsevacao());
        nomeLabel.setText(cliente.getNome());
        String endereco = ("Rua: "+cliente.getRua()+", "+cliente.getNumero()+", "+cliente.getBairro());
        enderecoLabel.setText(endereco);
        //entrada da lista
        insertDLM(pedido);
        preencherLista();

        setVisible(true);
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hide();
                pedidoForm.back();
            }
        });
    }
    public void insertDLM(Pedido pedido){
        DLM = new DefaultListModel<>();
        List<Produto> bebidas = ProdutoDAO.selectBebidas(pedido.getCod_ped());
        for (int i=0; i<bebidas.size(); i++){
            String linha = (bebidas.get(i).getCod_prod()+" - "+bebidas.get(i).getNome()+" - "+bebidas.get(i).getValor());
            DLM.addElement(linha);
        }
        List<Pizza> pizzas = PizzaDAO.selectPizzas(pedido.getCod_ped());
        for (int i=0; i<pizzas.size(); i++){
            String linha = (pizzas.get(i).getCod_prod()+" - "+pizzas.get(i).getNome()+" - "+pizzas.get(i).getValor()+" - "+pizzas.get(i).getBorda());
            DLM.addElement(linha);
        }
        List<Lanche> lanches = LancheDAO.selectLanches(pedido.getCod_ped());
        for (int i=0; i<lanches.size(); i++){
            String linha = (lanches.get(i).getCod_prod()+" - "+lanches.get(i).getNome()+" - "+lanches.get(i).getValor()+" - "+lanches.get(i).getPontoCarne()+" - "+lanches.get(i).getTipoPao());
            DLM.addElement(linha);
        }
    }
    public void preencherLista(){
        listaCar.setModel(DLM);
    }
}
