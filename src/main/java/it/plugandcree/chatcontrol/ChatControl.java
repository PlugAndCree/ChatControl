package it.plugandcree.chatcontrol;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;

import it.plugandcree.chatcontrol.config.CustomConfig;
import lombok.Getter;

@Getter
public class ChatControl extends JavaPlugin {
	private static @Getter ChatControl instance;
	private CustomConfig lang;
	private CustomConfig config;
	
	@Override
	public void onEnable() {
		instance = this;
		
		reload();
	}
	
	public void reload() {
		lang = createConfigFile("lang.yml");
		config = createConfigFile("config.yml");
	}
	
	private CustomConfig createConfigFile(String name) {
		File fc = new File(getDataFolder(), name);
		if (!fc.exists()) {
			fc.getParentFile().mkdirs();
			saveResource(name, false);
		}

		CustomConfig configFile = new CustomConfig();
		try {
			configFile.load(fc);
			return configFile;
		} catch (IOException | InvalidConfigurationException e) {
			throw new RuntimeException(e);
		}
	}
}
