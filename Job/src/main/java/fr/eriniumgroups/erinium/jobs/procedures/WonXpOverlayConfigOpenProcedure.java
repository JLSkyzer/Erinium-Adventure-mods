package fr.eriniumgroups.erinium.jobs.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroups.erinium.jobs.network.EriniumjobsModVariables;

public class WonXpOverlayConfigOpenProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			EriniumjobsModVariables.PlayerVariables _vars = entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES);
			_vars.won_xp_config = true;
			_vars.syncPlayerVariables(entity);
		}
		{
			EriniumjobsModVariables.PlayerVariables _vars = entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES);
			_vars.won_xp_message = "Message 1";
			_vars.syncPlayerVariables(entity);
		}
		{
			EriniumjobsModVariables.PlayerVariables _vars = entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES);
			_vars.won_xp_message_2 = "Message 2";
			_vars.syncPlayerVariables(entity);
		}
	}
}
