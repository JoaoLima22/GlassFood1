package Sistema.models;

import Sistema.repository.EntregadorDAO;

public class Entregador extends Pessoa{

    private int cod_entregador;
    private String veiculo;
    private String ocupado;

    public void cadastrar(String nome, String telef, String veiculo){
        setNome(nome);
        setTelefone(telef);
        setVeiculo(veiculo);
        setOcupado("Disponivel");
        EntregadorDAO.save(this);
    }

    public void modificar(String nome, String telef, String veiculo){
        setNome(nome);
        setTelefone(telef);
        setVeiculo(veiculo);
        EntregadorDAO.update(this);
    }

    public void deletar(){EntregadorDAO.delete(getCod_entregador());}

    public void setCod_entregador(int cod_entregador) {
        this.cod_entregador = cod_entregador;
    }
    public int getCod_entregador(){
        return cod_entregador;
    }


    public void setVeiculo(String veiculo){
        this.veiculo = veiculo;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setOcupado(String ocupado) {
        this.ocupado = ocupado;
    }

    public String getOcupado() {
        return ocupado;
    }
}
