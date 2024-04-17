package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.neoforged.neoforge.items.ItemHandlerHelper;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import java.util.Comparator;

import fr.eriniumgroup.eriniumadventure.base.init.EriniumAdventureModItems;
import fr.eriniumgroup.eriniumadventure.base.entity.RocketHeadEntity;
import fr.eriniumgroup.eriniumadventure.base.entity.RocketBoosterEntity;
import fr.eriniumgroup.eriniumadventure.base.EriniumAdventureMod;

public class RocketHeadTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof RocketHeadEntity _datEntL0 && _datEntL0.getEntityData().get(RocketHeadEntity.DATA_Lifting)) {
			if (entity.isVehicle()) {
				if ((entity instanceof RocketHeadEntity _datEntI ? _datEntI.getEntityData().get(RocketHeadEntity.DATA_countdown) : 0) > 0) {
					if ((entity instanceof RocketHeadEntity _datEntI ? _datEntI.getEntityData().get(RocketHeadEntity.DATA_countdown) : 0) == 200
							|| (entity instanceof RocketHeadEntity _datEntI ? _datEntI.getEntityData().get(RocketHeadEntity.DATA_countdown) : 0) == 180
							|| (entity instanceof RocketHeadEntity _datEntI ? _datEntI.getEntityData().get(RocketHeadEntity.DATA_countdown) : 0) == 160
							|| (entity instanceof RocketHeadEntity _datEntI ? _datEntI.getEntityData().get(RocketHeadEntity.DATA_countdown) : 0) == 140
							|| (entity instanceof RocketHeadEntity _datEntI ? _datEntI.getEntityData().get(RocketHeadEntity.DATA_countdown) : 0) == 120
							|| (entity instanceof RocketHeadEntity _datEntI ? _datEntI.getEntityData().get(RocketHeadEntity.DATA_countdown) : 0) == 100
							|| (entity instanceof RocketHeadEntity _datEntI ? _datEntI.getEntityData().get(RocketHeadEntity.DATA_countdown) : 0) == 80
							|| (entity instanceof RocketHeadEntity _datEntI ? _datEntI.getEntityData().get(RocketHeadEntity.DATA_countdown) : 0) == 60
							|| (entity instanceof RocketHeadEntity _datEntI ? _datEntI.getEntityData().get(RocketHeadEntity.DATA_countdown) : 0) == 40
							|| (entity instanceof RocketHeadEntity _datEntI ? _datEntI.getEntityData().get(RocketHeadEntity.DATA_countdown) : 0) == 20) {
						EriniumAdventureMod.queueServerWork(20, () -> {
							if ((entity.getFirstPassenger()) instanceof Player || (entity.getFirstPassenger()) instanceof ServerPlayer) {
								if (world instanceof ServerLevel _level)
									_level.getServer().getCommands().performPrefixedCommand(
											new CommandSourceStack(CommandSource.NULL, new Vec3(((entity.getFirstPassenger()).getX()), ((entity.getFirstPassenger()).getY()), ((entity.getFirstPassenger()).getZ())), Vec2.ZERO, _level, 4, "",
													Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
											("title " + (entity.getFirstPassenger()).getDisplayName().getString() + " subtitle \""
													+ ((new java.text.DecimalFormat("##").format(Math.ceil((entity instanceof RocketHeadEntity _datEntI ? _datEntI.getEntityData().get(RocketHeadEntity.DATA_countdown) : 0) / 20))) + "s") + "\""));
								if (world instanceof ServerLevel _level)
									_level.getServer().getCommands()
											.performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(((entity.getFirstPassenger()).getX()), ((entity.getFirstPassenger()).getY()), ((entity.getFirstPassenger()).getZ())), Vec2.ZERO,
													_level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(), ("title " + (entity.getFirstPassenger()).getDisplayName().getString() + " times " + 0 + 1 + 0));
								if (world instanceof ServerLevel _level)
									_level.getServer().getCommands()
											.performPrefixedCommand(
													new CommandSourceStack(CommandSource.NULL, new Vec3(((entity.getFirstPassenger()).getX()), ((entity.getFirstPassenger()).getY()), ((entity.getFirstPassenger()).getZ())), Vec2.ZERO, _level, 4, "",
															Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
													("title " + (entity.getFirstPassenger()).getDisplayName().getString() + " title \"" + "\u00A76Liftoff in..." + "\""));
							}
						});
					}
					if (entity instanceof RocketHeadEntity _datEntSetI)
						_datEntSetI.getEntityData().set(RocketHeadEntity.DATA_countdown, (int) ((entity instanceof RocketHeadEntity _datEntI ? _datEntI.getEntityData().get(RocketHeadEntity.DATA_countdown) : 0) - 1));
				} else {
					if (entity.getY() < 10000) {
						if (entity.getY() >= 5000 && entity.getY() < 7500) {
							entity.setDeltaMovement(new Vec3(0, 0, 0));
							entity.setDeltaMovement(new Vec3(0, ReturnLiftOffSpeedProcedure.execute(entity), 0));
							if (world instanceof ServerLevel _level)
								_level.sendParticles(ParticleTypes.CLOUD, x, (entity.getY() - 8), z, 10, 0, 0, 0, 1);
							if (world instanceof ServerLevel _level)
								_level.sendParticles(ParticleTypes.FLAME, x, (entity.getY() - 8), z, 50, 0, 0, 0, 1);
						} else if (entity.getY() >= 7500) {
							entity.setDeltaMovement(new Vec3(0, 0, 0));
							entity.setDeltaMovement(new Vec3(0, ReturnLiftOffSpeedProcedure.execute(entity), 0));
							if (world instanceof ServerLevel _level)
								_level.sendParticles(ParticleTypes.CLOUD, x, (entity.getY() - 8), z, 30, 0, 0, 0, 1);
							if (world instanceof ServerLevel _level)
								_level.sendParticles(ParticleTypes.FLAME, x, (entity.getY() - 8), z, 20, 0, 0, 0, 1);
						} else {
							entity.setDeltaMovement(new Vec3(0, 0, 0));
							entity.setDeltaMovement(new Vec3(0, ReturnLiftOffSpeedProcedure.execute(entity), 0));
						}
					} else {
						entity.setDeltaMovement(new Vec3(0, 0, 0));
						entity.setDeltaMovement(new Vec3(0, 1, 0));
						if (world instanceof ServerLevel _level)
							_level.sendParticles(ParticleTypes.CLOUD, x, (entity.getY() - 8), z, 70, 0, 0, 0, 1);
					}
				}
			} else {
				if (!(((Entity) world.getEntitiesOfClass(RocketBoosterEntity.class, AABB.ofSize(new Vec3(x, (y - 6), z), 5, 5, 5), e -> true).stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf(x, (y - 6), z)).findFirst().orElse(null)) == null)) {
					if (!((Entity) world.getEntitiesOfClass(RocketBoosterEntity.class, AABB.ofSize(new Vec3(x, (y - 6), z), 10, 10, 10), e -> true).stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
						}
					}.compareDistOf(x, (y - 6), z)).findFirst().orElse(null)).level().isClientSide())
						((Entity) world.getEntitiesOfClass(RocketBoosterEntity.class, AABB.ofSize(new Vec3(x, (y - 6), z), 10, 10, 10), e -> true).stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
							}
						}.compareDistOf(x, (y - 6), z)).findFirst().orElse(null)).discard();
				}
				if (!entity.level().isClientSide())
					entity.discard();
				if (!(((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 5, 5, 5), e -> true).stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf(x, y, z)).findFirst().orElse(null)) == null)) {
					if (((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 5, 5, 5), e -> true).stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
						}
					}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof Player _player) {
						ItemStack _setstack = new ItemStack(EriniumAdventureModItems.STARSHIP_ITEM.get()).copy();
						_setstack.setCount(1);
						ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
					}
				} else if (!(((Entity) world.getEntitiesOfClass(ServerPlayer.class, AABB.ofSize(new Vec3(x, y, z), 5, 5, 5), e -> true).stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf(x, y, z)).findFirst().orElse(null)) == null)) {
					if (((Entity) world.getEntitiesOfClass(ServerPlayer.class, AABB.ofSize(new Vec3(x, y, z), 5, 5, 5), e -> true).stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
						}
					}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof Player _player) {
						ItemStack _setstack = new ItemStack(EriniumAdventureModItems.STARSHIP_ITEM.get()).copy();
						_setstack.setCount(1);
						ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
					}
				} else {
					if (world instanceof ServerLevel _level) {
						ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(EriniumAdventureModItems.STARSHIP_ITEM.get()));
						entityToSpawn.setPickUpDelay(10);
						entityToSpawn.setUnlimitedLifetime();
						_level.addFreshEntity(entityToSpawn);
					}
				}
			}
		}
	}
}
