package huitca1212.alubia13.foro;

public class TitularForo {
    private String usuario, penya, comentario, fecha, vip, ban, id;

    public TitularForo(String usuario,  String penya, String comentario, String fecha, String vip, String ban, String id) {
        this.usuario = usuario;
        this.penya = penya;
        this.comentario = comentario;
        this.fecha = fecha;
        this.vip = vip;
        this.ban = ban;
        this.id = id;
    }

    public String getUsuario() {
        return this.usuario;
    }
    public String getPenya() {
        return this.penya;
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
    public String getId() {
        return this.id;
    }
    public String getBan() {
        return this.ban;
    }

}