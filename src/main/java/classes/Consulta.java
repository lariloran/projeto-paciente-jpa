package classes;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Consulta
 *
 *Justificativa: Levando em consideração que os meus dois tipos TEMPORAL estão atendendo propriedades
 *do tipo DATE e não calendar apenas utilizei o TEMPORAL específico para os dados que eu precisava, por exemplo
 *em data quero apenas a data, logo utilizo o DATE e em horario apenas TIME, por isso não utilizo
 *o TIMESTAMP porque não tenho uma propriedade que armazene os dois.
 */
@Entity

public class Consulta implements Serializable {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idConsulta;
	   
    @Temporal(TemporalType.DATE)
	private Date data;
    @Temporal(TemporalType.TIME)
	private Date horario;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="idMedico")
    private Medico medico; 
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="idPaciente")
    private Paciente paciente;
    
	private static final long serialVersionUID = 1L;

	public Consulta() {
		super();
	}   
	public Consulta(Date data, Date horario) { 
		super();
		this.data = data;
		this.horario = horario;
	}
	public Long getIdConsulta() {
		return idConsulta;
	}
	public void setIdConsulta(Long idConsulta) {
		this.idConsulta = idConsulta;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Date getHorario() {
		return horario;
	}
	public void setHorario(Date horario) {
		this.horario = horario;
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Consulta [idConsulta=" + idConsulta + ", data=" + data + ", horario=" + horario + ", medico=" + medico
				+ ", paciente=" + paciente + "]";
	}





}
