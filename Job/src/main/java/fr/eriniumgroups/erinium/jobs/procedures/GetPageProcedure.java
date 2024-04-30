package fr.eriniumgroups.erinium.jobs.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroups.erinium.jobs.network.EriniumjobsModVariables;

public class GetPageProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return "\u00A7ePage : \u00A7a" + new java.text.DecimalFormat("##").format(entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES).wonxp_page + 1) + " \u00A7eJob ID : \u00A7c"
				+ entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES).temp_job_id;
	}
}
