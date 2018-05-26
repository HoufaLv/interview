package com.iw.thread;

/**
 * @author Lvhoufa
 *
 */
public class TaskOrder {

	public static void main(String[] args) {
		// 现在有T1,T2,T3三个线程,你怎样保证T2在T1执行完后执行,T3在T2执行完后执行
		// 也就是执行完T1,再执行T2,再执行T3
		//将join放在start后面,可以确保线程的启动顺序 t1,t2,t3
		
		//如果先设置为守护线程 setDaemon 则可以保证启动线程3的时候,线程2启动,启动线程2的时候,线程1启动  t3,t2,t1
		
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("t1 线程执行");
			}
		});

		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				//thread1.setDaemon(true);
				try {
					thread1.start();
					thread1.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("t2 线程执行");
			}
		});

		Thread thread3 = new Thread(new Runnable() {
			@Override
			public void run() {
				//thread2.setDaemon(true);
				try {
					thread2.start();
					thread2.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("t3 线程执行");
			}
		});

		thread3.start();

		// thread3.start();
		// thread2.start();
		// thread1.start();
	}
}
