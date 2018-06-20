/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.zencherry.rpp;
import org.json.JSONArray;
import org.json.JSONObject;
/**
 *
 * @author rodri
 */
public class GetRPPDatos {
    
    public static String getEtapa(int folio){
        try{
            String sfolio=Integer.toString(folio);
            String out="";
            String url="http://www.insejupy.gob.mx:8040/DOCUMENTOSRPP/Seguimiento/BuscarSol";
            String[][] params=new String[2][2];
            params[0][0]="iIDSolicitud";
            params[0][1]=sfolio.substring(0, sfolio.length()-4);
            //System.out.println(params[0][1]);
            params[1][0]="iIDAnioFiscal";
            params[1][1]=sfolio.substring(sfolio.length()-4);
            //System.out.println(params[1][1]);
            String respuesta=BwRestclient.HttpPost(url,params);
            //System.out.println(respuesta);
            JSONObject json = new JSONObject(respuesta);
            JSONArray jsonArray =(JSONArray)json.get("lstDetalles");
            //System.out.println(jsonArray.get(0));
            for(int c=0;jsonArray.length()>c;c++){
                out+=new JSONObject(jsonArray.get(c).toString()).getString("cEtapa")+";";
            }
            return out;
        }catch(Exception e){
            return "NO ENCONTRADO";
        }
    }
    
}
