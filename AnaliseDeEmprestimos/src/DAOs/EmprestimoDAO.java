package DAOs;

import Beans.Emprestimos;
import Beans.Usuarios;

import java.sql.*;

public class EmprestimoDAO
{
    private Connection con = null;

    public EmprestimoDAO() {con = ConexaoBanco.getConnection();}

    public void SolicitarEmprestimo (Emprestimos emprestimos)
    {
        String sql = "INSERT INTO Emprestimos (id_emprestimos, valor_emprestimo, primeira_parcela," +
                                              "quantidade_parcelas, renda, fk_email) VALUES (DEFAULT, ?, ?, ?, ?, ?)";

        try
        {
            PreparedStatement stmt = con.prepareStatement(sql);

            java.util.Date utilDate = emprestimos.getPrimeira_parcela();
            java.sql.Date sqldate = new java.sql.Date(utilDate.getTime());

            stmt.setDouble(1, emprestimos.getValor_emprestimo());
            stmt.setDate(2, sqldate);
            stmt.setInt(3, emprestimos.getQuantidade_parcelas());
            stmt.setDouble(4, emprestimos.getRenda());
            stmt.setString(5, emprestimos.getEmail());

            stmt.execute();
            System.out.println(" ____________________________________ \n" +
                               "| Empréstimo solicitado com sucesso! |\n" +
                               "|____________________________________|");
        } catch (SQLException ex)
        {
            System.out.println("Erro: " + ex);
        } finally
        {
            ConexaoBanco.closeConnection(con);
        }
    }

    public void ListarEmprestimos(Usuarios usuario)
    {

        String sql = "SELECT id_emprestimos, valor_emprestimo, quantidade_parcelas FROM Emprestimos " +
                     "WHERE fk_email = ? ";

        try {
            con = ConexaoBanco.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, usuario.getEmail());

            ResultSet rs = stmt.executeQuery();
            System.out.println("             ____________________________             \n" +
                               "               Listagem dos Emprestimos               \n" +
                               "             ____________________________             \n");

            while (rs.next())
            {
                System.out.println("Código do Empréstimo: " + rs.getLong("id_emprestimos"));
                System.out.println("Valor do Empréstimo: R$ " + rs.getDouble("valor_emprestimo"));
                System.out.println("Quantidade de Parcelas: " + rs.getInt("quantidade_parcelas"));
                System.out.println("\n             _____________________________             \n");
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        } finally {
            ConexaoBanco.closeConnection(con);
        }
    }

    public void PesquisarEmprestimo(long id, Usuarios usuario)
    {
        int cont = 1;

        String sql = "SELECT id_emprestimos, valor_emprestimo, primeira_parcela, quantidade_parcelas, " +
                     "renda, fk_email FROM Emprestimos " +
                     "WHERE id_emprestimos = ? AND fk_email = ? ";

        try {
            con = ConexaoBanco.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1, id);
            stmt.setString(2, usuario.getEmail());

            ResultSet rs = stmt.executeQuery();


            while (rs.next())
            {
                System.out.println("              Empréstimo: " + id + "\n" +
                                   "            _________________             \n");
                System.out.println("Valor do Empréstimo: R$ " + rs.getDouble("valor_emprestimo"));
                System.out.println("Primeira Parcela: " + rs.getDate("primeira_parcela"));
                System.out.println("Quantidade de Parcelas: " + rs.getInt("quantidade_parcelas"));
                System.out.println("Renda: R$" + rs.getDouble("renda"));
                System.out.println("E-mail: " + rs.getString("fk_email"));
                cont = 0;
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        } finally {
            ConexaoBanco.closeConnection(con);
        }

        if(cont == 1)
        {
            System.out.println("\nCódigo não encontrado!");
        }
    }
}
