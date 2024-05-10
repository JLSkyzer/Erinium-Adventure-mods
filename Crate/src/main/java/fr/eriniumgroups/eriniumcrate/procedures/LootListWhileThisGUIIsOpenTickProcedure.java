package fr.eriniumgroups.eriniumcrate.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.nbt.StringTag;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.function.Supplier;
import java.util.Map;

import java.io.File;

import fr.eriniumgroups.eriniumcrate.network.EriniumcrateModVariables;
import fr.eriniumgroups.eriniumcrate.EriniumcrateMod;

public class LootListWhileThisGUIIsOpenTickProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		File file = new File("");
		com.google.gson.JsonObject SubJsonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		double lore_index = 0;
		double index = 0;
		double slot_count = 0;
		ItemStack temp_item = ItemStack.EMPTY;
		if (!entity.getData(EriniumcrateModVariables.PLAYER_VARIABLES).page_initialised) {
			index = entity.getData(EriniumcrateModVariables.PLAYER_VARIABLES).page * 27;
			JsonObject = ReturnJsonObjectForLootProcedure.execute(entity);
			for (int index0 = 0; index0 < 27; index0++) {
				if (JsonObject.has(("loot_" + new java.text.DecimalFormat("##").format(index)))) {
					SubJsonObject = JsonObject.get(("loot_" + new java.text.DecimalFormat("##").format(index))).getAsJsonObject();
					temp_item = new ItemStack(BuiltInRegistries.ITEM.get(new ResourceLocation((SubJsonObject.get("item").getAsString()).toLowerCase(java.util.Locale.ENGLISH))));
					temp_item.setHoverName(Component.literal(SubJsonObject.get("item_name").getAsString()));
					java.util.List<net.minecraft.network.chat.Component> componentList = new java.util.ArrayList<>();
					componentList.add(net.minecraft.network.chat.Component.literal(("\u00A76Chance : " + SubJsonObject.get("chance").getAsDouble() + "%")));
					componentList.add(net.minecraft.network.chat.Component.literal(("\u00A79Event Name : " + SubJsonObject.get("eventname").getAsString())));
					componentList.add(net.minecraft.network.chat.Component.literal(("\u00A79Event Message : " + SubJsonObject.get("eventmessage").getAsString())));
					while (SubJsonObject.has(("lore_" + new java.text.DecimalFormat("##").format(lore_index)))) {
						componentList.add(net.minecraft.network.chat.Component.literal(SubJsonObject.get(("lore_" + new java.text.DecimalFormat("##").format(lore_index))).getAsString()));
						lore_index = lore_index + 1;
					}
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
					if (temp_item != null) {
						net.minecraft.nbt.ListTag listTag = new net.minecraft.nbt.ListTag();
						String string = SubJsonObject.get("item_enchant").getAsString();
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
						net.minecraft.world.item.enchantment.EnchantmentHelper.setEnchantments(net.minecraft.world.item.enchantment.EnchantmentHelper.deserializeEnchantments(listTag), temp_item);
					}
					if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
						ItemStack _setstack = temp_item.copy();
						_setstack.setCount(1);
						((Slot) _slots.get((int) slot_count)).set(_setstack);
						_player.containerMenu.broadcastChanges();
					}
					temp_item = new ItemStack(Blocks.AIR);
					SubJsonObject = new Object() {
						public com.google.gson.JsonObject parse(String rawJson) {
							try {
								return new com.google.gson.Gson().fromJson(rawJson, com.google.gson.JsonObject.class);
							} catch (Exception e) {
								EriniumcrateMod.LOGGER.error(e);
								return new com.google.gson.Gson().fromJson("{}", com.google.gson.JsonObject.class);
							}
						}
					}.parse("{}");
					slot_count = slot_count + 1;
					index = index + 1;
				} else {
					break;
				}
			}
			{
				EriniumcrateModVariables.PlayerVariables _vars = entity.getData(EriniumcrateModVariables.PLAYER_VARIABLES);
				_vars.page_initialised = true;
				_vars.syncPlayerVariables(entity);
			}
		}
	}
}
