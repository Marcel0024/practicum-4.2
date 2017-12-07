package nl.hanze.web.rmi;

import java.rmi.*;
import java.rmi.server.*;

@SuppressWarnings("serial")
public class HelloImpl extends UnicastRemoteObject implements Hello {
    public HelloImpl() throws java.rmi.RemoteException {
        super();
    }

    public String sayHello() {
        return "Hello world!";
    }

    public String sayHello(String name) throws RemoteException {
        return "Hello "+name;
    }

    public String sayHello(int age) throws RemoteException {
        return "You're "+age+" years old";
    }

    public String sayHello(String name, int age) throws RemoteException {
        return "Hi "+name+", you're "+age+" years old";
    }

    public int ageNextYear(int age) throws RemoteException  {
        return ++age;
    }
}

