package Sistema.repository;

import Sistema.conn.ConnectionFactory;
import Sistema.models.Cliente;
import Sistema.models.Pedido;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {


    public static void save(Cliente cliente){

        String sql = ("INSERT INTO `glass_food`.`Cliente` (`nome`, `telefone`, `rua`, `bairro`, `numero`)" +
                " VALUES ('%s', '%s', '%s', '%s', '%s');").formatted(cliente.getNome(), cliente.getTelefone(),
                cliente.getRua(), cliente.getBairro(), cliente.getNumero());

        try(Connection conn = ConnectionFactory.getConecction();
            Statement stmt = conn.createStatement()){
            int rowsAfected = stmt.executeUpdate(sql);
            //System.out.println(rowsAfected);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void delete(int id){

        String sql = "DELETE FROM `glass_food`.`Cliente` WHERE (`cod_cli` = '%d');".formatted(id);

        try(Connection conn = ConnectionFactory.getConecction();
            Statement stmt = conn.createStatement()){
            int rowsAfected = stmt.executeUpdate(sql);
            //System.out.println(rowsAfected);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void update(Cliente cliente){

        String sql = ("UPDATE `glass_food`.`Cliente` SET `nome` = '%s'," +
                " `telefone` = '%s', `rua` = '%s', `bairro` = '%s', `numero` =" +
                " '%s' WHERE (`cod_cli` = '%d');").formatted(cliente.getNome(), cliente.getTelefone(),
                cliente.getRua(), cliente.getBairro(), cliente.getNumero(), cliente.getCod_cli());

        try(Connection conn = ConnectionFactory.getConecction();
            Statement stmt = conn.createStatement()){
            int rowsAfected = stmt.executeUpdate(sql);
            System.out.println(rowsAfected);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Cliente selectById(int id){

        String sql = "SELECT * FROM `glass_food`.`Cliente` WHERE (`cod_cli` = '%d');".formatted(id);
        Cliente cliente = new Cliente();

        try(Connection conn = ConnectionFactory.getConecction();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            //stmt.executeUpdate(sql);
            while ((rs.next())){
                cliente.setCod_cli(rs.getInt("cod_cli"));
                cliente.setNome(rs.getString("nome"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setRua(rs.getString("rua"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setNumero(rs.getString("numero"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }
    public static List<Cliente> selectAll(){

        String sql = "SELECT * FROM `glass_food`.`Cliente`;";
        List<Cliente> clientes = new ArrayList<Cliente>();

        try(Connection conn = ConnectionFactory.getConecction();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            //stmt.executeUpdate(sql);
            while ((rs.next())){
                Cliente cliente = new Cliente();
                cliente.setCod_cli(rs.getInt("cod_cli"));
                cliente.setNome(rs.getString("nome"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setRua(rs.getString("rua"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setNumero(rs.getString("numero"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clientes;
    }
    public static int countByPed(int id){
        String sql = "SELECT * FROM `glass_food`.`Pedido` WHERE (`cod_cli` = '%d');".formatted(id);
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
