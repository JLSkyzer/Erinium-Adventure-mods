package fr.eriniumgroups.erinium.factionmod.procedures;

import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;

import fr.eriniumgroups.erinium.factionmod.configuration.ConfigConfiguration;

public class TargetEntityHaveItemNeedToCreateProcedure {
	public static boolean execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return false;
		double count = 0;
		count = 0;
		if (entity.getCapability(Capabilities.ItemHandler.ENTITY, null) instanceof IItemHandlerModifiable _modHandler) {
			for (int _idx = 0; _idx < _modHandler.getSlots(); _idx++) {
				ItemStack itemstackiterator = _modHandler.getStackInSlot(_idx).copy();
				if (itemstackiterator.getItem() == BuiltInRegistries.ITEM.get(new ResourceLocation(((ConfigConfiguration.ITEM_NEED_CREATE.get())).toLowerCase(java.util.Locale.ENGLISH)))) {
					count = count + itemstackiterator.getCount();
				}
			}
		}
		if ((entity instanceof Player _playerHasItem
				? _playerHasItem.getInventory().contains(new ItemStack(BuiltInRegistries.ITEM.get(new ResourceLocation(((ConfigConfiguration.ITEM_NEED_CREATE.get())).toLowerCase(java.util.Locale.ENGLISH)))))
				: false) && count >= (double) ConfigConfiguration.ITEM_NUMBER_CREATE.get()) {
			return true;
		}
		return false;
	}
}
