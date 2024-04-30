package fr.eriniumgroups.erinium.jobs.procedures;

import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import javax.annotation.Nullable;

import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import fr.eriniumgroups.erinium.jobs.network.EriniumjobsModVariables;
import fr.eriniumgroups.erinium.jobs.configuration.CommonConfigConfiguration;

@Mod.EventBusSubscriber
public class PlayerJoinTheWorldProcedure {
	@SubscribeEvent
	public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		execute(event, event.getEntity().level(), event.getEntity());
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		File File = new File("");
		String returnOwned = "";
		String temp_uuid = "";
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject SecJsonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject ClearJsonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject JobJsonObject = new com.google.gson.JsonObject();
		{
			EriniumjobsModVariables.PlayerVariables _vars = entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES);
			_vars.xp_multiplier = (double) CommonConfigConfiguration.XP_MULTIPLIER.get();
			_vars.syncPlayerVariables(entity);
		}
		{
			EriniumjobsModVariables.PlayerVariables _vars = entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES);
			_vars.won_xp_multiplier = (double) CommonConfigConfiguration.WONXP_MULTIPLIER.get();
			_vars.syncPlayerVariables(entity);
		}
		if (!entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES).FirstJoined) {
			{
				EriniumjobsModVariables.PlayerVariables _vars = entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES);
				_vars.won_xp_percent_x = 75;
				_vars.syncPlayerVariables(entity);
			}
			{
				EriniumjobsModVariables.PlayerVariables _vars = entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES);
				_vars.won_xp_percent_y = 15;
				_vars.syncPlayerVariables(entity);
			}
			{
				EriniumjobsModVariables.PlayerVariables _vars = entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES);
				_vars.FirstJoined = true;
				_vars.syncPlayerVariables(entity);
			}
		}
		File = new File((FMLPaths.GAMEDIR.get().toString() + "/EriniumJobs/player_information/playername/"), File.separator + (entity.getDisplayName().getString() + ".json"));
		if (!File.exists()) {
			try {
				File.getParentFile().mkdirs();
				File.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			JsonObject.addProperty("uuid", entity.getUUID().toString());
			{
				com.google.gson.Gson mainGSONBuilderVariable = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
				try {
					FileWriter fileWriter = new FileWriter(File);
					fileWriter.write(mainGSONBuilderVariable.toJson(JsonObject));
					fileWriter.close();
				} catch (IOException exception) {
					exception.printStackTrace();
				}
			}
		}
		File = new File((FMLPaths.GAMEDIR.get().toString() + "/EriniumJobs/player_information/" + entity.getUUID().toString()), File.separator + "global_info.json");
		if (!File.exists()) {
			try {
				File.getParentFile().mkdirs();
				File.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			SecJsonObject.addProperty("name", (entity.getDisplayName().getString()));
			SecJsonObject.addProperty("command.admin", false);
			{
				com.google.gson.Gson mainGSONBuilderVariable = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
				try {
					FileWriter fileWriter = new FileWriter(File);
					fileWriter.write(mainGSONBuilderVariable.toJson(SecJsonObject));
					fileWriter.close();
				} catch (IOException exception) {
					exception.printStackTrace();
				}
			}
		} else {
			{
				try {
					BufferedReader bufferedReader = new BufferedReader(new FileReader(File));
					StringBuilder jsonstringbuilder = new StringBuilder();
					String line;
					while ((line = bufferedReader.readLine()) != null) {
						jsonstringbuilder.append(line);
					}
					bufferedReader.close();
					SecJsonObject = new com.google.gson.Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
					if (!(SecJsonObject.get("name").getAsString()).equals(entity.getDisplayName().getString())) {
						SecJsonObject.addProperty("name", (entity.getDisplayName().getString()));
						{
							com.google.gson.Gson mainGSONBuilderVariable = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
							try {
								FileWriter fileWriter = new FileWriter(File);
								fileWriter.write(mainGSONBuilderVariable.toJson(SecJsonObject));
								fileWriter.close();
							} catch (IOException exception) {
								exception.printStackTrace();
							}
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(("\u00A7e" + Component.translatable("jobs.file.loading.message").getString())), false);
		String cheminDossier = (FMLPaths.GAMEDIR.get().toString() + "/config/EriniumJobs/jobs/");
		java.io.File dossier = new java.io.File(cheminDossier);
		if (dossier.exists() && dossier.isDirectory()) {
			java.io.File[] fichiers = dossier.listFiles();
			// Parcours tous les fichiers du dossier
			for (java.io.File currentFile : fichiers) {
				// ...
				File = new File((FMLPaths.GAMEDIR.get().toString() + "/EriniumJobs/player_information/" + entity.getUUID().toString()), File.separator + currentFile.getName());
				if (!File.exists()) {
					try {
						File.getParentFile().mkdirs();
						File.createNewFile();
					} catch (IOException exception) {
						exception.printStackTrace();
					}
					JobJsonObject.addProperty("level", 0);
					JobJsonObject.addProperty("xp", 0);
					JobJsonObject.addProperty("cap_xp", 500);
					JobJsonObject.addProperty("old_cap_xp", 0);
					JobJsonObject.addProperty("xp_multiplier", 1);
					{
						com.google.gson.Gson mainGSONBuilderVariable = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
						try {
							FileWriter fileWriter = new FileWriter(File);
							fileWriter.write(mainGSONBuilderVariable.toJson(JobJsonObject));
							fileWriter.close();
						} catch (IOException exception) {
							exception.printStackTrace();
						}
					}
				}
				JobJsonObject = ClearJsonObject;
			}
		} else {
			System.out.println("Le dossier n'existe pas ou n'est pas un dossier valide.");
		}
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(("\u00A7a" + Component.translatable("jobs.file.loaded.message").getString())), false);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal((Component.translatable("player.join.message.help").getString())), false);
	}
}
