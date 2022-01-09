import Beans.Emprestimos;
import Beans.Usuarios;
import DAOs.EmprestimoDAO;
import DAOs.UsuarioDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class Sistema
{
    public static void main(String[] args)
    {
        Sistema m = new Sistema();
        m.MenuInicial();
    }

    public Scanner s()
    {
        return new Scanner(System.in);
    }

    public void MenuInicial()
    {
        System.out.println("                               _____________                                 \n" +
                           "                                    Menu                                     \n" +
                           "                               _____________                                 \n\n" +
                           " _______________________   _______________________   _______________________ \n" +
                           "|       1 - Login       | |     2 - Cadastrar     | | 3 - Sair da Aplicação |\n" +
                           "|_______________________| |_______________________| |_______________________|\n\n" +
                           "Digite a opção desejada: ");

        int op = s().nextInt();

        switch (op)
        {
            case 1:
                Login();
            case 2:
                CadastrarUsuario();
            case 3:
                System.exit(0);
        }
    }

    public void MenuUsuario(Usuarios usuario)
    {
        System.out.println("                                    ______________                                    \n" +
                           "                                         Menu                                         \n" +
                           "                                    ______________                                    \n\n" +
                           " __________________________   __________________________   __________________________ \n" +
                           "|  1 - Listar Empréstimos  | | 2 - Pesquisar Empréstimo | | 3 - Solicitar Empréstimo |\n" +
                           "|__________________________| |__________________________| |__________________________|\n\n" +
                           "                              __________________________                              \n" +
                           "                             |  4 - Sair da Aplicação   |                             \n" +
                           "                             |__________________________|                             \n\n" +
                           "Digite a opção desejada: ");

        int op = s().nextInt();

        switch (op)
        {
            case 1:
                Listagem(usuario);
            case 2:
                Pesquisar(usuario);
            case 3:
                SolicitarEmprestimo(usuario);
            case 4:
                System.exit(0);
        }
    }

    public void CadastrarUsuario()
    {
        System.out.println("                               ____________                                   \n" +
                           "                                 Cadastro                                     \n" +
                           "                               ____________                                 \n\n" +
                           "Nome Completo: ");
        String nome = s().nextLine();

        System.out.println("CPF: ");
        String cpf = s().nextLine();

        System.out.println("RG: ");
        String rg = s().nextLine();

        System.out.println("Endereço: ");
        String endereco = s().nextLine();

        System.out.println("Renda: ");
        double renda = s().nextDouble();

        System.out.println("E-mail: ");
        String email = s().nextLine();

        System.out.println("Senha: ");
        String senha = s().nextLine();

        Usuarios u = new Usuarios(nome, cpf, rg, endereco, renda, email, senha);
        UsuarioDAO udao = new UsuarioDAO();
        udao.AdicionarUsuario(u);

        MenuInicial();
    }

    public void Login()
    {
        int saida = 0;
        do
        {
            System.out.println("                           _____________                           \n" +
                               "                               Login                               \n" +
                               "                           _____________                           \n\n" +
                               "E-mail: ");
            String email = s().nextLine();

            System.out.println("Senha: ");
            String senha = s().nextLine();



            UsuarioDAO udao = new UsuarioDAO();
            Usuarios u = udao.acharUsuario(email, senha);

            if(u.getEmail() == null || u.getSenha() == null)
            {
                System.out.println("\n                     Usuario inválido!\n" +
                                   "Digite 0 para tentar novamente ou 1 para voltar ao Menu:");
                saida = s().nextInt();
            }
            else
            {
                MenuUsuario(u);
            }
        }while(saida < 1);
        MenuInicial();
    }

    public void SolicitarEmprestimo(Usuarios usuarioEmprestimo)
    {
        int saida;
        do
        {
            saida = 0;

            System.out.println("                       ________________________                       \n" +
                               "                         Solicitar Emprestimo                         \n" +
                               "                       ________________________                       \n\n" +
                               "Valor do Empréstimo: ");

            try
            {
                Double valorEmprestimo = s().nextDouble();

                System.out.println("Data da Primeira Parcela (yyyy/mm/dd): ");
                String primeiraParcela = s().nextLine();

                System.out.println("Quantidade de Parcelas: ");
                int quantidadeParcelas = s().nextInt();

                SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
                Date dataParcela = formato.parse(primeiraParcela);

                Date limite1 = java.sql.Date.valueOf(LocalDate.now().plusDays(90));
                Date limite2 = java.sql.Date.valueOf(LocalDate.now());

                if (dataParcela.after(limite1) || dataParcela.before(limite2))
                {
                    System.out.println("\nO limite para a data da primeira parcela é de no máximo 90 dias a partir do dia de hoje!\n" +
                                       "Digite 0 para tentar novamente ou 1 para voltar ao Menu Principal:");
                    saida = s().nextInt();
                }else
                {
                    if(quantidadeParcelas > 60 || quantidadeParcelas < 1)
                    {
                        System.out.println("\nO limite de parcelas é de no mínimo 1 e no máximo 60!\n" +
                                           "Digite 0 para tentar novamente ou 1 para voltar ao Menu Principal:");
                        saida = s().nextInt();
                    }
                    else
                    {
                        Emprestimos e = new Emprestimos(valorEmprestimo, dataParcela, quantidadeParcelas,
                                                       usuarioEmprestimo.getRenda(), usuarioEmprestimo.getEmail());
                        EmprestimoDAO edao = new EmprestimoDAO();
                        edao.SolicitarEmprestimo(e);
                        MenuUsuario(usuarioEmprestimo);
                    }
                }
            } catch (ParseException e)
            {
                e.printStackTrace();
            }

        }while (saida < 1);
        MenuUsuario(usuarioEmprestimo);
    }

    public void Listagem(Usuarios usuarioListagem)
    {
        EmprestimoDAO edao = new EmprestimoDAO();
        edao.ListarEmprestimos(usuarioListagem);

        System.out.println("\n\nPressione ENTER para voltar ao Menu Principal!");
        s().nextLine();
        MenuUsuario(usuarioListagem);
    }

    public void Pesquisar(Usuarios usuarioPesquisa)
    {
        System.out.println("\nDigite o código do empréstimo que deseja pesquisar: ");
        int id = s().nextInt();

        EmprestimoDAO edao = new EmprestimoDAO();
        edao.PesquisarEmprestimo(id, usuarioPesquisa);

        System.out.println("\n\nPressione ENTER para voltar ao Menu Principal!");
        s().nextLine();
        MenuUsuario(usuarioPesquisa);
    }
}
