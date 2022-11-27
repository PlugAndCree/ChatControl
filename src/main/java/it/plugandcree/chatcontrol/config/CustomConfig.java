package it.plugandcree.chatcontrol.config;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

public class CustomConfig extends YamlConfiguration {
	public String getRawString(String path) {
		return ChatColor.translateAlternateColorCodes('&', super.getString(path));
	}

	public String getPrefix() {
		return ChatColor.translateAlternateColorCodes('&', super.getString("messages.prefix"));
	}

	@Override
	public String getString(String path) {
		return getPrefix() + getRawString(path);
	}

	@Override
	public List<String> getStringList(String path) {
		List<String> stringList = new ArrayList<>();
		for(String s : super.getStringList(path)) 
			stringList.add(ChatColor.translateAlternateColorCodes('&', s));
		
		return stringList;
	}

	public String noPerm() {
		return getString("messages.no-perm");
	}
}