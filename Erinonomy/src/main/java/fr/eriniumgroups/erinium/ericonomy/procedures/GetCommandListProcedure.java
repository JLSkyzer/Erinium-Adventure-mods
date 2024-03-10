package fr.eriniumgroups.erinium.ericonomy.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

public class GetCommandListProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal("\u00A73---------- Ericonomy ----------"), false);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal("\u00A7a/ericonomy \u00A7eGet commands list"), false);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(("\u00A7a" + "pay <player>" + " \u00A7e" + "To pay a player")), false);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(("\u00A7a" + "money" + " \u00A7e" + "To get your balance")), false);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(("\u00A74ADMIN \u00A7a" + "money <player>" + " \u00A7e" + "To get the player's balance")), false);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(("\u00A74ADMIN \u00A7a" + "money reset <player>" + " \u00A7e" + "To reset the player's balance")), false);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(("\u00A74ADMIN \u00A7a" + "money take <player> <amount>" + " \u00A7e" + "To take money from player's balance")), false);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(("\u00A74ADMIN \u00A7a" + "money set <player> <amount>" + " \u00A7e" + "To set the player's balance to the value")), false);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(("\u00A74ADMIN \u00A7a" + "money give <player> <amount>" + " \u00A7e" + "Give money to the player's balance")), false);
	}
}
