package staticProxy;

/**
 * 设置业务代理类,静态代理
 * 静态代理缺点,一个代理类只能代理一个业务接口
 * @author Lvhoufa
 *
 */
public class AccountProxy implements Account{
	
	private AccountImpl accountImpl;
	
	public AccountProxy(AccountImpl accountImpl) {
		this.accountImpl = accountImpl;
	}
	
	@Override
	public void queryAccount() {
		System.out.println("查询账户前置消息");
		accountImpl.queryAccount();
		System.out.println("查询账户后置消息");
	}

	@Override
	public void updateAccount() {
		System.out.println("更新账户前置消息");
		accountImpl.updateAccount();
		System.out.println("更新账户后置消息");
	}
	
	
}
