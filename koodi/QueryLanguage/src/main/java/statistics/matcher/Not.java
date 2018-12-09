package statistics.matcher;

import java.util.List;
import statistics.Player;

public class Not implements Matcher {

    Matcher matcher;

    public Not(Matcher matcher) {
        this.matcher = matcher;
    }

    @Override
    public boolean matches(Player p) {
        return !matcher.matches(p);
    }
}
