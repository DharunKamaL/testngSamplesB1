package parallelScripts;

import org.testng.annotations.Test;

public class SampleOneTest {
  @Test
  public void testOne() {
	  long id = Thread.currentThread().getId();
	  System.out.println("This is the testOne from SampleOneTest "+id);
  }
  
  @Test
  public void testTwo() {
	  long id = Thread.currentThread().getId();
	  System.out.println("This is the testTwo from SampleOneTest "+id);
  }
  
  @Test
  public void testThree() {
	  long id = Thread.currentThread().getId();
	  System.out.println("This is the testThree from SampleOneTest "+id);
  }
  
  @Test
  public void testFour() {
	  long id = Thread.currentThread().getId();
	  System.out.println("This is the testFour from SampleOneTest "+id);
  }
  
  @Test//(threadPoolSize = 5, invocationCount = 10, timeOut = 10000)
  public void testFive() {
	  long id = Thread.currentThread().getId();
	  System.out.println("This is the testFive from SampleOneTest "+id);
  }
}
