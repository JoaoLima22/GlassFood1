package Sistema.forms.cliente;

import Sistema.forms.entregador.EntregadorForm;
import Sistema.forms.pedido.PedidoForm;
import Sistema.forms.produto.ProdutoForm;
import Sistema.models.Cliente;
import Sistema.repository.ClienteDAO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.util.ArrayList;
import java.util.List;

public class ClienteForm extends JFrame{
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


    public ClienteForm(Sistema.forms.Menu menu) {
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
            public void actionPerformed(ActionEvent e) {adicionar();}
        });

        lista.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {indice=lista.getSelectedIndex();}
        });
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificar(indice);
            }
        });
        removerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {deletar(indice);}
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
                show();
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
                EntregadorForm entregadorForm = new EntregadorForm(menu);
            }
        });
    }
    public void criarDLM(){
        List<Cliente> clientes = new ArrayList<Cliente>();
        clientes = ClienteDAO.selectAll();
        DLM = new DefaultListModel<>();
        for(int i=0; i<clientes.size(); i++){
            String linha = (clientes.get(i).getCod_cli()+" - "+clientes.get(i).getNome()+" - "+clientes.get(i).getTelefone()+" - "+clientes.get(i).getRua()+" - "+clientes.get(i).getBairro()+" - "+clientes.get(i).getNumero());
            DLM.addElement(linha);
        }
    }
    public void preencherLista(){lista.setModel(DLM);}
    public void back(){
        criarDLM();
        preencherLista();
    }
    public Cliente selecionar(){
        Cliente cliente = new Cliente();
        String linhaClique = (String) DLM.get(indice);
        List<Cliente> clientes = new ArrayList<Cliente>();
        clientes = ClienteDAO.selectAll();
        for(int i=0; i<clientes.size(); i++){
            String linha = (clientes.get(i).getCod_cli()+" - "+clientes.get(i).getNome()+" - "+clientes.get(i).getTelefone()+" - "+clientes.get(i).getRua()+" - "+clientes.get(i).getBairro()+" - "+clientes.get(i).getNumero());
            if (linha.equals(linhaClique)) {
                cliente = clientes.get(i);
            }
        }
        return cliente;
    }
    public void adicionar(){ClienteCadastro cliCadastro= new ClienteCadastro(this);}
    public void modificar(int id){ClienteModificar cliMod = new ClienteModificar(selecionar(), this);}
    public void deletar(int id){ClienteDeletar clieMod = new ClienteDeletar(selecionar(), this);}
}
