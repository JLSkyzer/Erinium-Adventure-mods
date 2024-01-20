package fr.eriniumgroups.erinium.factionmod.procedures;

import org.checkerframework.checker.units.qual.s;

import org.apache.http.util.EntityUtils;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.HttpEntity;

import net.minecraftforge.fml.loading.FMLPaths;

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

import com.google.gson.JsonObject;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

public class FactionDisbandProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		File file = new File("");
		String tempText = "";
		String player_name = "";
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject secJsonObject = new com.google.gson.JsonObject();
		{
			double _setval = 0;
			entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.temp_count = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			String _setval = "";
			entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.temp_text = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		file = new File((FMLPaths.GAMEDIR.get().toString() + "/Faction_list/" + (entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).faction_name + "/"),
				File.separator + "global_informations.json");
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
				{
					String _setval = JsonObject.get("member_count").getAsString();
					entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.temp_text = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String cheminDossierParent = (FMLPaths.GAMEDIR.get().toString() + "/Faction_list/" + (entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).faction_name);
		java.io.File dossierParent = new java.io.File(cheminDossierParent);
		if (dossierParent.exists() && dossierParent.isDirectory()) {
			java.io.File[] sousDossiers = dossierParent.listFiles();
			// Parcours tous les sous-dossiers du dossier parent
			for (java.io.File currentFolder : sousDossiers) {
				if (currentFolder.isDirectory()) {
					// ...
					if (new java.io.File(new String((FMLPaths.GAMEDIR.get().toString() + "/Faction_list/"
							+ (entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).faction_name + "/" + currentFolder.getName()))).isDirectory()) {
						// Récupérer la liste des fichiers et sous-dossiers dans le dossier
						java.io.File[] fichiers = new java.io.File(new String((FMLPaths.GAMEDIR.get().toString() + "/Faction_list/"
								+ (entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).faction_name + "/" + currentFolder.getName()))).listFiles();
						// Parcourir la liste et supprimer chaque fichier/dossier
						if (fichiers != null) {
							for (java.io.File fichier : fichiers) {
								fichier.delete();
							}
						}
						if (new java.io.File(new String((FMLPaths.GAMEDIR.get().toString() + "/Faction_list/"
								+ (entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).faction_name + "/" + currentFolder.getName()))).listFiles() != null) {
							new java.io.File(new String((FMLPaths.GAMEDIR.get().toString() + "/Faction_list/"
									+ (entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).faction_name + "/" + currentFolder.getName()))).delete();
						} else {
							System.out.println("Unable to delete the folder, it seems that a file was recreated after deleting all the files");
						}
					}
				}
			}
		} else {
			System.out.println("Le dossier parent n'existe pas ou n'est pas un dossier valide.");
		}
		if (new java.io.File(
				new String((FMLPaths.GAMEDIR.get().toString() + "/Faction_list/" + (entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).faction_name)))
				.isDirectory()) {
			// Récupérer la liste des fichiers et sous-dossiers dans le dossier
			java.io.File[] fichiers = new java.io.File(
					new String((FMLPaths.GAMEDIR.get().toString() + "/Faction_list/" + (entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).faction_name)))
					.listFiles();
			// Parcourir la liste et supprimer chaque fichier/dossier
			if (fichiers != null) {
				for (java.io.File fichier : fichiers) {
					fichier.delete();
				}
			}
			if (new java.io.File(
					new String((FMLPaths.GAMEDIR.get().toString() + "/Faction_list/" + (entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).faction_name)))
					.listFiles() != null) {
				new java.io.File(
						new String((FMLPaths.GAMEDIR.get().toString() + "/Faction_list/" + (entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).faction_name)))
						.delete();
			} else {
				System.out.println("Unable to delete the folder, it seems that a file was recreated after deleting all the files");
			}
		}
		if (!world.isClientSide() && world.getServer() != null)
			world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(
					("\u00A7eLa faction \u00A7a" + (entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).faction_name + " \u00A7e\u00E0 \u00E9t\u00E9 disband")),
					false);
		for (int index0 = 0; index0 < (int) (double) ConfigConfiguration.MAX_MEMBER.get(); index0++) {
			if (!(new Object() {
				private String split(String text, String space, int index) {
					String s = text.split(space)[index];
					return s;
				}
			}.split(((entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).temp_text), ", ",
					(int) ((entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).temp_count))).isEmpty()) {
				file = new File((FMLPaths.GAMEDIR.get().toString() + "/player_informations/"), File.separator + (new Object() {
					private String split(String text, String space, int index) {
						String s = text.split(space)[index];
						return s;
					}
				}.split(((entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).temp_text), ", ",
						(int) ((entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).temp_count)) + ".json"));
				{
					try {
						BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
						StringBuilder jsonstringbuilder = new StringBuilder();
						String line;
						while ((line = bufferedReader.readLine()) != null) {
							jsonstringbuilder.append(line);
						}
						bufferedReader.close();
						secJsonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
						secJsonObject.addProperty("faction", "wilderness");
						secJsonObject.addProperty("faction_rank", "");
						{
							Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
							try {
								FileWriter fileWriter = new FileWriter(file);
								fileWriter.write(mainGSONBuilderVariable.toJson(secJsonObject));
								fileWriter.close();
							} catch (IOException exception) {
								exception.printStackTrace();
							}
						}
						player_name = new Object() {
							public String getElementID() {
								try {
									CloseableHttpClient httpclient = HttpClients.createDefault();
									HttpGet httpget = new HttpGet(("https://mcuuid.net/?q=" + new Object() {
										private String split(String text, String space, int index) {
											String s = text.split(space)[index];
											return s;
										}
									}.split(((entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).temp_text), ", ",
											(int) ((entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).temp_count))));
									CloseableHttpResponse httpresponse = httpclient.execute(httpget);
									HttpEntity entityHTTP = httpresponse.getEntity();
									String responseString = EntityUtils.toString(entityHTTP, "UTF-8");
									// Utilisation de Jsoup pour analyser la page HTML
									org.jsoup.nodes.Document document = org.jsoup.Jsoup.parse(responseString);
									// Extraction de la valeur de l'élément d'entrée avec l'ID "results_id"
									String resultsIdValue = document.select("#" + "results_username").attr("value");
									return resultsIdValue;
								} catch (IOException e) {
									System.out.println("Error fetching URL");
									e.printStackTrace();
									return null;
								}
							}
						}.getElementID();
						for (Entity entityiterator : new ArrayList<>(world.players())) {
							if ((entityiterator.getDisplayName().getString()).equals(player_name)) {
								{
									String _setval = JsonObject.get("faction").getAsString();
									entityiterator.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
										capability.faction_name = _setval;
										capability.syncPlayerVariables(entityiterator);
									});
								}
								{
									String _setval = JsonObject.get("faction_rank").getAsString();
									entityiterator.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
										capability.faction_rank = _setval;
										capability.syncPlayerVariables(entityiterator);
									});
								}
								{
									String _setval = JsonObject.get("Wilderness").getAsString();
									entityiterator.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
										capability.faction_displayname = _setval;
										capability.syncPlayerVariables(entityiterator);
									});
								}
							}
						}
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
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				{
					double _setval = (entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).temp_count + 1;
					entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.temp_count = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			} else {
				{
					double _setval = 0;
					entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.temp_count = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				{
					String _setval = "";
					entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.temp_text = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				break;
			}
		}
	}
}
