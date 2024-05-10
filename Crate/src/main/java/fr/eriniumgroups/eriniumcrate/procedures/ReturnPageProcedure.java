package fr.eriniumgroups.eriniumcrate.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroups.eriniumcrate.network.EriniumcrateModVariables;

public class ReturnPageProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return new java.text.DecimalFormat("##").format(entity.getData(EriniumcrateModVariables.PLAYER_VARIABLES).page);
	}
}
