/*-----------------------------------------------------------------------------
 *              Hoehere Technische Bundeslehranstalt STEYR
 *----------------------------------------------------------------------------*/
/**
 * Kurzbeschreibung
 *
 * @author : Dino Kupinic
 * @date : 22.4.2023
 *
 * @details
 * Class to easily handle JSON
 */

package at.ac.htlsteyr.tetris.Saves;


import at.ac.htlsteyr.tetris.Model.Controls;
import at.ac.htlsteyr.tetris.Model.Player;
import com.google.gson.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

public class JSONhandler {
    private final Path PATH_TO_SAVE_JSON;
    private final Path PATH_TO_CONTROLS_JSON;
    private final File saveJSONfile;
    private final File controlsJSONfile;
    private final Gson gson;

    public JSONhandler() {
        PATH_TO_SAVE_JSON = Paths.get("src/main/resources/at/ac/htlsteyr/tetris/jsons/player.json");
        PATH_TO_CONTROLS_JSON = Paths.get("src/main/resources/at/ac/htlsteyr/tetris/jsons/controls.json");
        saveJSONfile = new File(PATH_TO_SAVE_JSON.toUri());
        controlsJSONfile = new File(PATH_TO_CONTROLS_JSON.toUri());
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * adds a player object to the JSON array in player.json
     * @param name name of the player
     * @param highscore highscore of the player
     */
    public void writePlayerToSaveJSON(String name, int highscore) {
        try {
            Player player = new Player(name, highscore);

            StringBuilder sb = getStringBuilder(saveJSONfile);

            if (checkIfNameAlreadyInJSON(sb.toString(), name)) {
                JsonArray jsonArray = gson.fromJson(sb.toString(), JsonArray.class);
                for (JsonElement element : jsonArray) {
                    JsonObject object = element.getAsJsonObject();
                    if (object.get("name").getAsString().equals(name)) {
                        object.addProperty("highscore", highscore);
                        break;
                    }
                }

                String json = gson.toJson(jsonArray);
                FileWriter fW = new FileWriter(saveJSONfile);
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
                FileWriter fW = new FileWriter(saveJSONfile);
                fW.write(json);
                fW.close();

                // Log action
                System.out.println("Wrote " + player + " to player.json!");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * deletes a player object from the JSON array
     * @param name name of the player
     * @throws IOException thrown when something goes wrong with accessing the file
     */
    public void deletePlayerFromSaveJSON(String name) throws IOException {
        StringBuilder sb = getStringBuilder(saveJSONfile);

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
        FileWriter fW = new FileWriter(saveJSONfile);
        fW.write(json);
        fW.close();
    }

    /**
     * utility function read a whole file with a string builder
     * @param file the file which is supposed to be read
     * @return StringBuilder
     * @throws FileNotFoundException Thrown when the target file can't be found
     */
    private StringBuilder getStringBuilder(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine());
        }
        return sb;
    }

    /**
     * Checks if a given name is already inside the player JSON
     * @param jsonString file content as a string
     * @param name name of the player
     * @return boolean whether the name is present
     */
    private boolean checkIfNameAlreadyInJSON(String jsonString, String name) {
        return jsonString.contains(name);
    }

    /**
     * Checks if the player JSON is valid
     * @throws IOException Thrown when something goes wrong when trying to access the file
     */
    public void checkIfSaveJSONisValid() throws IOException {
        try {
            Scanner scanner = new Scanner(saveJSONfile);
            if (!scanner.hasNextLine()) {
                addJSONArray(saveJSONfile);
            }
        } catch (FileNotFoundException e) {
            File f = new File(PATH_TO_SAVE_JSON.toUri());
            addJSONArray(f);
        }
    }

    /**
     * Checks if the controls JSON is valid
     * @throws IOException Thrown when something goes wrong when trying to access the file
     */
    public void checkIfControlsJSONisValid() throws IOException {
        try {
            Scanner scanner = new Scanner(controlsJSONfile);
            if (!scanner.hasNextLine()) {
                addJSONArray(controlsJSONfile);
            }
        } catch (FileNotFoundException e) {
            File f = new File(PATH_TO_CONTROLS_JSON.toUri());
            addJSONArray(f);
        }
    }

    /**
     * Adds an array initializer when the JSON is empty
     * @param file target file
     * @throws IOException Thrown when something goes wrong when trying to access the file
     */
    private void addJSONArray(File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("[]");
        fileWriter.close();
    }

    /**
     * Gets the name and highscore from a player in the player.json
     * @param playerName the name of the player
     * @return player object
     */
    public Player getPlayerInfos(String playerName) {
        try {
            StringBuilder sb = getStringBuilder(saveJSONfile);

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

    /**
     * reads all players from the player.json
     * @return observable list with all players
     */
    public ObservableList<Player> getAllPlayers() {
        try {
            StringBuilder sb = getStringBuilder(saveJSONfile);
            JsonArray jsonArray = gson.fromJson(sb.toString(), JsonArray.class);
            ObservableList<Player> players = FXCollections.observableArrayList();

            for (int i = 0; i < jsonArray.size(); i++) {
                JsonElement jsonElement = jsonArray.get(i);
                Player player = gson.fromJson(jsonElement, Player.class);
                players.add(player);
            }
            return players;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * gets infos about the controls object
     * @return controls object
     */
    public Controls getControlsInfos() {
        try {
            StringBuilder sb = getStringBuilder(controlsJSONfile);
            return gson.fromJson(sb.toString(), Controls.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * writes a controls JSON object to the JSOn
     * @param controlsObject object containing the information
     */
    public void writeControlsToJSON(Controls controlsObject) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("moveRight", controlsObject.getMoveRight());
            jsonObject.addProperty("moveLeft", controlsObject.getMoveLeft());
            jsonObject.addProperty("fastDrop", controlsObject.getFastDrop());
            jsonObject.addProperty("rotate", controlsObject.getRotate());
            jsonObject.addProperty("hold", controlsObject.getHold());
            jsonObject.addProperty("softDrop", controlsObject.getSoftdrop());
            jsonObject.addProperty("musicCheckBox", controlsObject.getMusicCheckbox());

            String json = gson.toJson(jsonObject);
            FileWriter fW = new FileWriter(controlsJSONfile);
            fW.write(json);
            fW.close();

            // Log action
            System.out.println("Wrote controls to controls.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
