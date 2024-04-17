package fr.eriniumgroups.erinium.factionmod.procedures;

import org.checkerframework.checker.units.qual.s;

import net.neoforged.fml.loading.FMLPaths;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;

import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;
import fr.eriniumgroups.erinium.factionmod.configuration.ConfigConfiguration;

public class FactionDisbandProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		File file = new File("");
		String tempText = "";
		String player_name = "";
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject secJsonObject = new com.google.gson.JsonObject();
		if (TargetEntityHaveFactionProcedure.execute(entity) && TargetEntityIsChefProcedure.execute(entity)) {
			{
				EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
				_vars.temp_count = 0;
				_vars.syncPlayerVariables(entity);
			}
			{
				EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
				_vars.temp_text = "";
				_vars.syncPlayerVariables(entity);
			}
			file = new File((FMLPaths.GAMEDIR.get().toString() + "/Faction_list/" + entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_name + "/"), File.separator + "global_informations.json");
			{
				try {
					BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
					StringBuilder jsonstringbuilder = new StringBuilder();
					String line;
					while ((line = bufferedReader.readLine()) != null) {
						jsonstringbuilder.append(line);
					}
					bufferedReader.close();
					JsonObject = new com.google.gson.Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
					{
						EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
						_vars.temp_text = JsonObject.get("member_count").getAsString();
						_vars.syncPlayerVariables(entity);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			String cheminDossierParent = (FMLPaths.GAMEDIR.get().toString() + "/Faction_list/" + entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_name);
			java.io.File dossierParent = new java.io.File(cheminDossierParent);
			if (dossierParent.exists() && dossierParent.isDirectory()) {
				java.io.File[] sousDossiers = dossierParent.listFiles();
				// Parcours tous les sous-dossiers du dossier parent
				for (java.io.File currentFolder : sousDossiers) {
					if (currentFolder.isDirectory()) {
						// ...
						if (new java.io.File(new String((FMLPaths.GAMEDIR.get().toString() + "/Faction_list/" + entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_name + "/" + currentFolder.getName()))).isDirectory()) {
							// Récupérer la liste des fichiers et sous-dossiers dans le dossier
							java.io.File[] fichiers = new java.io.File(new String((FMLPaths.GAMEDIR.get().toString() + "/Faction_list/" + entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_name + "/" + currentFolder.getName())))
									.listFiles();
							// Parcourir la liste et supprimer chaque fichier/dossier
							if (fichiers != null) {
								for (java.io.File fichier : fichiers) {
									fichier.delete();
								}
							}
							if (new java.io.File(new String((FMLPaths.GAMEDIR.get().toString() + "/Faction_list/" + entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_name + "/" + currentFolder.getName()))).listFiles() != null) {
								new java.io.File(new String((FMLPaths.GAMEDIR.get().toString() + "/Faction_list/" + entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_name + "/" + currentFolder.getName()))).delete();
							} else {
								System.out.println("Unable to delete the folder, it seems that a file was recreated after deleting all the files");
							}
						}
					}
				}
			} else {
				System.out.println("Le dossier parent n'existe pas ou n'est pas un dossier valide.");
			}
			if (new java.io.File(new String((FMLPaths.GAMEDIR.get().toString() + "/Faction_list/" + entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_name))).isDirectory()) {
				// Récupérer la liste des fichiers et sous-dossiers dans le dossier
				java.io.File[] fichiers = new java.io.File(new String((FMLPaths.GAMEDIR.get().toString() + "/Faction_list/" + entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_name))).listFiles();
				// Parcourir la liste et supprimer chaque fichier/dossier
				if (fichiers != null) {
					for (java.io.File fichier : fichiers) {
						fichier.delete();
					}
				}
				if (new java.io.File(new String((FMLPaths.GAMEDIR.get().toString() + "/Faction_list/" + entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_name))).listFiles() != null) {
					new java.io.File(new String((FMLPaths.GAMEDIR.get().toString() + "/Faction_list/" + entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_name))).delete();
				} else {
					System.out.println("Unable to delete the folder, it seems that a file was recreated after deleting all the files");
				}
			}
			if (!world.isClientSide() && world.getServer() != null)
				world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(("\u00A7eLa faction \u00A7a" + entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_name + " \u00A7e\u00E0 \u00E9t\u00E9 disband")), false);
			for (int index0 = 0; index0 < (int) (double) ConfigConfiguration.MAX_MEMBER.get(); index0++) {
				if (!(new Object() {
					private String split(String text, String space, int index) {
						String s = text.split(space)[index];
						return s;
					}
				}.split(entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).temp_text, ", ", (int) entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).temp_count)).isEmpty()) {
					file = new File((FMLPaths.GAMEDIR.get().toString() + "/player_informations/"), File.separator + (new Object() {
						private String split(String text, String space, int index) {
							String s = text.split(space)[index];
							return s;
						}
					}.split(entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).temp_text, ", ", (int) entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).temp_count) + ".json"));
					{
						try {
							BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
							StringBuilder jsonstringbuilder = new StringBuilder();
							String line;
							while ((line = bufferedReader.readLine()) != null) {
								jsonstringbuilder.append(line);
							}
							bufferedReader.close();
							secJsonObject = new com.google.gson.Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
							secJsonObject.addProperty("faction", "wilderness");
							secJsonObject.addProperty("faction_rank", "");
							{
								com.google.gson.Gson mainGSONBuilderVariable = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
								try {
									FileWriter fileWriter = new FileWriter(file);
									fileWriter.write(mainGSONBuilderVariable.toJson(secJsonObject));
									fileWriter.close();
								} catch (IOException exception) {
									exception.printStackTrace();
								}
							}
							player_name = JsonObject.get("player_name").getAsString();
							for (Entity entityiterator : new ArrayList<>(world.players())) {
								if ((entityiterator.getDisplayName().getString()).equals(player_name)) {
									{
										EriniumFactionModVariables.PlayerVariables _vars = entityiterator.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
										_vars.faction_name = JsonObject.get("faction").getAsString();
										_vars.syncPlayerVariables(entityiterator);
									}
									{
										EriniumFactionModVariables.PlayerVariables _vars = entityiterator.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
										_vars.faction_rank = JsonObject.get("faction_rank").getAsString();
										_vars.syncPlayerVariables(entityiterator);
									}
									{
										EriniumFactionModVariables.PlayerVariables _vars = entityiterator.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
										_vars.faction_displayname = JsonObject.get("Wilderness").getAsString();
										_vars.syncPlayerVariables(entityiterator);
									}
								}
							}
							{
								com.google.gson.Gson mainGSONBuilderVariable = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
								try {
									FileWriter fileWriter = new FileWriter(file);
									fileWriter.write(mainGSONBuilderVariable.toJson(JsonObject));
									fileWriter.close();
								} catch (IOException exception) {
									exception.printStackTrace();
								}
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					{
						EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
						_vars.temp_count = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).temp_count + 1;
						_vars.syncPlayerVariables(entity);
					}
				} else {
					{
						EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
						_vars.temp_count = 0;
						_vars.syncPlayerVariables(entity);
					}
					{
						EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
						_vars.temp_text = "";
						_vars.syncPlayerVariables(entity);
					}
					break;
				}
			}
		}
	}
}
