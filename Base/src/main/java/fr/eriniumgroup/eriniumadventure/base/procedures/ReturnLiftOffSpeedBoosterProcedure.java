package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.minecraft.world.entity.Entity;

public class ReturnLiftOffSpeedBoosterProcedure {
	public static double execute(Entity entity) {
		if (entity == null)
			return 0;
		if (entity.getY() >= 494) {
			return 1.5;
		} else if (entity.getY() >= 994) {
			return 1.8;
		} else if (entity.getY() >= 1494) {
			return 2.5;
		} else if (entity.getY() >= 1994) {
			return 3.5;
		} else if (entity.getY() >= 2994) {
			return 4;
		} else if (entity.getY() >= 4994) {
			return 5;
		}
		return 1;
	}
}
