package nl.hanze.web.rmi3b;

import java.rmi.*;

public interface Hello extends Remote {
    public String sayHello() throws RemoteException;
    public String sayHello(String name) throws RemoteException;
    public String sayHello(int age) throws RemoteException;
    public String sayHello(String name, int age) throws RemoteException;
    public int ageNextYear(int age) throws RemoteException;
}
