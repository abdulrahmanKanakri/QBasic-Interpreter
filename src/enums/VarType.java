package enums;

public enum VarType {
    INTEGER("%"),
    LONG("&"),
    SINGLE("!"), // default
    DOUBLE("#"),
    STRING("$");

    private final String value;

    VarType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
