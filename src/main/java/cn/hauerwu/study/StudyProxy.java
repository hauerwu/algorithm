package cn.hauerwu.study;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

interface Hello{
    void sayHello();
}

class HelloImpl implements Hello{
    public void sayHello(){
        System.out.println("hello world");
    }
}

class MyInvocationHandler implements InvocationHandler{
    private Object target;
    public MyInvocationHandler(Object target) { this.target = target; }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        System.out.println("Invoking sayHello");
        Object result = method.invoke(target, args);
        return result;
    }
}
