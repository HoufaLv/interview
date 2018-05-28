package com.iw;

import org.springframework.beans.factory.FactoryBean;

public class SpringAppleFactory implements FactoryBean<Apple>{

	@Override
	public Apple getObject() throws Exception {
		return new Apple();
	}

	@Override
	public Class<?> getObjectType() {
		return Apple.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
