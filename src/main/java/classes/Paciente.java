package classes;

import classes.Pessoa;
import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Paciente
 *
 */
@Entity
@PrimaryKeyJoinColumn(name = "idPaciente", referencedColumnName =
"idPessoa")

public class Paciente extends Pessoa implements Serializable {


	private String convenio;
	private String cpf;
	
    @Temporal(TemporalType.DATE)
	private Date dataNascimento;
    
    @ElementCollection
    @CollectionTable(name="paciente_emails")
	private List<String> emails; 
    
    @OneToMany (mappedBy = "paciente"
			, cascade =
			{CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="idConsulta")
	private List<Consulta> consultas;

	private static final long serialVersionUID = 1L;

	public Paciente() {
		super();
	}   
	public Paciente(String convenio, String cpf, Date dataNascimento, List<String> emails, List<Consulta> consultas) {
		super();
		this.convenio = convenio;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento; 
		this.emails = emails;
		this.consultas = consultas;
	}
	public String getConvenio() {
		return convenio;
	}
	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public List<String> getEmails() {
		return emails;
	}
	public void setEmails(List<String> emails) {
		this.emails = emails;
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
		return "Paciente [convenio=" + convenio + ", cpf=" + cpf + ", dataNascimento=" + dataNascimento + ", emails="
				+ emails + ", consultas=" + consultas + "]";
	}


}
