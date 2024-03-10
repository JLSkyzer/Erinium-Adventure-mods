package fr.eriniumgroups.erinium.auctionhouse.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.nbt.StringTag;
import net.minecraft.client.gui.components.EditBox;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import fr.eriniumgroups.erinium.auctionhouse.network.EriniumAhModVariables;

import com.google.gson.JsonObject;
import com.google.gson.Gson;

public class AhMainMenuWhileThisGUIIsOpenTickProcedure {
	public static void execute(LevelAccessor world, Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		File file = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		ItemStack tempItem = ItemStack.EMPTY;
		String BlackListItem = "";
		double whilecount = 0;
		double Count = 0;
		double slot_count = 0;
		double lore_count = 0;
		if (!(entity.getCapability(EriniumAhModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAhModVariables.PlayerVariables())).ah_initialised) {
			Count = (entity.getCapability(EriniumAhModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAhModVariables.PlayerVariables())).ah_page * 7;
			slot_count = 0;
			whilecount = 0;
			String cheminDossier = (FMLPaths.GAMEDIR.get().toString() + "/EriniumAH/");
			java.io.File dossier = new java.io.File(cheminDossier);
			if (dossier.exists() && dossier.isDirectory()) {
				java.io.File[] fichiers = dossier.listFiles();
				// Parcours tous les fichiers du dossier
				for (java.io.File currentFile : fichiers) {
					// ...
					if (slot_count < 7) {
						if (Count > 0) {
							if (whilecount < Count) {
								whilecount = whilecount + 1;
							} else {
								if ((guistate.containsKey("text:search") ? ((EditBox) guistate.get("text:search")).getValue() : "").equals("")) {
									file = new File((FMLPaths.GAMEDIR.get().toString() + "/EriniumAH/"), File.separator + currentFile.getName());
									{
										try {
											BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
											StringBuilder jsonstringbuilder = new StringBuilder();
											String line;
											while ((line = bufferedReader.readLine()) != null) {
												jsonstringbuilder.append(line);
											}
											bufferedReader.close();
											JsonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
											java.util.List<net.minecraft.network.chat.Component> componentList = new java.util.ArrayList<>();
											tempItem = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation((JsonObject.get("item").getAsString()).toLowerCase(java.util.Locale.ENGLISH))));
											lore_count = 0;
											if (!(tempItem.getItem() == Blocks.AIR.asItem())) {
												while (JsonObject.has(("lore" + new java.text.DecimalFormat("##").format(lore_count)))) {
													componentList.add(net.minecraft.network.chat.Component.literal(JsonObject.get(("lore" + new java.text.DecimalFormat("##").format(lore_count))).getAsString()));
													lore_count = lore_count + 1;
												}
												componentList.add(net.minecraft.network.chat.Component.literal(("\u00A76ID : " + currentFile.getName())));
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
												componentList.clear();
												if (tempItem != null) {
													net.minecraft.nbt.ListTag listTag = new net.minecraft.nbt.ListTag();
													String string = JsonObject.get("enchants").getAsString();
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
													net.minecraft.world.item.enchantment.EnchantmentHelper.setEnchantments(net.minecraft.world.item.enchantment.EnchantmentHelper.deserializeEnchantments(listTag), tempItem);
												}
												tempItem.setDamageValue((int) JsonObject.get("damage").getAsDouble());
												tempItem.setHoverName(Component.literal(JsonObject.get("name").getAsString()));
												tempItem.getOrCreateTag().putString("name", JsonObject.get("name").getAsString());
												tempItem.getOrCreateTag().putString("player", JsonObject.get("player").getAsString());
												tempItem.getOrCreateTag().putString("uuid", JsonObject.get("uuid").getAsString());
												tempItem.getOrCreateTag().putString("id", currentFile.getName());
												tempItem.getOrCreateTag().putDouble("quantity", JsonObject.get("quantity").getAsDouble());
												tempItem.getOrCreateTag().putDouble("price", JsonObject.get("price").getAsDouble());
												if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
													ItemStack _setstack = tempItem;
													_setstack.setCount(1);
													((Slot) _slots.get((int) slot_count)).set(_setstack);
													_player.containerMenu.broadcastChanges();
												}
												slot_count = slot_count + 1;
											}
										} catch (IOException e) {
											e.printStackTrace();
										}
									}
								} else {
									file = new File((FMLPaths.GAMEDIR.get().toString() + "/EriniumAH/"), File.separator + currentFile.getName());
									{
										try {
											BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
											StringBuilder jsonstringbuilder = new StringBuilder();
											String line;
											while ((line = bufferedReader.readLine()) != null) {
												jsonstringbuilder.append(line);
											}
											bufferedReader.close();
											JsonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
											java.util.List<net.minecraft.network.chat.Component> componentList = new java.util.ArrayList<>();
											tempItem = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation((JsonObject.get("item").getAsString()).toLowerCase(java.util.Locale.ENGLISH))));
											if (!(tempItem.getItem() == Blocks.AIR.asItem()) && (tempItem.getDisplayName().getString()).contains(guistate.containsKey("text:search") ? ((EditBox) guistate.get("text:search")).getValue() : "")) {
												lore_count = 0;
												while (JsonObject.has(("lore" + new java.text.DecimalFormat("##").format(lore_count)))) {
													componentList.add(net.minecraft.network.chat.Component.literal(JsonObject.get(("lore" + new java.text.DecimalFormat("##").format(lore_count))).getAsString()));
													lore_count = lore_count + 1;
												}
												componentList.add(net.minecraft.network.chat.Component.literal(("\u00A76ID : " + currentFile.getName())));
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
												componentList.clear();
												if (tempItem != null) {
													net.minecraft.nbt.ListTag listTag = new net.minecraft.nbt.ListTag();
													String string = JsonObject.get("enchants").getAsString();
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
													net.minecraft.world.item.enchantment.EnchantmentHelper.setEnchantments(net.minecraft.world.item.enchantment.EnchantmentHelper.deserializeEnchantments(listTag), tempItem);
												}
												tempItem.setDamageValue((int) JsonObject.get("damage").getAsDouble());
												tempItem.setHoverName(Component.literal(JsonObject.get("name").getAsString()));
												tempItem.getOrCreateTag().putString("name", JsonObject.get("name").getAsString());
												tempItem.getOrCreateTag().putString("player", JsonObject.get("player").getAsString());
												tempItem.getOrCreateTag().putString("uuid", JsonObject.get("uuid").getAsString());
												tempItem.getOrCreateTag().putString("id", currentFile.getName());
												tempItem.getOrCreateTag().putDouble("quantity", JsonObject.get("quantity").getAsDouble());
												tempItem.getOrCreateTag().putDouble("price", JsonObject.get("price").getAsDouble());
												if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
													ItemStack _setstack = tempItem;
													_setstack.setCount(1);
													((Slot) _slots.get((int) slot_count)).set(_setstack);
													_player.containerMenu.broadcastChanges();
												}
												slot_count = slot_count + 1;
											}
										} catch (IOException e) {
											e.printStackTrace();
										}
									}
								}
							}
						} else {
							if ((guistate.containsKey("text:search") ? ((EditBox) guistate.get("text:search")).getValue() : "").equals("")) {
								file = new File((FMLPaths.GAMEDIR.get().toString() + "/EriniumAH/"), File.separator + currentFile.getName());
								{
									try {
										BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
										StringBuilder jsonstringbuilder = new StringBuilder();
										String line;
										while ((line = bufferedReader.readLine()) != null) {
											jsonstringbuilder.append(line);
										}
										bufferedReader.close();
										JsonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
										java.util.List<net.minecraft.network.chat.Component> componentList = new java.util.ArrayList<>();
										tempItem = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation((JsonObject.get("item").getAsString()).toLowerCase(java.util.Locale.ENGLISH))));
										if (!(tempItem.getItem() == Blocks.AIR.asItem())) {
											lore_count = 0;
											while (JsonObject.has(("lore" + new java.text.DecimalFormat("##").format(lore_count)))) {
												componentList.add(net.minecraft.network.chat.Component.literal(JsonObject.get(("lore" + new java.text.DecimalFormat("##").format(lore_count))).getAsString()));
												lore_count = lore_count + 1;
											}
											componentList.add(net.minecraft.network.chat.Component.literal(("\u00A76ID : " + currentFile.getName())));
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
											componentList.clear();
											if (tempItem != null) {
												net.minecraft.nbt.ListTag listTag = new net.minecraft.nbt.ListTag();
												String string = JsonObject.get("enchants").getAsString();
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
												net.minecraft.world.item.enchantment.EnchantmentHelper.setEnchantments(net.minecraft.world.item.enchantment.EnchantmentHelper.deserializeEnchantments(listTag), tempItem);
											}
											tempItem.setDamageValue((int) JsonObject.get("damage").getAsDouble());
											tempItem.setHoverName(Component.literal(JsonObject.get("name").getAsString()));
											tempItem.getOrCreateTag().putString("name", JsonObject.get("name").getAsString());
											tempItem.getOrCreateTag().putString("player", JsonObject.get("player").getAsString());
											tempItem.getOrCreateTag().putString("uuid", JsonObject.get("uuid").getAsString());
											tempItem.getOrCreateTag().putString("id", currentFile.getName());
											tempItem.getOrCreateTag().putDouble("quantity", JsonObject.get("quantity").getAsDouble());
											tempItem.getOrCreateTag().putDouble("price", JsonObject.get("price").getAsDouble());
											if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
												ItemStack _setstack = tempItem;
												_setstack.setCount(1);
												((Slot) _slots.get((int) slot_count)).set(_setstack);
												_player.containerMenu.broadcastChanges();
											}
											slot_count = slot_count + 1;
										}
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
							} else {
								file = new File((FMLPaths.GAMEDIR.get().toString() + "/EriniumAH/"), File.separator + currentFile.getName());
								{
									try {
										BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
										StringBuilder jsonstringbuilder = new StringBuilder();
										String line;
										while ((line = bufferedReader.readLine()) != null) {
											jsonstringbuilder.append(line);
										}
										bufferedReader.close();
										JsonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
										java.util.List<net.minecraft.network.chat.Component> componentList = new java.util.ArrayList<>();
										tempItem = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation((JsonObject.get("item").getAsString()).toLowerCase(java.util.Locale.ENGLISH))));
										if (!(tempItem.getItem() == Blocks.AIR.asItem()) && (tempItem.getDisplayName().getString()).contains(guistate.containsKey("text:search") ? ((EditBox) guistate.get("text:search")).getValue() : "")) {
											lore_count = 0;
											while (JsonObject.has(("lore" + new java.text.DecimalFormat("##").format(lore_count)))) {
												componentList.add(net.minecraft.network.chat.Component.literal(JsonObject.get(("lore" + new java.text.DecimalFormat("##").format(lore_count))).getAsString()));
												lore_count = lore_count + 1;
											}
											componentList.add(net.minecraft.network.chat.Component.literal(("\u00A76ID : " + currentFile.getName())));
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
											componentList.clear();
											if (tempItem != null) {
												net.minecraft.nbt.ListTag listTag = new net.minecraft.nbt.ListTag();
												String string = JsonObject.get("enchants").getAsString();
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
												net.minecraft.world.item.enchantment.EnchantmentHelper.setEnchantments(net.minecraft.world.item.enchantment.EnchantmentHelper.deserializeEnchantments(listTag), tempItem);
											}
											tempItem.setDamageValue((int) JsonObject.get("damage").getAsDouble());
											tempItem.setHoverName(Component.literal(JsonObject.get("name").getAsString()));
											tempItem.getOrCreateTag().putString("name", JsonObject.get("name").getAsString());
											tempItem.getOrCreateTag().putString("player", JsonObject.get("player").getAsString());
											tempItem.getOrCreateTag().putString("uuid", JsonObject.get("uuid").getAsString());
											tempItem.getOrCreateTag().putString("id", currentFile.getName());
											tempItem.getOrCreateTag().putDouble("quantity", JsonObject.get("quantity").getAsDouble());
											tempItem.getOrCreateTag().putDouble("price", JsonObject.get("price").getAsDouble());
											if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
												ItemStack _setstack = tempItem;
												_setstack.setCount(1);
												((Slot) _slots.get((int) slot_count)).set(_setstack);
												_player.containerMenu.broadcastChanges();
											}
											slot_count = slot_count + 1;
										}
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
							}
						}
					} else {
						break;
					}
				}
			} else {
				System.out.println("Le dossier n'existe pas ou n'est pas un dossier valide.");
			}
			{
				boolean _setval = true;
				entity.getCapability(EriniumAhModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.ah_initialised = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
