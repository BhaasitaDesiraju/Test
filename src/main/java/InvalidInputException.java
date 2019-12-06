public class InvalidInputException extends java.io.IOException {

    private String message;

    InvalidInputException(String message) {
      this.message = message;
    }

    //Returns the invalid file format message
    public String getMessage() {
      return message;
    }

}
