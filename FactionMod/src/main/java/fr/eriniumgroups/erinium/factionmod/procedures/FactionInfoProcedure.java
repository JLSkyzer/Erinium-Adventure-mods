package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import java.io.File;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;
import fr.eriniumgroups.erinium.factionmod.configuration.ConfigConfiguration;

public class FactionInfoProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		File file = new File("");
		com.google.gson.JsonObject jsonObject = new com.google.gson.JsonObject();
		String player_name = "";
		String tempEndMessage = "";
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal("\u00A75===== Faction Info ====="), false);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(("\u00A7bName : \u00A7e" + (entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).faction_name)),
					false);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(
					Component.literal(("\u00A7bDisplayname : \u00A7e" + (entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).faction_displayname)), false);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(("\u00A7bDescription : \u00A7e" + GetFactionDescProcedure.execute(entity))), false);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(
					("\u00A7bPower : \u00A7e" + new java.text.DecimalFormat("###,###").format(GetFactionPowerProcedure.execute(entity)) + " \u00A7b/ \u00A76" + new java.text.DecimalFormat("###,###").format(GetMaxPowerProcedure.execute(entity)))),
					false);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(("\u00A7bClaims : \u00A7e" + new java.text.DecimalFormat("###,###").format(GetFactionClaimsNumberProcedure.execute(entity)) + " \u00A7b/ \u00A76"
					+ new java.text.DecimalFormat("###,###").format(GetMemberCountProcedure.execute(entity) * 10))), false);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(("\u00A7bMember count : \u00A7e" + new java.text.DecimalFormat("###,###").format(GetMemberCountProcedure.execute(entity)) + " \u00A7b/ \u00A76"
					+ new java.text.DecimalFormat("###,###").format((double) ConfigConfiguration.MAX_MEMBER.get()))), false);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(("\u00A7bMember : \u00A76[\u00A7e" + RttProcedure.execute(world, entity) + "\u00A76]")), false);
		for (int index0 = 0; index0 < (int) ("===== Faction Info =====").length(); index0++) {
			tempEndMessage = tempEndMessage + "=";
		}
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(("\u00A75" + tempEndMessage)), false);
	}
}
