package proyectoCoffeGame.models;

public class juegoModel {
    private int idJuego;
    private String nombre;
    private String genero;
    private String plataforma;
    private String clasificacion;

    public juegoModel() {
    }

    public juegoModel(int idJuego, String nombre, String genero, String plataforma, String clasificacion) {
        this.idJuego = idJuego;
        this.nombre = nombre;
        this.genero = genero;
        this.plataforma = plataforma;
        this.clasificacion = clasificacion;
    }

    public int getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(int idJuego) {
        this.idJuego = idJuego;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

}
