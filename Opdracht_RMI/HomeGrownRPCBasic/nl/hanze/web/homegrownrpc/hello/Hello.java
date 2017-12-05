package nl.hanze.web.homegrownrpc.hello;

public interface Hello {
    public String sayHello() throws Exception;
    public String sayHello(String name) throws Exception;
    public String sayHello(int age) throws Exception;
    public String sayHello(String name, int age) throws Exception;
    public int ageNextYear(int age) throws Exception;
}