package classes;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Prontuario
 *
 *Justificativa: utilizei o STRING porque, além de me possibilitar trocar a ordem dos enum 
 *e não quebrar a aplicação, ficará muito melhor a visibilidade na base de dados.
 *Utilizando o ORDINAL constará apenas o ID e como nesse sistema não temos uma tabela que
 *armazene esses enums torna-se complicado para o usuario identificar qual a sua situação.
 */
@Entity

public class Prontuario implements Serializable {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProntuario;
    @Temporal(TemporalType.DATE)
	private Date data;
	private String descricao;
	
	private Paciente paciente;
	
	@Enumerated(EnumType.STRING)
	private Situacao situacao;
	
	private static final long serialVersionUID = 1L;

	public Prontuario() {
		super();
	}   
	
	public Prontuario(Date data, String descricao, Paciente paciente, Situacao situacao) { 
		super();
		this.data = data;
		this.descricao = descricao;
		this.paciente = paciente;
		this.situacao = situacao;
	}

	public Long getIdProntuario() {
		return idProntuario;
	}

	public void setIdProntuario(Long idProntuario) {
		this.idProntuario = idProntuario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Prontuario [idProntuario=" + idProntuario + ", data=" + data + ", descricao=" + descricao
				+ ", paciente=" + paciente + ", situacao=" + situacao + "]";
	}



}
