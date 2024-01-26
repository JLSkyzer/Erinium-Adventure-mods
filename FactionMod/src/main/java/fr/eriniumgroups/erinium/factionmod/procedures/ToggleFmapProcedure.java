package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;

public class ToggleFmapProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).FMapToggle) {
			{
				boolean _setval = false;
				entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.FMapToggle = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("\u00A7eFaction Map \u00A74OFF"), false);
		} else {
			{
				boolean _setval = true;
				entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.FMapToggle = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("\u00A7eFaction Map \u00A7aON"), false);
		}
	}
}
