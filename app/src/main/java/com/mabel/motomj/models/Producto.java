package com.mabel.motomj.models;

public class   Producto {

    private String marca;
    private String modelo;
    private String arranque;
    private String chasis;
    private String color;
    private String tip_combustible;
    private String embrague;
    private String conf_motor;
    private String precio;
    private String motourl;

    public Producto() { }

    public Producto(String marca, String modelo, String arranque, String chasis, String color, String tip_combustible, String embrague, String conf_motor, String precio, String motourl) {
        this.marca = marca;
        this.modelo = modelo;
        this.arranque = arranque;
        this.chasis = chasis;
        this.color = color;
        this.tip_combustible = tip_combustible;
        this.embrague = embrague;
        this.conf_motor = conf_motor;
        this.precio = precio;
        this.motourl = motourl;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getArranque() {
        return arranque;
    }

    public void setArranque(String arranque) {
        this.arranque = arranque;
    }

    public String getChasis() {
        return chasis;
    }

    public void setChasis(String chasis) {
        this.chasis = chasis;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTip_combustible() {
        return tip_combustible;
    }

    public void setTip_combustible(String tip_combustible) {
        this.tip_combustible = tip_combustible;
    }

    public String getEmbrague() {
        return embrague;
    }

    public void setEmbrague(String embrague) {
        this.embrague = embrague;
    }

    public String getConf_motor() {
        return conf_motor;
    }

    public void setConf_motor(String conf_motor) {
        this.conf_motor = conf_motor;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getMotourl() {
        return motourl;
    }

    public void setMotourl(String motourl) {
        this.motourl = motourl;
    }
}
