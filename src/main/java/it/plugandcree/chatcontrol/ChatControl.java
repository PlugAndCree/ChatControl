package it.plugandcree.chatcontrol;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import it.plugandcree.chatcontrol.commands.ChatControlCommand;
import it.plugandcree.chatcontrol.config.CustomConfig;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ChatControl extends JavaPlugin {
	private static @Getter ChatControl instance;
	private CustomConfig lang;
	private CustomConfig config;
	
	private @Setter boolean globalMuteMode;
	private Map<String, Boolean> mutedPlayers;
	
	@Override
	public void onEnable() {
		instance = this;
		
		globalMuteMode = false;
		mutedPlayers = new HashMap<>();
		reload();
		
		new ChatControlCommand().register(this);
	}
	
	public void reload() {
		lang = createConfigFile("lang.yml");
		config = createConfigFile("config.yml");
	}
	
	/**
	 * Mutes a player
	 * @param p player name {@link Player#getName}
	 */
	public void mutePlayer(String p) {
		mutedPlayers.put(p, true);
	}
	
	/**
	 * Unmutes a player
	 * @param p player name {@link Player#getName}
	 */
	public void unmutePlayer(String p) {
		mutedPlayers.put(p, false);
	}
	
	/**
	 * Check if a player is muted
	 * @param p player name {@link Player#getName}
	 * @return true if player exists is muted, false otherwise
	 */
	public boolean checkMutedPlayer(String p) {
		Boolean result = mutedPlayers.get(p);
		
		return result == null ? false : result;
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
