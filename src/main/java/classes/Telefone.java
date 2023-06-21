package classes;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Telefone
 *
 */
@Entity

public class Telefone implements Serializable {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTelefone;
	    
	private int ddd;
	private long numero;
	private static final long serialVersionUID = 1L; 

	public Telefone() {
		super(); 
	}   
	
	
	public Telefone(int ddd, long numero) {
		super();
		this.ddd = ddd;
		this.numero = numero;
	}


	public Long getIdTelefone() {
		return idTelefone;
	}


	public void setIdTelefone(Long idTelefone) {
		this.idTelefone = idTelefone;
	}


	public int getDdd() {
		return ddd;
	}


	public void setDdd(int ddd) {
		this.ddd = ddd;
	}


	public long getNumero() {
		return numero;
	}


	public void setNumero(long numero) {
		this.numero = numero;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "Telefone [idTelefone=" + idTelefone + ", ddd=" + ddd + ", numero=" + numero + "]";
	}
}
