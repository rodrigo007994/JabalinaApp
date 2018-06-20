/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zencherry.rpp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author rodrigo
 */
@ManagedBean
@RequestScoped
public class tramitesForm {
    @ManagedProperty(value="#{tramite}")
    private Tramite tramite;
    
    
    public String guardarTramite(){
        try {
            if(this.getTramite().getFolio()>0){
                if (isValidDate(this.getTramite().getFecha())==true){
                }else{
                    this.getTramite().setFecha((new SimpleDateFormat("dd/MM/yyyy")).format(new Date()));
                }
                Writer.writeToFile("tramites.db",this.getTramite());
                return "consulta";
                
            }else{
                return "error";
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(tramitesForm.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }
    }
    public String viewTramites(){
        String outString="";
        Tramites tramites=Reader.readFromFile();
        if(!tramites.isEmpty()) {
            for(int c=0;c<tramites.size();c++){
                outString+=tramites.get(c).toString();
            }
        }
        return outString;
    }
    public static Tramite[] getTramites(){
        Tramites tramites=Reader.readFromFile();
        Tramite[] out=new Tramite[tramites.size()];
        
        if(!tramites.isEmpty()) {
            for(int c=0;c<tramites.size();c++){
                out[c]=tramites.get(c);
            }
        }
        return out;
    }
    public static Tramite[] getTramitesIngresadas(){
        Tramites tramites=Reader.readFromFile();
        for (int c = 0; c < tramites.size(); c++) {
            
            if(!tramites.get(c).getTabla().equals("INGRESADAS")){
                tramites.remove(c);
                c--;
            }
        }
        Tramite[] out=new Tramite[tramites.size()];
        
        if(!tramites.isEmpty()) {
            for(int c=0;c<tramites.size();c++){
                out[c]=tramites.get(c);
            }
        }
        return out;
    }
    public static Tramite[] getTramitesSuspendidas(){
        Tramites tramites=Reader.readFromFile();
        for (int c = 0; c < tramites.size(); c++) {
            
            if(!tramites.get(c).getTabla().equals("SUSPENDIDAS")){
                tramites.remove(c);
                c--;
            }
        }
        Tramite[] out=new Tramite[tramites.size()];
        
        if(!tramites.isEmpty()) {
            for(int c=0;c<tramites.size();c++){
                out[c]=tramites.get(c);
            }
        }
        return out;
    }
    public static Tramite[] getTramitesEntregadas(){
        Tramites tramites=Reader.readFromFile();
        for (int c = 0; c < tramites.size(); c++) {
            
            if(!tramites.get(c).getTabla().equals("ENTREGADAS")){
                tramites.remove(c);
                c--;
            }
        }
        Tramite[] out=new Tramite[tramites.size()];
        
        if(!tramites.isEmpty()) {
            for(int c=0;c<tramites.size();c++){
                out[c]=tramites.get(c);
            }
        }
        return out;
    }

    public Tramite getTramite() {
        return tramite;
    }

    public void setTramite(Tramite tramite) {
        this.tramite = tramite;
    }
    public String actualizar(){
        String fileName="tramites.db";
        Tramites tramites= Reader.readFromFile();
        for(Tramite tramite:tramites){
            if(tramite.getTabla().equals("INGRESADAS")){
                tramite.setStatus(GetRPPDatos.getEtapa(tramite.getFolio()));
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return "error";
            }
        }
        File f = new File(fileName);
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(f));
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
        try {
            oos.writeObject(tramites);
            oos.flush();
            oos.close();
            return "consulta";
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }

    }
    public String removeTramiteWithNumber(int removeid) {
        String fileName="tramites.db";
        Tramites tramites= Reader.readFromFile();
        int removeIndex=-1;
        for(Tramite tramite : tramites){
            if(tramite.getFolio()==removeid){
                removeIndex=tramites.indexOf(tramite);
                break;
            }
        }
        tramites.remove(removeIndex);

        File f = new File(fileName);
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(f));
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
        try {
            oos.writeObject(tramites);
            oos.flush();
            oos.close();
            return "exito";
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }


    }
    public String setObservacion(int changeid,String observacion){
        String fileName="tramites.db";
        Tramites tramites= Reader.readFromFile();

        for(Tramite tramite : tramites){
            if(tramite.getFolio()==changeid){
                tramite.setObservacion(observacion);
                break;
            }
        }
        File f = new File(fileName);
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(f));
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
        try {
            oos.writeObject(tramites);
            oos.flush();
            oos.close();
            return "exito";
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }


    }
    public String setTabla(int changeid,String tabla){
        String fileName="tramites.db";
        Tramites tramites= Reader.readFromFile();
        for(Tramite tramite : tramites){
            if(tramite.getFolio()==changeid){
                tramite.setTabla(tabla);
                break;
            }
        }
        File f = new File(fileName);
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(f));
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
        try {
            oos.writeObject(tramites);
            oos.flush();
            oos.close();
            return "exito";
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }


    }
    public void downloadCsv(){
    try {
    Tramites tramites= Reader.readFromFile();
    FacesContext fc = FacesContext.getCurrentInstance();
    ExternalContext ec = fc.getExternalContext();
    ec.responseReset(); // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.
    ec.setResponseContentType("UTF-8"); // Check http://www.iana.org/assignments/media-types for all types. Use if necessary ExternalContext#getMimeType() for auto-detection based on filename.
    ec.setResponseHeader("Content-Type", "text/csv");
    ec.setResponseHeader("Content-Disposition", "attachment; filename=\"Reporte_"+new java.text.SimpleDateFormat("dd-MM-yyyy").format(new Date())+".csv\""); // The Save As popup magic is done here. You can give it any file name you want, this only won't work in MSIE, it will use current request URL as file name instead.
    OutputStream output = ec.getResponseOutputStream();
    output.write("Folio,Folio Electronico,Status,Departamento,Tipo,Observaciones,Fecha,Tabla".getBytes());
    for(Tramite tramite:tramites){
        output.write(("\n"+tramite.toCsv()).getBytes());
    }
    fc.responseComplete();
    
    } catch (IOException ex) {
        Logger.getLogger(tramitesForm.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    
}
    public void downloadCsvIngresadas(){
    try {
    Tramites tramites= Reader.readFromFile();
        for (int c = 0; c < tramites.size(); c++) {
            
            if(!tramites.get(c).getTabla().equals("INGRESADAS")){
                tramites.remove(c);
                c--;
            }
        }
    FacesContext fc = FacesContext.getCurrentInstance();
    ExternalContext ec = fc.getExternalContext();
    ec.responseReset(); // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.
    ec.setResponseContentType("UTF-8"); // Check http://www.iana.org/assignments/media-types for all types. Use if necessary ExternalContext#getMimeType() for auto-detection based on filename.
    ec.setResponseHeader("Content-Type", "text/csv");
    ec.setResponseHeader("Content-Disposition", "attachment; filename=\"Reporte_Ingresadas_"+new java.text.SimpleDateFormat("dd-MM-yyyy").format(new Date())+".csv\""); // The Save As popup magic is done here. You can give it any file name you want, this only won't work in MSIE, it will use current request URL as file name instead.
    OutputStream output = ec.getResponseOutputStream();
    output.write("Folio,Folio Electronico,Status,Departamento,Tipo,Observaciones,Fecha,Tabla".getBytes());
    for(Tramite tramite:tramites){
        output.write(("\n"+tramite.toCsv()).getBytes());
    }
    fc.responseComplete();
    
    } catch (IOException ex) {
        Logger.getLogger(tramitesForm.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    
}
    public void downloadCsvSuspendidas(){
    try {
    Tramites tramites= Reader.readFromFile();
        for (int c = 0; c < tramites.size(); c++) {
            
            if(!tramites.get(c).getTabla().equals("SUSPENDIDAS")){
                tramites.remove(c);
                c--;
            }
        }
    FacesContext fc = FacesContext.getCurrentInstance();
    ExternalContext ec = fc.getExternalContext();
    ec.responseReset(); // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.
    ec.setResponseContentType("UTF-8"); // Check http://www.iana.org/assignments/media-types for all types. Use if necessary ExternalContext#getMimeType() for auto-detection based on filename.
    ec.setResponseHeader("Content-Type", "text/csv");
    ec.setResponseHeader("Content-Disposition", "attachment; filename=\"Reporte_"+new java.text.SimpleDateFormat("dd-MM-yyyy").format(new Date())+".csv\""); // The Save As popup magic is done here. You can give it any file name you want, this only won't work in MSIE, it will use current request URL as file name instead.
    OutputStream output = ec.getResponseOutputStream();
    output.write("Folio,Folio Electronico,Status,Departamento,Tipo,Observaciones,Fecha,Tabla".getBytes());
    for(Tramite tramite:tramites){
        output.write(("\n"+tramite.toCsv()).getBytes());
    }
    fc.responseComplete();
    
    } catch (IOException ex) {
        Logger.getLogger(tramitesForm.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    
}
    public static boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }
    
    public String actualizarTabla(Tramite tramite){
        System.out.println(tramite.toString());
        return "exito";
    }
    
    //Solo se necesita hacer una vez
    /*public String crearNuevoDB() throws IOException{
        tramite.setFolio(50);
        tramite.setFolioElectronico(50);
        tramite.setDepartamento("A".charAt(0));
        tramite.setFecha("06/08/2018");
        tramite.setObservacion("NA");
        tramite.setStatus("NA");
        tramite.setTipo("NA");
        tramite.setTabla("INGRESADAS");
        Tramites tramites= new Tramites();
        File f = new File("tramites.db");
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(f));
        } catch (IOException e) {
            e.printStackTrace();
        }
        tramites.add(tramite);
        oos.writeObject(tramites);
        oos.flush();
        oos.close();
        return "exito";
    }*/
    
}
