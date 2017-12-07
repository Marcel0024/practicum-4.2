package nl.hanze.web.homegrownrpc.hello;

import nl.hanze.web.homegrownrpc.generic.*;

public class HelloServer {
    public static void main(String[] args) throws Exception {
        Hello hsvHello=new HelloImpl();
        NameClient nameClient=new NameClient("localhost", 7090);
        nameClient.setReference("HelloServer", "nl.hanze.web.homegrownrpc.hello.HelloStub", "localhost", 8800);
        
        HelloSkel hskHello=new HelloSkel();
        hskHello.setPort(8800);
        
        hskHello.setImplementation(hsvHello);
        hskHello.listen();
    }
}