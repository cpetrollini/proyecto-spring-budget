package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Escuela;

public interface RepositorioEscuela {

	void guardar(Escuela escuela);

	Escuela buscarPor(Long id);

	List<Escuela> buscarPor(String nombre);

}
