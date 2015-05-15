package huitca1212.alubia13.foro;

public class TitularForo {
    private String usuario, comentario, fecha, vip;

    public TitularForo(String usuario, String comentario, String fecha, String vip) {
        this.usuario = usuario;
        this.comentario = comentario;
        this.fecha = fecha;
        this.vip = vip;
    }

    public String getUsuario() {
        return this.usuario;
    }
    public String getComentario() {
        return this.comentario;
    }
    public String getFecha() {
        return this.fecha;
    }
    public String getVip() {
        return this.vip;
    }
}