package Sistema.forms.pedido;

import Sistema.forms.cliente.ClienteForm;
import Sistema.forms.entregador.EntregadorForm;
import Sistema.forms.pedido.criar.PedidoCriar;
import Sistema.forms.pedido.modificar.PedidoModificar;
import Sistema.forms.produto.ProdutoForm;
import Sistema.forms.produto.ProdutoModificar;
import Sistema.models.Pedido;
import Sistema.models.Produto;
import Sistema.repository.PedidoDAO;
import Sistema.repository.ProdutoDAO;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoForm extends JFrame{
    private JPanel painel;
    private JPanel panelBotons;
    private JPanel panelOptions;
    private JButton adicionarButton;
    private JButton modificarButton;
    private JButton entregarButton;
    private JList lista;
    private JButton menuButton;
    private JButton pedidosButton;
    private JButton produtosButton;
    private JButton clientesButton;
    private JButton entregadoresButton;
    private JButton fecharButton;
    private JComboBox boxFiltro;
    private JButton okButton;
    private JButton detalharBtn;
    private JButton removerBtn;
    private JButton prepararBtn;
    private JButton finalizarBtn;
    private DefaultListModel DLM;
    private int indice, auxFiltrar=0;

    public PedidoForm(Sistema.forms.Menu menu){
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
                pedidoCriar();
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
                show();
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
        detalharBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detalhar();
            }
        });
        lista.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                indice=0;
                indice=lista.getSelectedIndex();
            }
        });
        removerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remover();
            }
        });
        prepararBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                preparar();
            }
        });
        entregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                entregar();
            }
        });
        finalizarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizar();
            }
        });
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificar();
            }
        });
    }
    public Pedido selecionar(){
        Pedido pedido = new Pedido();
        String linhaClique = (String) DLM.get(indice);
        List<Pedido> pedidos = new ArrayList<Pedido>();
        pedidos = PedidoDAO.selectAll();
        DLM = new DefaultListModel<>();
        for(int i=0; i<pedidos.size(); i++){
            String linha = (pedidos.get(i).getCod_ped()+" - Cliente: "+pedidos.get(i).getCod_cli()+" - Entregador: "+pedidos.get(i).getCod_ent()+" - "+pedidos.get(i).getValor()+" - "+pedidos.get(i).getEstado()+" - "+pedidos.get(i).getObsevacao());
            if(linha.equals(linhaClique)){
               pedido = pedidos.get(i);
            }
        }
        return pedido;
    }
    public void entregar(){PedidoEntregar pedPreparar = new PedidoEntregar(this, selecionar());}
    public void modificar(){
        try {
            PedidoModificar pedPreparar = new PedidoModificar(this, selecionar());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void detalhar(){
        PedidoDetalhar pedDetalhar = new PedidoDetalhar(this, selecionar());
        this.back();
    }
    public void finalizar(){PedidoFinalizar pedFinalizar = new PedidoFinalizar(selecionar(), this);}
    public void remover(){PedidoDeletar pedDeletar = new PedidoDeletar(selecionar(), this);}
    public void preparar(){PedidoPreparar pedPreparar = new PedidoPreparar(selecionar(), this);}
    public void pedidoCriar(){PedidoCriar pedCriar = new PedidoCriar(this);}

    public void criarDLM(){
        List<Pedido> pedidos = new ArrayList<Pedido>();
        pedidos = PedidoDAO.selectAll();
        DLM = new DefaultListModel<>();
        for(int i=0; i<pedidos.size(); i++){
            String linha = (pedidos.get(i).getCod_ped()+" - Cliente: "+pedidos.get(i).getCod_cli()+" - Entregador: "+pedidos.get(i).getCod_ent()+" - "+pedidos.get(i).getValor()+" - "+pedidos.get(i).getEstado()+" - "+pedidos.get(i).getObsevacao());
            DLM.addElement(linha);
        }
    }
    public void preencherLista(){lista.setModel(DLM);}
    public void DLMfiltro(){
        if(boxFiltro.getSelectedItem()=="Todos"){
            auxFiltrar=0;
            criarDLM();
            preencherLista();
        } else if (boxFiltro.getSelectedItem()=="Preparando") {
            auxFiltrar=1;
            dlmPreparando();
            preencherLista();
        } else if (boxFiltro.getSelectedItem()=="Pronto") {
            auxFiltrar=2;
            dlmPronto();
            preencherLista();
        } else if (boxFiltro.getSelectedItem()=="Entregando") {
            auxFiltrar=3;
            dlmEntregando();
            preencherLista();
        } else if (boxFiltro.getSelectedItem()=="Finalizado") {
            auxFiltrar=4;
            dlmFinalizado();
            preencherLista();
        }
    }
    public void back(){
        if(auxFiltrar==0){
            criarDLM();
        } else if (auxFiltrar==1) {
            dlmPreparando();
        }else if (auxFiltrar==2) {
            dlmPronto();
        }else if (auxFiltrar==3) {
            dlmEntregando();
        }
        else if (auxFiltrar==4) {
            dlmFinalizado();
        } else{
            criarDLM();
        }
        preencherLista();
    }
    public void dlmPreparando(){
        List<Pedido> pedidos = new ArrayList<Pedido>();
        pedidos = PedidoDAO.selectPreparando();
        DLM = new DefaultListModel<>();
        for(int i=0; i<pedidos.size(); i++){
            String linha = (pedidos.get(i).getCod_ped()+" - Cliente: "+pedidos.get(i).getCod_cli()+" - Entregador: "+pedidos.get(i).getCod_ent()+" - "+pedidos.get(i).getValor()+" - "+pedidos.get(i).getEstado()+" - "+pedidos.get(i).getObsevacao());
            DLM.addElement(linha);
        }
    }
    public void dlmPronto(){
        List<Pedido> pedidos = new ArrayList<Pedido>();
        pedidos = PedidoDAO.selectPronto();
        DLM = new DefaultListModel<>();
        for(int i=0; i<pedidos.size(); i++){
            String linha = (pedidos.get(i).getCod_ped()+" - Cliente: "+pedidos.get(i).getCod_cli()+" - Entregador: "+pedidos.get(i).getCod_ent()+" - "+pedidos.get(i).getValor()+" - "+pedidos.get(i).getEstado()+" - "+pedidos.get(i).getObsevacao());
            DLM.addElement(linha);
        }
    }
    public void dlmEntregando(){
        List<Pedido> pedidos = new ArrayList<Pedido>();
        pedidos = PedidoDAO.selectEntregando();
        DLM = new DefaultListModel<>();
        for(int i=0; i<pedidos.size(); i++){
            String linha = (pedidos.get(i).getCod_ped()+" - Cliente: "+pedidos.get(i).getCod_cli()+" - Entregador: "+pedidos.get(i).getCod_ent()+" - "+pedidos.get(i).getValor()+" - "+pedidos.get(i).getEstado()+" - "+pedidos.get(i).getObsevacao());
            DLM.addElement(linha);
        }
    }
    public void dlmFinalizado(){
        List<Pedido> pedidos = new ArrayList<Pedido>();
        pedidos = PedidoDAO.selectFinalizado();
        DLM = new DefaultListModel<>();
        for(int i=0; i<pedidos.size(); i++){
            String linha = (pedidos.get(i).getCod_ped()+" - Cliente: "+pedidos.get(i).getCod_cli()+" - Entregador: "+pedidos.get(i).getCod_ent()+" - "+pedidos.get(i).getValor()+" - "+pedidos.get(i).getEstado()+" - "+pedidos.get(i).getObsevacao());
            DLM.addElement(linha);
        }
    }
}
