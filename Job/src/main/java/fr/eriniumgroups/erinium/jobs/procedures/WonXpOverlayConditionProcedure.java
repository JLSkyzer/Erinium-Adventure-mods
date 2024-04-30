package fr.eriniumgroups.erinium.jobs.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroups.erinium.jobs.network.EriniumjobsModVariables;

public class WonXpOverlayConditionProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES).won_xp_timer > 0 || entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES).won_xp_config;
	}
}
