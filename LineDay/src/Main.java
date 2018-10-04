import java.io.PrintStream;
import java.util.Scanner;

import database.DAO;
import model.Usuario;





public class Main
{
  public Main() {}
  
  public static void menu()
  {
    System.out.println("------Menu------");
    System.out.println("0 - Sair ");
    System.out.println("1 - Editar Usuario ");
    System.out.println("2 - Cadastrar Atividades:");
    System.out.println("3 - Cadastrar Discplina");
    System.out.println("4 - Exibir Disciplina");
    System.out.println("5 - Exibir Atividades");
    System.out.println("6 - Buscar Disciplina por Acronimo");
    System.out.println("7 - Adicionar tag");
    System.out.println("8 - Excluir atvididade");
    System.out.println("9 - Exibir Arquivados");
    System.out.println("10 - Exibir Datas");
  }
  
  public static void main(String[] args)
  {
    DAO DAO = new DAO();
    Scanner inputStr = new Scanner(System.in);
    Scanner input = new Scanner(System.in);
    
    Fachada fachada = new Fachada();
    Usuario user = new Usuario();
    int opcao = -1;
    
    boolean executar = true;
    

    while (executar) {
      menu();
      try {
        opcao = Integer.parseInt(inputStr.next());
        



        if (opcao == 0) {
          executar = false;
        }
        

        if (opcao == 1)
        {
          fachada.editarUsuario(user);



        }
        else if (opcao == 2) {
          fachada.cadastrarAtividade(user);


        }
        else if (opcao == 3) {
          fachada.cadastrarDisicplina(user);


        }
        else if (opcao == 4) {
          fachada.exibirDisciplinas(user);


        }
        else if (opcao == 5) {
          fachada.exibirAtividades(user);


        }
        else if (opcao == 6) {
          fachada.buscarPorAcronimo(user);


        }
        else if (opcao == 7) {
          fachada.adicionarTag(user);


        }
        else if (opcao == 8) {
          fachada.excluirAtividadePorIndice(user);


        }
        else if (opcao == 9) {
          fachada.exibirAtividadesArquivadas(user);

        }
        else if (opcao == 10) {
          fachada.exibirDatasAtividades(user);
        }
        
      }
      catch (Exception e)
      {
    	System.out.println(e);
        System.out.println("!!! Digite um numero inteiro exemplo: 1 ou 2 ou 3...");
      }
    }
  }
}