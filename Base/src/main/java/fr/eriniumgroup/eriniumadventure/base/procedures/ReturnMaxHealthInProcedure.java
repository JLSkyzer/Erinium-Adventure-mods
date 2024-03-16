package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroup.eriniumadventure.base.network.EriniumAdventureModVariables;

public class ReturnMaxHealthInProcedure {
	public static double execute(Entity entity) {
		if (entity == null)
			return 0;
		return (entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAdventureModVariables.PlayerVariables())).max_health;
	}
}
