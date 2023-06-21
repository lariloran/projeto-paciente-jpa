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
	public int getDdd() {
		return this.ddd;
	}

	public void setDdd(int ddd) {
		this.ddd = ddd;
	}   
	public long getNumero() {
		return this.numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}
	@Override
	public String toString() {
		return "Telefone [idTelefone=" + idTelefone + ", ddd=" + ddd + ", numero=" + numero + "]";
	}
   
}
