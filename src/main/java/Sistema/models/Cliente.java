package Sistema.models;

import Sistema.repository.ClienteDAO;

public class Cliente extends Pessoa{
    private int cod_cli;
    private String rua;
    private String bairro;
    private String numero;

    public void cadastrar(String nome, String telef, String rua, String bairro, String num){
        setNome(nome);
        setTelefone(telef);
        setRua(rua);
        setBairro(bairro);
        setNumero(num);
        ClienteDAO.save(this);
    }

    public void modificar(String nome, String telefone, String rua, String bairro, String numero){
        this.setNome(nome);
        this.setTelefone(telefone);
        this.setRua(rua);
        this.setBairro(bairro);
        this.setNumero(numero);
        ClienteDAO.update(this);
    }
    @Override
    public void deletar(){ClienteDAO.delete(getCod_cli());}
    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getCod_cli() {
        return cod_cli;
    }

    public void setCod_cli(int cod_cli) {
        this.cod_cli = cod_cli;
    }
}
