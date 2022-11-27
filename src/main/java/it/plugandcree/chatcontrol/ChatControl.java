package it.plugandcree.chatcontrol;

import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;

public class ChatControl extends JavaPlugin {
	
	private static @Getter ChatControl instance;
	
	@Override
	public void onEnable() {
		instance = this;
	}
}
