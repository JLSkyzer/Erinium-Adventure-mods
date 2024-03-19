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
import fr.eriniumgroup.eriniumadventure.base.configuration.ServerConfigConfiguration;

public class InitialiseHeartProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		ItemStack tempItem = ItemStack.EMPTY;
		java.util.List<net.minecraft.network.chat.Component> componentList = new java.util.ArrayList<>();
		tempItem = new ItemStack(EriniumAdventureModItems.HEART_ICON.get());
		tempItem.enchant(Enchantments.BLOCK_FORTUNE, 10);
		tempItem.setHoverName(Component.literal("\u00A76Live upgrade"));
		componentList.add(net.minecraft.network.chat.Component.literal(("\u00A77" + Component.translatable("stats.life.desc").getString())));
		componentList.add(net.minecraft.network.chat.Component.literal(
				("\u00A76Level : \u00A7a" + new java.text.DecimalFormat("##").format((entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAdventureModVariables.PlayerVariables())).health_multiplier)
						+ " \u00A7f/ \u00A72" + new java.text.DecimalFormat("##").format((double) ServerConfigConfiguration.HEALTH_MAX.get()))));
		componentList.add(net.minecraft.network.chat.Component.literal(("\u00A76Your health : \u00A7a"
				+ new java.text.DecimalFormat("##").format(20 + (entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAdventureModVariables.PlayerVariables())).health_multiplier * 10) + "")));
		if ((entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAdventureModVariables.PlayerVariables())).health_multiplier < (double) ServerConfigConfiguration.HEALTH_MAX.get()) {
			tempItem.getOrCreateTag().putDouble("price", (12500 * ((entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAdventureModVariables.PlayerVariables())).health_multiplier + 1)));
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
			((Slot) _slots.get(1)).set(_setstack);
			_player.containerMenu.broadcastChanges();
		}
	}
}
