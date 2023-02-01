package Sistema.forms.pedido.criar;

import Sistema.models.Pizza;
import Sistema.models.Produto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PedidoCriarPizza extends JFrame{
    private JPanel Painel;
    private JComboBox boxPao;
    private JButton cancelarButton;
    private JButton confirmarButton;

    public PedidoCriarPizza(Produto produto, PedidoCriar2 ped){
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
                ped.refresh();
            }
        });
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmar(produto, ped);
            }
        });
    }
    public void confirmar(Produto produto, PedidoCriar2 ped){
        Pizza pizza = new Pizza();
        String borda = (String) boxPao.getSelectedItem();
        pizza.cadastrar(produto, borda);
        ped.addPizza(pizza);

        hide();
        ped.refresh();
    }
}
