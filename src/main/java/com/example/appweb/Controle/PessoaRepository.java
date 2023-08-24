package com.example.appweb.Controle;
import com.example.appweb.model.Pessoa;
import org.springframework.data.repository.CrudRepository;

public interface PessoaRepository extends CrudRepository<Pessoa, Integer > {
}