package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    public static void main(String[] args) {
        String alfabeto = "abcdefghilmnopqrstuvz";
        
        char[] lista = new char[10];
        
        for (int i=0; i<lista.length; i++) {
            lista[i] = alfabeto.charAt((int) (Math.random()*alfabeto.length()));
        }
        
        try {
            Socket server = new Socket("127.0.0.1", 5500);
            
            PrintWriter out = new PrintWriter(server.getOutputStream(), true);
            
            ObjectOutputStream oos = new ObjectOutputStream(server.getOutputStream());
            oos.writeObject(lista);
            
            out.close();
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}