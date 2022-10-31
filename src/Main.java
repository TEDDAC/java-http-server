import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        try {
            ServerSocket ecoute = new ServerSocket(8080);
            while(true) {
                try (Socket serveur = ecoute.accept()) {
                    InputStream entree = new DataInputStream(serveur.getInputStream());
                    OutputStream sortie = new PrintStream(serveur.getOutputStream());
                    StringBuilder stringBuilder = new StringBuilder();
                    while (entree.available() > 0) {
                        stringBuilder.append((char) entree.read());
                    }
                    System.out.print(stringBuilder.toString());
                    sortie.write(("HTTP/1.0 200 OK\n" +
                            "Content-Type: text/html\n" +
                            "Content-Length: 13" +
                            "\n\n" +
                            "<h1>Hello World !</h1>").getBytes(StandardCharsets.UTF_8));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}