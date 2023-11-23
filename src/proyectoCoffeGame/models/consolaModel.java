package proyectoCoffeGame.models;

public class consolaModel {
    private int idConsola;
    private String numSerie;
    private String modelo;
    private String estado;
    private int precioH;

    public consolaModel() {
    }

    public consolaModel(int idConsola, String numSerie, String modelo, String estado, int precioH) {
        this.idConsola = idConsola;
        this.numSerie = numSerie;
        this.modelo = modelo;
        this.estado = estado;
        this.precioH = precioH;
    }

    public int getIdConsola() {
        return idConsola;
    }

    public void setIdConsola(int idConsola) {
        this.idConsola = idConsola;
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

    public float getPrecioH() {
        return precioH;
    }

    public void setPrecioH(int precioH) {
        this.precioH = precioH;
    }

}
