
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dino.object;

import java.io.Serializable;

/**
 *
 * @author dmunoz
 */
public class Usuario implements Serializable {
    
    private String nombres = null;
    private String apellidos = null;
    private int quota;
    private transient String username = null;
    private transient String password = null;    
    
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "Usuario{" + "\nnombres=" + nombres + ",\napellidos=" + apellidos + ",\nquota=" + quota + ",\nusername=" + username + ",\npassword=" + password + '}';
    }
    
   
    
}
