package com.factory.abstractFactory;

import com.factory.Meizu;
import com.factory.Product;
import com.factory.Xiaomi;

public class FoxconnFactory extends AbstractFactory {

	@Override
	public Product createXiaomi() {
		return new Xiaomi();
	}

	@Override
	public Product createMeizu() {
		return new Meizu();
	}

}
