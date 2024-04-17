package fr.eriniumgroups.erinium.ericonomy.procedures;

import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.nbt.StringTag;

public class CobbleVoidItemInInventoryTickProcedure {
	public static void execute(LevelAccessor world, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		double totalMoney = 0;
		if (itemstack.getOrCreateTag().getBoolean("enabled")) {
			if (entity.getCapability(Capabilities.ItemHandler.ENTITY, null) instanceof IItemHandlerModifiable _modHandler) {
				for (int _idx = 0; _idx < _modHandler.getSlots(); _idx++) {
					ItemStack itemstackiterator = _modHandler.getStackInSlot(_idx).copy();
					if ((itemstackiterator.getItem() instanceof BlockItem _bi ? _bi.getBlock().defaultBlockState() : Blocks.AIR.defaultBlockState()).is(BlockTags.create(new ResourceLocation("minecraft:stone_ore_replaceables")))
							|| itemstackiterator.is(ItemTags.create(new ResourceLocation("minecraft:stone_crafting_materials")))) {
						itemstack.getOrCreateTag().putDouble("stones", (itemstack.getOrCreateTag().getDouble("stones") + itemstackiterator.getCount()));
						if (entity instanceof Player _player) {
							ItemStack _stktoremove = itemstackiterator;
							_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), itemstackiterator.getCount(), _player.inventoryMenu.getCraftSlots());
						}
					}
				}
			}
		}
		java.util.List<net.minecraft.network.chat.Component> componentList = new java.util.ArrayList<>();
		componentList.add(net.minecraft.network.chat.Component.literal(("\u00A7eStored stones : \u00A7a" + new java.text.DecimalFormat("#,###").format(itemstack.getOrCreateTag().getDouble("stones")))));
		if (componentList.size() > 0) {
			net.minecraft.nbt.CompoundTag display = itemstack.getOrCreateTagElement("display");
			net.minecraft.nbt.ListTag loreItems = new net.minecraft.nbt.ListTag();
			for (net.minecraft.network.chat.Component l : componentList) {
				if (l instanceof net.minecraft.network.chat.MutableComponent) {
					((net.minecraft.network.chat.MutableComponent) l).withStyle(style -> style.withItalic(style.isItalic()));
				}
				loreItems.add(StringTag.valueOf(net.minecraft.network.chat.Component.Serializer.toJson(l)));
			}
			display.put("Lore", loreItems);
		}
	}
}
