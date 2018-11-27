package model;

import java.util.Date;

public class Atividade implements Comparable<Atividade>
{
  private String nome;
  private Date deadline;
  private String[] tags = new String[50];
  private boolean andamento;
  private Disciplina disciplina;
  private String descricao; 
  private int id;
  
  public Atividade(String nome, Date deadline, String[] tags, Disciplina disciplina, String descricao, int id) {
    this.nome = nome;
    this.deadline = deadline;
    this.tags = tags;
    andamento = true;
    this.disciplina = disciplina;
    this.descricao = descricao;
    this.id = id;
  }
  
  public Atividade(String nome2, java.sql.Date deadline2, Disciplina disc) {
	
}

public String getNome()
  {
    return nome;
  }
  
  public void setNome(String nome) {
    this.nome = nome;
  }
  
  public Date getDeadline() {
    return deadline;
  }
  
  public void setDeadline(Date deadline) {
    this.deadline = deadline;
  }
  
  public String[] getTags() {
    return tags;
  }
  
  public void setTags(String[] tags) {
    this.tags = tags;
  }
  
  public boolean isAndamento() {
    return andamento;
  }
  
  public void setAndamento(boolean andamento) {
    this.andamento = andamento;
  }
  
  public Disciplina getDisciplina() {
    return disciplina;
  }
  
  public void setDisciplina(Disciplina disciplina) {
    this.disciplina = disciplina;
  }
  
 
  
  public String getDescricao() {
	return descricao;
  }

  public void setDescricao(String descricao) {
	this.descricao = descricao;
  }

public String toString()
  {
    String tagsStr = "";
    for (int i = 0; tags[i] != null; i++) {
      tagsStr = tagsStr + "   " + i + " - " + tags[i] + ".";
    }
    
    return 
      nome + "\nDeadLine:" + deadline + "\nTags:" + tagsStr + "\nAndamento " + andamento + "         disciplina: " + disciplina + "\nDescricao: " + this.descricao + "\n";
  }
  
  public int compareTo(Atividade outraAtividade) {
    return deadline.compareTo(outraAtividade.getDeadline());
  }
}