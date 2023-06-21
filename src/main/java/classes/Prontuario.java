package classes;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Prontuario
 *
 */
@Entity

public class Prontuario implements Serializable {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProntuario;
	private Date data;
	private String descricao;
	private Paciente paciente;
	private Situacao situacao;
	
	private static final long serialVersionUID = 1L;

	public Prontuario() {
		super();
	}   
	


	public Prontuario(Long idProntuario, Date data, String descricao, Paciente paciente, Situacao situacao) {
		super();
		this.idProntuario = idProntuario;
		this.data = data;
		this.descricao = descricao;
		this.paciente = paciente;
		this.situacao = situacao;
	}



	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}   
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@Override
	public String toString() {
		return "Prontuario [idProntuario=" + idProntuario + ", data=" + data + ", descricao=" + descricao + "]";
	}
   
}
