package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    public static void main(String[] args) {
        String alfabeto = "abcdefghilmnopqrstuvz";
        boolean controllo = true;
        int tentativi = 0;
        
        char[] lista = new char[10];
        char[] risposta;
        
        for (int i=0; i<lista.length; i++) {
            lista[i] = alfabeto.charAt((int) (Math.random()*alfabeto.length()));
        }
        
        try {
            Socket server = new Socket("127.0.0.1", 5500);
            
            ObjectOutputStream oos = new ObjectOutputStream(server.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(server.getInputStream());
            
            do {
                lista = (char[])ois.readObject();
                
                for (int i=0; i<lista.length-1; i++) {
                    for (int j=i; j<lista.length; j++) {
                        if (lista[i]>lista[j]) {
                            controllo = false;
                            System.out.println("Ci è riuscito in "+tentativi+1+" tentativi!");
                        } else {
                            oos.writeObject(lista);
                            tentativi++;
                        }
                    }
                }
            } while(controllo);
            
            
            
            ois.close();
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}