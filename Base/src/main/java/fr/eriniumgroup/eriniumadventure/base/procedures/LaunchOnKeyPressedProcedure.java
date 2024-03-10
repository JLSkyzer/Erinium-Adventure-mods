package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import java.util.Comparator;

import fr.eriniumgroup.eriniumadventure.base.entity.RocketHeadEntity;
import fr.eriniumgroup.eriniumadventure.base.entity.RocketBoosterEntity;
import fr.eriniumgroup.eriniumadventure.base.EriniumAdventureMod;

public class LaunchOnKeyPressedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		Entity entityHead = null;
		Entity entityBooster = null;
		int countdown = 0;
		if (entity.isPassenger()) {
			countdown = 10;
			if (entity instanceof Player || entity instanceof ServerPlayer) {
				if (world instanceof ServerLevel _level)
					_level.getServer().getCommands().performPrefixedCommand(
							new CommandSourceStack(CommandSource.NULL, new Vec3((entity.getX()), (entity.getY()), (entity.getZ())), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
							("title " + entity.getDisplayName().getString() + " subtitle \"" + "\u00A76Lifting in" + "\""));
				if (world instanceof ServerLevel _level)
					_level.getServer().getCommands().performPrefixedCommand(
							new CommandSourceStack(CommandSource.NULL, new Vec3((entity.getX()), (entity.getY()), (entity.getZ())), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
							("title " + entity.getDisplayName().getString() + " times " + 0 + 1 + 0));
				if (world instanceof ServerLevel _level)
					_level.getServer().getCommands().performPrefixedCommand(
							new CommandSourceStack(CommandSource.NULL, new Vec3((entity.getX()), (entity.getY()), (entity.getZ())), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
							("title " + entity.getDisplayName().getString() + " title \"" + (countdown + "s") + "\""));
			}
			while (countdown >= 0) {
				EriniumAdventureMod.queueServerWork(20, () -> {
					countdown = countdown - 1;
					if (entity instanceof Player || entity instanceof ServerPlayer) {
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(
									new CommandSourceStack(CommandSource.NULL, new Vec3((entity.getX()), (entity.getY()), (entity.getZ())), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									("title " + entity.getDisplayName().getString() + " subtitle \"" + "\u00A76Lifting in" + "\""));
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(
									new CommandSourceStack(CommandSource.NULL, new Vec3((entity.getX()), (entity.getY()), (entity.getZ())), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									("title " + entity.getDisplayName().getString() + " times " + 0 + 1 + 0));
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(
									new CommandSourceStack(CommandSource.NULL, new Vec3((entity.getX()), (entity.getY()), (entity.getZ())), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									("title " + entity.getDisplayName().getString() + " title \"" + (countdown + "s") + "\""));
					}
				});
			}
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
			if (entityBooster instanceof RocketBoosterEntity _datEntSetL)
				_datEntSetL.getEntityData().set(RocketBoosterEntity.DATA_Lifting, true);
			if (entityHead instanceof RocketHeadEntity _datEntSetL)
				_datEntSetL.getEntityData().set(RocketHeadEntity.DATA_Lifting, true);
		}
	}
}
