package fr.eriniumgroup.eriniumadventure.automation.procedures;

import net.minecraftforge.fml.loading.FMLPaths;

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
	}
}
