package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.minecraft.world.entity.Entity;

public class ReturnOverlayPositionProcedure {
	public static double execute(Entity entity) {
		if (entity == null)
			return 0;
		return 0.0118 * entity.getY();
	}
}
