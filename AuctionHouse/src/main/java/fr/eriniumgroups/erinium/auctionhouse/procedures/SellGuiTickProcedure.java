package fr.eriniumgroups.erinium.auctionhouse.procedures;

import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.client.gui.components.EditBox;

import java.util.function.Supplier;
import java.util.concurrent.atomic.AtomicReference;
import java.util.Map;
import java.util.HashMap;

import fr.eriniumgroups.erinium.auctionhouse.network.EriniumAhModVariables;

public class SellGuiTickProcedure {
	public static void execute(LevelAccessor world, Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		if (!(entity.getCapability(EriniumAhModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAhModVariables.PlayerVariables())).sell_loaded) {
			if (!((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Blocks.AIR.asItem())) {
				if (guistate.get("text:quantity") instanceof EditBox _tf)
					_tf.setValue((new java.text.DecimalFormat("##")
							.format((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getCount())));
				{
					double _setval = (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getCount();
					entity.getCapability(EriniumAhModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.selltempquantity = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				{
					AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
					entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(_iitemhandlerref::set);
					if (_iitemhandlerref.get() != null) {
						for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
							ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
							if (itemstackiterator.getItem() == (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY)
									.getItem()) {
								{
									double _setval = (entity.getCapability(EriniumAhModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAhModVariables.PlayerVariables())).selltempquantity + itemstackiterator.getCount();
									entity.getCapability(EriniumAhModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
										capability.selltempquantity = _setval;
										capability.syncPlayerVariables(entity);
									});
								}
							}
						}
					}
				}
			}
			{
				boolean _setval = true;
				entity.getCapability(EriniumAhModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.sell_loaded = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
