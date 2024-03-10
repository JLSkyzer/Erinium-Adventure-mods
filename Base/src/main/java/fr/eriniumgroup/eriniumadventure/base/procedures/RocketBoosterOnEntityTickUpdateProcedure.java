package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;

import fr.eriniumgroup.eriniumadventure.base.entity.RocketBoosterEntity;
import fr.eriniumgroup.eriniumadventure.base.EriniumAdventureMod;

public class RocketBoosterOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof RocketBoosterEntity _datEntL0 && _datEntL0.getEntityData().get(RocketBoosterEntity.DATA_Lifting)) {
			if ((entity instanceof RocketBoosterEntity _datEntI ? _datEntI.getEntityData().get(RocketBoosterEntity.DATA_countdown) : 0) > 0) {
				if (entity instanceof RocketBoosterEntity _datEntSetI)
					_datEntSetI.getEntityData().set(RocketBoosterEntity.DATA_countdown, (int) ((entity instanceof RocketBoosterEntity _datEntI ? _datEntI.getEntityData().get(RocketBoosterEntity.DATA_countdown) : 0) - 1));
			} else {
				if (entity.getY() < 4994) {
					entity.setDeltaMovement(new Vec3(0, ReturnLiftOffSpeedBoosterProcedure.execute(entity), 0));
					if (world instanceof ServerLevel _level)
						_level.sendParticles(ParticleTypes.LARGE_SMOKE, x, (y - 2), z, 10, 0, 0, 0, 1);
					if (world instanceof ServerLevel _level)
						_level.sendParticles(ParticleTypes.CLOUD, x, (y - 2), z, 10, 0, 0, 0, 1);
					if (world instanceof ServerLevel _level)
						_level.sendParticles(ParticleTypes.SMOKE, x, (y - 2), z, 10, 0, 0, 0, 1);
				} else {
					if (entity instanceof RocketBoosterEntity _datEntSetL)
						_datEntSetL.getEntityData().set(RocketBoosterEntity.DATA_descend, true);
					if (entity instanceof RocketBoosterEntity _datEntSetL)
						_datEntSetL.getEntityData().set(RocketBoosterEntity.DATA_Lifting, false);
				}
			}
		} else if (entity instanceof RocketBoosterEntity _datEntL11 && _datEntL11.getEntityData().get(RocketBoosterEntity.DATA_descend)) {
			entity.setDeltaMovement(new Vec3(0, (-0.01), 0));
			EriniumAdventureMod.queueServerWork(200, () -> {
				if (!entity.level().isClientSide())
					entity.discard();
			});
		}
	}
}
