package nl.hanze.web.rmi;

import java.rmi.*;

public class HelloClient {
    public static void main(String[] args) throws Exception {
        Hello hello = (Hello) Naming.lookup("rmi://localhost/HelloService");
        System.out.println("We obtained "+hello+" that implements Hello");
        System.out.println(hello.sayHello());
        System.out.println(hello.sayHello("Chris"));
        System.out.println(hello.sayHello(18));
        System.out.println(hello.sayHello("Chris", 18));
        System.out.println(hello.ageNextYear(18));
    } 
}
