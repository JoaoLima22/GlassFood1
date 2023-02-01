package Sistema.forms.produto;

import Sistema.forms.cliente.ClienteForm;
import Sistema.forms.entregador.EntregadorForm;
import Sistema.forms.pedido.PedidoForm;
import Sistema.models.Produto;
import Sistema.repository.ProdutoDAO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ProdutoForm extends JFrame{
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
    private JComboBox boxFiltro;
    private JButton okButton;
    private DefaultListModel DLM;
    private int indice, auxFiltrar=0;

    public ProdutoForm(Sistema.forms.Menu menu){
        setContentPane(painel); //define o frame
        setTitle("Glass Food");     // define o título da pag
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // fecha a aplicação
        setMinimumSize(new Dimension(1300, 700)); // define o tamanho da guia
        ImageIcon img = new ImageIcon("C:\\Users\\João Vitor\\eclipse-workspace\\GlassFood1\\src\\main\\resources\\icon.png");
        setIconImage(img.getImage()); //adiciona o icone
        criarDLM();   //Cria e preenche a lista
        preencherLista();
        setVisible(true); //torna a página visivel

        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastro();
            }
        });
        lista.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {indice=lista.getSelectedIndex();}
        });
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//Montar Limitação
                modificar();
            }
        });
        removerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletar();
            }
        });
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DLMfiltro();
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
                show();
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
    public Produto selecionar(){
        Produto produto = new Produto();
        String linhaClique = (String) DLM.get(indice);
        List<Produto> produtos = new ArrayList<Produto>();
        produtos = ProdutoDAO.selectAll();
        for(int i=0; i<produtos.size(); i++){
            String linha = (produtos.get(i).getCod_prod()+" - "+produtos.get(i).getNome()+" - "+produtos.get(i).getValor()+" - "+produtos.get(i).getDescricao()+" - "+produtos.get(i).getTipo());
            if (linha.equals(linhaClique)) {
                produto = produtos.get(i);
            }
        }
        return produto;
    }
    public void criarDLM(){
        List<Produto> produtos = new ArrayList<Produto>();
        produtos = ProdutoDAO.selectAll();
        DLM = new DefaultListModel<>();
        for(int i=0; i<produtos.size(); i++){
            String linha = (produtos.get(i).getCod_prod()+" - "+produtos.get(i).getNome()+" - "+produtos.get(i).getValor()+" - "+produtos.get(i).getDescricao()+" - "+produtos.get(i).getTipo());
            DLM.addElement(linha);
        }
    }
    public void preencherLista(){lista.setModel(DLM);}
    public void back(){
        if(auxFiltrar==0){
            criarDLM();
        } else if (auxFiltrar==1) {
            DLMpizzas();
        }else if (auxFiltrar==2) {
            DLMLanches();
        }else if (auxFiltrar==3) {
            DLMBebidas();
        }
        preencherLista();
    }
    public void cadastro(){
        ProdutoCadastro produtoCadastro = new ProdutoCadastro(this);
    }
    public void modificar(){ProdutoModificar produtoModificar = new ProdutoModificar(selecionar(), this);}
    public void deletar(){ProdutoDeletar produtoDeletar = new ProdutoDeletar(this, selecionar());}
    public void DLMfiltro(){
        if(boxFiltro.getSelectedItem()=="Todos"){
            auxFiltrar=0;
            criarDLM();
            preencherLista();
        } else if (boxFiltro.getSelectedItem()=="Pizzas") {
            auxFiltrar=1;
            DLMpizzas();
            preencherLista();
        } else if (boxFiltro.getSelectedItem()=="Lanches") {
            auxFiltrar=2;
            DLMLanches();
            preencherLista();
        } else if (boxFiltro.getSelectedItem()=="Bebidas") {
            auxFiltrar=3;
            DLMBebidas();
            preencherLista();
        }
    }
    public void DLMpizzas(){
        List<Produto> produtos = new ArrayList<Produto>();
        produtos = ProdutoDAO.selectPizzas();
        DLM = new DefaultListModel<>();
        for(int i=0; i<produtos.size(); i++){
            String linha = (produtos.get(i).getCod_prod()+" - "+produtos.get(i).getNome()+" - "+produtos.get(i).getValor()+" - "+produtos.get(i).getDescricao()+" - "+produtos.get(i).getTipo());
            DLM.addElement(linha);
        }
    }
    public void DLMLanches(){
        List<Produto> produtos = new ArrayList<Produto>();
        produtos = ProdutoDAO.selectLanches();
        DLM = new DefaultListModel<>();
        for(int i=0; i<produtos.size(); i++){
            String linha = (produtos.get(i).getCod_prod()+" - "+produtos.get(i).getNome()+" - "+produtos.get(i).getValor()+" - "+produtos.get(i).getDescricao()+" - "+produtos.get(i).getTipo());
            DLM.addElement(linha);
        }
    }
    public void DLMBebidas(){
        List<Produto> produtos = new ArrayList<Produto>();
        produtos = ProdutoDAO.selectBebidas();
        DLM = new DefaultListModel<>();
        for(int i=0; i<produtos.size(); i++){
            String linha = (produtos.get(i).getCod_prod()+" - "+produtos.get(i).getNome()+" - "+produtos.get(i).getValor()+" - "+produtos.get(i).getDescricao()+" - "+produtos.get(i).getTipo());
            DLM.addElement(linha);
        }
    }
}
