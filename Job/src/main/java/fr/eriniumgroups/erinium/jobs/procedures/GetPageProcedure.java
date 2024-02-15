package fr.eriniumgroups.erinium.jobs.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroups.erinium.jobs.network.EriniumjobsModVariables;

public class GetPageProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return "\u00A7ePage : \u00A7a" + new java.text.DecimalFormat("##").format((entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumjobsModVariables.PlayerVariables())).wonxp_page + 1)
				+ " \u00A7eJob ID : \u00A7c" + (entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumjobsModVariables.PlayerVariables())).temp_job_id;
	}
}
