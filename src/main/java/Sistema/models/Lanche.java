package Sistema.models;

import Sistema.repository.LancheDAO;

public class Lanche extends Produto{
    private String tipoPao;
    private String pontoCarne;

    public void cadastrar(Produto produto, String pao, String carne){
        setCod_prod(produto.getCod_prod());
        setNome(produto.getNome());
        setValor(produto.getValor());
        setDescricao(produto.getDescricao());
        setTipo(produto.getTipo());
        this.tipoPao=pao;
        this.pontoCarne=carne;
    }
    @Override
    public void deletar(int id){
        LancheDAO.deleteProd(id);
    }

    public void setTipoPao(String tipoPao){this.tipoPao=tipoPao;}
    public String getTipoPao(){return this.tipoPao;}
    public void setPontoCarne(String pontoCarne){this.pontoCarne=pontoCarne;}
    public String getPontoCarne(){return this.pontoCarne;}
}
