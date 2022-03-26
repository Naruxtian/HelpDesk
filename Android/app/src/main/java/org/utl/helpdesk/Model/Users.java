package org.utl.helpdesk.Model;

import com.google.gson.annotations.SerializedName;

public class Users {
    @SerializedName("id_user")
    private int id_user;
    @SerializedName("user_name")
    private String user_name;
    @SerializedName("password")
    private String password;
    @SerializedName("employee")
    private Employee employee;

    public Users() {}

    public Users(int id_user, String user_name, String password, Employee employee) {
        this.id_user = id_user;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id_user=" + id_user +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", id_employee=" + employee +
                '}';
    }
}
