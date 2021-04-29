package Server;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    public static void main(String[] args) {
        try {
            char[] risposta;
            
            ServerSocket ss = new ServerSocket(5500);    
            Socket client = ss.accept();
            
            ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
            
            risposta = (char[]) ois.readObject();
            
            for (int i=0; i<risposta.length; i++) {
                System.out.println(risposta[i]);
            }
            
            ois.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}