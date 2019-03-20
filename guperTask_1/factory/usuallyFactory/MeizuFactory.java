package com.factory.usuallyFactory;

import com.factory.Meizu;
import com.factory.Product;

public class MeizuFactory extends UsuallyFactory {

	@Override
	public Product create() {
		return new Meizu();
	}
	
}
