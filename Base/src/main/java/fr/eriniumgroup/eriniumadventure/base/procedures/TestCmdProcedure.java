package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.nbt.StringTag;

import java.io.File;

import fr.eriniumgroup.eriniumadventure.base.init.EriniumAdventureModItems;

public class TestCmdProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		File File = new File("");
		ItemStack item = ItemStack.EMPTY;
		ItemStack item2 = ItemStack.EMPTY;
		java.util.List<net.minecraft.network.chat.Component> componentList = new java.util.ArrayList<>();
		componentList.add(net.minecraft.network.chat.Component.literal("\u00A7aMy first text !"));
		componentList.add(net.minecraft.network.chat.Component.literal("\u00A7bMy second text !"));
		item = new ItemStack(Items.NETHERITE_SWORD);
		item.enchant(Enchantments.UNBREAKING, 3);
		item.enchant(Enchantments.SHARPNESS, 10);
		if (componentList.size() > 0) {
			net.minecraft.nbt.CompoundTag display = item.getOrCreateTagElement("display");
			net.minecraft.nbt.ListTag loreItems = new net.minecraft.nbt.ListTag();
			for (net.minecraft.network.chat.Component l : componentList) {
				if (l instanceof net.minecraft.network.chat.MutableComponent) {
					((net.minecraft.network.chat.MutableComponent) l).withStyle(style -> style.withItalic(style.isItalic()));
				}
				loreItems.add(StringTag.valueOf(net.minecraft.network.chat.Component.Serializer.toJson(l)));
			}
			display.put("Lore", loreItems);
		}
		if (item != null) {
			String result = item.getOrCreateTagElement("display").get("Lore").toString();
			result = result.replaceAll("'", "");
			com.google.gson.JsonArray jsonArray = com.google.gson.JsonParser.parseString(result).getAsJsonArray();
			for (int i = 0; i < jsonArray.size(); i++) {
				com.google.gson.JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
				String text = jsonObject.get("text").getAsString();
				if (entity instanceof Player _player)
					_player.displayClientMessage(Component.literal(("Hey lore's text : " + text)), false);
			}
		}
		if (entity instanceof Player _player) {
			ItemStack _setstack = item;
			_setstack.setCount(1);
			ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
		}
		item2 = new ItemStack(EriniumAdventureModItems.NITRIATE_AXE.get());
		if (item2 != null) {
			net.minecraft.nbt.ListTag listTag = new net.minecraft.nbt.ListTag();
			String string = item.getEnchantmentTags().toString();
			//System.out.println("Enchant string : " + string);
			java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("\\{id:\"(.*?)\",lvl:(\\d+)s\\}");
			java.util.regex.Matcher matcher = pattern.matcher(string);
			while (matcher.find()) {
				String id = matcher.group(1);
				int lvl = Integer.parseInt(matcher.group(2));
				net.minecraft.nbt.CompoundTag compoundTag = new net.minecraft.nbt.CompoundTag();
				compoundTag.putString("id", id);
				compoundTag.putInt("lvl", lvl);
				listTag.add(compoundTag);
			}
			net.minecraft.world.item.enchantment.EnchantmentHelper.setEnchantments(net.minecraft.world.item.enchantment.EnchantmentHelper.deserializeEnchantments(listTag), item2);
		}
		if (entity instanceof Player _player) {
			ItemStack _setstack = item2;
			_setstack.setCount(1);
			ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
		}
	}
}
