package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.neoforged.fml.loading.VersionSupportMatrix;
import org.joml.Matrix3dStack;

import java.io.File;

public class TestCmdProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		File File = new File("");
		ItemStack item = ItemStack.EMPTY;
		ItemStack item2 = ItemStack.EMPTY;

	}
}
