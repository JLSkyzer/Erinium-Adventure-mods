package fr.eriniumgroups.eriniumcrate.procedures;

import net.neoforged.neoforge.items.ItemHandlerHelper;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.nbt.StringTag;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.Random;
import java.util.Objects;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import java.io.File;

import fr.eriniumgroups.eriniumcrate.network.EriniumcrateModVariables;
import fr.eriniumgroups.eriniumcrate.init.EriniumcrateModItems;
import fr.eriniumgroups.eriniumcrate.EriniumcrateMod;

public class BasiccrateOnBlockRightClickedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject SubJsonObject = new com.google.gson.JsonObject();
		File file = new File("");
		ItemStack temp_item = ItemStack.EMPTY;
		double chance = 0;
		double lore_index = 0;
		double index = 0;
		double slot_count = 0;
		java.util.Map<String, Object> listchance = new java.util.HashMap<>();
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == EriniumcrateModItems.BASIC_KEY.get()) {
			{
				EriniumcrateModVariables.PlayerVariables _vars = entity.getData(EriniumcrateModVariables.PLAYER_VARIABLES);
				_vars.lootfor = "basic";
				_vars.syncPlayerVariables(entity);
			}
			JsonObject = ReturnJsonObjectForLootProcedure.execute(entity);
			chance = Math.random() * 100;
			while (JsonObject.has(("loot_" + new java.text.DecimalFormat("##").format(index)))) {
				SubJsonObject = JsonObject.get(("loot_" + new java.text.DecimalFormat("##").format(index))).getAsJsonObject();
				if (chance >= SubJsonObject.get("chance").getAsDouble()) {
					listchance.put(("loot_" + new java.text.DecimalFormat("##").format(index)), (chance - SubJsonObject.get("chance").getAsDouble()));
				} else {
					listchance.put(("loot_" + new java.text.DecimalFormat("##").format(index)), (SubJsonObject.get("chance").getAsDouble() - chance));
				}
				index = index + 1;
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
			}
			SubJsonObject = JsonObject.get(new Object() {
				private String getMinValue() {
					if (!listchance.isEmpty()) {
						// Initialisation des variables pour la valeur minimale et son nom de clé
						List<String> clesMin = new ArrayList<>();
						Double EriValeurMin = Double.MAX_VALUE;
						// Parcours de la HashMap pour trouver la valeur minimale et ses clés correspondantes
						for (Map.Entry<String, Object> entry : listchance.entrySet()) {
							if ((Double) entry.getValue() < EriValeurMin) {
								EriValeurMin = (Double) entry.getValue();
								clesMin.clear(); // Réinitialiser la liste des clés minimales
								clesMin.add(entry.getKey());
							} else if (Objects.equals(entry.getValue(), EriValeurMin)) {
								clesMin.add(entry.getKey());
							}
						}
						// Sélection aléatoire d'une clé parmi celles ayant la valeur minimale
						Random random = new Random();
						return clesMin.get(random.nextInt(clesMin.size()));
					}
					return null;
				}
			}.getMinValue()).getAsJsonObject();
			temp_item = new ItemStack(BuiltInRegistries.ITEM.get(new ResourceLocation((SubJsonObject.get("item").getAsString()).toLowerCase(java.util.Locale.ENGLISH))));
			temp_item.setHoverName(Component.literal(SubJsonObject.get("item_name").getAsString()));
			java.util.List<net.minecraft.network.chat.Component> componentList = new java.util.ArrayList<>();
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
			if (entity instanceof Player _player) {
				ItemStack _setstack = temp_item.copy();
				_setstack.setCount((int) SubJsonObject.get("count").getAsDouble());
				ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
			}
			if (entity instanceof Player _player) {
				ItemStack _stktoremove = new ItemStack(EriniumcrateModItems.BASIC_KEY.get());
				_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
			}
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(("You won : " + SubJsonObject.get("count").getAsDouble() + "x " + SubJsonObject.get("item_name").getAsString())), false);
		}
	}
}
