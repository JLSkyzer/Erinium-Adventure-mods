package fr.eriniumgroups.erinium.ericonomy.procedures;

import net.minecraft.world.item.ItemStack;

public class CobbleVoidHasItemGlowingEffectProcedure {
	public static boolean execute(ItemStack itemstack) {
		return itemstack.getOrCreateTag().getBoolean("enabled");
	}
}
