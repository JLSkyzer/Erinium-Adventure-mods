package fr.eriniumgroups.erinium.jobs.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroups.erinium.jobs.network.EriniumjobsModVariables;

public class UnlockedPreviousProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES).wonxp_page - 1 >= 0) {
			{
				EriniumjobsModVariables.PlayerVariables _vars = entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES);
				_vars.wonxp_page = entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES).wonxp_page - 1;
				_vars.syncPlayerVariables(entity);
			}
			{
				EriniumjobsModVariables.PlayerVariables _vars = entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES);
				_vars.wonxp_initialised = false;
				_vars.syncPlayerVariables(entity);
			}
		}
	}
}
