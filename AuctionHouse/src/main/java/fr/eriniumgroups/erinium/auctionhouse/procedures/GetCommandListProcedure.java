package fr.eriniumgroups.erinium.auctionhouse.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

public class GetCommandListProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal("\u00A73---------- Erinium Auction House ----------"), false);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal("\u00A7a/eriniumAH \u00A7eTo get command list"), false);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal("\u00A7a/ah \u00A7eto open the Auction House"), false);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal("\u00A7a/ah sell - \u00A7eTo sell an item in the Auction House"), false);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal("\u00A7a/ah theme - \u00A7eTo change the theme of the Auction House"), false);
	}
}
