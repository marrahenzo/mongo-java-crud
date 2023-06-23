package marrahenzo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Alta {
    public static void main(String[] args) {
        Lorem lorem = LoremIpsum.getInstance();
        MongoClient mongo = MongoClients.create(System.getProperty("mongodb.uri"));

        MongoDatabase db = mongo.getDatabase("mongo_java_crud");
        MongoCollection<Document> notificacionesCollection = db.getCollection("notificaciones");
        MongoCollection<Document> usuariosCollection = db.getCollection("usuarios");

        Random rand = new Random();
        Document notificacion;
        List<Document> notificaciones = new ArrayList<>();
        Document usuario;
        List<Document> usuarios = new ArrayList<>();
        List<Document> notificacionesPorUsuario = new ArrayList<>();

        for (int i = 0; i < 5000; i++) {
            notificacion = new Document("_id", new ObjectId());
            notificacion.append("_id", i + 1)
                    .append("texto", lorem.getWords(10))
                    .append("fecha", LocalTime.now());
            notificaciones.add(notificacion);
        }

        for (int i = 0; i < 500; i++) {
            usuario = new Document("_id", new ObjectId());
            usuario.append("usuario_id", i + 1)
                    .append("nombre", lorem.getFirstName())
                    .append("apellido", lorem.getLastName());
            int cantidadNotificaciones = rand.nextInt(9) + 1;
            for (int j = 0; j < cantidadNotificaciones; j++) {
                int idNotificacion = rand.nextInt(5000);
                notificacionesPorUsuario.add(notificaciones.get(idNotificacion));
            }
            usuario.append("notificaciones", notificacionesPorUsuario);
            usuarios.add(usuario);
        }

        //notificacionesCollection.insertMany(notificaciones);
        usuariosCollection.insertMany(usuarios);
    }
}
