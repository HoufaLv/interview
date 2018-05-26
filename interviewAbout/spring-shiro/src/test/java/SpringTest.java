import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.iw.entity.User;

public class SpringTest {
	
	@Test
	public void springTestCase() {
		
		//使用ClassPathXmlApplicationContext 来读取配置文件获取 应用程序上下文
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
		
		User user = (User) applicationContext.getBean("User");
		user.setName("Tom");
		user.setAge(11);
		
		System.out.println(user.toString());
		
		user.sayHello();
		
	}
}
