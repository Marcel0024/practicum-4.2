package nl.hanze.web.homegrownrpc.generic;

public interface Skel<T> {
    public void setPort(int port);
    public void setImplementation(T t);
    public void listen() throws Exception;
}