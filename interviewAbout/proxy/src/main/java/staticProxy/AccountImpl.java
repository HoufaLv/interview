package staticProxy;

/**
 * 账户接口的实现类
 * @author Lvhoufa
 *
 */
public class AccountImpl implements Account{

	@Override
	public void queryAccount() {
		System.out.println("AccountImpl.queryAccount()");
	}

	@Override
	public void updateAccount() {
		System.out.println("AccountImpl.updateAccount()");
	}

}
