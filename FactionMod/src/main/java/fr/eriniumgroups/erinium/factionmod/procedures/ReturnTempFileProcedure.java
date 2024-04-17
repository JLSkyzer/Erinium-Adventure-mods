package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;

public class ReturnTempFileProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return "\u00A7aYou are editing permission of rank : \u00A7e" + entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).temp_perm_file;
	}
}
