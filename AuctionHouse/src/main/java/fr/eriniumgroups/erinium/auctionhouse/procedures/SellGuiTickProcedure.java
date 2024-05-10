package fr.eriniumgroups.erinium.auctionhouse.procedures;

import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.client.gui.components.EditBox;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;

import fr.eriniumgroups.erinium.auctionhouse.network.EriniumAhModVariables;

public class SellGuiTickProcedure {
	public static void execute(LevelAccessor world, Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		if (!entity.getData(EriniumAhModVariables.PLAYER_VARIABLES).sell_loaded) {
			if (!((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Blocks.AIR.asItem())) {
				if (guistate.get("text:quantity") instanceof EditBox _tf)
					_tf.setValue((new java.text.DecimalFormat("##")
							.format((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getCount())));
				{
					EriniumAhModVariables.PlayerVariables _vars = entity.getData(EriniumAhModVariables.PLAYER_VARIABLES);
					_vars.selltempquantity = (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getCount();
					_vars.syncPlayerVariables(entity);
				}
				if (entity.getCapability(Capabilities.ItemHandler.ENTITY, null) instanceof IItemHandlerModifiable _modHandlerForEach) {
					for (int _idx = 0; _idx < _modHandlerForEach.getSlots(); _idx++) {
						ItemStack itemstackiterator = _modHandlerForEach.getStackInSlot(_idx).copy();
						if (itemstackiterator.getItem() == (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY)
								.getItem()) {
							{
								EriniumAhModVariables.PlayerVariables _vars = entity.getData(EriniumAhModVariables.PLAYER_VARIABLES);
								_vars.selltempquantity = entity.getData(EriniumAhModVariables.PLAYER_VARIABLES).selltempquantity + itemstackiterator.getCount();
								_vars.syncPlayerVariables(entity);
							}
						}
					}
				}
			}
			{
				EriniumAhModVariables.PlayerVariables _vars = entity.getData(EriniumAhModVariables.PLAYER_VARIABLES);
				_vars.sell_loaded = true;
				_vars.syncPlayerVariables(entity);
			}
		}
	}
}
