package fr.eriniumgroups.erinium.ericonomy.procedures;

import net.minecraft.world.item.ItemStack;

public class CobbleVoidRightclickedProcedure {
	public static void execute(ItemStack itemstack) {
		if (itemstack.getOrCreateTag().getBoolean("enabled")) {
			itemstack.getOrCreateTag().putBoolean("enabled", false);
		} else {
			itemstack.getOrCreateTag().putBoolean("enabled", true);
		}
	}
}
