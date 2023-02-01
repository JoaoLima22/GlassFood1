package Sistema.repository;

import Sistema.conn.ConnectionFactory;
import Sistema.models.Lanche;
import Sistema.models.Pizza;
import Sistema.models.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PizzaDAO {
    public static void insertPizza(int id_ped, Pizza pizza) throws SQLException {
        String sql = "INSERT INTO `glass_food`.`Pedido_Pizza` (`cod_ped`, `cod_prod`, `nome`, `valor`, `descricao`, `tipo`, `borda`) VALUES (?, ?, ?, ?, ?, ?, ?);";

        Connection conn = ConnectionFactory.getConecction();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_ped);
            stmt.setInt(2, pizza.getCod_prod());
            stmt.setString(3, pizza.getNome());
            stmt.setDouble(4, pizza.getValor());
            stmt.setString(5, pizza.getDescricao());
            stmt.setString(6, pizza.getTipo());
            stmt.setString(7, pizza.getBorda());

            stmt.execute();
            stmt.close();
            //JOptionPane.showMessageDialog(null, "USUARIO " + nome + "CADASTRADO COM SUCESSO!");
        }
        catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }
    public static List<Pizza> selectPizzas(int id){

        String sql = "SELECT * FROM `glass_food`.`Pedido_Pizza` WHERE (`cod_ped` = '%d');".formatted(id);
        List<Pizza> pizzas = new ArrayList<Pizza>();

        try(Connection conn = ConnectionFactory.getConecction();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            //stmt.executeUpdate(sql);
            while ((rs.next())){
                Pizza pizza = new Pizza();
                pizza.setCod_prod(rs.getInt("cod_prod"));
                pizza.setNome(rs.getString("nome"));
                pizza.setValor(rs.getDouble("valor"));
                pizza.setDescricao(rs.getString("descricao"));
                pizza.setTipo(rs.getString("tipo"));
                pizza.setBorda(rs.getString("borda"));
                pizzas.add(pizza);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pizzas;
    }
    public static void deleteProd(int id){
        String sql = "DELETE FROM `glass_food`.`Pedido_Pizza` WHERE (`cod_ped` = '%d');".formatted(id);
        try(Connection conn = ConnectionFactory.getConecction();
            Statement stmt = conn.createStatement()){
            int rowsAfected = stmt.executeUpdate(sql);
            //System.out.println(rowsAfected);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
