package fr.eriniumgroups.erinium.jobs.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.nbt.StringTag;

import java.util.function.Supplier;
import java.util.Map;

import fr.eriniumgroups.erinium.jobs.init.EriniumjobsModItems;

public class EarnXpMakerWhileThisGUIIsOpenTickProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		ItemStack temp_item = ItemStack.EMPTY;
		if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Blocks.AIR.asItem()) {
			java.util.List<net.minecraft.network.chat.Component> componentList = new java.util.ArrayList<>();
			componentList.add(net.minecraft.network.chat.Component.literal(("\u00A7e" + "BREAK" + " \u00A7b" + "(When you break block)")));
			componentList.add(net.minecraft.network.chat.Component.literal(("\u00A7e" + "EAT" + " \u00A7b" + "(When you eat / drink)")));
			componentList.add(net.minecraft.network.chat.Component.literal(("\u00A7e" + "SMELTED" + " \u00A7b" + "SMELTED (An item smelted \u00A7c/!\\RESULT ITEM*/!\\)")));
			componentList.add(net.minecraft.network.chat.Component.literal(("\u00A7e" + "CRAFTED" + " \u00A7b" + "(An item crafted \u00A7c/!\\RESULT ITEM*/!\\)")));
			componentList.add(net.minecraft.network.chat.Component.literal(("\u00A7e" + "KILL" + " \u00A7b" + "(Kill an entities)")));
			temp_item = new ItemStack(EriniumjobsModItems.INFO_ITEM.get());
			if (componentList.size() > 0) {
				net.minecraft.nbt.CompoundTag display = temp_item.getOrCreateTagElement("display");
				net.minecraft.nbt.ListTag loreItems = new net.minecraft.nbt.ListTag();
				for (net.minecraft.network.chat.Component l : componentList) {
					if (l instanceof net.minecraft.network.chat.MutableComponent) {
						((net.minecraft.network.chat.MutableComponent) l).withStyle(style -> style.withItalic(style.isItalic()));
					}
					loreItems.add(StringTag.valueOf(net.minecraft.network.chat.Component.Serializer.toJson(l)));
				}
				display.put("Lore", loreItems);
			}
			if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
				ItemStack _setstack = temp_item;
				_setstack.setCount(1);
				((Slot) _slots.get(0)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
		}
	}
}
