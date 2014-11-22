/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dino.object;

import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 *
 * @author dmunoz
 */
public class Empleado implements Serializable {
    
    private String departamento;
    private float salario;
    private Usuario usuario;
    private ImageIcon foto;
    
    public Empleado(String departamento, float salario) {
        this.departamento = departamento;
        this.salario = salario;
    }
    public  Empleado(){}
    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ImageIcon getFoto() {
        return foto;
    }

    public void setFoto(ImageIcon foto) {
        this.foto = foto;
    }

    
    @Override
    public String toString() {
        return "Empleado{" + "\ndepartamento=" + departamento + ",\nsalario=" + salario + ",\nusuario=" + usuario + '}';
    }                    
    
    
}
