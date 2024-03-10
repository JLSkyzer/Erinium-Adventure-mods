package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.Entity;

import fr.eriniumgroup.eriniumadventure.base.entity.RocketHeadEntity;

public class RocketHeadTickProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof RocketHeadEntity _datEntL0 && _datEntL0.getEntityData().get(RocketHeadEntity.DATA_Lifting)) {
			if (entity.isVehicle()) {
				if (entity.getY() < 5000) {
					entity.setDeltaMovement(new Vec3(0, 1, 0));
				}
			}
		}
	}
}
