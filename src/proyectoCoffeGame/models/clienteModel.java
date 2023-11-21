
package proyectoCoffeGame.models;

import java.time.LocalDate;

public class clienteModel {
    private int idCliente;
    private String nombre;
    private String email;
    private LocalDate fechaRegistro;

    public clienteModel() {
    }

    public clienteModel(int idCliente, String nombre, String email, LocalDate fechaRegistro) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.email = email;
        this.fechaRegistro = fechaRegistro;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

}
