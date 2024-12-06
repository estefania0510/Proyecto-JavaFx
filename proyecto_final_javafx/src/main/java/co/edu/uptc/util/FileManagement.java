package co.edu.uptc.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FileManagement<T> {
    private static final Gson gson = new Gson();

    public boolean saveObject(List<T> objects, String filePath, Type type) {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(objects, writer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public List<T> readObjects(String filePath, Type type) {
        try {
            createFileIfNotExists(filePath);

            File file = new File(filePath);
            if (file.length() == 0) {
                return new ArrayList<>(); 
            }

            try (FileReader reader = new FileReader(filePath)) {
                JsonElement jsonElement = JsonParser.parseReader(reader);
                if (jsonElement.isJsonArray()) {
                    List<T> objects = gson.fromJson(jsonElement, type);
                    return objects != null ? objects : new ArrayList<>();
                } else {
                    return new ArrayList<>();
                }
            }
        } catch (JsonSyntaxException | IOException e) {
            e.printStackTrace();
            return new ArrayList<>(); 
        }
    }

    private void createFileIfNotExists(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
