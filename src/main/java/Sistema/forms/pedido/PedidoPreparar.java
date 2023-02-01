package Sistema.forms.pedido;

import Sistema.models.Pedido;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PedidoPreparar extends JFrame{
    private JPanel Painel;
    private JLabel txtCodigo;
    private JLabel txtValor;
    private JLabel txtObservacao;
    private JButton cancelarButton;
    private JButton confirmarButton;
    private JLabel titulo;

    public PedidoPreparar(Pedido pedido, PedidoForm pedidoForm){
        setContentPane(Painel); //define o frame
        setTitle("Glass Food");     // define o título da pag
        setSize(500, 350); // define o tamanho da pag
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE); // fecha a aplicação
        setResizable(false); //impede que modifiquem o tamanho da pagina
        ImageIcon img = new ImageIcon("C:\\Users\\João Vitor\\eclipse-workspace\\GlassFood1\\src\\main\\resources\\icon.png");
        setIconImage(img.getImage());
        confirmarButton.setEnabled(false);
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
                pedido.preparar();
                hide();
                pedidoForm.back();
            }
        });
    }
    public void verificação(Pedido pedido){
        if (pedido.getEstado().equals("Preparando")){
            titulo.setText("Confirma a preparação de:");
            confirmarButton.setEnabled(true);
        }else if(pedido.getEstado().equals("Pronto")){
            titulo.setText("Pedido já preparado!");
        }else if(pedido.getEstado().equals("Entregando")){
            titulo.setText("Pedido já sendo entrege!");
        }else if(pedido.getEstado().equals("Finalizado")){
            titulo.setText("Pedido já finalizado!");
        }
    }
}
