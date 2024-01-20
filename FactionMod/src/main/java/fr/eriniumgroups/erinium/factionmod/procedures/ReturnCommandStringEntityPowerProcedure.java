package fr.eriniumgroups.erinium.factionmod.procedures;

import org.apache.http.util.EntityUtils;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.HttpEntity;

import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.commands.CommandSourceStack;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.StringArgumentType;

import com.google.gson.JsonObject;
import com.google.gson.Gson;

public class ReturnCommandStringEntityPowerProcedure {
	public static double execute(CommandContext<CommandSourceStack> arguments) {
		File file = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		double power = 0;
		file = new File((FMLPaths.GAMEDIR.get().toString() + "/player_informations/"), File.separator + ((new Object() {
			public String getElementID() {
				try {
					CloseableHttpClient httpclient = HttpClients.createDefault();
					HttpGet httpget = new HttpGet(("https://mcuuid.net/?q=" + StringArgumentType.getString(arguments, "player")));
					CloseableHttpResponse httpresponse = httpclient.execute(httpget);
					HttpEntity entityHTTP = httpresponse.getEntity();
					String responseString = EntityUtils.toString(entityHTTP, "UTF-8");
					// Utilisation de Jsoup pour analyser la page HTML
					org.jsoup.nodes.Document document = org.jsoup.Jsoup.parse(responseString);
					// Extraction de la valeur de l'élément d'entrée avec l'ID "results_id"
					String resultsIdValue = document.select("#" + "results_id").attr("value");
					return resultsIdValue;
				} catch (IOException e) {
					System.out.println("Error fetching URL");
					e.printStackTrace();
					return null;
				}
			}
		}.getElementID()) + ".json"));
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
					JsonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
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
