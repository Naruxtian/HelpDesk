/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utl.dsm502.helpDesk.model;

/**
 *
 * @author Chris
 */
public class Usuario {
    int id_user;
    String user_name;
    String password;
    Empleado employee;

    public Usuario() {
    }

    public Usuario(int id_user, String user_name, String password, Empleado employee) {
        this.id_user = id_user;
        this.user_name = user_name;
        this.password = password;
        this.employee = employee;
    }

    public Usuario(String user_name, String password, Empleado employee) {
        this.user_name = user_name;
        this.password = password;
        this.employee = employee;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Empleado getEmployee() {
        return employee;
    }

    public void setEmployee(Empleado employee) {
        this.employee = employee;
    }

    
    
}
