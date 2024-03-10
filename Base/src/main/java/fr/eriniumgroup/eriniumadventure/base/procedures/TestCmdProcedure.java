package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

import java.io.File;

import fr.eriniumgroup.eriniumadventure.base.init.EriniumAdventureModEntities;

public class TestCmdProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		File File = new File("");
		ItemStack item = ItemStack.EMPTY;
		ItemStack item2 = ItemStack.EMPTY;
		if (world instanceof ServerLevel _level) {
			Entity entityToSpawn = EriniumAdventureModEntities.ROCKET_BOOSTER.get().spawn(_level, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
			if (entityToSpawn != null) {
				entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
			}
		}
		if (world instanceof ServerLevel _level) {
			Entity entityToSpawn = EriniumAdventureModEntities.ROCKET_HEAD.get().spawn(_level, BlockPos.containing(x, y + 6, z), MobSpawnType.MOB_SUMMONED);
			if (entityToSpawn != null) {
				entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
			}
		}
	}
}
