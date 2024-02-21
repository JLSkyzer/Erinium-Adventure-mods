package fr.eriniumgroups.erinium.jobs.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroups.erinium.jobs.network.EriniumjobsModVariables;

public class ReturnJobIdProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return "\u00A7eJob info of \u00A7b" + (entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumjobsModVariables.PlayerVariables())).temp_job_id;
	}
}
