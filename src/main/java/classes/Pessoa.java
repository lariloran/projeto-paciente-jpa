package classes;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Pessoa
 *
 */
@Entity

public class Pessoa implements Serializable {

	   
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPessoa;
    
	private String nome;   
	private String endereco;
	private static final long serialVersionUID = 1L;
	private ArrayList<Telefone> telefones;
	
	public Pessoa() {
		super();
	}   
	
	public Pessoa(Long idPessoa, String nome, String endereco) {
		super();
		this.idPessoa = idPessoa;
		this.nome = nome;
		this.endereco = endereco;
		telefones = new ArrayList<>();
	}
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}   
	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	@Override
	public String toString() {
		return "Pessoa [idPessoa=" + idPessoa + ", nome=" + nome + ", endereco=" + endereco + "]";
	}
   
}
