package exceptions;

import compiler.ParseException;

public class NumericException extends ParseException {
    public NumericException(String variableName) {
        super("Incompatible numeric variable {" + variableName + "} with non numeric value");
    }
}
