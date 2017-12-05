package nl.hanze.web.homegrownrpc.generic;

import java.net.*;
import java.util.*;
import java.io.*;

public class NameServer {
    private ServerSocket ssoNameServer;
    
    @SuppressWarnings("rawtypes")
    private HashMap<String, Class> hsmServerClass;
    private HashMap<String, String> hsmServerIP;
    private HashMap<String, Integer> hsmServerPort;

    @SuppressWarnings("rawtypes")
    public NameServer(int port) throws Exception {
        ssoNameServer = new ServerSocket(port);
        hsmServerClass=new HashMap<String, Class>();
        hsmServerIP=new HashMap<String, String>();
        hsmServerPort=new HashMap<String, Integer>();
    }

    @SuppressWarnings("rawtypes")
    public void listenAndHandle() throws Exception {
        System.out.println ("Nameserver waiting for requests...");

        while (true) {
            Socket socNameServiceUser = ssoNameServer.accept();
            ObjectInputStream inputStream = new ObjectInputStream(socNameServiceUser.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(socNameServiceUser.getOutputStream());
            String command=(String) inputStream.readObject();
            String name=(String) inputStream.readObject();

            if (command.equals("PUT")) {
                Class c=(Class) inputStream.readObject();
                hsmServerClass.put(name, c);
                String serverIP=(String) inputStream.readObject();
                hsmServerIP.put(name, serverIP);
                int serverPort=inputStream.readInt();
                hsmServerPort.put(name, serverPort);
                outputStream.writeObject("OK");
            } else {
                Class c=hsmServerClass.get(name);
                outputStream.writeObject(c);
                String serverIP=hsmServerIP.get(name);
                outputStream.writeObject(serverIP);
                int serverPort=hsmServerPort.get(name);
                outputStream.writeInt(serverPort);
            }
            
            outputStream.close();
            inputStream.close();
            socNameServiceUser.close();
        }
    }
    
    
    public static void main(String[] args) {
        try {
            NameServer s = new NameServer(7090);
            s.listenAndHandle();
        } catch (BindException e) {
        	System.out.println("Server already started");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}