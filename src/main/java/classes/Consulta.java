package classes;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Consulta
 *
 */
@Entity

public class Consulta implements Serializable {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idConsulta;
	    
	private Date data;
	private Date horario;
	private static final long serialVersionUID = 1L;

	public Consulta() {
		super();
	}   
	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}   
	public Date getHorario() {
		return this.horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}
	@Override
	public String toString() {
		return "Consulta [idConsulta=" + idConsulta + ", data=" + data + ", horario=" + horario + "]";
	}
   
}
