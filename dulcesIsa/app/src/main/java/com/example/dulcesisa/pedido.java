package com.example.dulcesisa;

public class pedido {
    private String uid;
    private String Nombre;
    private String Fecha;
    private String Hora;
    private String Tel;
    private String Des;
    private String Tip;


    public pedido(){}

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setHora(String hora) {
        Hora = hora;
    }

    public String getHora() {
        return Hora;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getTel() {
        return Tel;
    }

    public void setDes(String des) {
        Des = des;
    }

    public String getDes() {
        return Des;
    }

    public void setTip(String tip) {
        Tip = tip;
    }


    public String getTip() {
        return Tip;
    }

 public String toString(){
        return "Fecha: " + Fecha + " / Nombre: " + Nombre + " / Hora: " + Hora + " / Descripcion: " +Des + " / Tipo: "+ Tip + " Telefono: " + Tel ;

 }




}
