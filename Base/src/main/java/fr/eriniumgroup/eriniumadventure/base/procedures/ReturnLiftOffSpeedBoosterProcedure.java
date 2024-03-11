package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.minecraft.world.entity.Entity;

public class ReturnLiftOffSpeedBoosterProcedure {
	public static double execute(Entity entity) {
		if (entity == null)
			return 0;
		if (entity.getY() >= 494) {
			return 2.5;
		} else if (entity.getY() >= 994) {
			return 5;
		} else if (entity.getY() >= 1494) {
			return 10;
		} else if (entity.getY() >= 1994) {
			return 15;
		} else if (entity.getY() >= 2994) {
			return 20;
		} else if (entity.getY() >= 3994) {
			return 25;
		} else if (entity.getY() >= 4994) {
			return 30;
		}
		return 1;
	}
}
