package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;

public class TargetEntityIsChefProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if ((entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_rank).equals("Chef")) {
			return true;
		}
		return false;
	}
}
