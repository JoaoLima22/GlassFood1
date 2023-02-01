package Sistema.forms.pedido;

import Sistema.models.Pedido;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PedidoFinalizar extends JFrame{
    private JPanel Painel;
    private JLabel titulo;
    private JLabel txtCodigo;
    private JLabel txtValor;
    private JLabel txtObservacao;
    private JButton cancelarButton;
    private JButton confirmarButton;
    private JLabel txtAviso;

    public PedidoFinalizar(Pedido pedido, PedidoForm pedidoForm){
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
                pedido.finalizar();
                hide();
                pedidoForm.back();
            }
        });
    }
    public void verificação(Pedido pedido){
        if (pedido.getEstado().equals("Preparando")){
            txtAviso.setText("Impossivel, pedido ainda em preparação!");
        }else if(pedido.getEstado().equals("Pronto")){
            txtAviso.setText("Impossivel, pedido ainda não entregue!");
        }else if(pedido.getEstado().equals("Entregando")){
            confirmarButton.setEnabled(true);
        }else if(pedido.getEstado().equals("Finalizado")){
            txtAviso.setText("Impossivel, pedido já finalizado!");
        }
    }
}

