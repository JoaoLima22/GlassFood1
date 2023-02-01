package Sistema.forms.pedido.criar;

import Sistema.forms.pedido.PedidoForm;
import Sistema.models.Cliente;
import Sistema.repository.ClienteDAO;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoCriar extends JFrame{
    private JPanel pedidoCriar;
    private JButton confirmarButton;
    private JButton cancelarButton;
    private JList lista;
    private JLabel labelAviso;
    private DefaultListModel DLM;
    private int indice;

    public PedidoCriar(PedidoForm pedidoForm){
        setContentPane(pedidoCriar); //define o frame
        setTitle("Glass Food");     // define o título da pag
        setSize(700, 700); // define o tamanho da pag
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE); // fecha a aplicação
        ImageIcon img = new ImageIcon("C:\\Users\\João Vitor\\eclipse-workspace\\GlassFood1\\src\\main\\resources\\icon.png");
        setIconImage(img.getImage());
        criarDLM();
        preencherLista();
        setVisible(true); //torna a página visivel
        setResizable(false); //impede que modifiquem o tamanho da pagina

        lista.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                indice=lista.getSelectedIndex();
            }
        });
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmar(pedidoForm);
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hide();
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hide();
            }
        });
    }
    public void confirmar(PedidoForm pedidoForm){
        String linhaClique = (String) DLM.get(indice);
        List<Cliente> clientes = new ArrayList<Cliente>();
        clientes = ClienteDAO.selectAll();
        for(int i=0; i<clientes.size(); i++){
            String linha = (clientes.get(i).getCod_cli()+" - "+clientes.get(i).getNome()+" - "+clientes.get(i).getTelefone()+" - "+clientes.get(i).getRua()+" - "+clientes.get(i).getBairro()+" - "+clientes.get(i).getNumero());
            if (linha.equals(linhaClique)) {
                //Passo
                try {
                    PedidoCriar2 pedidoCriar2 = new PedidoCriar2(pedidoForm, clientes.get(i));
                    hide();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public void criarDLM(){
        int cont=0;
        List<Cliente> clientes = new ArrayList<Cliente>();
        clientes = ClienteDAO.selectAll();
        DLM = new DefaultListModel<>();
        for(int i=0; i<clientes.size(); i++){
            String linha = (clientes.get(i).getCod_cli()+" - "+clientes.get(i).getNome()+" - "+clientes.get(i).getTelefone()+" - "+clientes.get(i).getRua()+" - "+clientes.get(i).getBairro()+" - "+clientes.get(i).getNumero());
            DLM.addElement(linha);
            cont++;
        }
        if(cont==0){
            confirmarButton.setEnabled(false);
            labelAviso.setText("Impossivel entregar, nenhum cliente disponível!");
        }
    }
    public void preencherLista(){
        lista.setModel(DLM);
        //JScrollPane sp = new JScrollPane(lista);
    }
}
