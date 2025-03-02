package com.ytrsoft;

public class ClassThread extends Thread {

    private final ClassLoader cl;
    private final String path;
    private Future future;

    public interface Future {
        void onResolve(Class<?> clz);
    }

    public ClassThread(ClassLoader cl, String path) {
        this.cl = cl;
        this.path = path;
    }

    public void setFuture(Future future) {
        this.future = future;
    }

    private Class<?> loadClass() {
        try {
            return cl.loadClass(path);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    private void await() {
        try {
            Thread.sleep((long) 3 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while (true) {
            Class<?> clz = loadClass();
            if (clz != null) {
                if (future != null) {
                    future.onResolve(clz);
                }
                break;
            }
            await();
            Console.log("<Search> " + path);
        }
    }
}
