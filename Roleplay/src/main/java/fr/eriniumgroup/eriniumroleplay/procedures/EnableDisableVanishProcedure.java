package fr.eriniumgroup.eriniumroleplay.procedures;

import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.fml.ModList;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import fr.eriniumgroup.eriniumroleplay.network.EriniumroleplayModVariables;

public class EnableDisableVanishProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (new Object() {
			private Boolean getPermission(String perm) {
				if (ModList.get().isLoaded("eriniumrank")) {
					String TempText = perm;
					com.google.gson.JsonObject RankPerm;
					com.google.gson.JsonObject PlayerPerm;
					String rank = new Object() {
						private String getRank() {
							java.io.File file;
							String TempText = "";
							com.google.gson.JsonObject RankPerm = new com.google.gson.JsonObject();
							file = new java.io.File((FMLPaths.GAMEDIR.get().toString() + "/config/eriniumRanks/players/"), java.io.File.separator + (entity.getDisplayName().getString() + ".json"));
							if (file.exists()) {
								try {
									BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
									StringBuilder jsonstringbuilder = new StringBuilder();
									String line;
									while ((line = bufferedReader.readLine()) != null) {
										jsonstringbuilder.append(line);
									}
									bufferedReader.close();
									RankPerm = new com.google.gson.Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
									TempText = RankPerm.get("rank").getAsString();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
							return TempText;
						}
					}.getRank();
					if (entity instanceof Player || entity instanceof ServerPlayer) {
						RankPerm = new Object() {
							public com.google.gson.JsonObject parse() {
								java.io.File File = new java.io.File("");
								com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
								File = new java.io.File((FMLPaths.GAMEDIR.get().toString() + "/config/eriniumRanks/"), java.io.File.separator + TempText + ".json");
								{
									try {
										BufferedReader bufferedReader = new BufferedReader(new FileReader(File));
										StringBuilder jsonstringbuilder = new StringBuilder();
										String line;
										while ((line = bufferedReader.readLine()) != null) {
											jsonstringbuilder.append(line);
										}
										bufferedReader.close();
										JsonObject = new com.google.gson.Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
								return JsonObject;
							}
						}.parse();
						PlayerPerm = new Object() {
							public com.google.gson.JsonObject parse() {
								java.io.File File = new java.io.File("");
								com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
								File = new File((FMLPaths.GAMEDIR.get().toString() + "/config/eriniumRanks/players/"), java.io.File.separator + (entity.getDisplayName().getString() + ".json"));
								{
									try {
										BufferedReader bufferedReader = new BufferedReader(new FileReader(File));
										StringBuilder jsonstringbuilder = new StringBuilder();
										String line;
										while ((line = bufferedReader.readLine()) != null) {
											jsonstringbuilder.append(line);
										}
										bufferedReader.close();
										JsonObject = new com.google.gson.Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
								return JsonObject;
							}
						}.parse();
						if (RankPerm.has((TempText))) {
							if (RankPerm.get((TempText)).getAsBoolean()) {
								return true;
							} else {
								return false;
							}
						} else if (PlayerPerm.has((TempText))) {
							if (PlayerPerm.get((TempText)).getAsBoolean()) {
								return true;
							} else {
								return false;
							}
						}
					}
				}
				return false;
			}
		}.getPermission("eriniumroleplay.vanish")) {
			if (entity.getData(EriniumroleplayModVariables.PLAYER_VARIABLES).vanish_mode) {
				{
					EriniumroleplayModVariables.PlayerVariables _vars = entity.getData(EriniumroleplayModVariables.PLAYER_VARIABLES);
					_vars.vanish_mode = false;
					_vars.syncPlayerVariables(entity);
				}
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("\u00A74OFF"), false);
				if (entity instanceof Player _player) {
					_player.getAbilities().flying = false;
					_player.onUpdateAbilities();
				}
				entity.setInvisible(false);
			} else {
				{
					EriniumroleplayModVariables.PlayerVariables _vars = entity.getData(EriniumroleplayModVariables.PLAYER_VARIABLES);
					_vars.vanish_mode = true;
					_vars.syncPlayerVariables(entity);
				}
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("\u00A7aON"), false);
				if (entity instanceof Player _player) {
					_player.getAbilities().flying = true;
					_player.onUpdateAbilities();
				}
				entity.setInvisible(true);
			}
		} else {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("\u00A7cSorry but you can't do that !"), false);
		}
	}
}
