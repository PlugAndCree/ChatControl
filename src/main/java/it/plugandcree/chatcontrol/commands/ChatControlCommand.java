package it.plugandcree.chatcontrol.commands;


import org.bukkit.command.CommandSender;
import org.spigot.commons.commands.Command;
import org.spigot.commons.commands.ExecutionContext;
import org.spigot.commons.commands.HelpCommand;
import org.spigot.commons.commands.annotations.Help;

import it.plugandcree.chatcontrol.ChatControl;
import it.plugandcree.chatcontrol.commands.mute.MuteCommand;
import net.md_5.bungee.api.ChatColor;

@Help("Main command")
public class ChatControlCommand extends Command {

	public ChatControlCommand() {
		super("chatcontrol");
		registerSubcommand(HelpCommand.builder()
				.label("help")
				.command(this)
				.header(ChatColor.translateAlternateColorCodes('&', "&a&lCHAT CONTROL &7plugin made by &aPlug_And_Cree\n&7Version: " + ChatControl.getInstance().getDescription().getVersion()))
				.format(ChatControl.getInstance().getLang().getRawString("messages.help-format"))
				.build());
		
		registerSubcommand(new MuteCommand());
	}

	@Override
	public boolean execute(CommandSender sender, ExecutionContext context) {
		if(!context.isLastCommand())
			return false;
		
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lCHAT CONTROL &7plugin made by &aPlug_And_Cree"));
		if(sender.hasPermission("chatcontrol.help"))
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Type &a/chatcontrol help &7for more info"));
		
		return true;
	}

}
