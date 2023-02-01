package Sistema.models;

import Sistema.repository.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class Pedido {
    private int cod_ped;
    private int cod_cli;
    private int cod_ent;
    private double valor;
    private String estado;
    private String obsevacao;
    private ArrayList<Produto> bebidas = new ArrayList<Produto>();
    private ArrayList<Lanche> lanches = new ArrayList<Lanche>();
    private ArrayList<Pizza> pizzas = new ArrayList<Pizza>();

    public void adicionar(int cod_ped, int cod_cli, double valor, String obsevacao) {
        this.cod_ped = cod_ped;
        this.cod_cli = cod_cli;
        this.valor = valor;
        this.estado = "Preparando";
        this.obsevacao = obsevacao;
    }
    public void modificar(int cod_ped, int cod_cli, double valor, String obsevacao) {
        this.cod_ped = cod_ped;
        this.cod_cli = cod_cli;
        this.valor = valor;
        this.obsevacao = obsevacao;
    }
    public void deletar(int id){
        ProdutoDAO.deleteProd(getCod_ped());
        Pizza pizza = new Pizza();
        pizza.deletar(getCod_ped());
        Lanche lanche = new Lanche();
        lanche.deletar(getCod_ped());
        PedidoDAO.delete(id);
    }
    public void preparar(){
        this.setEstado("Pronto");
        try {
            PedidoDAO.update(this);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void entregar(Entregador entregador){
        this.setCod_ent(entregador.getCod_entregador());
        entregador.setOcupado("Ocupado");
        EntregadorDAO.update(entregador);
        this.setEstado("Entregando");
        try {
            PedidoDAO.update(this);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void finalizar(){
        this.setEstado("Finalizado");
        Entregador entregador = new Entregador();
        entregador= EntregadorDAO.selectById(this.getCod_ent());
        entregador.setOcupado("Disponivel");
        EntregadorDAO.update(entregador);
        try {
            PedidoDAO.update(this);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void pegarProdutos(){
        bebidas= (ArrayList<Produto>) ProdutoDAO.selectBebidas(getCod_ped());
        lanches= (ArrayList<Lanche>) LancheDAO.selectLanches(getCod_ped());
        pizzas= (ArrayList<Pizza>) PizzaDAO.selectPizzas(getCod_ped());
    }

    public int getCod_ped() {
        return cod_ped;
    }

    public void setCod_ped(int cod_ped) {
        this.cod_ped = cod_ped;
    }

    public int getCod_cli() {
        return cod_cli;
    }

    public void setCod_cli(int cod_cli) {
        this.cod_cli = cod_cli;
    }

    public int getCod_ent() {
        return cod_ent;
    }

    public void setCod_ent(int cod_ent) {
        this.cod_ent = cod_ent;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObsevacao() {
        return obsevacao;
    }

    public void setObsevacao(String obsevacao) {
        this.obsevacao = obsevacao;
    }

    public ArrayList<Produto> getBebidas() {
        return bebidas;
    }

    public void setBebidas(ArrayList<Produto> bebidas) {
        this.bebidas = bebidas;
    }

    public ArrayList<Lanche> getLanches() {
        return lanches;
    }

    public void setLanches(ArrayList<Lanche> lanches) {
        this.lanches = lanches;
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(ArrayList<Pizza> pizzas) {
        this.pizzas = pizzas;
    }
}
