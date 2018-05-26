package jdkProxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class BookStoreProxy implements InvocationHandler{

	/**
	 * 业务类实现对象
	 */
	private Object target;
	
	
	/**
	 * 绑定业务对象,并返回一个代理类
	 * @param target
	 * @return
	 */
	public Object bind(Object target) {
		this.target = target;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}
	
	
	/**
	 * 包装调用方法
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result = null;
		System.out.println("预处理操作");
		
		result = method.invoke(target, args);
		
		System.out.println("调用后处理操作");
		return result;
	}
	
}
