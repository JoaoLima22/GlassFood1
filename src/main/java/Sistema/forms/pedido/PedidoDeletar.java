package Sistema.forms.pedido;

import Sistema.models.Entregador;
import Sistema.models.Pedido;
import Sistema.repository.EntregadorDAO;
import Sistema.repository.PedidoDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PedidoDeletar extends JFrame{
    private JPanel Painel;
    private JLabel titulo;
    private JLabel txtCodigo;
    private JLabel txtValor;
    private JLabel txtObservacao;
    private JButton cancelarButton;
    private JButton confirmarButton;
    private JLabel txtAviso;
    public PedidoDeletar(Pedido pedido, PedidoForm pedidoForm){
        setContentPane(Painel); //define o frame
        setTitle("Glass Food");     // define o título da pag
        setSize(500, 350); // define o tamanho da pag
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setResizable(false); //impede que modifiquem o tamanho da pagina
        ImageIcon img = new ImageIcon("C:\\Users\\João Vitor\\eclipse-workspace\\GlassFood1\\src\\main\\resources\\icon.png");
        setIconImage(img.getImage());
        confirmarButton.setEnabled(true);
        verificação(pedido);
        setVisible(true);
        txtCodigo.setText(String.valueOf(pedido.getCod_ped()));
        txtValor.setText(String.valueOf(pedido.getValor()));
        txtObservacao.setText(pedido.getObsevacao());

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hide();
                pedidoForm.back();
            }
        });
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pedido.deletar(pedido.getCod_ped());
                pedidoForm.back();
                hide();
            }
        });
    }
    public void verificação(Pedido pedido){
        if(pedido.getEstado().equals("Entregando")){
            confirmarButton.setEnabled(false);
            txtAviso.setText("Impossivel, pedido sendo entregue!");
        }
    }
}
