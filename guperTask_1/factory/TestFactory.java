package com.factory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.factory.abstractFactory.AbstractFactory;
import com.factory.abstractFactory.FoxconnFactory;
import com.factory.simpleFactory.SimpleFactory;
import com.factory.usuallyFactory.MeizuFactory;
import com.factory.usuallyFactory.UsuallyFactory;
import com.factory.usuallyFactory.XiaomiFactory;

class TestFactory {

	@Test
	void testSimpleFactory() {
		Meizu p1 = SimpleFactory.createMeizu();
		Xiaomi p2 = SimpleFactory.createXiaomi();

		System.out.println("SimpleFactory: ");
		System.out.println(p1.getClass().getName());
		p1.os();
		System.out.println(p2.getClass().getName());
		p2.os();
	}
	
	@Test
	void testFactory() {
		UsuallyFactory factory = null;
		
		factory  = new MeizuFactory();
		Meizu p1 = (Meizu)factory.create();
		
		factory = new XiaomiFactory();
		Xiaomi p2 = (Xiaomi)factory.create();
		
		System.out.println("UsuallyFactory:");
		System.out.println(p1.getClass().getName());
		p1.os();
		System.out.println(p2.getClass().getName());
		p2.os();
		
	}
	
	@Test
	void testAbstractFactory() {
		AbstractFactory factory = new FoxconnFactory();
		
		Meizu p1 =(Meizu) factory.createMeizu();
		Xiaomi p2 = (Xiaomi) factory.createXiaomi();
		
		System.out.println("abstract factory: ");
		System.out.println(p1.getClass().getName());
		p1.os();
		System.out.println(p2.getClass().getName());
		p2.os();
		
	}

}
