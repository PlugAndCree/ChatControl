package it.plugandcree.chatcontrol.commands.mute;

import org.bukkit.command.CommandSender;
import org.spigot.commons.commands.ExecutionContext;
import org.spigot.commons.commands.annotations.Help;
import org.spigot.commons.commands.builtin.PermissibleCommand;

import it.plugandcree.chatcontrol.ChatControl;
import it.plugandcree.chatcontrol.config.CustomConfig;

@Help("Mute a specific player if one is provided, otherwise all players will be muted")
public class MuteCommand extends PermissibleCommand {

	// TODO Mute chat listener
	
	public MuteCommand() {
		super("mute", 1, "chatcontrol.mute", CustomConfig.noPerm());
	}

	@Override
	public boolean execute(CommandSender sender, ExecutionContext context) {
		CustomConfig lang = ChatControl.getInstance().getLang();
		
		String playerName = null;
		
		if (context.getCallArguments().size() == 1)
			playerName = context.getCallArguments().get(0);
		
		if (playerName == null) {
			boolean mode = ChatControl.getInstance().toggleGlobalMute();
			if (mode)
				sender.sendMessage(lang.getString("messages.global-mute-on"));
			else
				sender.sendMessage(lang.getString("messages.global-mute-on"));
		} else {
			if (ChatControl.getInstance().checkMutedPlayer(playerName)) {
				ChatControl.getInstance().unmutePlayer(playerName);
				sender.sendMessage(lang.getString("messages.player-unmuted").replace("%player%", playerName));
			} else {
				ChatControl.getInstance().mutePlayer(playerName);
				sender.sendMessage(lang.getString("messages.player-muted").replace("%player%", playerName));
			}
		}
		
		return true;
	}

}
