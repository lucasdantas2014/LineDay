import java.util.Arrays;
import java.util.Date;

public class Atividade implements Comparable<Atividade>{
	private String nome;
	private Date deadline;
	private String[] tags = new String[50];
	private boolean andamento;
	private Disciplina disciplina;
	
	public Atividade(String nome,Date deadline, String[] tags, Disciplina disciplina){
		this.nome = nome;
		this.deadline = deadline;
		this.tags = tags;
		this.andamento = true;
		this.disciplina = disciplina;
		
	}

	public String getNome() {
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

	@Override
	public String toString() {
		String tagsStr = "";
		for (int i = 0; this.tags[i] != null; i++) {
			tagsStr += "   " + i + " - " + this.tags[i] + ".";
			
		}
		return nome + "\nDeadLine:" + deadline + "\nTags:" + tagsStr + "\nAndamento "
				+ andamento + "         disciplina: " + disciplina + "\n";
	}

  public int compareTo(Atividade outraAtividade) {
			return this.deadline.compareTo(outraAtividade.getDeadline());
		}
}
