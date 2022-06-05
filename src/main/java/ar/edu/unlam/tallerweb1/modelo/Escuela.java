package ar.edu.unlam.tallerweb1.modelo;
import javax.persistence.*;

@Entity
public class Escuela {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nombre;
	
	public Long getId() {
		// TODO Auto-generated method stub
		return 1L;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
