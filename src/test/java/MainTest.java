import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;

@Test
public class MainTest {

 private String[] input = {
      "a,b,/x",
      ",,p/l",
      ",c,/q",
      "d,,r/s"
  };
  private Main mainOutput = new Main();
  private HashMap<String, String[]> map = mainOutput.getHashMap(input);

  @Test
  public void keyParserCheck() {
    //Checking if key values are being stored correctly
    Assert.assertTrue(map.containsKey("x"));
    Assert.assertTrue(map.containsKey("p"));
    Assert.assertFalse(map.containsKey("l"));
  }

  @Test
  public void testWhenBothValuesArePresent() {
    //When both values are present
    String key = "x";
    String[] result = {"a","b"};
    String[] values = map.get(key);
    Assert.assertEquals(values[0], result[0]);
    Assert.assertEquals(values[1], result[1]);
  }

  @Test
  public void testWhenNoValuesArePresent() {
    //When no values are present
    String key = "p";
    String[] result = {"a","b"};
    String[] values = map.get(key);
    Assert.assertEquals(values[0], result[0]);
    Assert.assertEquals(values[1], result[1]);
  }

  @Test
  public void test1WhenOnlyOneValueIsPresent() {
    //When both values are present
    String key = "q";
    String[] result = {"a","c"};
    String[] values = map.get(key);
    Assert.assertEquals(values[0], result[0]);
    Assert.assertEquals(values[1], result[1]);
  }

  @Test
  public void test2WhenOnlyOneValueIsPresent() {
    //When both values are present
    String key = "r";
    String[] result = {"d","c"};
    String[] values = map.get(key);
    Assert.assertEquals(values[0], result[0]);
    Assert.assertEquals(values[1], result[1]);
  }
}
