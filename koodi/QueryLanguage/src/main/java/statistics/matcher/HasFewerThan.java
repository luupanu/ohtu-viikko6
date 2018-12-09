
package statistics.matcher;

import java.lang.reflect.Method;
import statistics.Player;

public class HasFewerThan implements Matcher {
    
    private int value;
    private String fieldName;

    public HasFewerThan(int value, String category) {
        this.value = value;
        fieldName = "get"+Character.toUpperCase(category.charAt(0))+category.substring(1, category.length());
    }

    @Override
    public boolean matches(Player p) {
        return new Not(new HasAtLeast(value, fieldName)).matches(p);
    }    
    
}
