package fr.eriniumgroups.erinium.jobs.procedures;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TempProcProcedure {

	static ItemStack itemStack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("minecraft:dirt")));
	public static void execute(Entity entity) {

		if (entity == null)
			return;
		if (entity instanceof Player _player) {
			// Create itemstack with an desciption
			

			ItemStack itemStack = new ItemStack(Items.DIRT);

			// Create tooltip NBT
			ListTag lore = new ListTag();
			lore.add(StringTag.valueOf("This is a custom tooltip!"));

			// Add tooltip NBT to item
			CompoundTag display = itemStack.getOrCreateTag().getCompound("display");
			display.put("Lore", lore);

			// Set item count and give to player
			itemStack.setCount(1);
			_player.getInventory().add(itemStack);

		}
	}
}
