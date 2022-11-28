package it.plugandcree.chatcontrol.commands.mute;

import org.bukkit.command.CommandSender;
import org.spigot.commons.commands.ExecutionContext;
import org.spigot.commons.commands.annotations.Help;
import org.spigot.commons.commands.builtin.PermissibleCommand;

import it.plugandcree.chatcontrol.config.CustomConfig;

@Help("Mute a specific player if one is provided, otherwise all players will be muted")
public class MuteCommand extends PermissibleCommand {

	public MuteCommand() {
		super("mute", 1, "chatcontrol.mute", CustomConfig.noPerm());
	}

	@Override
	public boolean execute(CommandSender sender, ExecutionContext context) {
		// TODO
		
		return false;
	}

}
