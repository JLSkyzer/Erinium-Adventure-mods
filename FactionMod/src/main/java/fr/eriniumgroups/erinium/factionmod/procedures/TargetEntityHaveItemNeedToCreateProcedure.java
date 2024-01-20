package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;

import java.util.concurrent.atomic.AtomicReference;

import fr.eriniumgroups.erinium.factionmod.configuration.ConfigConfiguration;

public class TargetEntityHaveItemNeedToCreateProcedure {
	public static boolean execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return false;
		double count = 0;
		count = 0;
		{
			AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
			entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(_iitemhandlerref::set);
			if (_iitemhandlerref.get() != null) {
				for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
					ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
					if (itemstackiterator.getItem() == ForgeRegistries.ITEMS.getValue(new ResourceLocation(ConfigConfiguration.ITEM_NEED_CREATE.get()))) {
						count = count + itemstackiterator.getCount();
					}
				}
			}
		}
		if ((entity instanceof Player _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(ConfigConfiguration.ITEM_NEED_CREATE.get())))) : false)
				&& count >= (double) ConfigConfiguration.ITEM_NUMBER_CREATE.get()) {
			return true;
		}
		return false;
	}
}
