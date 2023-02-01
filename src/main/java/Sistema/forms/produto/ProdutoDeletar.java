package Sistema.forms.produto;

import Sistema.forms.entregador.EntregadorForm;
import Sistema.models.Cliente;
import Sistema.models.Entregador;
import Sistema.models.Pedido;
import Sistema.models.Produto;
import Sistema.repository.ClienteDAO;
import Sistema.repository.EntregadorDAO;
import Sistema.repository.ProdutoDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProdutoDeletar extends JFrame{
    private JPanel PainelDeletar;
    private JLabel txtCodigo;
    private JLabel txtNome;
    private JLabel txtValor;
    private JLabel txtTipo;
    private JButton cancelarButton;
    private JButton confirmarButton;
    private JLabel txtAviso;

    public ProdutoDeletar(ProdutoForm produtoForm, Produto produto){

        setContentPane(PainelDeletar); //define o frame
        setTitle("Glass Food");     // define o título da pag
        setSize(500, 350); // define o tamanho da pag
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE); // fecha a aplicação
        setResizable(false); //impede que modifiquem o tamanho da pagina
        ImageIcon img = new ImageIcon("C:\\Users\\João Vitor\\eclipse-workspace\\GlassFood1\\src\\main\\resources\\icon.png");
        setIconImage(img.getImage());
        verificacao(produto);
        String cod = String.valueOf(produto.getCod_prod());
        txtCodigo.setText(cod);
        txtNome.setText(produto.getNome());
        txtValor.setText(String.valueOf(produto.getValor()));
        txtTipo.setText(produto.getTipo());

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
                produto.deletar(produto.getCod_prod());
                produtoForm.back();
                hide();
            }
        });
    }
    public void verificacao(Produto produto){
        if(ProdutoDAO.countByPed(produto.getCod_prod())!=0){
            confirmarButton.setEnabled(false);
            txtAviso.setText("Impossivel remover este produto, pendência!");
        }
    }
}
