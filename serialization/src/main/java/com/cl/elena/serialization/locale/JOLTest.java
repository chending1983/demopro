package com.cl.elena.serialization.locale;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import static java.lang.System.out;

public class JOLTest {

	
	public static void main(String[] args) throws Exception {
        out.println(VM.current().details());
        out.println(ClassLayout.parseClass(Object.class).toPrintable());
    }

    public static class A {
    }

}
