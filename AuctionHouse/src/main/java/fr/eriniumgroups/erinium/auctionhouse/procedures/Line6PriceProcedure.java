package fr.eriniumgroups.erinium.auctionhouse.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import java.util.function.Supplier;
import java.util.Map;

import fr.eriniumgroups.erinium.auctionhouse.network.EriniumAhModVariables;

public class Line6PriceProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		if (!((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(6)).getItem() : ItemStack.EMPTY).getItem() == Blocks.AIR.asItem())) {
			return (entity.getCapability(EriniumAhModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAhModVariables.PlayerVariables())).theme_text_color + ""
					+ (new java.text.DecimalFormat("#,###,##").format(
							(entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(6)).getItem() : ItemStack.EMPTY).getOrCreateTag().getDouble("price")))
					+ " $";
		}
		return (entity.getCapability(EriniumAhModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAhModVariables.PlayerVariables())).theme_text_color;
	}
}
