package ar.edu.unlam.tallerweb1.controladores;

public class DatosRegistro {

	private String mail;
	private String clave;
	private String claveRepetida;
	
	
	public DatosRegistro() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DatosRegistro(String mail, String clave, String claveRepetida) {
		this.mail =  mail;
		this.clave = clave;
		this.claveRepetida = claveRepetida;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getClaveRepetida() {
		return claveRepetida;
	}

	public void setClaveRepetida(String claveRepetida) {
		this.claveRepetida = claveRepetida;
	}

}
