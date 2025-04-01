package com.example.proyectoextraordinario;

public class Estadisticas {

    private String ritmo;
    private String regate;
    private String tiro;
    private String defensa;
    private String pase;
    private String fisico;

    public Estadisticas(String ritmo, String regate, String tiro, String defensa, String pase, String fisico) {
        this.ritmo = ritmo;
        this.regate = regate;
        this.tiro = tiro;
        this.defensa = defensa;
        this.pase = pase;
        this.fisico = fisico;
    }

    public String getRitmo() {
        return ritmo;
    }

    public void setRitmo(String ritmo) {
        this.ritmo = ritmo;
    }

    public String getRegate() {
        return regate;
    }

    public void setRegate(String regate) {
        this.regate = regate;
    }

    public String getTiro() {
        return tiro;
    }

    public void setTiro(String tiro) {
        this.tiro = tiro;
    }

    public String getDefensa() {
        return defensa;
    }

    public void setDefensa(String defensa) {
        this.defensa = defensa;
    }

    public String getPase() {
        return pase;
    }

    public void setPase(String pase) {
        this.pase = pase;
    }

    public String getFisico() {
        return fisico;
    }

    public void setFisico(String fisico) {
        this.fisico = fisico;
    }

    @Override
    public String toString() {
        return "Estadisticas{" +
                "ritmo='" + ritmo + '\'' +
                ", regate='" + regate + '\'' +
                ", tiro='" + tiro + '\'' +
                ", defensa='" + defensa + '\'' +
                ", pase='" + pase + '\'' +
                ", fisico='" + fisico + '\'' +
                '}';
    }
}
