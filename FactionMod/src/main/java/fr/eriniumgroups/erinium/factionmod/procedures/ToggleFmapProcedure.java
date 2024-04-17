package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;

public class ToggleFmapProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).FMapToggle) {
			{
				EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
				_vars.FMapToggle = false;
				_vars.syncPlayerVariables(entity);
			}
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("\u00A7eFaction Map \u00A74OFF"), false);
		} else {
			{
				EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
				_vars.FMapToggle = true;
				_vars.syncPlayerVariables(entity);
			}
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("\u00A7eFaction Map \u00A7aON"), false);
		}
	}
}
