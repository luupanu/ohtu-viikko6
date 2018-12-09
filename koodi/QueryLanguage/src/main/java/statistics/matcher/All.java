package statistics.matcher;

import java.util.List;
import statistics.Player;

public class All implements Matcher {

    private Matcher matcher;
    private List<Player> players;

    public All(Matcher matcher, List<Player> players) {
        this.matcher = matcher;
        this.players = players;
    }

    @Override
    public boolean matches(Player p) {
        for (Player player : players) {
            if (!matcher.matches(player)) {
                return false;
            }
        }

        return true;
    }
}
