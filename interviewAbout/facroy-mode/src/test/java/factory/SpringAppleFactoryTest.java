package factory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iw.Apple;
import com.iw.AppleFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class SpringAppleFactoryTest {
	
	@Autowired
	private AppleFactory appleFactory;

	@Test
	public void springAppleFactoryBeanTestCase() {
		Apple apple = appleFactory.getApple();
		
		apple.appleHello();
	}

}
