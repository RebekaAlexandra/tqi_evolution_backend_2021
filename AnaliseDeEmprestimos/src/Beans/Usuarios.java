package Beans;

public class Usuarios
{
    private String nome;
    private String cpf;
    private String rg;
    private String endereco;
    private double renda;
    private String email;
    private String senha;

    public Usuarios() {}

    public Usuarios(String nome, String cpf, String rg, String endereco,
                    double renda, String email, String senha)
    {
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.endereco = endereco;
        this.renda = renda;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getCpf() {return cpf;}

    public void setCpf(String cpf) {this.cpf = cpf;}

    public String getRg() {return rg;}

    public void setRg(String rg) {this.rg = rg;}

    public String getEndereco() {return endereco;}

    public void setEndereco(String endereco) {this.endereco = endereco;}

    public double getRenda() {return renda;}

    public void setRenda(double renda) {this.renda = renda;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getSenha() {return senha;}

    public void setSenha(String senha) {this.senha = senha;}
}
