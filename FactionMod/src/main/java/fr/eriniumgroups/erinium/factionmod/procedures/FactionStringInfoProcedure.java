package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;

import java.io.File;

import fr.eriniumgroups.erinium.factionmod.configuration.ConfigConfiguration;

import com.mojang.brigadier.context.CommandContext;

public class FactionStringInfoProcedure {
	public static void execute(LevelAccessor world, CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		File file = new File("");
		com.google.gson.JsonObject jsonObject = new com.google.gson.JsonObject();
		String player_name = "";
		String tempEndMessage = "";
		if (StringFactionExistProcedure.execute(arguments)) {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("\u00A75===== Faction Info ====="), false);
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(("\u00A7bName : \u00A7e" + GetStringFactionNameProcedure.execute(arguments))), false);
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(("\u00A7bDisplayname : \u00A7e" + GetStringFactionDisplayNameProcedure.execute(entity))), false);
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(("\u00A7bDescription : \u00A7e" + GetStringFactionDescProcedure.execute(arguments))), false);
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(("\u00A7bPower : \u00A7e" + new java.text.DecimalFormat("###,###").format(GetStringFactionPowerProcedure.execute(arguments)) + " \u00A7b/ \u00A76"
						+ new java.text.DecimalFormat("###,###").format(GetStringFactionMaxPowerProcedure.execute(arguments, entity)))), false);
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(("\u00A7bClaims : \u00A7e" + new java.text.DecimalFormat("###,###").format(GetStringFactionClaimsNumberProcedure.execute(arguments)) + " \u00A7b/ \u00A76"
						+ new java.text.DecimalFormat("###,###").format(GetStringFactionMemberCountProcedure.execute(arguments, entity) * 10))), false);
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(("\u00A7bMember count : \u00A7e" + new java.text.DecimalFormat("###,###").format(GetStringFactionMemberCountProcedure.execute(arguments, entity)) + " \u00A7b/ \u00A76"
						+ new java.text.DecimalFormat("###,###").format((double) ConfigConfiguration.MAX_MEMBER.get()))), false);
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(("\u00A7bMember : \u00A76[\u00A7e" + GetStringFactionMemberListProcedure.execute(world, arguments, entity) + "\u00A76]")), false);
			for (int index0 = 0; index0 < (int) ("===== Faction Info =====").length(); index0++) {
				tempEndMessage = tempEndMessage + "=";
			}
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(("\u00A75" + tempEndMessage)), false);
		} else {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(("\u00A7c" + "The faction don't exist !")), false);
		}
	}
}
