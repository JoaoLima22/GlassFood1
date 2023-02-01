package Sistema.forms.pedido.criar;

import Sistema.forms.pedido.PedidoForm;
import Sistema.models.*;
import Sistema.repository.LancheDAO;
import Sistema.repository.PedidoDAO;
import Sistema.repository.PizzaDAO;
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

public class PedidoCriar2 extends JFrame{
    private JPanel painel;
    private JPanel panelBotons;
    private JList listaCar;
    private JList listaProd;
    private JButton adicionarButton;
    private JButton removerButton;
    private JButton confirmarButton;
    private JButton cancelarButton;
    private JComboBox boxFiltro;
    private JButton okButton;
    private JTextField observaçãotf;
    private JLabel txAviso;
    private Pedido pedido;
    private DefaultListModel dlmProdutos, dlmCarrinho;
    private int auxFiltrar=0, indiceCar, indiceProd;


    public PedidoCriar2(PedidoForm pedidoForm, Cliente cliente) throws SQLException {
        setContentPane(painel); //define o frame
        setTitle("Glass Food");     // define o título da pag
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); // fecha a aplicação
        setMinimumSize(new Dimension(1300, 700));
        ImageIcon img = new ImageIcon("C:\\Users\\João Vitor\\eclipse-workspace\\GlassFood1\\src\\main\\resources\\icon.png");
        setIconImage(img.getImage());
        start();
        //preencher as duas listas

