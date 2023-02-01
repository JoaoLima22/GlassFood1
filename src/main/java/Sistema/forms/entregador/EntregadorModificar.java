package Sistema.forms.entregador;

import Sistema.forms.cliente.ClienteForm;
import Sistema.models.Cliente;
import Sistema.models.Entregador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntregadorModificar extends JFrame{
    private JPanel entregadorModificar;
    private JTextArea nometf;
    private JTextArea telefonetf;
    private JTextArea veiculotf;
    private JButton confirmarButton;
    private JButton cancelarButton;
    private JLabel txtModificar;

    public EntregadorModificar(Entregador entregador, EntregadorForm entreForm){
        setContentPane(entregadorModificar); //define o frame
        setTitle("Glass Food");     // define o título da pag
        setSize(700, 700); // define o tamanho da pag
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE); // fecha a aplicação
        ImageIcon img = new ImageIcon("C:\\Users\\João Vitor\\eclipse-workspace\\GlassFood1\\src\\main\\resources\\icon.png");
        setIconImage(img.getImage());
        setVisible(true); //torna a página visivel
        setResizable(false); //impede que modifiquem o tamanho da pagina

        nometf.setText(entregador.getNome());
        telefonetf.setText(entregador.getTelefone());
        veiculotf.setText(entregador.getVeiculo());

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hide();
            }
        });
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmar(entreForm, entregador);
            }
        });
    }
    public void confirmar(EntregadorForm entreForm, Entregador entregador){
        int verificacao=0;

        if (nometf.getText().isBlank()==true){verificacao++;}
        if (telefonetf.getText()==null){verificacao++;}
        if (veiculotf.getText().isBlank()==true){verificacao++;}

        if(verificacao==0){
            entregador.modificar(nometf.getText(), telefonetf.getText(), veiculotf.getText());
            hide();
            entreForm.back();
        }else{
            txtModificar.setText("Preencha todos os campos!");
        }
    }

}
