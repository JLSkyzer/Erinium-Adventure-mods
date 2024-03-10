package fr.eriniumgroups.erinium.auctionhouse.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.ModList;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.Minecraft;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;

import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import fr.eriniumgroups.erinium.auctionhouse.network.EriniumAhModVariables;
import fr.eriniumgroups.erinium.auctionhouse.client.toasts.NotEnoughtMoneyToast;

import com.google.gson.JsonObject;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

public class SellItemProcedure {
	public static void execute(LevelAccessor world, Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		File file = new File("");
		ItemStack temp_item = ItemStack.EMPTY;
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		double idCount = 0;
		double lore_count = 0;
		double temp_quantity = 0;
		if (!((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Blocks.AIR.asItem())) {
			if (new Object() {
				double convert(String s) {
					try {
						return Double.parseDouble(s.trim());
					} catch (Exception e) {
					}
					return 0;
				}
			}.convert(guistate.containsKey("text:price") ? ((EditBox) guistate.get("text:price")).getValue() : "") > 0) {
				if (new Object() {
					double convert(String s) {
						try {
							return Double.parseDouble(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert(guistate.containsKey("text:quantity")
						? ((EditBox) guistate.get("text:quantity")).getValue()
						: "") <= (entity.getCapability(EriniumAhModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAhModVariables.PlayerVariables())).selltempquantity) {
					file = new File((FMLPaths.GAMEDIR.get().toString() + "/EriniumAH/"), File.separator + (new java.text.DecimalFormat("##").format(idCount) + ".json"));
					while (file.exists()) {
						idCount = idCount + 1;
						file = new File((FMLPaths.GAMEDIR.get().toString() + "/EriniumAH/"), File.separator + (new java.text.DecimalFormat("##").format(idCount) + ".json"));
					}
					file = new File((FMLPaths.GAMEDIR.get().toString() + "/EriniumAH/"), File.separator + (new java.text.DecimalFormat("##").format(idCount) + ".json"));
					temp_item = (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY);
					temp_quantity = (entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getCount();
					if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
						((Slot) _slots.get(0)).set(ItemStack.EMPTY);
						_player.containerMenu.broadcastChanges();
					}
					try {
						file.getParentFile().mkdirs();
						file.createNewFile();
					} catch (IOException exception) {
						exception.printStackTrace();
					}
					JsonObject.addProperty("item", (ForgeRegistries.ITEMS.getKey(temp_item.getItem()).toString()));
					if (temp_item != null && temp_item.getOrCreateTagElement("display").get("Lore") != null) {
						String result = temp_item.getOrCreateTagElement("display").get("Lore").toString();
						result = result.replaceAll("'", "");
						com.google.gson.JsonArray jsonArray = com.google.gson.JsonParser.parseString(result).getAsJsonArray();
						for (int i = 0; i < jsonArray.size(); i++) {
							com.google.gson.JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
							String text = jsonObject.get("text").getAsString();
							JsonObject.addProperty(("lore" + new java.text.DecimalFormat("##").format(lore_count)), text);
							lore_count = lore_count + 1;
						}
					}
					JsonObject.addProperty("enchants", temp_item.getEnchantmentTags().toString());
					JsonObject.addProperty("damage", (temp_item.getDamageValue()));
					JsonObject.addProperty("name", (((temp_item.getDisplayName().getString()).replace("[", "")).replace("]", "")));
					JsonObject.addProperty("player", (entity.getDisplayName().getString()));
					JsonObject.addProperty("uuid", entity.getUUID().toString());
					JsonObject.addProperty("quantity", new Object() {
						double convert(String s) {
							try {
								return Double.parseDouble(s.trim());
							} catch (Exception e) {
							}
							return 0;
						}
					}.convert(guistate.containsKey("text:quantity") ? ((EditBox) guistate.get("text:quantity")).getValue() : ""));
					JsonObject.addProperty("price", new Object() {
						double convert(String s) {
							try {
								return Double.parseDouble(s.trim());
							} catch (Exception e) {
							}
							return 0;
						}
					}.convert(guistate.containsKey("text:price") ? ((EditBox) guistate.get("text:price")).getValue() : ""));
					{
						Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
						try {
							FileWriter fileWriter = new FileWriter(file);
							fileWriter.write(mainGSONBuilderVariable.toJson(JsonObject));
							fileWriter.close();
						} catch (IOException exception) {
							exception.printStackTrace();
						}
					}
					if (guistate.containsKey("checkbox:announce") && ((Checkbox) guistate.get("checkbox:announce")).selected()) {
						if (new Object() {
							private double getPlayerMoney(Entity entity) {
								if (ModList.get().isLoaded("ericonomy")) {
									// Procedure here
									java.io.File eriFile = new java.io.File("");
									com.google.gson.JsonObject eriJsonObject = new com.google.gson.JsonObject();
									double returnnbr = 0;
									eriFile = new File((FMLPaths.GAMEDIR.get().toString() + "/Ericonomy/accounts/"), File.separator + (entity.getUUID().toString() + ".json"));
									try {
										BufferedReader bufferedReader = new BufferedReader(new FileReader(eriFile));
										StringBuilder jsonstringbuilder = new StringBuilder();
										String line;
										while ((line = bufferedReader.readLine()) != null) {
											jsonstringbuilder.append(line);
										}
										bufferedReader.close();
										eriJsonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
										// Retour
										returnnbr = eriJsonObject.get("money").getAsDouble();
									} catch (IOException e) {
										e.printStackTrace();
									}
									return returnnbr;
								} else {
									if (ModList.get().isLoaded("erinium_logs")) {
										if (world instanceof ServerLevel _level)
											_level.getServer().getCommands().performPrefixedCommand(
													new CommandSourceStack(CommandSource.NULL, new Vec3(0, 0, 0), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
													"erilog " + "GameLoading" + " " + "info" + " " + "Mod Ericonomy not exist");
									} else {
										System.out.println("Erilog is not installed ! install here : https://github.com/JLSkyzer/Erinium-Adventure-mods/releases/tag/Erilog");
									}
									return 0;
								}
							};
						}.getPlayerMoney(entity) >= 1000) {
							if (ModList.get().isLoaded("ericonomy")) {
								// Procedure here
								java.io.File eriFile = new java.io.File("");
								com.google.gson.JsonObject eriJsonObject = new com.google.gson.JsonObject();
								eriFile = new File((FMLPaths.GAMEDIR.get().toString() + "/Ericonomy/accounts/"), File.separator + (entity.getUUID().toString() + ".json"));
								try {
									BufferedReader bufferedReader = new BufferedReader(new FileReader(eriFile));
									StringBuilder jsonstringbuilder = new StringBuilder();
									String line;
									while ((line = bufferedReader.readLine()) != null) {
										jsonstringbuilder.append(line);
									}
									bufferedReader.close();
									eriJsonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
									if (eriJsonObject.get("money").getAsDouble() >= 1000) {
										eriJsonObject.addProperty("money", (eriJsonObject.get("money").getAsDouble() - 1000));
									} else {
										eriJsonObject.addProperty("money", 0);
									}
									{
										Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
										try {
											FileWriter fileWriter = new FileWriter(eriFile);
											fileWriter.write(mainGSONBuilderVariable.toJson(eriJsonObject));
											fileWriter.close();
										} catch (IOException exception) {
											exception.printStackTrace();
										}
									}
								} catch (IOException e) {
									e.printStackTrace();
								}
							} else {
								if (ModList.get().isLoaded("erinium_logs")) {
									if (world instanceof ServerLevel _level)
										_level.getServer().getCommands().performPrefixedCommand(
												new CommandSourceStack(CommandSource.NULL, new Vec3(0, 0, 0), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
												"erilog " + "GameLoading" + " " + "info" + " " + "Mod Ericonomy not exist");
								} else {
									System.out.println("Erilog is not installed ! install here : https://github.com/JLSkyzer/Erinium-Adventure-mods/releases/tag/Erilog");
								}
							}
							if (!world.isClientSide() && world.getServer() != null)
								world.getServer().getPlayerList()
										.broadcastSystemMessage(Component.literal(("\u00A76Erinium AH \u00A7e>>> \u00A7b" + entity.getDisplayName().getString() + " \u00A7asell \u00A7dx" + new java.text.DecimalFormat("##").format(new Object() {
											double convert(String s) {
												try {
													return Double.parseDouble(s.trim());
												} catch (Exception e) {
												}
												return 0;
											}
										}.convert(guistate.containsKey("text:quantity") ? ((EditBox) guistate.get("text:quantity")).getValue() : "")) + " " + temp_item.getDisplayName().getString() + " \u00A7afor \u00A72"
												+ new java.text.DecimalFormat("#,###.##").format(new Object() {
													double convert(String s) {
														try {
															return Double.parseDouble(s.trim());
														} catch (Exception e) {
														}
														return 0;
													}
												}.convert(guistate.containsKey("text:price") ? ((EditBox) guistate.get("text:price")).getValue() : "")) + "$")), false);
						} else {
							Minecraft.getInstance().getToasts().addToast(new NotEnoughtMoneyToast());
						}
					}
					if (entity instanceof Player _player) {
						ItemStack _stktoremove = temp_item;
						_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(),
								(int) ((entity.getCapability(EriniumAhModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAhModVariables.PlayerVariables())).selltempquantity - temp_quantity), _player.inventoryMenu.getCraftSlots());
					}
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("\u00A7aSuccefully set your item in auction house"), false);
				}
			}
		}
	}
}
