package cglibProxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class BookShopCglibProxy implements MethodInterceptor {
	
	/**
	 * 业务类对象,代理方法中真正的业务方法调用
	 */
	private Object target;
	
	/**
	 * 相当于jdk 代理中的动态绑定
	 * @param target
	 * @return
	 */
	public Object getInstance(Object target) {
		//给业务对象赋值
		this.target = target;
		//创建增强者对象
		Enhancer enhancer = new Enhancer();
		//为增强者指定要代理的业务类
		enhancer.setSuperclass(this.target.getClass());
		//设置回调: 对于代理类上所有的方法调用,都会调用CallBack,而CallBack需要实现Interceptor() 方法进行拦截
		enhancer.setCallback(this);
		//创建动态代理类对象并返回
		return enhancer.create();
	}
	
	/**
	 * 实现回调方法
	 */
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("前置通知");
		proxy.invokeSuper(obj, args);
		System.out.println("后置通知");
		return null;
	}

}
