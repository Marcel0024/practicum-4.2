package nl.hanze.web.rmi;

import java.rmi.*;

public class HelloServer {
    public static void main(String[] args) throws Exception {
        Hello hello=new HelloImpl();
        Naming.rebind("rmi://localhost:1099/HelloService", hello);
    }
}

