package marrahenzo;

import com.mongodb.client.*;

public class Conexion {
    public static void main(String[] args) {
        MongoClient mongo = MongoClients.create(System.getProperty("mongodb.uri"));

        MongoDatabase db = mongo.getDatabase("mongo_java_crud");

        MongoCollection usuariosCollection = db.getCollection("usuarios");
        MongoCollection notificacionesCollection = db.getCollection("notificaciones");

        MongoIterable<String> databases = db.listCollectionNames();
        databases.forEach(System.out::println);
    }
}