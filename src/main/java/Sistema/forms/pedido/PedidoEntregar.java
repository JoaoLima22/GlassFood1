package Sistema.forms.pedido;

import Sistema.models.Entregador;
import Sistema.models.Pedido;
import Sistema.repository.EntregadorDAO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PedidoEntregar extends JFrame{
    private JPanel pedidoEntregar;
    private JButton confirmarButton;
    private JButton cancelarButton;
    private JList lista;
    private JLabel labelAviso;
    private DefaultListModel DLM;
    private int indice;

    public PedidoEntregar(PedidoForm pedidoForm, Pedido pedido){
        setContentPane(pedidoEntregar); //define o frame
        setTitle("Glass Food");     // define o título da pag
        setSize(700, 700); // define o tamanho da pag
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE); // fecha a aplicação
        ImageIcon img = new ImageIcon("C:\\Users\\João Vitor\\eclipse-workspace\\GlassFood1\\src\\main\\resources\\icon.png");
        setIconImage(img.getImage());
        confirmarButton.setEnabled(false);
        verificacao(pedido);
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
                confirmar(pedidoForm, pedido);
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hide();
                pedidoForm.back();
            }
        });
    }
    public void confirmar(PedidoForm pedidoForm, Pedido pedido){
        String linhaClique = (String) DLM.get(indice);
        List<Entregador> entregadores = new ArrayList<Entregador>();
        entregadores = EntregadorDAO.selectAll();
        for(int i=0; i<entregadores.size(); i++){
            String linha = (entregadores.get(i).getCod_entregador()+" - "+entregadores.get(i).getNome()+" - "+entregadores.get(i).getTelefone()+" - "+entregadores.get(i).getVeiculo()+" - "+entregadores.get(i).getOcupado());
            if (linha.equals(linhaClique)) {
                pedido.entregar(entregadores.get(i));
                pedidoForm.back();
                hide();
            }
        }
    }
    public void criarDLM(){
        int cont=0;
        DLM = new DefaultListModel<>();
        List<Entregador> entregadores = new ArrayList<Entregador>();
        entregadores = EntregadorDAO.selectAll();
        for(int i=0; i<entregadores.size(); i++){
            String linha = (entregadores.get(i).getCod_entregador()+" - "+entregadores.get(i).getNome()+" - "+entregadores.get(i).getTelefone()+" - "+entregadores.get(i).getVeiculo()+" - "+entregadores.get(i).getOcupado());
            if(entregadores.get(i).getOcupado().equals("Ocupado")){

            } else {
                DLM.addElement(linha);
                cont++;
            }
        }
        if(cont==0){
            confirmarButton.setEnabled(false);
            labelAviso.setText("Impossivel entregar, nenhum entregador disponível!");
        }
    }
    public void preencherLista(){
        lista.setModel(DLM);
    }
    public void verificacao(Pedido pedido){
        if(pedido.getEstado().equals("Preparando")){
            labelAviso.setText("Impossivel entregar, pedido não preparado!");
        }else if(pedido.getEstado().equals("Pronto")){
            confirmarButton.setEnabled(true);
        }else if(pedido.getEstado().equals("Entregando")){
            labelAviso.setText("Impossivel entregar, pedido já sendo entregue!");
        }else if(pedido.getEstado().equals("Finalizado")){
            labelAviso.setText("Impossivel entregar, pedido já finalizado!");
        }
    }
}

