package factory;

import org.junit.Test;

import com.iw.Apple;
import com.iw.AppleFactory;

public class AppleFactoryTest {
	
	
	@Test
	public void appFactoryTestCase() {
		AppleFactory appleFactory = new AppleFactory();
		Apple apple = appleFactory.getApple();
		apple.appleHello();
		
	}
}
