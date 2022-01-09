package DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco
{
    private static final String URL = "jdbc:postgresql://ec2-44-199-52-133.compute-1.amazonaws.com:5432/d2jtf8s2abie0j";
    private static final String USER = "ceqrdsmvytsauf";
    private static final String PASS = "7e27b7fbb5746e70e6e26ef4ea1a158d12412f42ad9ccfefadc3878ef00071b8";

    public static Connection getConnection()
    {
        try
        {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException ex)
        {
            System.err.println("Erro: " + ex);
            return null;
        }
    }
    public static void closeConnection(Connection con)
    {
        if (con != null)
        {
            try
            {
                con.close();
            } catch (SQLException ex)
            {
                System.err.println("Erro: " + ex);
            }
        }
    }
}
