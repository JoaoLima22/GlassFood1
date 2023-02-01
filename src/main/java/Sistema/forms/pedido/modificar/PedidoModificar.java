package Sistema.forms.pedido.modificar;

import Sistema.forms.pedido.PedidoForm;
import Sistema.forms.pedido.criar.PedidoCriarLanche;
import Sistema.forms.pedido.criar.PedidoCriarPizza;
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

public class PedidoModificar extends JFrame{
    private JPanel painel;
    private JPanel panelBotons;
    private JList listaProd;
    private JList listaCar;
    private JComboBox boxFiltro;
    private JButton okButton;
    private JButton adicionarButton;
    private JButton removerButton;
    private JLabel txAviso;
    private JButton confirmarButton;
    private JButton cancelarButton;
    private JTextField observaçãotf;
    private JButton clienteBtn;
    private DefaultListModel dlmProdutos, dlmCarrinho;
    private int auxFiltrar=0, indiceCar, indiceProd;


    public PedidoModificar(PedidoForm pedidoForm, Pedido pedido) throws SQLException {
        setContentPane(painel); //define o frame
        setTitle("Glass Food");     // define o título da pag
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); // fecha a aplicação
        //setPreferredSize(new Dimension(400, 400));
        //setResizable(false); //impede que modifiquem o tamanho da pagina
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(1300, 700));
        ImageIcon img = new ImageIcon("C:\\Users\\João Vitor\\eclipse-workspace\\GlassFood1\\src\\main\\resources\\icon.png");
        setIconImage(img.getImage());
        start(pedido);
        observaçãotf.setText(pedido.getObsevacao());
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
                adicionar(pedido);
                refresh(pedido);
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hide();
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
                remover(pedido);
                refresh(pedido);
            }
        });
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmar(pedido);

                pedidoForm.back();
            }
        });
        clienteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PedidoModificarCliente pmc = new PedidoModificarCliente(pedido);
            }
        });
    }
    public void adicionar(Pedido pedido){
        String linhaClique = (String) dlmProdutos.get(indiceProd);
        java.util.List<Produto> produtos = new ArrayList<Produto>();
        produtos = ProdutoDAO.selectAll();
        dlmProdutos = new DefaultListModel<>();
        for(int i=0; i<produtos.size(); i++){
            String linha = (produtos.get(i).getCod_prod()+" - "+produtos.get(i).getNome()+" - "+produtos.get(i).getValor()+" - "+produtos.get(i).getDescricao()+" - "+produtos.get(i).getTipo());
            if(linha.equals(linhaClique)){
                //Verifica o tipo...
                if(produtos.get(i).getTipo().equals("Bebida")){
                    pedido.getBebidas().add(produtos.get(i));
                }else if(produtos.get(i).getTipo().equals("Lanche")){
                    PedidoModificarLanche pml = new PedidoModificarLanche(produtos.get(i), this, pedido);
                } else if(produtos.get(i).getTipo().equals("Pizza")) {
                    PedidoModificarPizza pmp = new PedidoModificarPizza(produtos.get(i), this, pedido);                }
            }
        }
        refresh(pedido);
    }
    public void remover(Pedido pedido){
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

        refresh(pedido);
    }

    public void refresh(Pedido pedido){
        dlmProdutos();
        DlmCarrinho(pedido);
        preencherProdutos();
        preencherCarrinho();
    }

    public void start(Pedido pedido) throws SQLException {
        pedido.pegarProdutos();
        dlmProdutos();
        preencherProdutos();
        DlmCarrinho(pedido);
        preencherCarrinho();
    }
    public void dlmProdutos(){
        java.util.List<Produto> produtos = new ArrayList<Produto>();
        produtos = ProdutoDAO.selectAll();
        dlmProdutos = new DefaultListModel<>();
        for(int i=0; i<produtos.size(); i++){
            String linha = (produtos.get(i).getCod_prod()+" - "+produtos.get(i).getNome()+" - "+produtos.get(i).getValor()+" - "+produtos.get(i).getDescricao()+" - "+produtos.get(i).getTipo());
            dlmProdutos.addElement(linha);
        }
    }
    public void DlmCarrinho(Pedido pedido){
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
        java.util.List<Produto> produtos = new ArrayList<Produto>();
        produtos = ProdutoDAO.selectPizzas();
        dlmProdutos = new DefaultListModel<>();
        for(int i=0; i<produtos.size(); i++){
            String linha = (produtos.get(i).getCod_prod()+" - "+produtos.get(i).getNome()+" - "+produtos.get(i).getValor()+" - "+produtos.get(i).getDescricao()+" - "+produtos.get(i).getTipo());
            dlmProdutos.addElement(linha);
        }
    }
    public void DLMLanches(){
        java.util.List<Produto> produtos = new ArrayList<Produto>();
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
    public void confirmar(Pedido pedido){
        int somaElementos = 0;
        somaElementos=(pedido.getBebidas().size()+pedido.getPizzas().size()+pedido.getLanches().size());
        if(somaElementos==0){
            txAviso.setText("Adicione ao menos um produto!");
        }else{
            ProdutoDAO.deleteProd(pedido.getCod_ped());
            PizzaDAO.deleteProd(pedido.getCod_ped());
            LancheDAO.deleteProd(pedido.getCod_ped());
            double soma=0;
            for (int i=0; i<pedido.getBebidas().size(); i++){
                try {
                    ProdutoDAO.insertBebida(pedido.getCod_ped(),pedido.getBebidas().get(i));
                    soma=soma+pedido.getBebidas().get(i).getValor();
                } catch (SQLException e) {
                    System.out.println("Erro na entrada de bebidas");
                    throw new RuntimeException(e);
                }
            }
            for (int i=0; i<pedido.getPizzas().size(); i++){
                try {
                    PizzaDAO.insertPizza(pedido.getCod_ped(), pedido.getPizzas().get(i));
                    soma=soma+pedido.getPizzas().get(i).getValor();
                } catch (SQLException e) {
                    System.out.println("Erro na entrada de pizzas");
                    throw new RuntimeException(e);
                }
            }
            for (int i=0; i<pedido.getLanches().size(); i++){
                try {
                    LancheDAO.insertLanche(pedido.getCod_ped(), pedido.getLanches().get(i));
                    soma=soma+pedido.getLanches().get(i).getValor();
                } catch (SQLException e) {
                    System.out.println("Erro na entrada de lanches");
                    throw new RuntimeException(e);
                }
            }
            String observacao = observaçãotf.getText();
            pedido.modificar(pedido.getCod_ped(), pedido.getCod_cli(), soma, observacao);
            try {
                PedidoDAO.update(pedido);
            } catch (SQLException e) {
                System.out.println("Erro no update");
                throw new RuntimeException(e);
            }
            hide();
        }

    }
}
