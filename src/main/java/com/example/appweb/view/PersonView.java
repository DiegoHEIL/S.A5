package com.example.appweb.view;

import com.example.appweb.Controle.Controller;
import com.example.appweb.model.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class PersonView {

    @Autowired
    Controller controller;
    @GetMapping("/person")
    public Pessoa findPerson(@PathParam("nome") String name){
        return controller.findPerson(name);
    }


    @DeleteMapping("/person")
    public String deletePerson(@PathParam("name") String name){
        controller.removePessoa(name);

        return "Pessoa com o nome de " + name + " foi deletada";
    }

    @PostMapping("/person")
    public Pessoa addPerson(@PathParam("nome") String name, @PathParam("sexo") String sexo){
        return controller.addPessoa(name, sexo);
    }

    @PutMapping("/person")
    public Pessoa updatePerson(@PathParam("nome") String name, @PathParam("sexo") String sexo){
        return controller.editPessoa(name, sexo);
    }

    @GetMapping("/all")
    public List<Pessoa> listAll(){
        return controller.listAll();
    }

    @GetMapping("/home")
    public String helloWorld(){
        return "Olá Cliente";
    }
}