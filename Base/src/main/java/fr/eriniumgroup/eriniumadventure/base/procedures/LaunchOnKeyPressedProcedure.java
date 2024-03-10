package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.minecraft.server.level.ChunkLevel;
import net.minecraft.server.level.ChunkMap;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

import java.util.Comparator;

import fr.eriniumgroup.eriniumadventure.base.entity.RocketHeadEntity;
import fr.eriniumgroup.eriniumadventure.base.entity.RocketBoosterEntity;
import net.minecraftforge.event.level.ChunkTicketLevelUpdatedEvent;

public class LaunchOnKeyPressedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		Entity entityHead = null;
		Entity entityBooster = null;
		double countdown = 0;
		if (entity.isPassenger()) {
			entityHead = (Entity) world.getEntitiesOfClass(RocketHeadEntity.class, AABB.ofSize(new Vec3(x, y, z), 5, 5, 5), e -> true).stream().sorted(new Object() {
				Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
					return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
				}
			}.compareDistOf(x, y, z)).findFirst().orElse(null);
			entityBooster = (Entity) world.getEntitiesOfClass(RocketBoosterEntity.class, AABB.ofSize(new Vec3((entityHead.getX()), (entityHead.getY() - 6), (entityHead.getZ())), 5, 5, 5), e -> true).stream().sorted(new Object() {
				Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
					return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
				}
			}.compareDistOf((entityHead.getX()), (entityHead.getY() - 6), (entityHead.getZ()))).findFirst().orElse(null);
			if (entityBooster instanceof RocketBoosterEntity _datEntSetI)
				_datEntSetI.getEntityData().set(RocketBoosterEntity.DATA_countdown, 200);
			if (entityHead instanceof RocketHeadEntity _datEntSetI)
				_datEntSetI.getEntityData().set(RocketHeadEntity.DATA_countdown, 200);
			if (entityBooster instanceof RocketBoosterEntity _datEntSetL)
				_datEntSetL.getEntityData().set(RocketBoosterEntity.DATA_Lifting, true);
			if (entityHead instanceof RocketHeadEntity _datEntSetL)
				_datEntSetL.getEntityData().set(RocketHeadEntity.DATA_Lifting, true);

		}
	}
}
