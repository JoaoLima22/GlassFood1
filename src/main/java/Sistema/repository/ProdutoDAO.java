package Sistema.repository;

import Sistema.conn.ConnectionFactory;
import Sistema.models.Cliente;
import Sistema.models.Lanche;
import Sistema.models.Pizza;
import Sistema.models.Produto;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public static void insert(Produto produto) throws SQLException {
        String sql = "INSERT INTO `glass_food`.`Produto` (`nome`, `valor`, `descricao`, `tipo`) VALUES (?, ?, ?, ?);";

        Connection conn = ConnectionFactory.getConecction();
        try {

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getValor());
            stmt.setString(3, produto.getDescricao());
            stmt.setString(4, produto.getTipo());
            stmt.execute();
            stmt.close();
            //JOptionPane.showMessageDialog(null, "USUARIO " + nome + "CADASTRADO COM SUCESSO!");
        }
        catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public static void delete(int id){

        String sql = "DELETE FROM `glass_food`.`produto` WHERE (`cod_prod` = '%d');".formatted(id);

        try(Connection conn = ConnectionFactory.getConecction();
            Statement stmt = conn.createStatement()){
            int rowsAfected = stmt.executeUpdate(sql);
            //System.out.println(rowsAfected);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void update(Produto produto) throws SQLException {
        String sql = "UPDATE `glass_food`.`Produto` SET `nome` = ?, `valor` = ?, `descricao` = ?, `tipo` = ? WHERE (`cod_prod` = ?);";

        Connection conn = ConnectionFactory.getConecction();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getValor());
            stmt.setString(3, produto.getDescricao());
            stmt.setString(4, produto.getTipo());
            stmt.setInt(5, produto.getCod_prod());
            stmt.execute();
            stmt.close();
            //JOptionPane.showMessageDialog(null, "USUARIO " + nome + "CADASTRADO COM SUCESSO!");
        }
        catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public static List<Produto> selectAll(){

        String sql = "SELECT * FROM `glass_food`.`Produto`;";
        List<Produto> produtos = new ArrayList<Produto>();

        try(Connection conn = ConnectionFactory.getConecction();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            //stmt.executeUpdate(sql);
            while ((rs.next())){
                Produto produto = new Produto();
                produto.setCod_prod(rs.getInt("cod_prod"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getDouble("valor"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setTipo(rs.getString("tipo"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return produtos;
    }
    public static List<Produto> selectPizzas(){

        String sql = "SELECT * FROM `glass_food`.`Produto` WHERE (`tipo` = 'Pizza');";
        List<Produto> produtos = new ArrayList<Produto>();

        try(Connection conn = ConnectionFactory.getConecction();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            //stmt.executeUpdate(sql);
            while ((rs.next())){
                Produto produto = new Produto();
                produto.setCod_prod(rs.getInt("cod_prod"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getDouble("valor"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setTipo(rs.getString("tipo"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return produtos;
    }
    public static List<Produto> selectLanches(){

        String sql = "SELECT * FROM `glass_food`.`Produto` WHERE (`tipo` = 'Lanche');";
        List<Produto> produtos = new ArrayList<Produto>();

        try(Connection conn = ConnectionFactory.getConecction();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            //stmt.executeUpdate(sql);
            while ((rs.next())){
                Produto produto = new Produto();
                produto.setCod_prod(rs.getInt("cod_prod"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getDouble("valor"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setTipo(rs.getString("tipo"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return produtos;
    }
    public static List<Produto> selectBebidas(){

        String sql = "SELECT * FROM `glass_food`.`Produto` WHERE (`tipo` = 'Bebida');";
        List<Produto> produtos = new ArrayList<Produto>();

        try(Connection conn = ConnectionFactory.getConecction();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            //stmt.executeUpdate(sql);
            while ((rs.next())){
                Produto produto = new Produto();
                produto.setCod_prod(rs.getInt("cod_prod"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getDouble("valor"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setTipo(rs.getString("tipo"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return produtos;
    }
    public static void insertBebida(int id_ped, Produto produto) throws SQLException {
        String sql = "INSERT INTO `glass_food`.`Pedido_Bebida` (`cod_ped`, `cod_prod`, `nome`, `valor`, `descricao`, `tipo`) VALUES (?, ?, ?, ?, ?, ?);";

        Connection conn = ConnectionFactory.getConecction();
        try {

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_ped);
            stmt.setInt(2, produto.getCod_prod());
            stmt.setString(3, produto.getNome());
            stmt.setDouble(4, produto.getValor());
            stmt.setString(5, produto.getDescricao());
            stmt.setString(6, produto.getTipo());
            stmt.execute();
            stmt.close();
            //JOptionPane.showMessageDialog(null, "USUARIO " + nome + "CADASTRADO COM SUCESSO!");
        }
        catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }
    public static List<Produto> selectBebidas(int id){

        String sql = "SELECT * FROM `glass_food`.`Pedido_Bebida` WHERE (`cod_ped` = '%d');".formatted(id);
        List<Produto> produtos = new ArrayList<Produto>();

        try(Connection conn = ConnectionFactory.getConecction();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            //stmt.executeUpdate(sql);
            while ((rs.next())){
                Produto produto = new Produto();
                produto.setCod_prod(rs.getInt("cod_prod"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getDouble("valor"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setTipo(rs.getString("tipo"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return produtos;
    }
    public static void deleteProd(int id){
        String sql = "DELETE FROM `glass_food`.`Pedido_Bebida` WHERE (`cod_ped` = '%d');".formatted(id);

        try(Connection conn = ConnectionFactory.getConecction();
            Statement stmt = conn.createStatement()){
            int rowsAfected = stmt.executeUpdate(sql);
            //System.out.println(rowsAfected);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static int countByPed(int id){
        int cont=0;

        String sql = "SELECT * FROM `glass_food`.`Pedido_Bebida` WHERE (`cod_prod` = '%d');".formatted(id);
        try(Connection conn = ConnectionFactory.getConecction();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            //stmt.executeUpdate(sql);
            while ((rs.next())){
                cont++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        sql = "SELECT * FROM `glass_food`.`Pedido_Pizza` WHERE (`cod_prod` = '%d');".formatted(id);
        List<Pizza> pizzas = new ArrayList<Pizza>();

        try(Connection conn = ConnectionFactory.getConecction();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            //stmt.executeUpdate(sql);
            while ((rs.next())){
                cont++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        sql = "SELECT * FROM `glass_food`.`Pedido_Lanche` WHERE (`cod_prod` = '%d');".formatted(id);
        List<Lanche> lanches = new ArrayList<Lanche>();

        try(Connection conn = ConnectionFactory.getConecction();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            //stmt.executeUpdate(sql);
            while ((rs.next())){
                cont++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cont;
    }

}
