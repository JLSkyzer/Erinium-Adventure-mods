package fr.eriniumgroups.erinium.auctionhouse.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import fr.eriniumgroups.erinium.auctionhouse.network.EriniumAhModVariables;

public class RedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player)
			_player.closeContainer();
		{
			String _setval = "\u00A70";
			entity.getCapability(EriniumAhModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.theme_text_color = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			String _setval = "red";
			entity.getCapability(EriniumAhModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.theme = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal("Set to red"), false);
	}
}
