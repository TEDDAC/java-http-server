package elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Server {
    public static final String ROOTPATH = "www";

    public void initialize() throws IOException {
        File root = new File(ROOTPATH);
        if(!root.exists()){
            root.mkdir();
            File index = new File(ROOTPATH + "/index.html");
            FileWriter writer = new FileWriter(index);
            writer.write("""
                     <!DOCTYPE html>
                    <html>
                    <head>
                    <title>Index</title>
                    </head>

                    <body>
                    This is my website.
                    </body>

                    </html>\s""");
            writer.close();

            File error = new File(ROOTPATH + "/error.html");
            writer = new FileWriter(error);
            writer.write("""
                     <!DOCTYPE html>
                    <html>
                    <head>
                    <title>Error</title>
                    </head>

                    <body>
                    <h1>An error was occured.</h1>
                    </body>

                    </html>\s""");
            writer.close();
        }
    }

    public String getPageString(String pathname) throws FileNotFoundException {
        if(pathname.equals("/")){
            pathname = "/index.html";
        }
        File file = new File(ROOTPATH + pathname);
        Scanner scanner = new Scanner(file);
        StringBuilder builder = new StringBuilder();
        while(scanner.hasNextLine()){
            builder.append(scanner.nextLine());
        }

        return builder.toString();
    }
}
