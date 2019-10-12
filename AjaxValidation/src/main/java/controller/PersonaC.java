package controller;

import dao.PersonaImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import model.Persona;

@Named(value = "personaC")
@SessionScoped

public class PersonaC implements Serializable {

    private Persona persona;
    private PersonaImpl dao;
    private List<Persona> listadoper;
    private List<Persona> listadoper2;

    public PersonaC() {
        persona = new Persona();
        dao = new PersonaImpl();
        listadoper = new ArrayList();
    }

    @PostConstruct
    public void Iniciar() {
        try {
            listar();
        } catch (Exception e) {
        }
    }

    public void registrar() throws Exception {
        try {
            dao.registrar(persona);
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(persona);
        } catch (Exception e) {
            throw e;
        }

    }

    public void eliminar(Persona persona) throws Exception {
        try {
            dao.eliminar(persona);
        } catch (Exception e) {
            throw e;
        }
    }

    public void listar() throws Exception {
        try {
            listadoper = dao.listarPer();

        } catch (Exception e) {
            throw e;
        }
    }

    
    
    
    
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public PersonaImpl getDao() {
        return dao;
    }

    public void setDao(PersonaImpl dao) {
        this.dao = dao;
    }

    public List<Persona> getListadoper() {
        return listadoper;
    }

    public void setListadoper(List<Persona> listadoper) {
        this.listadoper = listadoper;
    }

    public List<Persona> getListadoper2() {
        return listadoper2;
    }

    public void setListadoper2(List<Persona> listadoper2) {
        this.listadoper2 = listadoper2;
    }
}
