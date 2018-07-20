package test;

import java.util.Date;

import org.junit.Test;

public class Test2Test {

	@Test
	public void 日付の比較() throws Exception {
		Date date = new Date();
		assertthat(date,is(dateOf("2011,2,10")));

	}

}
