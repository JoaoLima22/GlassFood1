package Sistema.main;

import Sistema.conn.ConnectionFactory;
import Sistema.forms.Menu;
import Sistema.forms.cliente.ClienteForm;
import Sistema.models.Produto;
import Sistema.repository.ProdutoDAO;

import java.util.ArrayList;
import java.util.List;

public class Executavel {
    //public static Inicio inic = new Inicio();

    public static void main(String[] args) {
        //inic.setVisible(true);

        System.out.println("Equipe: João Vitor Lima, Mateus Couto e Vagner.");


        /* Set the Nimbus look and feel */

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConnectionFactory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConnectionFactory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConnectionFactory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConnectionFactory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }


        //</editor-fold>

        Menu menu = new Menu();
    }
}
