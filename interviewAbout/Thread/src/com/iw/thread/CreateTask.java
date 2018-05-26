package com.iw.thread;

/**
 * 创建线程的第一种方式,实现Runnable 接口
 * @author Lvhoufa
 *
 */
public class CreateTask {
	
	public static void main(String[] args) {
		//1.实现Runable接口
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("线程1启动");
			}
		});
		
		thread.start();
		
		//此时启动是不能确定顺序的,将会出现资源竞争现象
		Thread2 thread2 = new Thread2();
		thread2.start();
	}
}

/**
 * 创建线程的第二种方式,extend Thread 类
 * @author Lvhoufa
 *
 */
class Thread2 extends Thread{
	
	@Override
	public void run() {
		System.out.println("线程2 启动");
	}
}