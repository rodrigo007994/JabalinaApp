/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zencherry.rpp;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author rodrigo
 */
@ManagedBean
@RequestScoped
public class Tramite implements Serializable{
    private int folio;
    private int folioElectronico;
    private String status;
    private char departamento;
    private String tipo;
    private String observacion;
    private String fecha;
    private String tabla;

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }


    public int getFolioElectronico() {
        return folioElectronico;
    }

    public void setFolioElectronico(int folioElectronico) {
        this.folioElectronico = folioElectronico;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public char getDepartamento() {
        return departamento;
    }

    public void setDepartamento(char departamento) {
        this.departamento = departamento;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Override
    public String toString() {
        return "Tramite{" + "folio=" + folio + ", folioElectronico=" + folioElectronico + ", status=" + status + ", departamento=" + departamento + ", tipo=" + tipo + ", observacion=" + observacion + ", fecha=" + fecha + ", tabla=" + tabla + '}';
    }

    
    public String toCsv() {
        return folio +
                "," + folioElectronico +
                "," + status +
                "," + departamento +
                "," + tipo +
                "," + observacion +
                "," + fecha +
                "," + tabla;

    }

    
}
