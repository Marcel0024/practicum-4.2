package nl.hanze.web.homegrownrpc.generic;

import java.net.*;
import java.io.*;

public class NameClient {
    private String strIP;
    private int port;

    public NameClient(String strIP, int port) {
        this.strIP = strIP;
        this.port = port;
    }

    @SuppressWarnings("rawtypes")
	public Stub getReference(String strName) throws Exception {
        Socket socNameClient = new Socket(this.strIP, this.port);
        ObjectOutputStream outputStream=new ObjectOutputStream(socNameClient.getOutputStream());
        ObjectInputStream inputStream=new ObjectInputStream(socNameClient.getInputStream());
        outputStream.writeObject("GET");
        outputStream.writeObject(strName);
        outputStream.flush();
        Class c=(Class) inputStream.readObject();
        Stub stub=null;
        if (c!=null) {
            stub=(Stub) c.newInstance();
            String serverIP=(String) inputStream.readObject();
            int serverPort=inputStream.readInt();
            stub.setSkelLocation(serverIP, serverPort);
        }
        inputStream.close();
        outputStream.close();
        socNameClient.close();
        return stub;
    }

    @SuppressWarnings("rawtypes")
    public void setReference(String strServerName, String strStubName, String strServerIP, int port) throws Exception {
        Class c=Class.forName(strStubName);
        Socket socNameClient = new Socket(this.strIP, this.port);
        ObjectOutputStream outputStreams=new ObjectOutputStream(socNameClient.getOutputStream());
        ObjectInputStream inputStream=new ObjectInputStream(socNameClient.getInputStream());
        outputStreams.writeObject("PUT");
        outputStreams.writeObject(strServerName);
        outputStreams.writeObject(c);
        outputStreams.writeObject(strServerIP);
        outputStreams.writeInt(port);
        outputStreams.flush();
        inputStream.readObject();
    }
}