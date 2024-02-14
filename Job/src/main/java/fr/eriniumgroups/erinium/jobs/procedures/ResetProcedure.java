package fr.eriniumgroups.erinium.jobs.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroups.erinium.jobs.network.EriniumjobsModVariables;

public class ResetProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			double _setval = 75;
			entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.won_xp_percent_x = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			double _setval = 15;
			entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.won_xp_percent_y = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		UpdateOverlayPositionProcedure.execute(entity);
	}
}
