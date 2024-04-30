package fr.eriniumgroups.erinium.jobs.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroups.erinium.jobs.network.EriniumjobsModVariables;

public class ReturnMessage2Procedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES).won_xp_message_2;
	}
}
