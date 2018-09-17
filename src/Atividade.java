import java.util.Arrays;

public class Atividade {
	private String nome;
	private String deadline;
	private String[] tags = new String[50];
	private boolean andamento;
	private Disciplina disciplina;
	
	public Atividade(String nome,String deadline, String[] tags, Disciplina disciplina){
		this.nome = nome;
		this.deadline = deadline;
		this.tags = tags;
		this.disciplina = disciplina;
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
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
		return "Atividade [nome=" + nome + ", deadline=" + deadline + ", tags=" + Arrays.toString(tags) + ", andamento="
				+ andamento + ", disciplina=" + disciplina + "]";
	}
}
