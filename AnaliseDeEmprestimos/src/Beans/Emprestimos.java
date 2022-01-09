package Beans;

import java.util.Date;

public class Emprestimos
{
    private Double valor_emprestimo;
    private Date primeira_parcela;
    private int quantidade_parcelas;
    private Double renda;
    private String email;


    public Emprestimos() {}

    public Emprestimos(Double valor_emprestimo, Date primeira_parcela, int quantidade_parcelas, Double renda, String email)
    {
        this.valor_emprestimo = valor_emprestimo;
        this.primeira_parcela = primeira_parcela;
        this.quantidade_parcelas = quantidade_parcelas;
        this.renda = renda;
        this.email = email;
    }

    public Double getValor_emprestimo() {return valor_emprestimo;}

    public void setValor_emprestimo(Double valor_emprestimo) {this.valor_emprestimo = valor_emprestimo;}

    public Date getPrimeira_parcela() {return primeira_parcela;}

    public void setPrimeira_parcela(Date primeira_parcela) {this.primeira_parcela = primeira_parcela;}

    public int getQuantidade_parcelas() {return quantidade_parcelas;}

    public void setQuantidade_parcelas(int quantidade_parcelas) {this.quantidade_parcelas = quantidade_parcelas;}

    public Double getRenda() {return renda;}

    public void setRenda(Double renda) {this.renda = renda;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}
}
