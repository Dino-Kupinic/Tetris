package at.ac.htlsteyr.tetris.Saves;


import at.ac.htlsteyr.tetris.Model.Player;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JSONhandler {
    private final Path PATH_TO_JSON;
    private final File jsonFile;
    private final Gson gson;

    public JSONhandler() {
        PATH_TO_JSON = Paths.get("src/main/resources/at/ac/htlsteyr/tetris/savefile/player.json");
        jsonFile = new File(PATH_TO_JSON.toUri());
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void writeToJSON(String name, int highscore) {
        try {
            // Currently data always get overwritten
            // TODO: Save data from player.json and append new data
            Player player = new Player(name, highscore);
            String json = gson.toJson(player);
            FileWriter fW = new FileWriter(jsonFile);
            fW.write(json);
            fW.close();

            // Log action
            System.out.println("Wrote " + player + " to player.json!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
