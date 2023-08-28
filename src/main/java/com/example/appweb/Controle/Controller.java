package com.example.appweb.Controle;

import com.example.appweb.model.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Controller {

    @Autowired
    private PessoaRepository personRepository;
    private List<Pessoa> person = new ArrayList<>();
    private int id = 0;

    public Pessoa findPerson(String name){
        List<Pessoa> persons = (List<Pessoa>) personRepository.findAll();
        for(Pessoa person : persons){
            if(person.getName().equals(name)){
                return person;
            }
        }

        return null;
    }

    public Pessoa addPessoa(String name, String sexo){
        Pessoa person = new Pessoa();
        person.setName(name);
        person.setSexo(sexo);
        id++;
        person.setId(id);
        personRepository.save(person);
        return person;
    }

    public void removePessoa(String name){
        Pessoa person = findPerson(name);
        personRepository.delete(person);
    }

    public Pessoa editPessoa(String name, String sexo){
        Pessoa person = findPerson(name);
        person.setSexo(sexo);
        personRepository.save(person);
        return person;
    }

    public List<Pessoa> listAll(){
        return (List<Pessoa>)personRepository.findAll();
    }

}
