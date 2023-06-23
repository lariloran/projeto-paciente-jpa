package classes;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Pessoa
 * 
 * Justificativa: Utilizei o JOINED porque acredito que a visualização da minha hierarquia de
 * classes fica mais organizada, evita a possibilidade de deixar campos nulos, como no SINGLE_TABLE.
 * Me possibilita, também, que num futuro caso eu tenha outras Entidades que herdem de pessoa
 * a organização a nivel de banco ficará muito mais clara do que se estivessem
 * todos numa mesma tabela e evitará duplicação de dados.
 */
@Entity
@Inheritance (strategy = InheritanceType.JOINED)
public class Pessoa implements Serializable {

	   
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idPessoa")
	private Long idPessoa;
    
	private String nome;   
	private String endereco;
	private static final long serialVersionUID = 1L;
	
	@OneToMany(cascade=CascadeType.PERSIST)
	@JoinColumn(name="idPessoa")
	private List<Telefone> telefones;
	
	public Pessoa() {
		super();
	}   
	
	public Pessoa(String nome, String endereco) {
		super();
		this.nome = nome;
		this.endereco = endereco;
		telefones = new ArrayList<>();
	}

	public Long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Pessoa [idPessoa=" + idPessoa + ", nome=" + nome + ", endereco=" + endereco + ", telefones=" + telefones
				+ "]";
	}

  
}
