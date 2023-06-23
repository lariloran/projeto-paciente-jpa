package classes;
public enum Situacao {
	NORMAL(1, "NORMAL"),
	EM_OBSERVACAO(2, "EM OBSERVAÇÃO"),
	HOSPITALIZADO(3, "HOSPITALIZADO");
	
	private int id;
	private String descricao;
	
	private Situacao(int id, String nome) {
		this.id = id;
		this.descricao = nome;
	}
}
