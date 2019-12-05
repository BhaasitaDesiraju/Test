import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

@Test
public class MainTest {

  String[] input = {
      "a,b,/x",
      ",,p/l",
      ",c,/q"
  };
  Main mainOutput = new Main();
  Map<String, String[]> map = mainOutput.getHashMap(input);

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
    //When both values are present
    String key = "p";
    String[] result = {"a","b"};
    String[] values = map.get(key);
    Assert.assertEquals(values[0], result[0]);
    Assert.assertEquals(values[1], result[1]);
  }

  @Test
  public void testWhenOnlyOneValueIsPresent() {
    //When both values are present
    String key = "q";
    String[] result = {"a","c"};
    String[] values = map.get(key);
    Assert.assertEquals(values[0], result[0]);
    Assert.assertEquals(values[1], result[1]);
  }
}
