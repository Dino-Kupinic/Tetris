package at.ac.htlsteyr.tetris.Saves;


import at.ac.htlsteyr.tetris.Model.Player;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JSONhandler {
    Gson gson;

    public JSONhandler() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void writeToJSON(String name, int highscore) {
        String json = gson.toJson(new Player(name, highscore));
        try {
            FileWriter fileWriter = new FileWriter("src/main/java/at/ac/htlsteyr/tetris/savefile/player.json");
            fileWriter.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
