package test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class Test1Test {

	@Test
	public void testTestSpeak1() {
		Test1 test1 = new Test1();
		String t1 = test1.testSpeak(1);
		assertThat(t1,is("Hello"));
	}
	@Test
	public void testTestSpeak2() {
		Test1 test1 = new Test1();
		String t1  = test1.testSpeak(2);
		assertThat(t1,is("こんにちは"));
		}

	@Test
	public void testTestSpeak3() {
		Test1 test1 = new Test1();
		String t1  = test1.testSpeak(3);
		assertThat(t1,is("1or2を入力してください"));
		}

	}


