
package fr.eriniumgroups.erinium.ericonomy.item;

import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.Component;

import java.util.List;

import fr.eriniumgroups.erinium.ericonomy.procedures.CobbleVoidRightclickedProcedure;
import fr.eriniumgroups.erinium.ericonomy.procedures.CobbleVoidItemInInventoryTickProcedure;
import fr.eriniumgroups.erinium.ericonomy.procedures.CobbleVoidHasItemGlowingEffectProcedure;

public class CobbleVoidItem extends Item {
	public CobbleVoidItem() {
		super(new Item.Properties().stacksTo(1).rarity(Rarity.COMMON));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public boolean isFoil(ItemStack itemstack) {
		return CobbleVoidHasItemGlowingEffectProcedure.execute(itemstack);
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, level, list, flag);
		list.add(Component.literal("Store stones"));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
		CobbleVoidRightclickedProcedure.execute(ar.getObject());
		return ar;
	}

	@Override
	public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
		super.inventoryTick(itemstack, world, entity, slot, selected);
		CobbleVoidItemInInventoryTickProcedure.execute(world, entity, itemstack);
	}
}
