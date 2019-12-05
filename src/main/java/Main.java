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
import java.util.HashMap;

public class Main{

  HashMap<String, String[]> getHashMap(String[] input) {

    HashMap<String, String[]> map = new HashMap<>(); //Creating an empty Map to store the result
    String previousKey = "";                        //String to store previous key

    for(String temp: input) {
      //Split input using delimiter ","
      String[] tokens = temp.split(",");
      int tokenLength = tokens.length;
      String key;

      //Getting key value
      //if key starts with "/" and the string after "/"
      if(tokens[tokenLength-1].startsWith("/")) {
        key = tokens[tokenLength-1].split("/")[1];
      }
      //else use string before "/"
      else {
        key = tokens[tokenLength-1].split("/")[0];
      }

      //Getting values
      String[] values = new String[input.length];

      for(int i=0; i<tokenLength-1; i++) {

        //if token is empty, get previously used token from the map
        if(tokens[i].equals("")) {
          values[i] = map.get(previousKey)[i];
        }
        else {
          values[i] = tokens[i];
        }
      }
      previousKey = key;
      map.put(key, values);
    }
    return map;
  }

  public static void main(String[] args) {
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
    HashMap<String, String[]> map = mainOutput.getHashMap(input);
    for (String key : map.keySet()) {
      String[] values = map.get(key);
      System.out.println(key+ " => [" + values[0]+","+values[1]+"]");
    }
  }
}
