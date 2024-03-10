package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.minecraft.world.entity.Entity;

import fr.eriniumgroup.eriniumadventure.base.entity.RocketHeadEntity;

public class LiftOffProgressionOverlayDisplayOverlayIngameProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if (entity.isPassenger() && (entity.getVehicle()) instanceof RocketHeadEntity && entity.getY() <= 10000) {
			return true;
		}
		return false;
	}
}
