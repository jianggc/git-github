package com.jane;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JucApplicationTests {

	@Test
	public void contextLoads() {
//		System.out.println(Instant.now());
		System.out.println(123);
	}

	// for 循环
	@Test
	public void testFor(){

		Instant start = Instant.now();

		long sum = 0L;
//		for(long i = 0;i<=1000000000L;i++){
		for(long i = 0;i<=50000000000L;i++){
			sum+=i;
		}
		System.out.println(sum);
		Instant end = Instant.now();
		long millis = Duration.between(start, end).toMillis();//2526--22117
		System.out.println("耗费时间为 ： "+millis);
	}

	// java8新特性
	@Test
	public void test2(){
		Instant start = Instant.now();
//		long sum = LongStream.rangeClosed(0L, 1000000000L).parallel().reduce(0L, Long::sum);
		long sum = LongStream.rangeClosed(0L, 50000000000L).parallel().reduce(0L, Long::sum);
		System.out.println(sum);
		Instant end = Instant.now();
		long millis = Duration.between(start, end).toMillis();//1699--18909
		System.out.println("耗费时间为 ： "+millis);
	}

}
