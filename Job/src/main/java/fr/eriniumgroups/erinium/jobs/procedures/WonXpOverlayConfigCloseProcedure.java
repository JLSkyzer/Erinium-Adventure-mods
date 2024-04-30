package fr.eriniumgroups.erinium.jobs.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroups.erinium.jobs.network.EriniumjobsModVariables;

public class WonXpOverlayConfigCloseProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			EriniumjobsModVariables.PlayerVariables _vars = entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES);
			_vars.won_xp_config = false;
			_vars.syncPlayerVariables(entity);
		}
		{
			EriniumjobsModVariables.PlayerVariables _vars = entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES);
			_vars.won_xp_message = "";
			_vars.syncPlayerVariables(entity);
		}
		{
			EriniumjobsModVariables.PlayerVariables _vars = entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES);
			_vars.won_xp_message_2 = "";
			_vars.syncPlayerVariables(entity);
		}
	}
}
