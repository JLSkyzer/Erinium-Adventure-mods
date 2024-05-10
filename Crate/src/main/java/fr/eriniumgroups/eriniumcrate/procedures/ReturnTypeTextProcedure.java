package fr.eriniumgroups.eriniumcrate.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroups.eriniumcrate.network.EriniumcrateModVariables;

public class ReturnTypeTextProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return entity.getData(EriniumcrateModVariables.PLAYER_VARIABLES).creator_types;
	}
}
