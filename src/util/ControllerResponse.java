package util;

public class ControllerResponse {
    private boolean isSuccessful;
    private String message;

    public ControllerResponse(boolean isSuccessful, String message) {
        this.isSuccessful = isSuccessful;
        this.message = message;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public String getMessage() {
        return message;
    }
}
