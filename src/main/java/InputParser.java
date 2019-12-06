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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class InputParser{

  private static final Logger LOGGER = LogManager.getLogger(InputParser.class.getName());

  HashMap<String, String[]> getHashMap(String[] input) throws InvalidInputException {
    //validate input
    if(input == null) {
      LOGGER.error("Invalid Input");
      throw new NullPointerException("Invalid input");
    }

    LOGGER.info("Converting input string to HashMap");
    HashMap<String, String[]> map = new HashMap<>(); //Creating an empty Map to store the result
    String previousKey = "";                        //String to store previous key

    for(String temp: input) {
      //Split input using delimiter ","
      String[] tokens = temp.split(",");
      int tokenLength = tokens.length;
      String key;

      if(tokenLength<= 1) {
        throw new InvalidInputException("Invalid key error");
      }

      LOGGER.debug("Retrieving Key");
      //if key starts with "/" and the string after "/"
      if(tokens[tokenLength-1].startsWith("/")) {
        key = tokens[tokenLength-1].split("/")[1];
      }
      //else use string before "/"
      else {
        key = tokens[tokenLength-1].split("/")[0];
      }

      LOGGER.debug("Retrieving Values");
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
      LOGGER.debug("Updating map");
      map.put(key, values);
    }
    return map;
  }
}
