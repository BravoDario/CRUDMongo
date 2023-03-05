  /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crudmongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import java.net.UnknownHostException;

/**
 *
 * @author adria
 */

public class MongoConexion {
    DB baseDatos;
    DBCollection coleccion;
    BasicDBObject documento = new BasicDBObject();
    public MongoConexion(){
        try{
        Mongo mongo = new Mongo("localhost", 27017);
        baseDatos = mongo.getDB("sonyMusic");
        coleccion = mongo.getCollection("music");
        System.out.println("Conexión exitosa...");
        } catch (UnknownHostException ex){
            System.out.println(ex);
        }
    }
    public boolean insertar(String accion){
        documento.put("accion", accion);
        coleccion.insert(documento);
        return true;
    }
    public void mostrar(){
        DBCursor cursor = coleccion.find();
        while(cursor.hasNext()){
            System.out.println(cursor.next());
        }
    }
    public boolean actualizar(String accionVieja, String accionNueva){
        documento.put("accion", accionVieja);
        BasicDBObject documentoNuevo = new BasicDBObject();
        documentoNuevo.put("accion", accionNueva);
        coleccion.findAndModify(documento, documentoNuevo);
        
        return true;
    }
    public boolean eliminar(String accion){
        documento.put("accion", accion);
        coleccion.remove(documento);
         
        return true;
    }
}
