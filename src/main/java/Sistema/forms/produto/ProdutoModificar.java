package Sistema.forms.produto;

import Sistema.forms.entregador.EntregadorForm;
import Sistema.models.Entregador;
import Sistema.models.Produto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ProdutoModificar extends JFrame{
    private JPanel produtoModificar;
    private JTextArea nometf;
    private JButton confirmarButton;
    private JButton cancelarButton;
    private JLabel txtModificar;
    private JFormattedTextField valortf;
    private JTextArea descricaotf;
    private JComboBox tipoBox;

    public ProdutoModificar(Produto produto, ProdutoForm produtoForm){
        setContentPane(produtoModificar); //define o frame
        setTitle("Glass Food");     // define o título da pag
        setSize(700, 700); // define o tamanho da pag
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE); // fecha a aplicação
        ImageIcon img = new ImageIcon("C:\\Users\\João Vitor\\eclipse-workspace\\GlassFood1\\src\\main\\resources\\icon.png");
        setIconImage(img.getImage());
        setVisible(true); //torna a página visivel
        setResizable(false); //impede que modifiquem o tamanho da pagina

        nometf.setText(produto.getNome());
        valortf.setText(String.valueOf(produto.getValor()));
        descricaotf.setText(produto.getDescricao());
        tipoBox.setSelectedItem(produto.getTipo());

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hide();
            }
        });
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    confirmar(produtoForm, produto);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    public void confirmar(ProdutoForm produtoForm, Produto produto) throws SQLException {
        int verificacao=0;

        if (nometf.getText().isBlank()==true){verificacao++;}
        if (valortf.getText()==null){verificacao++;}
        if (descricaotf.getText().isBlank()==true){verificacao++;}
        if (tipoBox.getSelectedItem()=="- - - -"){verificacao++;}

        if(verificacao==0){
            String auxValor = valortf.getText();
            double valor = Double.parseDouble(auxValor);
            produto.modificar(nometf.getText(), valor, descricaotf.getText(), (String) tipoBox.getSelectedItem());

            nometf.setText("");
            valortf.setText("");
            descricaotf.setText("");
            tipoBox.setSelectedIndex(0);

            hide();
            produtoForm.back();
        }else{
            txtModificar.setText("Preencha todos os campos!");
        }
    }
}
