package com.factory.simpleFactory;

import com.factory.Meizu;
import com.factory.Xiaomi;

public class SimpleFactory {
	
	public static Meizu createMeizu() {
		return new Meizu();
	}
	
	public static Xiaomi createXiaomi() {
		return new Xiaomi();
	}
}
