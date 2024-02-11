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
			if ((entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).bypass_claim) {
				{
					boolean _setval = false;
					entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.bypass_claim = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("\u00A74OFF"), false);
			} else {
				{
					boolean _setval = true;
					entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.bypass_claim = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("\u00A7aON"), false);
			}
		}
	}
}
