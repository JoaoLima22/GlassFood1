package Sistema.forms.cliente;

import Sistema.models.Cliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClienteModificar extends JFrame{
    private JPanel clienteModificar2;
    private JTextArea nometf;
    private JTextArea telefonetf;
    private JTextArea ruatf;
    private JTextArea bairrotf;
    private JTextArea numerotf;
    private JButton confirmarButton;
    private JButton cancelarButton;
    private JLabel txtModificar;

    public ClienteModificar(Cliente cliente, ClienteForm cliForm){
        setContentPane(clienteModificar2); //define o frame
        setTitle("Glass Food");     // define o título da pag
        setSize(700, 700); // define o tamanho da pag
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE); // fecha a aplicação
        ImageIcon img = new ImageIcon("C:\\Users\\João Vitor\\eclipse-workspace\\GlassFood1\\src\\main\\resources\\icon.png");
        setIconImage(img.getImage());
        setVisible(true); //torna a página visivel
        setResizable(false); //impede que modifiquem o tamanho da pagina

        nometf.setText(cliente.getNome());
        telefonetf.setText(cliente.getTelefone());
        ruatf.setText(cliente.getRua());
        bairrotf.setText(cliente.getBairro());
        numerotf.setText(cliente.getNumero());

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hide();
            }
        });
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmar(cliForm, cliente);
            }
        });
    }
    public void confirmar(ClienteForm cliForm, Cliente cliente){
        int verificacao=0;

        if (nometf.getText().isBlank()==true){verificacao++;}
        if (telefonetf.getText()==null){verificacao++;}
        if (ruatf.getText().isBlank()==true){verificacao++;}
        if (bairrotf.getText().isBlank()==true){verificacao++;}
        if (numerotf.getText().isBlank()==true){verificacao++;}

        if(verificacao==0){
            cliente.modificar(nometf.getText(), telefonetf.getText(), ruatf.getText(), bairrotf.getText(), numerotf.getText());
            hide();
            cliForm.back();
        }else{
            txtModificar.setText("Preencha todos os campos!");
        }

    }
}
