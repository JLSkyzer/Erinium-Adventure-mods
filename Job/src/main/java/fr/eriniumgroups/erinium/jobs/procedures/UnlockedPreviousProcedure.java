package fr.eriniumgroups.erinium.jobs.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroups.erinium.jobs.network.EriniumjobsModVariables;

public class UnlockedPreviousProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumjobsModVariables.PlayerVariables())).wonxp_page - 1 >= 0) {
			{
				double _setval = (entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumjobsModVariables.PlayerVariables())).wonxp_page - 1;
				entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.wonxp_page = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				boolean _setval = false;
				entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.wonxp_initialised = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
