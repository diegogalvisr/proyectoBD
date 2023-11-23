package proyectoCoffeGame.models;

public class computadorModel {
    private int idCompu;
    private String numSerie;
    private String modelo;
    private String estado;
    private int precioHora;

    public computadorModel() {
    }

    public computadorModel(int idCompu, String numSerie, String modelo, String estado, int precioHora) {
        this.idCompu = idCompu;
        this.numSerie = numSerie;
        this.modelo = modelo;
        this.estado = estado;
        this.precioHora = precioHora;
    }

    public int getIdCompu() {
        return idCompu;
    }

    public void setIdCompu(int idCompu) {
        this.idCompu = idCompu;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public float getPrecioHora() {
        return precioHora;
    }

    public void setPrecioHora(int precioHora) {
        this.precioHora = precioHora;
    }

}
