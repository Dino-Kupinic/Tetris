package at.ac.htlsteyr.tetris.Saves;


import at.ac.htlsteyr.tetris.Model.Player;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JSONhandler {
    private final Path PATH_TO_JSON = Paths.get("src/main/java/at/ac/htlsteyr/tetris/savefile/player.json");
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public void writeToJSON(String name, int highscore) {
        String json = gson.toJson(new Player(name, highscore), Player.class);
    }
}
