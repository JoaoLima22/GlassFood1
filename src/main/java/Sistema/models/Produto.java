package Sistema.models;

import Sistema.repository.ProdutoDAO;

import java.sql.SQLException;

public class Produto {
    private int cod_prod;
    private String nome;
    private double valor;
    private String descricao;
    private String tipo;


    public void cadastrar(String nome, double valor, String descricao, String tipo) throws SQLException {
        this.nome=nome;
        this.valor=valor;
        this.descricao=descricao;
        this.tipo=tipo;
        ProdutoDAO.insert(this);
    }
    public void modificar(String nome, double valor, String descricao, String tipo) throws SQLException {
        this.nome=nome;
        this.valor=valor;
        this.descricao=descricao;
        this.tipo=tipo;
        ProdutoDAO.update(this);
    }
    public void deletar(int id){
        ProdutoDAO.delete(id);
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getCod_prod() {
        return cod_prod;
    }

    public void setCod_prod(int cod_cli) {
        this.cod_prod = cod_cli;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

