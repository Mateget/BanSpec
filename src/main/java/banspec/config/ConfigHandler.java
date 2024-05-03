package banspec.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import banspec.BanSpec;
import banspec.config.models.Config;

public class ConfigHandler {
    public static Config config;
    
    public static void readAllConfigs() {
    	loadConfigFile(new File(BanSpec.configDir,"config.json"));
    }   
    
    public static void creationCheckConfig() {
    	if (config == null){
    		config = new Config();
        }
    }
    
    public static void writeConfig() {
    	writeConfigFile(new File(BanSpec.configDir,"config.json"));
    }
    
    public static void loadConfigFile(File file) {
        try {
            if (!file.exists())
                file.createNewFile();
            Gson gson = new Gson();
            BufferedReader br = new BufferedReader(new FileReader(file));
            config = gson.fromJson(br, Config.class);
            br.close();
        } catch (Exception e) {
        	BanSpec.LOGGER.error("Failed to read config:\r\n" + e.getMessage());
        }
    }
        
    public static void writeConfigFile(File file) {
        try {
            if (!file.exists())
                file.createNewFile();
            Gson gson = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
            String json = gson.toJson(config);
            FileWriter writer = new FileWriter(file);
            writer.write(json);
            writer.close();
        } catch (Exception e) {
            BanSpec.LOGGER.error("Failed to save config:\r\n" + e.getMessage());
        }
    }    
}
