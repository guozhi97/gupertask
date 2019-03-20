package com.factory.usuallyFactory;

import com.factory.Product;
import com.factory.Xiaomi;

public class XiaomiFactory extends UsuallyFactory {

	@Override
	public Product create() {
		return new Xiaomi();
	}

}
