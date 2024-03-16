package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.nbt.StringTag;

import java.util.function.Supplier;
import java.util.Map;

import fr.eriniumgroup.eriniumadventure.base.network.EriniumAdventureModVariables;
import fr.eriniumgroup.eriniumadventure.base.init.EriniumAdventureModItems;

public class StatGui0WhileThisGUIIsOpenTickProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		double i = 0;
		double j = 0;
		String tag = "";
		ItemStack tempItem = ItemStack.EMPTY;
		if (!(entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAdventureModVariables.PlayerVariables())).stat_initialised) {
			java.util.List<net.minecraft.network.chat.Component> componentList = new java.util.ArrayList<>();
			tempItem = new ItemStack(EriniumAdventureModItems.FLAME_ICON.get());
			tempItem.enchant(Enchantments.FIRE_ASPECT, 10);
			tempItem.setHoverName(Component.literal("\u00A76Flame reduction"));
			componentList.add(net.minecraft.network.chat.Component.literal(("\u00A77" + Component.translatable("stats.flame.desc").getString())));
			componentList.add(net.minecraft.network.chat.Component.literal(("\u00A76Level : \u00A7a"
					+ new java.text.DecimalFormat("##").format((entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAdventureModVariables.PlayerVariables())).fire_reduction * 10)
					+ " \u00A7f/ \u00A726")));
			componentList.add(net.minecraft.network.chat.Component.literal(("\u00A76Flamme reduction : \u00A7a"
					+ new java.text.DecimalFormat("##").format((entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAdventureModVariables.PlayerVariables())).fire_reduction * 100) + "%")));
			if ((entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAdventureModVariables.PlayerVariables())).fire_reduction < 0.6) {
				tempItem.getOrCreateTag().putDouble("price", (10000 * ((entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAdventureModVariables.PlayerVariables())).fire_reduction * 10 + 1)));
				componentList.add(net.minecraft.network.chat.Component.literal(("\u00A76Price : \u00A7d" + new java.text.DecimalFormat("#,###.##").format(tempItem.getOrCreateTag().getDouble("price")) + "$")));
			}
			if (componentList.size() > 0) {
				net.minecraft.nbt.CompoundTag display = tempItem.getOrCreateTagElement("display");
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
				ItemStack _setstack = tempItem;
				_setstack.setCount(1);
				((Slot) _slots.get(0)).set(_setstack);
				_player.containerMenu.broadcastChanges();
			}
			{
				boolean _setval = true;
				entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.stat_initialised = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
