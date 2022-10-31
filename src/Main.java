import elements.Request;
import elements.Response;
import elements.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.initialize();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            ServerSocket ecoute = new ServerSocket(8080);
            while(true) {
                Socket serveur = ecoute.accept();
                InputStream entree = new DataInputStream(serveur.getInputStream());
                OutputStream sortie = new PrintStream(serveur.getOutputStream());
                StringBuilder stringBuilder = new StringBuilder();
                while (entree.available() > 0) {
                    stringBuilder.append((char) entree.read());
                }
                Response res;
                try {
                    Request req = new Request(stringBuilder.toString());

                    System.out.println(req.getResourcePath());

                    String body = server.getPageString(req.getResourcePath());
                    res = new Response(200, "OK", body, req);
                } catch (FileNotFoundException e){
                    String body = server.getPageString("/error.html");
                    res = new Response(404, "KO", body, null);
                }


                sortie.write(res.toBytes());

                System.out.println(res);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}