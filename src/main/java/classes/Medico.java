package classes;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

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
	
	@OneToMany (mappedBy = "medico"
			, cascade =
			{CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="idConsulta")
	private List<Consulta> consultas;  

	private static final long serialVersionUID = 1L;

	public Medico() {
		super();
	}   
	public Medico(String crm, String especialidade) {
		super();
		this.crm = crm;
		this.especialidade = especialidade; 
		consultas = new ArrayList<>();
	}
	public Long getIdMedico() {
		return idMedico;
	}
	public void setIdMedico(Long idMedico) {
		this.idMedico = idMedico;
	}
	public String getCrm() {
		return crm;
	}
	public void setCrm(String crm) {
		this.crm = crm;
	}
	public String getEspecialidade() {
		return especialidade;
	}
	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	public List<Consulta> getConsultas() {
		return consultas;
	}
	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
	    return "Medico [idMedico=" + idMedico + ", crm=" + crm + ", especialidade=" + especialidade + "]";
	}

}
