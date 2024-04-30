package fr.eriniumgroups.erinium.jobs.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroups.erinium.jobs.network.EriniumjobsModVariables;

public class LeftProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES).won_xp_percent_x - 1 >= 0) {
			{
				EriniumjobsModVariables.PlayerVariables _vars = entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES);
				_vars.won_xp_percent_x = entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES).won_xp_percent_x - 1;
				_vars.syncPlayerVariables(entity);
			}
		}
	}
}
