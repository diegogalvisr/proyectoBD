package proyectoCoffeGame.models;

import java.util.Date;

public class torneoModel {
    private int idTorneo;
    private int idJuego;
    private Date fechaHoraI;
    private int numParticipantes;
    private int premio;
    private int idEquipoGanador;

    public torneoModel() {
    }

    public torneoModel(int idTorneo, int idJuego, Date fechaHoraI, int numParticipantes, int premio,
            int idEquipoGanador) {
        this.idTorneo = idTorneo;
        this.idJuego = idJuego;
        this.fechaHoraI = fechaHoraI;
        this.numParticipantes = numParticipantes;
        this.premio = premio;
        this.idEquipoGanador = idEquipoGanador;
    }

    public int getIdTorneo() {
        return idTorneo;
    }

    public void setIdTorneo(int idTorneo) {
        this.idTorneo = idTorneo;
    }

    public int getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(int idJuego) {
        this.idJuego = idJuego;
    }

    public Date getFechaHoraI() {
        return fechaHoraI;
    }

    public void setFechaHoraI(Date fechaHoraI) {
        this.fechaHoraI = fechaHoraI;
    }

    public int getNumParticipantes() {
        return numParticipantes;
    }

    public void setNumParticipantes(int numParticipantes) {
        this.numParticipantes = numParticipantes;
    }

    public int getPremio() {
        return premio;
    }

    public void setPremio(int premio) {
        this.premio = premio;
    }

    public int getIdEquipoGanador() {
        return idEquipoGanador;
    }

    public void setIdEquipoGanador(int idEquipoGanador) {
        this.idEquipoGanador = idEquipoGanador;
    }

}
