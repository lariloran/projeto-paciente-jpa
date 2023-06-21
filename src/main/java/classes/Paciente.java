package classes;

import classes.Pessoa;
import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Paciente
 *
 */
@Entity

public class Paciente extends Pessoa implements Serializable {

	private String convenio;
	private String cpf;
	private Date dataNascimento;
	private ArrayList emails;
	private ArrayList<Consulta> consultas;

	private static final long serialVersionUID = 1L;

	public Paciente() {
		super();
	}   
	public Paciente(String convenio, String cpf, Date dataNascimento, ArrayList emails, ArrayList<Consulta> consultas) {
		super();
		this.convenio = convenio;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.emails = emails;
		this.consultas = consultas;
	}
	public String getConvenio() {
		return this.convenio;
	}

	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}   
	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}   
	public Date getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}   
	public ArrayList getEmails() {
		return this.emails;
	}

	public void setEmails(ArrayList emails) {
		this.emails = emails;
	}
	@Override
	public String toString() {
		return "Paciente [convenio=" + convenio + ", cpf=" + cpf + ", dataNascimento=" + dataNascimento + ", emails="
				+ emails + "]";
	}
   
}
