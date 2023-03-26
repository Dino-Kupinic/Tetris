package at.ac.htlsteyr.tetris.Saves;


import at.ac.htlsteyr.tetris.Model.Player;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.nio.file.Path;
import java.nio.file.Paths;

public class JSONhandler {
    private final Path PATH_TO_JSON = Paths.get("src/main/java/at/ac/htlsteyr/tetris/savefile/player.json");

    public void writeToJSON(String name, int highscore) {
        Gson gson = new Gson();
        Player p = new Player(name, highscore);
        String json = gson.toJson(p);
        System.out.println(json);
    }
}
