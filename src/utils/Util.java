package utils;

import compiler.ParseException;
import enums.VarType;
import nodes.*;
import java.util.Scanner;

public class Util {
    private static final Scanner scanner = new Scanner(System.in);
    public static String getStringContent(String str) {
        return str.substring(1, str.length() - 1);
    }

    public static boolean isString(String str) {
        return str.charAt(0) == '"' && str.charAt(str.length() - 1) == '"';
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    public static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    public static boolean isLong(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDouble(String str) {
        return isNumeric(str);
    }

    public static VarType getVariableType(String str) {
        switch (str.substring(str.length() - 1)) {
            case "%":
                return VarType.INTEGER;
            case "&":
                return VarType.LONG;
            case "$":
                return VarType.STRING;
            case "#":
                return VarType.DOUBLE;
            case "!":
            default:
                return VarType.SINGLE;
        }
    }

    public static Object performOperation(Context context, String op, Node node1, Node node2) throws Exception {
        Object left = node1.run(context);
        Object right = node2.run(context);
        switch (op) {
            case "+":
                if(left instanceof String && right instanceof String) {
                    return left + (String) right;
                } else if(left instanceof Number && right instanceof Number) {
                    if(left instanceof Double || right instanceof Double) {
                        return parseDouble(left) + parseDouble(right);
                    } else if(left instanceof Long || right instanceof Long) {
                        return parseLong(left) + parseLong(right);
                    }
                    return parseInt(left) + parseInt(right);
                } else {
                    throw new Exception("Incompatible operator '" + op + "' with STRING,NUMBER");
                }
            case "-":
                if(left instanceof Number && right instanceof Number) {
                    if(left instanceof Double || right instanceof Double) {
                        return parseDouble(left) - parseDouble(right);
                    } else if(left instanceof Long || right instanceof Long) {
                        return parseLong(left) - parseLong(right);
                    }
                    return parseInt(left) - parseInt(right);
                } else {
                    throw new Exception("Incompatible operator '" + op + "' with STRING");
                }
            case "*":
                if(left instanceof Number && right instanceof Number) {
                    if(left instanceof Double || right instanceof Double) {
                        return parseDouble(left) * parseDouble(right);
                    } else if(left instanceof Long || right instanceof Long) {
                        return parseLong(left) * parseLong(right);
                    }
                    return parseInt(left) * parseInt(right);
                } else {
                    throw new Exception("Incompatible operator '" + op + "' with STRING");
                }
            case "/":
                if(left instanceof Number && right instanceof Number) {
                    double res = parseDouble(left) / parseDouble(right);
                    if(res == Math.floor(res)) {
                        return (int) res;
                    }
                    return res;
                } else {
                    throw new Exception("Incompatible operator '" + op + "' with STRING");
                }
            case "\\":
                if(left instanceof Number && right instanceof Number) {
                    return (int) Math.floor(parseDouble(left) / parseDouble(right));
                } else {
                    throw new Exception("Incompatible operator '" + op + "' with STRING");
                }
            case "MOD":
                if(left instanceof Number && right instanceof Number) {
                    if(left instanceof Double || right instanceof Double) {
                        return parseDouble(left) % parseDouble(right);
                    } else if(left instanceof Long || right instanceof Long) {
                        return parseLong(left) % parseLong(right);
                    }
                    return parseInt(left) % parseInt(right);
                } else {
                    throw new Exception("Incompatible operator '" + op + "' with STRING");
                }
            case "^":
                if(left instanceof Number && right instanceof Number) {
                    if(left instanceof Double || right instanceof Double) {
                        return Math.pow(parseDouble(left), parseDouble(right));
                    } else if(left instanceof Long || right instanceof Long) {
                        return (long) Math.pow(parseLong(left), parseLong(right));
                    }
                    return (int) Math.pow(parseInt(left), parseInt(right));
                } else {
                    throw new Exception("Incompatible operator '" + op + "' with STRING");
                }
            case "OR":
                if(left instanceof Number && right instanceof Number) {
                    return Math.round(parseDouble(left)) | Math.round(parseDouble(right));
                } else {
                    throw new Exception("Incompatible operator '" + op + "' with non INTEGER");
                }
            case "AND":
                if(left instanceof Number && right instanceof Number) {
                    return Math.round(parseDouble(left)) & Math.round(parseDouble(right));
                } else {
                    throw new Exception("Incompatible operator '" + op + "' with non INTEGER");
                }
            case "<>":
                if(left instanceof String && right instanceof String) {
                    return !left.toString().equals(right) ? -1 : 0;
                } else if(left instanceof Number && right instanceof Number) {
                    return parseDouble(left) != parseDouble(right) ? -1 : 0;
                } else {
                    throw new Exception("Incompatible operator '" + op + "' with STRING,NUMBER");
                }
            case "=":
                if(left instanceof String && right instanceof String) {
                    return left.toString().equals(right) ? -1 : 0;
                } else if(left instanceof Number && right instanceof Number) {
                    return parseDouble(left) == parseDouble(right) ? -1 : 0;
                } else {
                    throw new Exception("Incompatible operator '" + op + "' with STRING,NUMBER");
                }
            case ">":
                if(left instanceof String && right instanceof String) {
                    return ((String) left).compareTo(String.valueOf(right)) > 0 ? -1 : 0;
                } else if(left instanceof Number && right instanceof Number) {
                    return parseDouble(left) > parseDouble(right) ? -1 : 0;
                } else {
                    throw new Exception("Incompatible operator '" + op + "' with STRING,NUMBER");
                }
            case ">=":
                if(left instanceof String && right instanceof String) {
                    return ((String) left).compareTo(String.valueOf(right)) >= 0 ? -1 : 0;
                } else if(left instanceof Number && right instanceof Number) {
                    return parseDouble(left) >= parseDouble(right) ? -1 : 0;
                } else {
                    throw new Exception("Incompatible operator '" + op + "' with STRING,NUMBER");
                }
            case "<":
                if(left instanceof String && right instanceof String) {
                    return ((String) left).compareTo(String.valueOf(right)) < 0 ? -1 : 0;
                } else if(left instanceof Number && right instanceof Number) {
                    return parseDouble(left) < parseDouble(right) ? -1 : 0;
                } else {
                    throw new Exception("Incompatible operator '" + op + "' with STRING,NUMBER");
                }
            case "<=":
                if(left instanceof String && right instanceof String) {
                    return ((String) left).compareTo(String.valueOf(right)) <= 0 ? -1 : 0;
                } else if(left instanceof Number && right instanceof Number) {
                    return parseDouble(left) <= parseDouble(right) ? -1 : 0;
                } else {
                    throw new Exception("Incompatible operator '" + op + "' with STRING,NUMBER");
                }
            default:
                return null;
        }
    }

    public static double parseDouble(Object o) {
        return Double.parseDouble(o.toString());
    }

    public static int parseInt(Object o) {
        if(isInt(o.toString())) {
            return Integer.parseInt(o.toString());
        }
        return (int) Math.round(parseDouble(o));
    }

    public static long parseLong(Object o) {
        if(isLong(o.toString())) {
            return Long.parseLong(o.toString());
        }
        return Math.round(parseDouble(o));
    }

    public static int readInt() {
        int d;
        d = scanner.nextInt();
        scanner.nextLine();
        return d;
    }

    public static long readLong() {
        long d;
        d = scanner.nextLong();
        scanner.nextLine();
        return d;
    }

    public static double readDouble() {
        double d;
        d = scanner.nextDouble();
        scanner.nextLine();
        return d;
    }

    public static String readString() {
        String s;
        s = scanner.nextLine();
        return s;
    }

    public static boolean getCondition(Object condition) throws ParseException {
        if(condition instanceof String) {
            throw new ParseException("Expected numeric expression after 'WHILE'");
        }
        return parseDouble(condition) != 0;
    }
}
