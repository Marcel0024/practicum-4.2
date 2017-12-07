package nl.hanze.web.homegrownrpc.hello;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import nl.hanze.web.homegrownrpc.generic.Skel;

//import org.apache.commons.codec.binary.Base64;
import java.util.Base64;

public class HelloSkel implements Skel<Hello> {
    private int port;
    private Hello hello;
    private ServerSocket ssoHelloSkel;
	
    public void setPort(int port) {
        this.port=port;
    }

    public void setImplementation(Hello hello) {
        this.hello = hello;
    }

    public void listen() throws Exception {
        ssoHelloSkel = new ServerSocket(port);

        while(true) {
            Socket socHelloSkel=ssoHelloSkel.accept();
            OutputStreamWriter oswHelloSkel=new OutputStreamWriter(socHelloSkel.getOutputStream());
            BufferedReader bufHelloSkel=new BufferedReader(new InputStreamReader(socHelloSkel.getInputStream()));
            String strMethod=bufHelloSkel.readLine();

            if (strMethod.startsWith("sayHello#0")) {
                //String value=Base64.encodeBase64String(hello.sayHello().getBytes());
            	String value=Base64.getEncoder().encodeToString(hello.sayHello().getBytes());
                oswHelloSkel.write("java.lang.String#"+value+"\n");
                oswHelloSkel.flush();
                continue;
            }

            if (strMethod.startsWith("sayHello#1#java.lang.String")) {
                String param1=strMethod.substring("sayHello#1#java.lang.String".length()+1);
              //param1=new String(Base64.decodeBase64(param1));
                //String value=Base64.encodeBase64String(hello.sayHello(param1).getBytes());
                param1=new String(Base64.getDecoder().decode(param1));
                String value=Base64.getEncoder().encodeToString(hello.sayHello(param1).getBytes());
                oswHelloSkel.write("java.lang.String#"+value+"\n");
                oswHelloSkel.flush();
                continue;
            }

            if (strMethod.startsWith("sayHello#1#int")) {
                int temp=Integer.parseInt(strMethod.substring("sayHello#1#int".length()+1);
                String value=Base64.getEncoder().encodeToString(hello.sayHello(temp).getBytes());
                oswHelloSkel.write("java.lang.int#"+value+"\n");
                oswHelloSkel.flush();
                continue;
            }

            if (strMethod.startsWith("sayHello#2#java.lang.String#int")) {
                String[] param=strMethod.substring("sayHello#2#java.lang.String#int".length()+1).split("#");
                //String param1=new String(Base64.decodeBase64(param[0]));
                String param1=new String(Base64.getDecoder().decode(param[0]));
                int param2=Integer.parseInt(param[1]);
                //String value=Base64.encodeBase64String(hello.sayHello(param1, param2).getBytes());
                String value=Base64.getEncoder().encodeToString(hello.sayHello(param1, param2).getBytes());
                oswHelloSkel.write("java.lang.String#"+value+"\n");
                oswHelloSkel.flush();
                continue;
            }

            if (strMethod.startsWith("ageNextYear#1#int")) {
                int param1=Integer.parseInt(strMethod.substring("ageNextYear#1#int".length()+1));
                int value=hello.ageNextYear(param1);
                oswHelloSkel.write("int#"+value+"\n");
                oswHelloSkel.flush();
                continue;
            }
        }
    }
}