/*
Excel file
----------------------------------
ns   | module  | path
----------------------------------
core | default | /version
     |         | categories/list
     |         | categories/query
vmm  | default | /vms/list
     |         | /vms/vm_disk
     | ahv     | /images/list
     | ahv     | /images/remove
----------------------------------


Read from file to string array

String[] input = {
"core,default,/version",
",,categories/list",
",,categories/query",
"vmm,default,/vms/list",
",,/vms/vm_disk",
",ahv,/images/list",
",ahv,/images/list"
};


Map : key is a string, value is an array
version => core, default
categories => core, default
vms => vmm, default
images => vmm, ahv

Implement a fuction to convert input string to a hash map then return the map. The key is a string and value is an array with 2 elements.
*/
import java.util.*;

public class Main{

  public Map getHashMap(String[] input) {
    //create a Map
    Map<String, String[]> map = new HashMap<>();
    String previousKey = "";

    for(String temp: input) {
      //Get each value from the input and split using ","
      String[] tokens = temp.split(",");
      String[] values = new String[input.length];
      //split the last token to get key value
      String key = "";
      //if it starts with a / and use keyToken[1]
      if(tokens[2].startsWith("/")) {
        key = tokens[2].split("/")[1];
//        System.out.println(key);
      }
      //else use keyToken[0]
      else
      {
        key = tokens[2].split("/")[0];
//        System.out.println(key);
      }

      if(!tokens[0].equals("") && !tokens[1].equals("")) {
        //if tokens are not empty
        values[0] = tokens[0];
//           System.out.println(values[0]);
        values[1] = tokens[1];
//           System.out.println(values[1]);
      }

      // if either one of them are empty, get the previous values from map and use them as values to this key
      if (tokens[0].equals("") && !tokens[1].equals("")) {
//        System.out.println("else");
//        System.out.println(previousKey);
        values[0] = map.get(previousKey)[0];
        values[1] = tokens[1];
      }
      if (!tokens[0].equals("") && tokens[1].equals("")) {
        values[0] = tokens[0];
        values[1] = map.get(previousKey)[1];
//        System.out.println(values1[0]);
//        System.out.println(values1[1]);
      }
      if (tokens[0].equals("") && tokens[1].equals("")) {
        values[0] = map.get(previousKey)[0];
        values[1] = map.get(previousKey)[1];
//        System.out.println(values1[0]);
//        System.out.println(values1[1]);
      }

      previousKey = key;

      map.put(key, values);
    }
    return map;
  }


  public static void main(String args[]) {
    String[] input = {
        "core,default,/version",
        ",,categories/list",
        ",,categories/query",
        "vmm,default,/vms/list",
        ",,/vms/vm_disk",
        ",ahv,/images/list",
        ",ahv,/images/list"
    };
    Main mainOutput = new Main();
    Map<String, String[]> map = mainOutput.getHashMap(input);
    for (String key : map.keySet())
    {
      String[] values = map.get(key);
      System.out.println(key+ " => [" + values[0]+","+values[1]+"]");
    }
  }
}
