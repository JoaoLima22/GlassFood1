package Sistema.models;

import Sistema.repository.PizzaDAO;

public class Pizza extends  Produto{
    private String borda;

    public String getBorda() {
        return borda;
    }

    public void setBorda(String borda) {
        this.borda = borda;
    }

    public void cadastrar(Produto produto, String borda){
        setCod_prod(produto.getCod_prod());
        setNome(produto.getNome());
        setValor(produto.getValor());
        setDescricao(produto.getDescricao());
        setTipo(produto.getTipo());
        this.borda=borda;
    }
    @Override
    public void deletar(int id){
        PizzaDAO.deleteProd(id);
    }
}
