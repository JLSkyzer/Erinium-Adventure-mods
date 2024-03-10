package fr.eriniumgroups.erinium.auctionhouse.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.ModList;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.Minecraft;

import java.util.HashMap;
import java.util.ArrayList;

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

public class BuyBtnProcedure {
	public static void execute(LevelAccessor world, Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		File file = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		double quantity = 0;
		String finalText = "";
		boolean needToBeDelete = false;
		file = new File((FMLPaths.GAMEDIR.get().toString() + "/EriniumAH/"),
				File.separator + (((entity.getCapability(EriniumAhModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAhModVariables.PlayerVariables())).ah_temp_item).getOrCreateTag().getString("id")));
		if (file.exists()) {
			quantity = new Object() {
				double convert(String s) {
					try {
						return Double.parseDouble(s.trim());
					} catch (Exception e) {
					}
					return 0;
				}
			}.convert(guistate.containsKey("text:quantity") ? ((EditBox) guistate.get("text:quantity")).getValue() : "");
			needToBeDelete = false;
			if (quantity > 0 && quantity <= ((entity.getCapability(EriniumAhModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAhModVariables.PlayerVariables())).ah_temp_item).getOrCreateTag().getDouble("quantity")) {
				if (!(quantity * ((entity.getCapability(EriniumAhModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAhModVariables.PlayerVariables())).ah_temp_item).getOrCreateTag().getDouble("price") > new Object() {
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
				}.getPlayerMoney(entity))) {
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
							if (JsonObject.get("quantity").getAsDouble() - quantity <= 0) {
								needToBeDelete = true;
							} else {
								JsonObject.addProperty("quantity", (JsonObject.get("quantity").getAsDouble() - quantity));
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if (needToBeDelete) {
						if (new java.io.File(new String(file.getPath())).exists()) { // Vérifie si le fichier existe avant de le supprimer
							// Supprime le fichier
							boolean suppressionReussie = new java.io.File(file.getPath()).delete();
							if (suppressionReussie) {
								System.out.println("Le fichier a été supprimé avec succès.");
							} else {
								System.out.println("Impossible de supprimer le fichier.");
							}
						} else {
							System.out.println("Le fichier n'existe pas.");
						}
					}
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
							if (eriJsonObject.get("money").getAsDouble() >= (quantity
									* ((entity.getCapability(EriniumAhModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAhModVariables.PlayerVariables())).ah_temp_item).getOrCreateTag().getDouble("price"))) {
								eriJsonObject.addProperty("money", (eriJsonObject.get("money").getAsDouble()
										- (quantity * ((entity.getCapability(EriniumAhModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAhModVariables.PlayerVariables())).ah_temp_item).getOrCreateTag().getDouble("price"))));
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
								_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(0, 0, 0), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
										"erilog " + "GameLoading" + " " + "info" + " " + "Mod Ericonomy not exist");
						} else {
							System.out.println("Erilog is not installed ! install here : https://github.com/JLSkyzer/Erinium-Adventure-mods/releases/tag/Erilog");
						}
					}
					if (ModList.get().isLoaded("ericonomy")) {
						// Procedure here
						java.io.File eriFile = new java.io.File("");
						com.google.gson.JsonObject eriJsonObject = new com.google.gson.JsonObject();
						eriFile = new File((FMLPaths.GAMEDIR.get().toString() + "/Ericonomy/accounts/"),
								File.separator + ((((entity.getCapability(EriniumAhModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAhModVariables.PlayerVariables())).ah_temp_item).getOrCreateTag().getString("uuid")) + ".json"));
						try {
							BufferedReader bufferedReader = new BufferedReader(new FileReader(eriFile));
							StringBuilder jsonstringbuilder = new StringBuilder();
							String line;
							while ((line = bufferedReader.readLine()) != null) {
								jsonstringbuilder.append(line);
							}
							bufferedReader.close();
							eriJsonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
							eriJsonObject.addProperty("money", (eriJsonObject.get("money").getAsDouble()
									+ (quantity * ((entity.getCapability(EriniumAhModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAhModVariables.PlayerVariables())).ah_temp_item).getOrCreateTag().getDouble("price"))));
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
								_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(0, 0, 0), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
										"erilog " + "GameLoading" + " " + "info" + " " + "Mod Ericonomy not exist");
						} else {
							System.out.println("Erilog is not installed ! install here : https://github.com/JLSkyzer/Erinium-Adventure-mods/releases/tag/Erilog");
						}
					}
					for (Entity entityiterator : new ArrayList<>(world.players())) {
						if ((entityiterator.getDisplayName().getString())
								.equals(((entity.getCapability(EriniumAhModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAhModVariables.PlayerVariables())).ah_temp_item).getOrCreateTag().getString("player"))) {
							if (entityiterator instanceof Player _player && !_player.level().isClientSide())
								_player.displayClientMessage(Component.literal(("\u00A76Erinium AH >>> \u00A7a+"
										+ new java.text.DecimalFormat("#,###.##")
												.format(quantity * ((entity.getCapability(EriniumAhModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAhModVariables.PlayerVariables())).ah_temp_item).getOrCreateTag().getDouble("price"))
										+ "$ \u00A7ereason : item sell")), false);
							break;
						}
					}
					if (entity instanceof Player _player) {
						ItemStack _setstack = ((entity.getCapability(EriniumAhModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAhModVariables.PlayerVariables())).ah_temp_item);
						_setstack.setCount((int) quantity);
						ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
					}
					if (entity instanceof Player _player)
						_player.closeContainer();
				} else {
					Minecraft.getInstance().getToasts().addToast(new NotEnoughtMoneyToast());
				}
			} else {
				if (guistate.get("text:quantity") instanceof EditBox _tf)
					_tf.setValue("QUANTITY");
			}
		} else {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("\u00A7cError"), false);
			if (entity instanceof Player _player)
				_player.closeContainer();
		}
	}
}
