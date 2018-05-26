package cglibProxy;

/**
 * 使用cglib 实现动态代理,不需要实现业务接口
 * @author Lvhoufa
 *
 */
public class BookShopImpl {
	
	
	/**
	 * 业务方法
	 */
	public void saleBook() {
		System.out.println("BookShopImpl.saleBook()");
	}
}
