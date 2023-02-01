package Sistema.forms.produto;

import Sistema.models.Produto;
import Sistema.repository.ProdutoDAO;

import javax.swing.*;
import javax.swing.text.InternationalFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

public class ProdutoCadastro extends JFrame{
    private JPanel produtoCadastro;
    private JTextArea nometf;
    private JFormattedTextField valortf;
    private JTextArea descricaotf;
    private JButton confirmarButton;
    private JButton cancelarButton;
    private JLabel txtModificar;
    private JComboBox tipoBox;

    public ProdutoCadastro(ProdutoForm prodForm){
        setContentPane(produtoCadastro); //define o frame
        setTitle("Glass Food");     // define o título da pag
        setSize(700, 700); // define o tamanho da pag
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE); // fecha a aplicação
        ImageIcon img = new ImageIcon("C:\\Users\\João Vitor\\eclipse-workspace\\GlassFood1\\src\\main\\resources\\icon.png");
        setIconImage(img.getImage());
        setVisible(true); //torna a página visivel
        setResizable(false); //impede que modifiquem o tamanho da pagina


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
                    clique(prodForm);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    public void clique(ProdutoForm prodForm) throws ParseException, SQLException {
        int verificacao=0;

        if (nometf.getText().isBlank()==true){verificacao++;}
        if (valortf.getText()==null){verificacao++;}
        if (descricaotf.getText().isBlank()==true){verificacao++;}
        if (tipoBox.getSelectedItem()=="- - - -"){verificacao++;}

        if(verificacao==0){
            String auxValor = valortf.getText();
            double valor = Double.parseDouble(auxValor);
            Produto produto = new Produto();
            produto.cadastrar(nometf.getText(), valor, descricaotf.getText(), (String) tipoBox.getSelectedItem());

            nometf.setText("");
            valortf.setText("");
            descricaotf.setText("");
            tipoBox.setSelectedIndex(0);

            hide();
            prodForm.back();
        }else{
            txtModificar.setText("Preencha todos os campos!");
        }
    }
}
