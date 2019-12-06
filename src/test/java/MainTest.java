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
  private InputParser mainOutput = new InputParser();
  private HashMap<String, String[]> map;
  {
    try {
      map = mainOutput.getHashMap(input);
    }
    catch (InvalidInputException e) {
      e.printStackTrace();
    }
  }

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

  @Test (expectedExceptions = {NullPointerException.class})
  public void inputMismatchError() throws InvalidInputException {
    String[] sampleInput = new String[10];
    HashMap<String, String[]> sampleMap = mainOutput.getHashMap(sampleInput);
    Assert.assertNull(sampleMap);
  }

  @Test (expectedExceptions = {InvalidInputException.class})
  public void invalidKeyError() throws InvalidInputException {
    String[] sampleInput = {
        "a,b,/x",
        ",,",
        ",c,/q",
        "d,,r/s"
    };
    HashMap<String, String[]> sampleMap = mainOutput.getHashMap(sampleInput);
    Assert.assertNull(sampleMap);
  }

  @Test
  public void validateOutput() throws InvalidInputException {
    String[] inputString = {
        "core,default,/version",
        ",,categories/list",
        ",,categories/query",
        "vmm,default,/vms/list",
        ",,/vms/vm_disk",
        ",ahv,/images/list",
        ",ahv,/images/list"
    };
    HashMap<String, String[]> sampleMap = mainOutput.getHashMap(inputString);
    HashMap<String, String[]> outputMap = new HashMap<>();
    outputMap.put("images", new String[]{"vmm", "ahv"});
    outputMap.put("categories", new String[]{"core", "default"});
    outputMap.put("version", new String[]{"core", "default"});
    outputMap.put("vms", new String[]{"vmm", "default"});

    Assert.assertEquals(sampleMap.keySet(), outputMap.keySet());
    for (String key : sampleMap.keySet()) {
      String[] resultValues = sampleMap.get(key);
      String[] outputValues = outputMap.get(key);
      Assert.assertEquals(resultValues[0], outputValues[0]);
      Assert.assertEquals(resultValues[1], outputValues[1]);
    }
  }
}
