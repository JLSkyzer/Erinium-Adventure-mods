package fr.eriniumgroups.erinium.auctionhouse.procedures;

import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.fml.ModList;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

public class GetMoneyProcedure {
	public static String execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return "";
		return (new java.text.DecimalFormat("#,###.##").format(new Object() {
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
						eriJsonObject = new com.google.gson.Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
						// Retour
						returnnbr = eriJsonObject.get("money").getAsDouble();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return returnnbr;
				} else {
					if (ModList.get().isLoaded("erinium_logs")) {
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(0, 0, 0), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									"erilog " + "GameLoading" + " " + "info" + " " + "Mod Ericonomy not exist");
					} else {
						System.out.println("Erilog is not installed ! install here : https://github.com/JLSkyzer/Erinium-Adventure-mods/releases/tag/Erilog");
					}
					return 0;
				}
			};
		}.getPlayerMoney(entity))) + " $";
	}
}
