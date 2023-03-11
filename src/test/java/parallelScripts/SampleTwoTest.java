package parallelScripts;

import org.testng.annotations.Test;

public class SampleTwoTest {
	@Test(groups = "feature 3")
	public void testOne() {
		System.out.println("This is the testOne from SampleTwoTest");
	}

	@Test(groups = "feature 3")
	public void testTwo() {
		System.out.println("This is the testTwo from SampleTwoTest");
	}

	@Test(groups = "feature 3")
	public void testThree() {
		System.out.println("This is the testThree from SampleTwoTest");
	}

	@Test
	public void testFour() {
		System.out.println("This is the testFour from SampleTwoTest");
	}

	@Test
	public void testFive() {
		System.out.println("This is the testFive from SampleTwoTest");
	}
}
