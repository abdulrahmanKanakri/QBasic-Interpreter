package exceptions;

import compiler.ParseException;

public class StringException extends ParseException {
    public StringException(String variableName) {
        super("Incompatible string variable {" + variableName + "} with non string value");
    }
}
