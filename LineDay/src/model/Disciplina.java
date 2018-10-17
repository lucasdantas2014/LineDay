package model;

public class Disciplina {
  private String acronimo;
  private String nomeProf;
  private String nomeDisc;
  
  public Disciplina(String acronimo, String nomeProf, String nomeDisc) {
    this.acronimo = acronimo;
    this.nomeProf = nomeProf;
    this.nomeDisc = nomeDisc;
  }
  
  public String getAcronimo()
  {
    return acronimo;
  }
  
  public void setAcronimo(String acronimo) { this.acronimo = acronimo; }
  
  public String getNomeProf() {
    return nomeProf;
  }
  
  public void setNomeProf(String nomeProf) { this.nomeProf = nomeProf; }
  
  public String getNomeDisc() {
    return nomeDisc;
  }
  
  public void setNomeDisc(String nomeDisc) { this.nomeDisc = nomeDisc; }
  
  public String toString()
  {
    return "  Acronimo: " + acronimo + " - Nome Professor: " + nomeProf + " - Nome da Disciplina: " + nomeDisc;
  }
}