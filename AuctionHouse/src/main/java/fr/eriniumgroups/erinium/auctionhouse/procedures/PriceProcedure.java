package fr.eriniumgroups.erinium.auctionhouse.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.ModList;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;
import net.minecraft.client.gui.components.EditBox;

import java.util.HashMap;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import fr.eriniumgroups.erinium.auctionhouse.network.EriniumAhModVariables;

import com.google.gson.JsonObject;
import com.google.gson.Gson;

public class PriceProcedure {
	public static String execute(LevelAccessor world, Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return "";
		String finalText = "";
		if (new Object() {
			double convert(String s) {
				try {
					return Double.parseDouble(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert(guistate.containsKey("text:quantity") ? ((EditBox) guistate.get("text:quantity")).getValue() : "")
				* ((entity.getCapability(EriniumAhModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAhModVariables.PlayerVariables())).ah_temp_item).getOrCreateTag().getDouble("price") > new Object() {
					private double getPlayerMoney(Entity entity) {
						if (ModList.get().isLoaded("ericonomy")) {
							// Procedure here
							java.io.File file = new java.io.File("");
							com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
							double returnnbr = 0;
							file = new File((FMLPaths.GAMEDIR.get().toString() + "/Ericonomy/accounts/"), File.separator + (entity.getUUID().toString() + ".json"));
							try {
								BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
								StringBuilder jsonstringbuilder = new StringBuilder();
								String line;
								while ((line = bufferedReader.readLine()) != null) {
									jsonstringbuilder.append(line);
								}
								bufferedReader.close();
								JsonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
								// Retour
								returnnbr = JsonObject.get("money").getAsDouble();
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
				}.getPlayerMoney(entity)) {
			finalText = "Price : \u00A74" + new java.text.DecimalFormat("#,###.##").format(new Object() {
				double convert(String s) {
					try {
						return Double.parseDouble(s.trim());
					} catch (Exception e) {
					}
					return 0;
				}
			}.convert(guistate.containsKey("text:quantity") ? ((EditBox) guistate.get("text:quantity")).getValue() : "")
					* ((entity.getCapability(EriniumAhModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAhModVariables.PlayerVariables())).ah_temp_item).getOrCreateTag().getDouble("price")) + " $";
		} else {
			finalText = "Price : \u00A7a" + new java.text.DecimalFormat("#,###.##").format(new Object() {
				double convert(String s) {
					try {
						return Double.parseDouble(s.trim());
					} catch (Exception e) {
					}
					return 0;
				}
			}.convert(guistate.containsKey("text:quantity") ? ((EditBox) guistate.get("text:quantity")).getValue() : "")
					* ((entity.getCapability(EriniumAhModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAhModVariables.PlayerVariables())).ah_temp_item).getOrCreateTag().getDouble("price")) + " $";
		}
		return finalText;
	}
}
