package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;

import java.io.File;

public class TestCmdProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		ItemStack item = ItemStack.EMPTY;
		File File = new File("");
		entity.setNoGravity(false);
	}
}
