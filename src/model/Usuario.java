package model;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;




public class Usuario
{
  private String Nome;
  private String Curso;
  private String[] tags;
  private Atividade[] atividades;
  private Atividade[] atividadesArquivadas;
  private Disciplina[] disciplinas;
  private int qtd_atividades;
  
  public Usuario() {}
  
  public void ordenarAtividades()
  {
    for (int i = 0; i < qtd_atividades - 1; i++) {
      Atividade tmp = atividades[i];
      for (int j = i + 1; j < qtd_atividades; j++) {
        Atividade tmp2 = atividades[j];
        if (tmp.getDeadline().compareTo(tmp2.getDeadline()) > 0) {
          Atividade temp = tmp;
          atividades[i] = tmp2;
          atividades[j] = temp;
        }
      }
    }
  }
  
  public void CadastrarAtiv(Atividade atividade)
  {
    qtd_atividades += 1;
    Date data = atividade.getDeadline();
    for (int n = 0; n < atividades.length; n++) {
      if (atividades[n] == null) {
        atividades[n] = atividade;
        break;
      }
    }
  }
  

  public void CadastrarDisc(Disciplina disc)
  {
    for (int n = 0; n < disciplinas.length; n++) {
      if (disciplinas[n] == null) {
        disciplinas[n] = disc;
        break;
      }
    }
  }
  



  public void ExcluirAtiv() {}
  


  public Disciplina buscarDiscPorAcronimo(String acronimo)
  {
    for (int i = 0; disciplinas[i] != null; i++) {
      if (acronimo.equals(disciplinas[i].getAcronimo()))
      {
        return disciplinas[i];
      }
    }
    

    System.out.println("NAO FOI ENCONTRADO");
    return null;
  }
  
  public void exibirAtividades()
  {
    ordenarAtividades();
    for (int n = 0; atividades[n] != null; n++) {
      System.out.println("<<----------------------------->>");
      System.out.println("id da atividade: " + n + " - " + atividades[n]);
      System.out.println("<<----------------------------->>");
    }
  }
  

  public void exibirAtividadesArquivadas()
  {
    for (int n = 0; atividadesArquivadas[n] != null; n++)
    {
      System.out.println(n + " - " + atividadesArquivadas[n]);
    }
  }
  


  public void exibirDatasAtividades()
  {
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    for (int n = 0; atividades[n] != null; n++)
    {
      System.out.println(sdf1.format(atividades[n].getDeadline()));
    }
  }
  

  public void exibirDisciplinas()
  {
    for (int n = 0; disciplinas[n] != null; n++) {
      System.out.println(n + " - " + disciplinas[n]);
    }
  }
  
  public void exibirTags()
  {
    for (int n = 0; tags[n] != null; n++) {
      System.out.println(n + " - " + tags[n]);
    }
  }
  
  public void ExcluirAtividadePorIndice(int indice)
  {
    qtd_atividades -= 1;
    atividades[indice] = null;
    
    for (int n = indice; n < atividades.length - 1; n++) {
      Atividade aux = atividades[n];
      atividades[n] = atividades[(n + 1)];
      atividades[(n + 1)] = aux;
    }
  }
  
  public void arquivarPorIndice(int indice)
  {
    System.out.println(indice);
    for (int n = 0; n < atividadesArquivadas.length; n++) {
      System.out.println("Entrou no loop");
      System.out.println(atividadesArquivadas[n]);
      if (atividadesArquivadas[n] == null) {
        atividadesArquivadas[n] = atividades[indice];
        System.out.println(atividades[indice]);
        System.out.println(atividadesArquivadas[n]);
        break;
      }
    }
  }
  
  public String toString()
  {
    return "Nome: " + Nome + "\nCurso: " + Curso;
  }
  

  public void EditarPerfil() {}
  
  public String getNome()
  {
    return Nome;
  }
  
  public void setNome(String nome) {
    Nome = nome;
  }
  
  public String getCurso() {
    return Curso;
  }
  
  public void setCurso(String curso) {
    Curso = curso;
  }
  
  public String[] getTags() {
    return tags;
  }
  
  public void setTags(String[] tags) {
    this.tags = tags;
  }
  
  public Atividade[] getAtividades() {
    return atividades;
  }
  
  public void setAtividades(Atividade[] atividades) {
    this.atividades = atividades;
  }
  
  public void setDisciplinas(Disciplina[] disciplinas) {
    this.disciplinas = disciplinas;
  }
  
  public void adicionarTag(String novaTag)
  {
    for (int n = 0; n < tags.length; n++) {
      if (tags[n] == null) {
        tags[n] = novaTag;
        break;
      }
    }
  }
}