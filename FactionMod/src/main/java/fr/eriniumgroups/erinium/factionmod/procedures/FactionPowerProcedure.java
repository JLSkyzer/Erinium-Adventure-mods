package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

public class FactionPowerProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		String tempEndMessage = "";
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal("\u00A75===== Faction Power ====="), false);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(("\u00A7a" + Component.translatable("faction.message.faction.getpower").getString() + "\u00A7e" + new java.text.DecimalFormat("###,###").format(GetFactionPowerProcedure.execute(entity))
					+ " \u00A7b/ \u00A76" + new java.text.DecimalFormat("###,###").format(GetMaxPowerProcedure.execute(entity)))), false);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(
					("\u00A7a" + Component.translatable("faction.message.player.getpower").getString() + "\u00A7e" + new java.text.DecimalFormat("###,###").format(ReturnTargetEntityPowerProcedure.execute(entity)) + " \u00A7b/ \u00A76" + "10")),
					false);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal("\u00A75========================="), false);
	}
}
