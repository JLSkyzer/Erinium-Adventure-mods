package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.minecraft.client.renderer.block.model.Variant;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.items.ItemHandlerHelper;

import java.io.File;

public class TestCmdProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		ItemStack item = ItemStack.EMPTY;
		File File = new File("");
		java.util.List<net.minecraft.network.chat.Component> componentList = new java.util.ArrayList<>();
		item = new ItemStack(Items.DIAMOND_SWORD);
		componentList.add(net.minecraft.network.chat.Component.literal("Lore text"));
		componentList.add(net.minecraft.network.chat.Component.literal("\u00A7bLore text 2"));
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
		if (entity instanceof Player _player) {
			ItemStack _setstack = item;
			_setstack.setCount(1);

			_setstack.enchant(Enchantments.SHARPNESS, 3);
			_setstack.enchant(Enchantments.UNBREAKING, 3);
			//{Damage:0,Enchantments:{id:"minecraft:sharpness",lvl:3s},{id:"minecraft:unbreaking",lvl:3s},display:{Lore:'{"italic":false,"text":"Lore text"}','{"italic":false,"text":"Lore text 2"}'}}

			ItemHandlerHelper.giveItemToPlayer(_player, _setstack);

			if (entity instanceof Player _temp && !_temp.level().isClientSide())
				_temp.displayClientMessage(Component.literal(("" + _setstack + " " + _setstack.getTag().toString())), false);

			String nbtString = "{Damage:0,Enchantments:{id:\"minecraft:sharpness\",lvl:3s},{id:\"minecraft:unbreaking\",lvl:3s},display:{Lore:'{\"italic\":false,\"text\":\"Lore text\"}','{\"italic\":false,\"text\":\"Lore text 2\"}'}}";

			// Créer un nouveau CompoundTag
			CompoundTag compoundTag = new CompoundTag();
			compoundTag.putString("", nbtString);
			_setstack = Items.DIAMOND_SHOVEL.getDefaultInstance();
			_setstack.setTag();


			ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
		}
	}
}
