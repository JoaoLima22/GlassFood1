package Sistema.repository;

import Sistema.conn.ConnectionFactory;
import Sistema.models.Lanche;
import Sistema.models.Pizza;
import Sistema.models.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LancheDAO {
    public static void insertLanche(int id_ped, Lanche lanche) throws SQLException {
        String sql = "INSERT INTO `glass_food`.`Pedido_Lanche` (`cod_ped`, `cod_prod`, `nome`, `valor`, `descricao`, `tipo`, `tipo_pao`, `ponto_carne`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        Connection conn = ConnectionFactory.getConecction();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_ped);
            stmt.setInt(2, lanche.getCod_prod());
            stmt.setString(3, lanche.getNome());
            stmt.setDouble(4, lanche.getValor());
            stmt.setString(5, lanche.getDescricao());
            stmt.setString(6, lanche.getTipo());
            stmt.setString(7, lanche.getTipoPao());
            stmt.setString(8, lanche.getPontoCarne());
            stmt.execute();
            stmt.close();
            //JOptionPane.showMessageDialog(null, "USUARIO " + nome + "CADASTRADO COM SUCESSO!");
        }
        catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }
    public static List<Lanche> selectLanches(int id){

        String sql = "SELECT * FROM `glass_food`.`Pedido_Lanche` WHERE (`cod_ped` = '%d');".formatted(id);
        List<Lanche> lanches = new ArrayList<Lanche>();

        try(Connection conn = ConnectionFactory.getConecction();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            //stmt.executeUpdate(sql);
            while ((rs.next())){
                Lanche lanche = new Lanche();
                lanche.setCod_prod(rs.getInt("cod_prod"));
                lanche.setNome(rs.getString("nome"));
                lanche.setValor(rs.getDouble("valor"));
                lanche.setDescricao(rs.getString("descricao"));
                lanche.setTipo(rs.getString("tipo"));
                lanche.setPontoCarne(rs.getString("ponto_carne"));
                lanche.setTipoPao(rs.getString("tipo_pao"));
                lanches.add(lanche);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lanches;
    }
    public static void deleteProd(int id){
        String sql = "DELETE FROM `glass_food`.`Pedido_Lanche` WHERE (`cod_ped` = '%d');".formatted(id);
        try(Connection conn = ConnectionFactory.getConecction();
            Statement stmt = conn.createStatement()){
            int rowsAfected = stmt.executeUpdate(sql);
            //System.out.println(rowsAfected);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
