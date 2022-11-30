package it.plugandcree.chatcontrol.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import it.plugandcree.chatcontrol.ChatControl;

public class ChatEvent implements Listener {
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		if (e.isCancelled())
			return;
		
		if (e.getPlayer().hasPermission("chatcontrol.bypass.mute"))
			return;
		
		if (ChatControl.getInstance().isGlobalMuteMode()) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(ChatControl.getInstance().getLang().getString("messages.player-cannot-chat-global"));
		} else if (ChatControl.getInstance().checkMutedPlayer(e.getPlayer().getName())) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(ChatControl.getInstance().getLang().getString("messages.player-cannot-chat-single"));
		}
	}
}
