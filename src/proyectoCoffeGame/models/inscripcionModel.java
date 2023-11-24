package proyectoCoffeGame.models;

public class inscripcionModel {

    private int idInscripcion;
    private int idEquipo;
    private int idTorneo;

    public inscripcionModel() {
    }

    public inscripcionModel(int idInscripcion, int idEquipo, int idTorneo) {
        this.idInscripcion = idInscripcion;
        this.idEquipo = idEquipo;
        this.idTorneo = idTorneo;
    }

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public int getIdTorneo() {
        return idTorneo;
    }

    public void setIdTorneo(int idTorneo) {
        this.idTorneo = idTorneo;
    }

}
