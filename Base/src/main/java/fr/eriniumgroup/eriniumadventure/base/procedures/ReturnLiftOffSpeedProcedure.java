package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.minecraft.world.entity.Entity;

public class ReturnLiftOffSpeedProcedure {
	public static double execute(Entity entity) {
		if (entity == null)
			return 0;
		if (entity.getY() >= 500) {
			return 1.5;
		} else if (entity.getY() >= 1000) {
			return 1.8;
		} else if (entity.getY() >= 1500) {
			return 2.5;
		} else if (entity.getY() >= 2000) {
			return 3.5;
		} else if (entity.getY() >= 3000) {
			return 4;
		} else if (entity.getY() >= 5000) {
			return 5;
		} else if (entity.getY() >= 6000) {
			return 10;
		} else if (entity.getY() >= 7500) {
			return 20;
		}
		return 1;
	}
}
