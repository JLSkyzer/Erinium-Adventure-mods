package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;

public class ProcedBypassClaimProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (TargetEntityIsAdminProcedure.execute(entity)) {
			if (entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).bypass_claim) {
				{
					EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
					_vars.bypass_claim = false;
					_vars.syncPlayerVariables(entity);
				}
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("\u00A74OFF"), false);
			} else {
				{
					EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
					_vars.bypass_claim = true;
					_vars.syncPlayerVariables(entity);
				}
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("\u00A7aON"), false);
			}
		}
	}
}
