package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;

public class ReturnTempFileProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return "\u00A7aYou are editing permission of rank : \u00A7e" + (entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).temp_perm_file;
	}
}
