package com.cl.elena.serialization.locale;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class ObjectTest {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception{
//		ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("./POJO.data"));
//        POJO person = new POJO();
//        person.setName("Samuel");
//        person.setAge(18);
//        stream.writeObject(person);
//        stream.close();
        ObjectInputStream insInputStream = new ObjectInputStream(new FileInputStream("./POJO.data"));
        
        
	}
}
