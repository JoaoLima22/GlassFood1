package Sistema.forms;

import Sistema.forms.cliente.ClienteForm;
import Sistema.forms.entregador.EntregadorForm;
import Sistema.forms.pedido.PedidoForm;
import Sistema.forms.produto.ProdutoForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame{


    private JPanel MenuPainel;
    private JPanel Central;
    private JButton produtosButton;
    private JButton pedidosButton;
    private JButton clientesButton;
    private JButton entregadoresButton;
    private JButton fecharButton;

    public Menu(){
        setContentPane(MenuPainel); //define o frame
        setTitle("Glass Food");     // define o título da pag
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // fecha a aplicação
        //setPreferredSize(new Dimension(400, 400));
        //setResizable(false); //impede que modifiquem o tamanho da pagina
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(1300, 700));
        ImageIcon img = new ImageIcon("C:\\Users\\João Vitor\\eclipse-workspace\\GlassFood1\\src\\main\\resources\\icon.png");
        setIconImage(img.getImage());
        setVisible(true); //torna a página visivel

        fecharButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        clientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientes();
            }
        });
        entregadoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                entregadores();
            }
        });
        produtosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                produtos();
            }
        });
        pedidosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pedidos();
            }
        });
    }
    public void clientes(){
        hide();
        ClienteForm clienteForm = new ClienteForm(this);
    }
    public void entregadores(){
        hide();
        EntregadorForm entregadorForm = new EntregadorForm(this);
    }
    public void produtos(){
        hide();
        ProdutoForm produtoForm = new ProdutoForm(this);
    }
    public void pedidos(){
        hide();
        PedidoForm pedidoForm = new PedidoForm(this);
    }
}
