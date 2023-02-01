package Sistema.forms.entregador;

import Sistema.forms.cliente.ClienteForm;
import Sistema.forms.pedido.PedidoForm;
import Sistema.forms.produto.ProdutoForm;
import Sistema.models.Entregador;
import Sistema.repository.EntregadorDAO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EntregadorForm extends JFrame {

    private JPanel painel;
    private JPanel panelBotons;
    private JPanel panelOptions;
    private JButton adicionarButton;
    private JButton modificarButton;
    private JButton removerButton;
    private JList lista;
    private JButton menuButton;
    private JButton pedidosButton;
    private JButton produtosButton;
    private JButton clientesButton;
    private JButton entregadoresButton;
    private JButton fecharButton;
    private DefaultListModel DLM;
    private int indice;

    public EntregadorForm(Sistema.forms.Menu menu){
        setContentPane(painel); //define o frame
        setTitle("Glass Food");     // define o título da pag
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // fecha a aplicação
        //setPreferredSize(new Dimension(400, 400));
        //setResizable(false); //impede que modifiquem o tamanho da pagina
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(1300, 700));
        ImageIcon img = new ImageIcon("C:\\Users\\João Vitor\\eclipse-workspace\\GlassFood1\\src\\main\\resources\\icon.png");
        setIconImage(img.getImage());

        criarDLM();
        preencherLista();
        setVisible(true); //torna a página visivel
        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startCadastro();
            }
        });

        lista.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {indice=lista.getSelectedIndex();}
        });
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificar();
            }
        });
        removerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletar();
            }
        });
        fecharButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hide();
                menu.show();
            }
        });
        clientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hide();
                ClienteForm cliForm = new ClienteForm(menu);
            }
        });
        pedidosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hide();
                PedidoForm pedidosForm = new PedidoForm(menu);
            }
        });
        produtosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hide();
                ProdutoForm produtoForm = new ProdutoForm(menu);
            }
        });
        entregadoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hide();
                show();
            }
        });
    }
    public Entregador selecionar(){
        Entregador entregador = new Entregador();
        String linhaClique = (String) DLM.get(indice);
        List<Entregador> entregadores = new ArrayList<Entregador>();
        entregadores = EntregadorDAO.selectAll();
        for(int i=0; i<entregadores.size(); i++){
            String linha = (entregadores.get(i).getCod_entregador()+" - "+entregadores.get(i).getNome()+" - "+entregadores.get(i).getTelefone()+" - "+entregadores.get(i).getVeiculo()+" - "+entregadores.get(i).getOcupado());
            if (linha.equals(linhaClique)) {
                entregador = entregadores.get(i);
            }
        }
        return entregador;
    }
    public void startCadastro(){
        EntregadorCadastro entregadorCadastro = new EntregadorCadastro(this);
    }
    public void modificar(){EntregadorModificar entregadorModificar = new EntregadorModificar(selecionar(), this);}
    public void deletar(){EntregadorDeletar entregadorDeletar = new EntregadorDeletar(this, selecionar());}
    public void criarDLM(){
        List<Entregador> entregadores = new ArrayList<Entregador>();
        entregadores = EntregadorDAO.selectAll();
        DLM = new DefaultListModel<>();
        for(int i=0; i<entregadores.size(); i++){
            String linha = (entregadores.get(i).getCod_entregador()+" - "+entregadores.get(i).getNome()+" - "+entregadores.get(i).getTelefone()+" - "+entregadores.get(i).getVeiculo()+" - "+entregadores.get(i).getOcupado());
            DLM.addElement(linha);
        }
    }
    public void preencherLista(){lista.setModel(DLM);}
    public void back(){
        criarDLM();
        preencherLista();
    }
}
