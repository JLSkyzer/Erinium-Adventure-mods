package fr.eriniumgroup.eriniumadventure.automation.procedures;

import net.neoforged.fml.loading.FMLPaths;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

public class FarmerHelpBtnProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player)
			_player.closeContainer();
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(("\u00A7eConfig folder : \u00A7c" + FMLPaths.GAMEDIR.get().toString() + "/config/eriniumAutomation/Farmer/")), false);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal("\u00A7eUse the command \u00A7a/farmerjsonbuilder \u00A7eto make a json more fast and easy"), false);
	}
}