        setVisible(true); //torna a página visivel
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DLMfiltro();
            }
        });
        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionar();
                refresh();
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hide();
                pedidoForm.back();
                pedido.deletar(pedido.getCod_ped());
                pedidoForm.back();
            }
        });
        listaCar.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                indiceCar=listaCar.getSelectedIndex();
                //System.out.println(indice);
            }
        });
        listaProd.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                indiceProd=listaProd.getSelectedIndex();
                //System.out.println(indice);
            }
        });
        removerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remover();
                refresh();
            }
        });
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmar(cliente, pedidoForm);

                pedidoForm.back();
            }
        });
    }
    public void adicionar(){
        String linhaClique = (String) dlmProdutos.get(indiceProd);
        List<Produto> produtos = new ArrayList<Produto>();
        produtos = ProdutoDAO.selectAll();
        dlmProdutos = new DefaultListModel<>();
        for(int i=0; i<produtos.size(); i++){
            String linha = (produtos.get(i).getCod_prod()+" - "+produtos.get(i).getNome()+" - "+produtos.get(i).getValor()+" - "+produtos.get(i).getDescricao()+" - "+produtos.get(i).getTipo());
            if(linha.equals(linhaClique)){
                //Verifica o tipo...
                if(produtos.get(i).getTipo().equals("Bebida")){
                    //PedidoDAO.insertBebida(pedido.getCod_ped(), produtos.get(i));
                    pedido.getBebidas().add(produtos.get(i));
                }else if(produtos.get(i).getTipo().equals("Lanche")){
                    PedidoCriarLanche pcL = new PedidoCriarLanche(produtos.get(i), this);
                } else if(produtos.get(i).getTipo().equals("Pizza")) {
                    PedidoCriarPizza pcP = new PedidoCriarPizza(produtos.get(i), this);
                }
            }
        }
        refresh();
    }
    public void remover(){
        String linhaClique = (String) dlmCarrinho.get(indiceCar);

        for (int i=0; i<pedido.getBebidas().size(); i++){
            String linha = (pedido.getBebidas().get(i).getCod_prod()+" - "+pedido.getBebidas().get(i).getNome()+" - "+pedido.getBebidas().get(i).getValor());
            if(linha.equals(linhaClique)){
                pedido.getBebidas().remove(i);
                break;
            }
        }
        for (int i=0; i<pedido.getPizzas().size(); i++){
            String linha = (pedido.getPizzas().get(i).getCod_prod()+" - "+pedido.getPizzas().get(i).getNome()+" - "+pedido.getPizzas().get(i).getValor()+" - "+pedido.getPizzas().get(i).getBorda());
            if(linha.equals(linhaClique)){
                pedido.getPizzas().remove(i);
                break;
            }
        }
        for (int i=0; i<pedido.getLanches().size(); i++){
            String linha = (pedido.getLanches().get(i).getCod_prod()+" - "+pedido.getLanches().get(i).getNome()+" - "+pedido.getLanches().get(i).getValor()+" - "+pedido.getLanches().get(i).getPontoCarne()+" - "+pedido.getLanches().get(i).getTipoPao());
            if(linha.equals(linhaClique)){
                pedido.getLanches().remove(i);
                break;
            }
        }

        refresh();
    }
    public void refresh(){
        dlmProdutos();
        DlmCarrinho();
        preencherProdutos();
        preencherCarrinho();
    }

    public void start() throws SQLException {
        pedido = new Pedido();
        pedido.setCod_cli(0);
        pedido.setCod_ent(0);
        pedido.setObsevacao("");
        pedido.setValor(0);
        pedido.setEstado("");
        PedidoDAO.insert(pedido);

        dlmProdutos();

        List<Pedido> pedidos = new ArrayList<Pedido>();
        pedidos = PedidoDAO.selectAll();
        pedido=pedidos.get(pedidos.size()-1);
        preencherProdutos();
        DlmCarrinho();
        preencherCarrinho();

    }
    public void addLanche(Lanche lanche){
        pedido.getLanches().add(lanche);
    }
    public void addPizza(Pizza pizza){
        pedido.getPizzas().add(pizza);
    }
    public void dlmProdutos(){
        List<Produto> produtos = new ArrayList<Produto>();
        produtos = ProdutoDAO.selectAll();
        dlmProdutos = new DefaultListModel<>();
        for(int i=0; i<produtos.size(); i++){
            String linha = (produtos.get(i).getCod_prod()+" - "+produtos.get(i).getNome()+" - "+produtos.get(i).getValor()+" - "+produtos.get(i).getDescricao()+" - "+produtos.get(i).getTipo());
            dlmProdutos.addElement(linha);
        }
    }
    public void DlmCarrinho(){
        dlmCarrinho = new DefaultListModel<>();
        for (int i=0; i<pedido.getBebidas().size(); i++){
            String linha = (pedido.getBebidas().get(i).getCod_prod()+" - "+pedido.getBebidas().get(i).getNome()+" - "+pedido.getBebidas().get(i).getValor());
            dlmCarrinho.addElement(linha);
        }
        for (int i=0; i<pedido.getPizzas().size(); i++){
            String linha = (pedido.getPizzas().get(i).getCod_prod()+" - "+pedido.getPizzas().get(i).getNome()+" - "+pedido.getPizzas().get(i).getValor()+" - "+pedido.getPizzas().get(i).getBorda());
            dlmCarrinho.addElement(linha);
        }
        for (int i=0; i<pedido.getLanches().size(); i++){
            String linha = (pedido.getLanches().get(i).getCod_prod()+" - "+pedido.getLanches().get(i).getNome()+" - "+pedido.getLanches().get(i).getValor()+" - "+pedido.getLanches().get(i).getPontoCarne()+" - "+pedido.getLanches().get(i).getTipoPao());
            dlmCarrinho.addElement(linha);
        }
    }
    public void preencherProdutos(){
        listaProd.setModel(dlmProdutos);
    }
    public void preencherCarrinho(){
        listaCar.setModel(dlmCarrinho);
    }
    public void DLMfiltro(){
        if(boxFiltro.getSelectedItem()=="Todos"){
            auxFiltrar=0;
            dlmProdutos();
            preencherProdutos();
        } else if (boxFiltro.getSelectedItem()=="Pizzas") {
            auxFiltrar=1;
            DLMpizzas();
            preencherProdutos();
        } else if (boxFiltro.getSelectedItem()=="Lanches") {
            auxFiltrar=2;
            DLMLanches();
            preencherProdutos();
        } else if (boxFiltro.getSelectedItem()=="Bebidas") {
            auxFiltrar=3;
            DLMBebidas();
            preencherProdutos();
        }
    }
    public void DLMpizzas(){
        List<Produto> produtos = new ArrayList<Produto>();
        produtos = ProdutoDAO.selectPizzas();
        dlmProdutos = new DefaultListModel<>();
        for(int i=0; i<produtos.size(); i++){
            String linha = (produtos.get(i).getCod_prod()+" - "+produtos.get(i).getNome()+" - "+produtos.get(i).getValor()+" - "+produtos.get(i).getDescricao()+" - "+produtos.get(i).getTipo());
            dlmProdutos.addElement(linha);
        }
    }
    public void DLMLanches(){
        List<Produto> produtos = new ArrayList<Produto>();
        produtos = ProdutoDAO.selectLanches();
        dlmProdutos = new DefaultListModel<>();
        for(int i=0; i<produtos.size(); i++){
            String linha = (produtos.get(i).getCod_prod()+" - "+produtos.get(i).getNome()+" - "+produtos.get(i).getValor()+" - "+produtos.get(i).getDescricao()+" - "+produtos.get(i).getTipo());
            dlmProdutos.addElement(linha);
        }
    }
    public void DLMBebidas(){
        List<Produto> produtos = new ArrayList<Produto>();
        produtos = ProdutoDAO.selectBebidas();
        dlmProdutos = new DefaultListModel<>();
        for(int i=0; i<produtos.size(); i++){
            String linha = (produtos.get(i).getCod_prod()+" - "+produtos.get(i).getNome()+" - "+produtos.get(i).getValor()+" - "+produtos.get(i).getDescricao()+" - "+produtos.get(i).getTipo());
            dlmProdutos.addElement(linha);
        }
    }
    public void confirmar(Cliente cliente, PedidoForm pedForm){

        int somaElementos = 0;
        somaElementos=(pedido.getBebidas().size()+pedido.getPizzas().size()+pedido.getLanches().size());
        if(somaElementos==0){
            txAviso.setText("Adicione ao menos um produto!");
        }else {
            double soma = 0;
            for (int i = 0; i < pedido.getBebidas().size(); i++) {
                try {
                    ProdutoDAO.insertBebida(pedido.getCod_ped(), pedido.getBebidas().get(i));
                    soma = soma + pedido.getBebidas().get(i).getValor();
                } catch (SQLException e) {
                    System.out.println("Erro na entrada de bebidas");
                    throw new RuntimeException(e);
                }
            }
            for (int i = 0; i < pedido.getPizzas().size(); i++) {
                try {
                    PizzaDAO.insertPizza(pedido.getCod_ped(), pedido.getPizzas().get(i));
                    soma = soma + pedido.getPizzas().get(i).getValor();
                } catch (SQLException e) {
                    System.out.println("Erro na entrada de pizzas");
                    throw new RuntimeException(e);
                }
            }
            for (int i = 0; i < pedido.getLanches().size(); i++) {
                try {
                    LancheDAO.insertLanche(pedido.getCod_ped(), pedido.getLanches().get(i));
                    soma = soma + pedido.getLanches().get(i).getValor();
                } catch (SQLException e) {
                    System.out.println("Erro na entrada de lanches");
                    throw new RuntimeException(e);
                }
            }
            String observacao = observaçãotf.getText();
            pedido.adicionar(pedido.getCod_ped(), cliente.getCod_cli(), soma, observacao);
            try {
                PedidoDAO.update(pedido);
            } catch (SQLException e) {
                System.out.println("Erro no update");
                throw new RuntimeException(e);
            }
            pedForm.back();
            hide();
        }
    }
}
