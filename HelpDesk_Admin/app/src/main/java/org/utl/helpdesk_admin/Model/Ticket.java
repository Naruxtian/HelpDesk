package org.utl.helpdesk_admin.Model;

public class Ticket {
    private int id_ticket;
    private Employee employee;
    private String device;
    private String type;
    private String date_of;
    private String time_of;
    private String description_of;
    private int estatus;

    public Ticket() {}

    public Ticket(int id_ticket, Employee employee, String device, String type, String date_of, String time_of, String description_of, int estatus) {
        this.id_ticket = id_ticket;
        this.employee = employee;
        this.device = device;
        this.type = type;
        this.date_of = date_of;
        this.time_of = time_of;
        this.description_of = description_of;
        this.estatus = estatus;
    }

    public Ticket(Employee employee, String device, String type, String date_of, String time_of, String description_of, int estatus) {
        this.employee = employee;
        this.device = device;
        this.type = type;
        this.date_of = date_of;
        this.time_of = time_of;
        this.description_of = description_of;
        this.estatus = estatus;
    }

    public int getId_ticket() {
        return id_ticket;
    }

    public void setId_ticket(int id_ticket) {
        this.id_ticket = id_ticket;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate_of() {
        return date_of;
    }

    public void setDate_of(String date_of) {
        this.date_of = date_of;
    }

    public String getTime_of() {
        return time_of;
    }

    public void setTime_of(String time_of) {
        this.time_of = time_of;
    }

    public String getDescription_of() {
        return description_of;
    }

    public void setDescription_of(String description_of) {
        this.description_of = description_of;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id_ticket=" + id_ticket +
                ", employee=" + employee +
                ", device='" + device + '\'' +
                ", type='" + type + '\'' +
                ", date_of='" + date_of + '\'' +
                ", time_of='" + time_of + '\'' +
                ", description_of='" + description_of + '\'' +
                ", estatus=" + estatus +
                '}';
    }
}
