package com.cl.elena.serialization.locale;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import static java.lang.System.out;

public class JOLSample_25_ArrayAlignment {

	public static void main(String[] args) throws Exception {
		out.println(VM.current().details());
		out.println(ClassLayout.parseInstance(new long[0]).toPrintable());
		for (int size = 0; size <= 8; size++) {
			out.println(ClassLayout.parseInstance(new byte[size]).toPrintable());
		}
	}

}
