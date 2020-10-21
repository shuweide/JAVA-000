package jvm;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HelloClassloader extends ClassLoader {
    public static void main(String[] args) {
        try {
            Class<?> hello = new HelloClassloader().findClass("Hello");
            Method method = hello.getMethod("hello");
            method.invoke(hello.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        byte[] bytes = new byte[0];

        try {
            URI uri = this.getClass().getResource("/jvm/Hello.xlass").toURI();
            bytes = Files.readAllBytes(Paths.get(uri));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            throw new ClassNotFoundException(e.getMessage());
        }

        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (255 - bytes[i]);
        }
        return defineClass(name, bytes, 0, bytes.length);

    }
}
