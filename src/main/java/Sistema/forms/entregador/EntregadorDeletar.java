package Sistema.forms.entregador;

import Sistema.models.Entregador;
import Sistema.repository.ClienteDAO;
import Sistema.repository.EntregadorDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntregadorDeletar extends JFrame{
    private JPanel PainelDeletar;
    private JLabel txtCodigo;
    private JLabel txtNome;
    private JLabel txtTelefone;
    private JLabel txtVeiculo;
    private JButton cancelarButton;
    private JButton confirmarButton;
    private JLabel txtOcupado;
    private JLabel txtAviso;

    public EntregadorDeletar(EntregadorForm entreForm, Entregador entregador){

        setContentPane(PainelDeletar); //define o frame
        setTitle("Glass Food");     // define o título da pag
        setSize(500, 350); // define o tamanho da pag
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE); // fecha a aplicação
        setResizable(false); //impede que modifiquem o tamanho da pagina
        ImageIcon img = new ImageIcon("C:\\Users\\João Vitor\\eclipse-workspace\\GlassFood1\\src\\main\\resources\\icon.png");
        setIconImage(img.getImage());
        String cod = String.valueOf(entregador.getCod_entregador());
        txtCodigo.setText(cod);
        txtNome.setText(entregador.getNome());
        txtTelefone.setText(entregador.getTelefone());
        txtVeiculo.setText(entregador.getVeiculo());
        txtOcupado.setText(String.valueOf(entregador.getOcupado()));
        verificacao(entregador);
        setVisible(true); //torna a página visivel

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hide();
            }
        });
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                entregador.deletar();
                entreForm.back();
                hide();
            }
        });
    }
    public void verificacao(Entregador entregador){
        if(EntregadorDAO.countByPed(entregador.getCod_entregador())!=0){
            confirmarButton.setEnabled(false);
            txtAviso.setText("Impossivel remover este entregador, pendência!");
        }
    }
}
