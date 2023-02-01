package Sistema.repository;

import Sistema.conn.ConnectionFactory;
import Sistema.models.Pedido;
import Sistema.models.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {
    public static void insert(Pedido pedido) throws SQLException {
        String sql = "INSERT INTO `glass_food`.`Pedido` (`cod_cli`, `cod_ent`, `valor`, `estado`, `observacao`) VALUES (?, ?, ?, ?, ?);";

        Connection conn = ConnectionFactory.getConecction();
        try {

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, pedido.getCod_cli());
            stmt.setInt(2, pedido.getCod_ent());
            stmt.setDouble(3, pedido.getValor());
            stmt.setString(4, pedido.getEstado());
            stmt.setString(5, pedido.getObsevacao());
            stmt.execute();
            stmt.close();
            //JOptionPane.showMessageDialog(null, "USUARIO " + nome + "CADASTRADO COM SUCESSO!");
        }
        catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }
    public static void delete(int id){
        String sql = "DELETE FROM `glass_food`.`pedido` WHERE (`cod_ped` = '%d');".formatted(id);
        try(Connection conn = ConnectionFactory.getConecction();
            Statement stmt = conn.createStatement()){
            int rowsAfected = stmt.executeUpdate(sql);
            //System.out.println(rowsAfected);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void update(Pedido pedido) throws SQLException {
        String sql = "UPDATE `glass_food`.`Pedido` SET `cod_cli` = ?, `cod_ent` = ?, `valor` = ?, `estado` = ?, `observacao` = ? WHERE (`cod_ped` = ?);";

        Connection conn = ConnectionFactory.getConecction();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, pedido.getCod_cli());
            stmt.setInt(2, pedido.getCod_ent());
            stmt.setDouble(3, pedido.getValor());
            stmt.setString(4, pedido.getEstado());
            stmt.setString(5, pedido.getObsevacao());
            stmt.setInt(6, pedido.getCod_ped());
            stmt.execute();
            stmt.close();
            //JOptionPane.showMessageDialog(null, "USUARIO " + nome + "CADASTRADO COM SUCESSO!");
        }
        catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }
    public static List<Pedido> selectAll(){

        String sql = "SELECT * FROM `glass_food`.`Pedido`;";
        List<Pedido> pedidos = new ArrayList<Pedido>();

        try(Connection conn = ConnectionFactory.getConecction();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            //stmt.executeUpdate(sql);
            while ((rs.next())){
                Pedido pedido = new Pedido();
                pedido.setCod_ped(rs.getInt("cod_ped"));
                pedido.setCod_cli(rs.getInt("cod_cli"));
                pedido.setCod_ent(rs.getInt("cod_ent"));
                pedido.setValor(rs.getDouble("valor"));
                pedido.setEstado(rs.getString("estado"));
                pedido.setObsevacao(rs.getString("observacao"));
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pedidos;
    }
    public static List<Pedido> selectPreparando(){

        String sql = "SELECT * FROM `glass_food`.`Pedido` WHERE (`estado` = 'Preparando');";
        List<Pedido> pedidos = new ArrayList<Pedido>();

        try(Connection conn = ConnectionFactory.getConecction();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            //stmt.executeUpdate(sql);
            while ((rs.next())){
                Pedido pedido = new Pedido();
                pedido.setCod_ped(rs.getInt("cod_ped"));
                pedido.setCod_cli(rs.getInt("cod_cli"));
                pedido.setCod_ent(rs.getInt("cod_ent"));
                pedido.setValor(rs.getDouble("valor"));
                pedido.setEstado(rs.getString("estado"));
                pedido.setObsevacao(rs.getString("observacao"));
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pedidos;
    }
    public static List<Pedido> selectPronto(){

        String sql = "SELECT * FROM `glass_food`.`Pedido` WHERE (`estado` = 'Pronto');";
        List<Pedido> pedidos = new ArrayList<Pedido>();

        try(Connection conn = ConnectionFactory.getConecction();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            //stmt.executeUpdate(sql);
            while ((rs.next())){
                Pedido pedido = new Pedido();
                pedido.setCod_ped(rs.getInt("cod_ped"));
                pedido.setCod_cli(rs.getInt("cod_cli"));
                pedido.setCod_ent(rs.getInt("cod_ent"));
                pedido.setValor(rs.getDouble("valor"));
                pedido.setEstado(rs.getString("estado"));
                pedido.setObsevacao(rs.getString("observacao"));
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pedidos;
    }
    public static List<Pedido> selectEntregando(){

        String sql = "SELECT * FROM `glass_food`.`Pedido` WHERE (`estado` = 'Entregando');";
        List<Pedido> pedidos = new ArrayList<Pedido>();

        try(Connection conn = ConnectionFactory.getConecction();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            //stmt.executeUpdate(sql);
            while ((rs.next())){
                Pedido pedido = new Pedido();
                pedido.setCod_ped(rs.getInt("cod_ped"));
                pedido.setCod_cli(rs.getInt("cod_cli"));
                pedido.setCod_ent(rs.getInt("cod_ent"));
                pedido.setValor(rs.getDouble("valor"));
                pedido.setEstado(rs.getString("estado"));
                pedido.setObsevacao(rs.getString("observacao"));
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pedidos;
    }
    public static List<Pedido> selectFinalizado(){

        String sql = "SELECT * FROM `glass_food`.`Pedido` WHERE (`estado` = 'Finalizado');";
        List<Pedido> pedidos = new ArrayList<Pedido>();

        try(Connection conn = ConnectionFactory.getConecction();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            //stmt.executeUpdate(sql);
            while ((rs.next())){
                Pedido pedido = new Pedido();
                pedido.setCod_ped(rs.getInt("cod_ped"));
                pedido.setCod_cli(rs.getInt("cod_cli"));
                pedido.setCod_ent(rs.getInt("cod_ent"));
                pedido.setValor(rs.getDouble("valor"));
                pedido.setEstado(rs.getString("estado"));
                pedido.setObsevacao(rs.getString("observacao"));
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pedidos;
    }
}
