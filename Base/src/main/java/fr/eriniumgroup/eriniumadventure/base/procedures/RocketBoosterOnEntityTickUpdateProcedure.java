package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.Entity;

import fr.eriniumgroup.eriniumadventure.base.entity.RocketBoosterEntity;

public class RocketBoosterOnEntityTickUpdateProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof RocketBoosterEntity _datEntL0 && _datEntL0.getEntityData().get(RocketBoosterEntity.DATA_Lifting)) {
			if (entity.getY() < 5000) {
				entity.setDeltaMovement(new Vec3(0, 1, 0));
			}
		}
	}
}
