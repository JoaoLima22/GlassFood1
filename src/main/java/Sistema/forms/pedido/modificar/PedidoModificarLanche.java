package Sistema.forms.pedido.modificar;

import Sistema.forms.pedido.criar.PedidoCriar2;
import Sistema.models.Lanche;
import Sistema.models.Pedido;
import Sistema.models.Produto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PedidoModificarLanche extends JFrame{
    private JPanel Painel;
    private JComboBox boxCarne;
    private JComboBox boxPao;
    private JButton cancelarButton;
    private JButton confirmarButton;
    public PedidoModificarLanche(Produto produto, PedidoModificar ped, Pedido pedido){
        setContentPane(Painel); //define o frame
        setTitle("Glass Food");     // define o título da pag
        setSize(500, 350); // define o tamanho da pag
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); // fecha a aplicação
        setResizable(false); //impede que modifiquem o tamanho da pagina
        ImageIcon img = new ImageIcon("C:\\Users\\João Vitor\\eclipse-workspace\\GlassFood1\\src\\main\\resources\\icon.png");
        setIconImage(img.getImage());
        setVisible(true);

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hide();
                ped.refresh(pedido);
            }
        });
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmar(produto, ped, pedido);
            }
        });
    }
    public void confirmar(Produto produto, PedidoModificar ped, Pedido pedido){
        Lanche lanche = new Lanche();
        String pao = (String) boxPao.getSelectedItem();
        String carne = (String) boxCarne.getSelectedItem();
        lanche.cadastrar(produto, pao, carne);
        pedido.getLanches().add(lanche);
        hide();
        ped.refresh(pedido);
    }
}
