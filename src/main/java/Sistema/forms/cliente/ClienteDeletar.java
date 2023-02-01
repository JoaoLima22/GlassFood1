package Sistema.forms.cliente;

import Sistema.models.Cliente;
import Sistema.repository.ClienteDAO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClienteDeletar extends JFrame{
    private JPanel PainelDeletar;
    private JLabel txtCodigo;
    private JLabel txtNome;
    private JLabel txtTelefone;
    private JLabel txtRua;
    private JLabel txtBairro;
    private JLabel txtNumero;
    private JButton cancelarButton;
    private JButton confirmarButton;
    private JLabel txtAviso;

    public ClienteDeletar(Cliente cliente, ClienteForm cliForm){
        setContentPane(PainelDeletar); //define o frame
        setTitle("Glass Food");     // define o título da pag
        setSize(500, 350); // define o tamanho da pag
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE); // fecha a aplicação
        setResizable(false); //impede que modifiquem o tamanho da pagina
        ImageIcon img = new ImageIcon("C:\\Users\\João Vitor\\eclipse-workspace\\GlassFood1\\src\\main\\resources\\icon.png");
        setIconImage(img.getImage());
        String cod = String.valueOf(cliente.getCod_cli());
        txtCodigo.setText(cod);
        txtNome.setText(cliente.getNome());
        txtTelefone.setText(cliente.getTelefone());
        txtRua.setText(cliente.getRua());
        txtBairro.setText(cliente.getBairro());
        txtNumero.setText((cliente.getNumero()));
        verificacao(cliente);

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
                cliente.deletar();
                cliForm.back();
                hide();
            }
        });
    }
    private void verificacao(Cliente cliente){
        if(ClienteDAO.countByPed(cliente.getCod_cli())!=0){
            confirmarButton.setEnabled(false);
            txtAviso.setText("Impossivel remover este cliente, pendência!");
        }
    }
}
