package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroup.eriniumadventure.base.network.EriniumAdventureModVariables;

public class ReturnHealthIntProcedure {
	public static double execute(Entity entity) {
		if (entity == null)
			return 0;
		return entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES).health;
	}
}
