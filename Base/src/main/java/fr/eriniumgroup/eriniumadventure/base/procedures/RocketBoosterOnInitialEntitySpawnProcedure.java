package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.minecraft.world.entity.Entity;

public class RocketBoosterOnInitialEntitySpawnProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		entity.setNoGravity(true);
	}
}
