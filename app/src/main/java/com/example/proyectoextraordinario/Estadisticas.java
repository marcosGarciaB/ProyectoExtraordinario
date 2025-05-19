package com.example.proyectoextraordinario;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Estadisticas implements Parcelable {

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

    protected Estadisticas(Parcel in) {
        ritmo = in.readString();
        regate = in.readString();
        tiro = in.readString();
        defensa = in.readString();
        pase = in.readString();
        fisico = in.readString();
    }

    public static final Creator<Estadisticas> CREATOR = new Creator<Estadisticas>() {
        @Override
        public Estadisticas createFromParcel(Parcel in) {
            return new Estadisticas(in);
        }

        @Override
        public Estadisticas[] newArray(int size) {
            return new Estadisticas[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(ritmo);
        dest.writeString(regate);
        dest.writeString(tiro);
        dest.writeString(defensa);
        dest.writeString(pase);
        dest.writeString(fisico);
    }
}
