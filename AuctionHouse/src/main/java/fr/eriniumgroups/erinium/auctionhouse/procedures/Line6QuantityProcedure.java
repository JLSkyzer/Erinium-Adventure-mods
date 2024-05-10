package fr.eriniumgroups.erinium.auctionhouse.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import java.util.function.Supplier;
import java.util.Map;

import fr.eriniumgroups.erinium.auctionhouse.network.EriniumAhModVariables;

public class Line6QuantityProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		if (!((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(6)).getItem() : ItemStack.EMPTY).getItem() == Blocks.AIR.asItem())) {
			return entity.getData(EriniumAhModVariables.PLAYER_VARIABLES).theme_text_color + "x " + (new java.text.DecimalFormat("##").format(
					(entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(6)).getItem() : ItemStack.EMPTY).getOrCreateTag().getDouble("quantity")));
		}
		return entity.getData(EriniumAhModVariables.PLAYER_VARIABLES).theme_text_color;
	}
}
