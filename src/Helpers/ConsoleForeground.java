package Helpers;

public enum ConsoleForeground {
    RESET("\u001B[0m"),
    BLACK("\u001B[30m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    PURPLE("\u001B[35m");

    private final String Value;

    // Constructeur :
    ConsoleForeground(String value) {
        this.Value =value;
    }

    //getters:

    //getters:
    public String getValue() {
        return this.Value;
    }


}
