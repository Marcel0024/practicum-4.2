package nl.hanze.web.homegrownrpc.hello;

import nl.hanze.web.homegrownrpc.generic.*;

public class HelloClient {
    public static void main(String[] args) throws Exception {
        NameClient nc=new NameClient("localhost", 7090);
        Hello hello = (Hello) nc.getReference("HelloServer");
        System.out.println("We obtained "+hello+" that implements Hello");
        System.out.println(hello.sayHello());
        System.out.println(hello.sayHello("Chris"));
        
        
        //System.out.println(hello.sayHello(18));
        //System.out.println(hello.sayHello("Chris", 18));
        //System.out.println(hello.ageNextYear(18));
    }
}