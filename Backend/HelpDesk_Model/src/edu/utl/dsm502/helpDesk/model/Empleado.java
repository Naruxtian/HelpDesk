package edu.utl.dsm502.helpDesk.model;

/**
 *
 * @author Chris
 */
public class Empleado {
    int id_employee;
    String first_name;
    String last_name;
    String email;
    String phone_number;
    String department;

    public Empleado() {
    }

    public Empleado(int id_employee) {
        this.id_employee = id_employee;
    }
    
    public Empleado(int id_employee, String first_name, String last_name, String email, String phone_number, String department) {
        this.id_employee = id_employee;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone_number = phone_number;
        this.department = department;
    }

    public Empleado(String first_name, String last_name, String email, String phone_number, String department) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone_number = phone_number;
        this.department = department;
    }

    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }


    
}
