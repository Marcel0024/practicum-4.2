package nl.hanze.web.homegrownrpc.hello;

public class HelloImpl implements Hello  {
    public String sayHello() {
        return "Hello world!";
    }

    public String sayHello(String name) throws Exception {
        return "Hello "+name;
    }

    public String sayHello(int age) throws Exception {
        return "You're "+age+" years old";
    }

    public String sayHello(String name, int age) throws Exception {
        return "Hi "+name+", you're "+age+" years old";
    }

    public int ageNextYear(int age) throws Exception {
        return ++age;
    }
}