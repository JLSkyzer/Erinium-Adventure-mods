package fr.eriniumgroups.erinium.jobs.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroups.erinium.jobs.network.EriniumjobsModVariables;

public class TestProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			double _setval = 60;
			entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.won_xp_timer = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
