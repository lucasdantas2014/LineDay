import java.io.PrintStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import database.UsuarioDAO;
import model.Atividade;
import model.Disciplina;
import model.Usuario;





public class Fachada
{
  Scanner input = new Scanner(System.in);
  Scanner inputStr = new Scanner(System.in);
  SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
  UsuarioDAO userDAO = new UsuarioDAO();
  

  public Fachada() {}
  

  public void editarUsuario(Usuario user) throws SQLException
  {
    boolean continuarEdicao = true;
    while (continuarEdicao) {
      System.out.println(user);
      System.out.println("0 - terminar edição");
      System.out.println("1 - mudar nome");
      System.out.println("2 - mudar curso");
      int op = -1;
      
      try{
    	  op = Integer.parseInt(input.nextLine());
      }catch (Exception e) {
		System.out.println("Digite um numero inteiro; exemplo: 1 ou 2 ou 3...");
      }
      
      if (op == 0) {
        break;
      }
      if (op == 1) {
        System.out.println("Digite o novo nome do usuario:");
        String nome = inputStr.nextLine();
        user.setNome(nome);
      }
      else if (op == 2) {
        System.out.println("Digite o novo curso do usuario:");
        String curso = inputStr.nextLine();
        user.setCurso(curso);
      }
    }
    
//    userDAO.update(user);
  }
  

  public void cadastrarAtividade(Usuario user)
  {
	  
	 
    System.out.println("Digite o nome da Atividae:");
    String nomeAtv = inputStr.nextLine();
    System.out.println("Digite a data final para entrega:");
    String deadlineStr = inputStr.nextLine();
    
    //leitura do tipo de data
    Date deadline = null;
    

    //Formatação do tipo dd/mm/yyyy
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    
    try {
      deadline = formato.parse(deadlineStr);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    

    //Se não houver disciplina cadastrada
    if(user.getDisciplinas()[0] == null){
    	System.out.println("Você não tem nehuma disciplina Cadastrada.");
    	System.out.println("Digite 1 para cadastrar uma disciplina");
//    	System.out.println("Digite 2 para cadastrar atividade sem associar a uma disciplina");
    	
    	int escolha = input.nextInt();
    	if(escolha == 1){
    		cadastrarDisicplina(user);
    	}
    }
    System.out.println("Digite o acronimo da discilpna desejada:");
    String acronimo = inputStr.nextLine();
    

    Disciplina disc = user.buscarDiscPorAcronimo(acronimo);
    

    boolean maisTags = true;
    
    String[] tags = new String[50];
    
    while (maisTags)
    {

      System.out.println("0-Salvar Atividade");
      System.out.println("1-Escolher entre as tags predefinidas");
      System.out.println("2-Definir uma nova tag");
      
      int opTag = input.nextInt();
      

      if (opTag == 0) {
        maisTags = false;
        break;
      }
      if (opTag == 1) {
        System.out.println("Digite o número referente a tag que deseja acrescentar a essa atividade:");
        System.out.println("-1 Para voltar");
        

        user.exibirTags();
        

        int indexTag = input.nextInt();
        if (indexTag != -1)
        {


          appendStr(tags, user.getTags()[indexTag]);
        }
        
      }
      else if (opTag == 2)
      {
        System.out.println("Digite a nova tag:");
        String novaTag = inputStr.nextLine();
        

        user.adicionarTag(novaTag);
        

        appendStr(tags, novaTag);
      }
    }
    


    Atividade atv = new Atividade(nomeAtv, deadline, tags, disc);
    
    user.CadastrarAtiv(atv);
  }
  //Fim da funcao de cadastrar atividade
  
  public void cadastrarDisicplina(Usuario user)
  {
    System.out.println("Digite o acronimo da Disciplina: ");
    String acronimo = inputStr.nextLine();
    System.out.println("Digite op nome do professor: ");
    String nomeProf = inputStr.nextLine();
    System.out.println("Digite o nome da Disicplina: ");
    String nomeDisc = inputStr.nextLine();
    
    Disciplina disciplina = new Disciplina(acronimo, nomeProf, nomeDisc);
    user.CadastrarDisc(disciplina);
  }
  
  public void exibirDisciplinas(Usuario user)
  {
    user.exibirAtividades();
  }
  
  public void exibirAtividades(Usuario user)
  {
    user.exibirAtividades();
  }
  

  public void buscarPorAcronimo(Usuario user)
  {
    String acronimo = inputStr.nextLine();
    
    Disciplina disc = user.buscarDiscPorAcronimo(acronimo);
    System.out.println(disc);
  }
  

  public void appendStr(String[] lista, String elem)
  {
    for (int n = 0; n < lista.length; n++) {
      if (lista[n] == null) {
        lista[n] = elem;
        break;
      }
    }
  }
  
  public void adicionarTag(Usuario user) {
    System.out.println("Digite a nova tag:");
    String novaTag = inputStr.nextLine();
    user.adicionarTag(novaTag);
  }
  
  public void excluirAtividadePorIndice(Usuario user) {
    user.exibirAtividades();
    System.out.println("Digite o indice da atividade que deseja excluir:");
    int indice = input.nextInt();
    System.out.println("1 - Desejo arquivar");
    System.out.println("2 - SÃƒÂ³ excluir");
    
    int op = input.nextInt();
    if (op == 1) {
      System.out.println("Arquivando");
      user.arquivarPorIndice(indice);
    }
    
    user.ExcluirAtividadePorIndice(indice);
  }
  
  public void exibirAtividadesArquivadas(Usuario user)
  {
    user.exibirAtividadesArquivadas();
  }
  
  public void exibirDatasAtividades(Usuario user)
  {
    user.exibirDatasAtividades();
  }
}
