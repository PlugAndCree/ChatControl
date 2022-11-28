package it.plugandcree.chatcontrol;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

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
	private Set<String> mutedPlayers;
	
	@Override
	public void onEnable() {
		instance = this;
		
		globalMuteMode = false;
		mutedPlayers = new HashSet<>();
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
		mutedPlayers.add(p);
	}
	
	/**
	 * Unmutes a player
	 * @param p player name {@link Player#getName}
	 */
	public void unmutePlayer(String p) {
		mutedPlayers.remove(p);
	}
	
	/**
	 * Check if a player is muted
	 * @param p player name {@link Player#getName}
	 * @return true if player is muted, false otherwise
	 */
	public boolean checkMutedPlayer(String p) {
		return mutedPlayers.contains(p);
	}
	
	/**
	 * Toggles the global mute mode boolean flag
	 * @return toggled state of global mute mode
	 */
	public boolean toggleGlobalMute() {
		globalMuteMode = !globalMuteMode;
		return globalMuteMode;
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
