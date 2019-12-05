
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;
import org.jgroups.util.Util;

import java.io.*;
import java.util.List;
import java.util.LinkedList;

public class SimpleChat extends ReceiverAdapter {
    JChannel channel;
    String user_name="Josue";
    final List<String> state=new LinkedList<>();

    public void viewAccepted(View new_view) {
        System.out.println("-vista: " + new_view);
    }

    public void receive(Message msg) {
        String line = msg.getSrc() + ": " + msg.getObject();
        
        // Implementacion del factorial
        if (line.contains("factorial")) {
            String numero = line.substring(41, line.length() - 1);
            int n = Integer.valueOf(numero);
            line = "El factorial es: " + String.valueOf(factorial(n));
        }
        System.out.println(line);
        
        synchronized(state) {
            state.add(line);
        }
    }

    // La funcion factorial
    private int factorial(int n) {
        if(n == 0)
            return 1;
        else 
            return factorial(n - 1) * n;
    }
    
    public void getState(OutputStream output) throws Exception {
        synchronized(state) {
            Util.objectToStream(state, new DataOutputStream(output));
        }
    }

    @SuppressWarnings("unchecked")
    public void setState(InputStream input) throws Exception {
        List<String> list=(List<String>)Util.objectFromStream(new DataInputStream(input));
        synchronized(state) {
            state.clear();
            state.addAll(list);
        }
        System.out.println("estado recibido (" + list.size() + " mensajes en la historia del chat ):");
        for(String str: list) {
            System.out.println(str);
        }
    }


    private void start() throws Exception {
        channel=new JChannel();
        channel.setReceiver(this);
        channel.connect("ChatCluster");
        channel.getState(null, 10000);
        eventLoop();
        channel.close();
    }

    private void eventLoop() {
        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            try {
                System.out.print("# "); System.out.flush();
                String line=in.readLine().toLowerCase();
                if(line.startsWith("quit") || line.startsWith("exit")) {
                    break;
                }
                line="[" + user_name + "] " + line;
                Message msg=new Message(null,line);
                channel.send(msg);
            } catch(Exception e) {
                System.out.println(e);
            }
        }
    }


    public static void main(String[] args) throws Exception {
        new SimpleChat().start();
    }

}
