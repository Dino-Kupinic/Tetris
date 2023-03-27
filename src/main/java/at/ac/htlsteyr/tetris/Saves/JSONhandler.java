package at.ac.htlsteyr.tetris.Saves;


import at.ac.htlsteyr.tetris.Model.Player;
import com.google.gson.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

public class JSONhandler {
    private final Path PATH_TO_JSON;
    private final File jsonFile;
    private final Gson gson;

    public JSONhandler() {
        PATH_TO_JSON = Paths.get("src/main/resources/at/ac/htlsteyr/tetris/savefile/player.json");
        jsonFile = new File(PATH_TO_JSON.toUri());
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    // TODO: Check for duplicate and if so, change value instead of append

    public void writeToJSON(String name, int highscore) {
        try {
            Player player = new Player(name, highscore);

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("name", player.name());
            jsonObject.addProperty("highscore", player.highscore());

            Scanner scanner = new Scanner(jsonFile);
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine());
            }

            JsonArray jsonArray = gson.fromJson(sb.toString(), JsonArray.class);
            jsonArray.add(jsonObject);

            String json = gson.toJson(jsonArray);
            FileWriter fW = new FileWriter(jsonFile);
            fW.write(json);
            fW.close();

            // Log action
            System.out.println("Wrote " + player + " to player.json!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Player getPlayerInfos(String playerName) {
        try {
            Scanner scanner = new Scanner(jsonFile);
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine());
            }

            JsonArray jsonArray = gson.fromJson(sb.toString(), JsonArray.class);

            System.out.println(jsonArray);
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonElement jsonElement = jsonArray.get(i);
                JsonObject jsonObject = jsonElement.getAsJsonObject();

                String name = jsonObject.get("name").toString().replaceAll("\"", "");
                int highscore = jsonObject.get("highscore").getAsInt();
                if (Objects.equals(name, playerName)) {
                    return new Player(name, highscore);
                }
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
