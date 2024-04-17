package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;

public class TargetEntityHaveFactionProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if (!((entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_name).equals("wilderness") || (entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_name).equals(""))) {
			return true;
		}
		return false;
	}
}
