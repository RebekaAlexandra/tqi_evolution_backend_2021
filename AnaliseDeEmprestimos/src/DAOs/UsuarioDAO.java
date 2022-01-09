package DAOs;

import Beans.Usuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO
{
    private Connection con = null;

    public UsuarioDAO() {con = ConexaoBanco.getConnection();}

    public void AdicionarUsuario (Usuarios usuarios)
    {
        String sql = "INSERT INTO Usuarios (nome, cpf, rg, endereco, renda, email, senha) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try
        {
            con = ConexaoBanco.getConnection();

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, usuarios.getNome());
            stmt.setString(2, usuarios.getCpf());
            stmt.setString(3, usuarios.getRg());
            stmt.setString(4, usuarios.getEndereco());
            stmt.setDouble(5, usuarios.getRenda());
            stmt.setString(6, usuarios.getEmail());
            stmt.setString(7, usuarios.getSenha());

            stmt.execute();
            System.out.println(" ___________________________________ \n" +
                               "|   Login cadastrado com sucesso!   |\n" +
                               "|___________________________________|");
        } catch (SQLException ex)
        {
            System.out.println("Erro: " + ex);
        } finally
        {
            ConexaoBanco.closeConnection(con);
        }
    }

    public Usuarios acharUsuario(String email, String senha)
    {
        Usuarios u = new Usuarios();
        String sql = "SELECT * FROM Usuarios WHERE email = ? AND senha = ?";

        try
        {
            con = ConexaoBanco.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                //Chamo o Setters padrão de pessoa, e no parametro coloco o rs.getTipo("nome_da_coluna_igual_do_banco");
                u.setNome(rs.getString("nome"));
                u.setCpf(rs.getString("cpf"));
                u.setRg(rs.getString("rg"));
                u.setEndereco(rs.getString("endereco"));
                u.setRenda(rs.getDouble("renda"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
            }
            return u;
        } catch (SQLException ex){
            System.out.println("Erro: " + ex);
            return null;
        } finally {
            ConexaoBanco.closeConnection(con);
        }
    }

    public Usuarios ConsultaSenha(String email, String senha)
    {
        Usuarios u = new Usuarios();
        String sql = "SELECT * FROM Usuarios WHERE cpf = ?";

        try
        {
            con = ConexaoBanco.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                //Chamo o Setters padrão de pessoa, e no parametro coloco o rs.getTipo("nome_da_coluna_igual_do_banco");
                u.setNome(rs.getString("nome"));
                u.setCpf(rs.getString("cpf"));
                u.setRg(rs.getString("rg"));
                u.setEndereco(rs.getString("endereco"));
                u.setRenda(rs.getDouble("renda"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
            }
            return u;
        } catch (SQLException ex){
            System.out.println("Erro: " + ex);
            return null;
        } finally {
            ConexaoBanco.closeConnection(con);
        }
    }
}
