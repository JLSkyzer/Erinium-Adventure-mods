package fr.eriniumgroups.erinium.factionmod.procedures;

import net.neoforged.fml.loading.FMLPaths;

import net.minecraft.commands.CommandSourceStack;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.net.URL;
import java.net.HttpURLConnection;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.StringArgumentType;

public class ReturnCommandStringEntityPowerProcedure {
	public static double execute(CommandContext<CommandSourceStack> arguments) {
		File file = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		double power = 0;
		file = new File((FMLPaths.GAMEDIR.get().toString() + "/player_informations/"), File.separator + (new Object() {
			private String getHtmlStringByID(String urlString, String element) {
				String url = urlString; // URL de la page que vous souhaitez lire
				String elementId = element; // ID de l'élément HTML que vous souhaitez récupérer
				try {
					// Ouvrir une connexion HTTP vers l'URL
					URL urlObj = new URL(url);
					HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
					// Obtenir le code de réponse HTTP
					int responseCode = connection.getResponseCode();
					if (responseCode == HttpURLConnection.HTTP_OK) {
						// Créer un lecteur pour lire les données de la connexion
						BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(connection.getInputStream()));
						StringBuilder stringBuilder = new StringBuilder();
						String line;
						// Lire le contenu de la page ligne par ligne
						while ((line = reader.readLine()) != null) {
							stringBuilder.append(line);
						}
						// Fermer le lecteur
						reader.close();
						// Convertir le contenu en une chaîne de caractères
						String htmlContent = stringBuilder.toString();
						System.out.println("Contenu HTML complet de la page :");
						System.out.println(htmlContent);
						// Utiliser une expression régulière pour extraire la valeur de l'élément avec l'ID spécifié
						String regex = "<[^>]*id=\"" + elementId + "\"[^>]*value=\"([^\"]*)\"[^>]*>";
						Pattern pattern = Pattern.compile(regex);
						Matcher matcher = pattern.matcher(htmlContent);
						if (matcher.find()) {
							// Récupérer la valeur de l'élément
							String valeur = matcher.group(1);
							System.out.println("Valeur de l'élément avec l'ID " + elementId + ": " + valeur);
							return valeur;
						} else {
							// Si la valeur de l'attribut value n'est pas trouvée, extraire simplement le texte de l'élément
							regex = "<[^>]*id=\"" + elementId + "\"[^>]*>(.*?)</[^>]*>";
							pattern = Pattern.compile(regex);
							matcher = pattern.matcher(htmlContent);
							if (matcher.find()) {
								// Récupérer le texte de l'élément
								String texte = matcher.group(1);
								System.out.println("Texte de l'élément avec l'ID " + elementId + ": " + texte);
								return texte;
							} else {
								System.out.println("Aucun élément trouvé avec l'ID " + elementId);
								return "No element";
							}
						}
					} else {
						System.out.println("Erreur de connexion HTTP : " + responseCode);
					}
					// Fermer la connexion
					connection.disconnect();
					return "Error";
				} catch (IOException e) {
					e.printStackTrace();
				}
				return "No element or link";
			}
		}.getHtmlStringByID(("https://mcuuid.net/?q=" + StringArgumentType.getString(arguments, "player")), "results_id") + ".json"));
		power = 0;
		if (file.exists()) {
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
					power = JsonObject.get("power").getAsDouble();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return power;
		}
		return 0;
	}
}
