package classes;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Medico
 *
 */
@Entity

public class Medico implements Serializable {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMedico;
	    
	private String crm;
	private String especialidade;
	private ArrayList<Consulta> consultas;

	private static final long serialVersionUID = 1L;

	public Medico() {
		super();
	}   
	public Medico(Long idMedico, String crm, String especialidade) {
		super();
		this.idMedico = idMedico;
		this.crm = crm;
		this.especialidade = especialidade;
		consultas = new ArrayList<>();
	}
	
	public String getCrm() {
		return this.crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}   
	public String getEspecialidade() {
		return this.especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	@Override
	public String toString() {
		return "Medico [idMedico=" + idMedico + ", crm=" + crm + ", especialidade=" + especialidade + "]";
	}
   
}
