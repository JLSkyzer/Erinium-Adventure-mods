package fr.eriniumgroups.erinium.jobs.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroups.erinium.jobs.network.EriniumjobsModVariables;

public class LeftProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumjobsModVariables.PlayerVariables())).won_xp_percent_x - 1 >= 0) {
			{
				double _setval = (entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumjobsModVariables.PlayerVariables())).won_xp_percent_x - 1;
				entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.won_xp_percent_x = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			UpdateOverlayPositionProcedure.execute(entity);
		}
	}
}
