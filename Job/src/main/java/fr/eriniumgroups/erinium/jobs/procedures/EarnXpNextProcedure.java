package fr.eriniumgroups.erinium.jobs.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroups.erinium.jobs.network.EriniumjobsModVariables;

public class EarnXpNextProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			EriniumjobsModVariables.PlayerVariables _vars = entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES);
			_vars.wonxp_page = entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES).wonxp_page + 1;
			_vars.syncPlayerVariables(entity);
		}
		{
			EriniumjobsModVariables.PlayerVariables _vars = entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES);
			_vars.wonxp_initialised = false;
			_vars.syncPlayerVariables(entity);
		}
	}
}
