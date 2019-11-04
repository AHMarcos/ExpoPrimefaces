package controller;

import Services.SessionUtils;
import dao.UsuarioImpl;
import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.Usuario;

@Named(value = "usuarioC")
@SessionScoped
public class UsuarioC implements Serializable {

    //Objeto de Sesion
    private Usuario usuario = new Usuario();

    //Variables de Logeo
    public void setPass(String Pass) {
        this.Pass = Pass;
    }
    private String User;
    private String Pass;

    public void startSession() throws Exception {
        try {
            UsuarioImpl dao = new UsuarioImpl();
            usuario = dao.startSession(User, Pass);
            if (usuario != null) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", usuario);
                switch (usuario.getNivel()) {
                    case "A":
                        FacesContext.getCurrentInstance().getExternalContext().redirect("/AjaxValidation/faces/vista/Persona.xhtml");
                }
            } else {
                setPass(null);
                usuario = new Usuario();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Contraseña / Usuario Incorrecto", ""));
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void finishSession() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear(); //Cierra la Session
        FacesContext.getCurrentInstance().getExternalContext().redirect("/AjaxValidation"); // Mandamos al Login
    }

//    public void securityLogin() throws IOException {
//        Usuario us = SessionUtils.obtenerObjetoSesion();
//        if (us != null) {
//            switch (us.getNivel()) {
//                case "A":
//                    FacesContext.getCurrentInstance().getExternalContext().redirect("/AjaxValidation/faces/vista/Persona.xhtml");
//            }
//        }
//    }

    public void securitySession() throws IOException {
        if (SessionUtils.obtenerObjetoSesion() == null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/AjaxValidation");
        }
    }

    public void obtenerDatos() {
        System.out.println(SessionUtils.ObtenerCodigoSesion());
        System.out.println(SessionUtils.ObtenerNombreSesion());
    }

    public void irLogin() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/AjaxValidation");
        System.out.println("Ya Mande");
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String User) {
        this.User = User;
    }

    public String getPass() {
        return Pass;
    }

}
