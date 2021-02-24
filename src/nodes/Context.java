package nodes;

import java.util.HashMap;

public class Context {
    private final HashMap<String, Object> variables = new HashMap<>();

    public HashMap<String, Object> getVariables() {
        return variables;
    }

    public void addVariable(String key, Object value) {
        this.variables.put(key, value);
    }
}
