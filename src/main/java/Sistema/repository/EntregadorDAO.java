package Sistema.repository;

import Sistema.conn.ConnectionFactory;
import Sistema.models.Entregador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EntregadorDAO {

    public static void save(Entregador entregador){

        String sql = ("INSERT INTO `glass_food`.`Entregador` (`nome`, `telefone`, `veiculo`, `ocupado`) VALUES ('%s', '%s', '%s', '%s')").formatted(entregador.getNome(), entregador.getTelefone(), entregador.getVeiculo(), entregador.getOcupado());

        try(Connection conn = ConnectionFactory.getConecction();
            Statement stmt = conn.createStatement()){
            int rowsAfected = stmt.executeUpdate(sql);
            //System.out.println(rowsAfected);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void delete(int id){

        String sql = "DELETE FROM `glass_food`.`Entregador` WHERE (`cod_ent` = '%d');".formatted(id);

        try(Connection conn = ConnectionFactory.getConecction();
            Statement stmt = conn.createStatement()){
            int rowsAfected = stmt.executeUpdate(sql);
            //System.out.println(rowsAfected);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void update(Entregador entregador){

        String sql = ("UPDATE `glass_food`.`Entregador` SET `nome` = '%s', `telefone` = '%s', `veiculo` = '%s', `ocupado` = '%s' WHERE (`cod_ent` = '%s');").formatted(entregador.getNome(), entregador.getTelefone(), entregador.getVeiculo(), entregador.getOcupado(), entregador.getCod_entregador());

        try(Connection conn = ConnectionFactory.getConecction();
            Statement stmt = conn.createStatement()){
            int rowsAfected = stmt.executeUpdate(sql);
            //System.out.println(rowsAfected);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Entregador selectById(int id){

        String sql = "SELECT * FROM `glass_food`.`Entregador` WHERE (`cod_ent` = '%d');".formatted(id);
       Entregador entregador = new Entregador();

        try(Connection conn = ConnectionFactory.getConecction();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            //stmt.executeUpdate(sql);
            while ((rs.next())){
                entregador.setCod_entregador(rs.getInt("cod_ent"));
                entregador.setNome(rs.getString("nome"));
                entregador.setTelefone(rs.getString("telefone"));
                entregador.setVeiculo(rs.getString("veiculo"));
                entregador.setOcupado(rs.getString("ocupado"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entregador;
    }
    public static List<Entregador> selectAll(){

        String sql = "SELECT * FROM `glass_food`.`Entregador`;";
        List<Entregador> entregadores = new ArrayList<Entregador>();

        try(Connection conn = ConnectionFactory.getConecction();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            //stmt.executeUpdate(sql);
            while ((rs.next())){
                Entregador entregador = new Entregador();
                entregador.setCod_entregador(rs.getInt("cod_ent"));
                entregador.setNome(rs.getString("nome"));
                entregador.setTelefone(rs.getString("telefone"));
                entregador.setVeiculo(rs.getString("veiculo"));
                entregador.setOcupado(rs.getString("ocupado"));
                entregadores.add(entregador);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entregadores;
    }
    public static int countByPed(int id){
        String sql = "SELECT * FROM `glass_food`.`Pedido` WHERE (`cod_ent` = '%d');".formatted(id);
        int cont=0;

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
