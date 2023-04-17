package at.ac.htlsteyr.tetris.Saves;


import at.ac.htlsteyr.tetris.Model.Player;
import com.google.gson.*;

import java.io.File;
import java.io.FileNotFoundException;
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
        PATH_TO_JSON = Paths.get("src/main/resources/at/ac/htlsteyr/tetris/jsons/player.json");
        jsonFile = new File(PATH_TO_JSON.toUri());
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void writeToJSON(String name, int highscore) {
        try {
            Player player = new Player(name, highscore);

            Scanner scanner = new Scanner(jsonFile);
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine());
            }

            if (checkIfAlreadyInJSON(sb.toString(), name)) {
                JsonArray jsonArray = gson.fromJson(sb.toString(), JsonArray.class);
                for (JsonElement element : jsonArray) {
                    JsonObject object = element.getAsJsonObject();
                    if (object.get("name").getAsString().equals(name)) {
                        object.addProperty("highscore", highscore);
                        break;
                    }
                }

                String json = gson.toJson(jsonArray);
                FileWriter fW = new FileWriter(jsonFile);
                fW.write(json);
                fW.close();

                // Log action
                System.out.println("Changed " + player.getName() + " in player.json!");
            } else {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("name", player.getName());
                jsonObject.addProperty("highscore", player.getHighscore());

                JsonArray jsonArray = gson.fromJson(sb.toString(), JsonArray.class);
                jsonArray.add(jsonObject);

                String json = gson.toJson(jsonArray);
                FileWriter fW = new FileWriter(jsonFile);
                fW.write(json);
                fW.close();

                // Log action
                System.out.println("Wrote " + player + " to player.json!");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletePlayerFromJSON(String name) throws IOException {
        Scanner scanner = new Scanner(jsonFile);
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine());
        }

        int index = -1;
        JsonArray array = gson.fromJson(sb.toString(), JsonArray.class);
        for (int i = 0; i < array.size(); i++) {
            JsonObject object = array.get(i).getAsJsonObject();
            if (object.get("name").getAsString().equals(name)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            array.remove(index);
        }

        String json = gson.toJson(array);
        FileWriter fW = new FileWriter(jsonFile);
        fW.write(json);
        fW.close();
    }

    private boolean checkIfAlreadyInJSON(String jsonString, String name) {
        return jsonString.contains(name);
    }

    public void checkIfJSONisValid() throws IOException {
        try {
            Scanner scanner = new Scanner(jsonFile);
            if (!scanner.hasNextLine()) {
                addJSONArray(jsonFile);
            }
        } catch (FileNotFoundException e) {
            File f = new File(PATH_TO_JSON.toUri());
            addJSONArray(f);
        }

    }

    private void addJSONArray(File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("[]");
        fileWriter.close();
    }

    public Player getPlayerInfos(String playerName) {
        try {
            Scanner scanner = new Scanner(jsonFile);
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine());
            }

            JsonArray jsonArray = gson.fromJson(sb.toString(), JsonArray.class);

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
