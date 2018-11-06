package com.web.maven.test;

import org.junit.Test;

public class TestController {

	@Test
	public void testAES(){
//		byte[] decode = org.apache.shiro.codec.Base64.decode("4AvVhmFLUs0KTA3Kprsdag==");
		byte[] decode = org.apache.shiro.codec.Base64.decode("lT2UvDUmQwewm6mMoiw4Ig==");
		System.out.println(decode.length);
		String encodeToString = org.apache.shiro.codec.Base64.encodeToString("januszll".getBytes());
		System.out.println("encodeToString : "+encodeToString);
		byte[] decode2 = org.apache.shiro.codec.Base64.decode(encodeToString);
		System.out.println(decode2.length);
		byte[] testAEs={-107, 61, -108, -68, 53, 38, 67, 7, -80, -101, -87, -116, -94, 44, 56, 34};
		String encodeToString2 = org.apache.shiro.codec.Base64.encodeToString(testAEs);
		System.out.println("encodeToString2 : "+encodeToString2);
	}
}
