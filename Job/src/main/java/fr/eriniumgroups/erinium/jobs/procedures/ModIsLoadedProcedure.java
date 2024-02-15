package fr.eriniumgroups.erinium.jobs.procedures;

import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;

import javax.annotation.Nullable;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

import com.google.gson.JsonObject;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModIsLoadedProcedure {
	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		execute();
	}

	public static void execute() {
		execute(null);
	}

	private static void execute(@Nullable Event event) {
		File File = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject SecJsonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject ThirdJsonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject FourthJsonObject = new com.google.gson.JsonObject();
		File = new File((FMLPaths.GAMEDIR.get().toString() + "/config/EriniumJobs/jobs/"), File.separator + "miner.json");
		if (!File.exists()) {
			try {
				File.getParentFile().mkdirs();
				File.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			JsonObject.addProperty("displayname", "Mineur");
			{
				Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
				try {
					FileWriter fileWriter = new FileWriter(File);
					fileWriter.write(mainGSONBuilderVariable.toJson(JsonObject));
					fileWriter.close();
				} catch (IOException exception) {
					exception.printStackTrace();
				}
			}
		}
		File = new File((FMLPaths.GAMEDIR.get().toString() + "/config/EriniumJobs/jobs/"), File.separator + "hunter.json");
		if (!File.exists()) {
			try {
				File.getParentFile().mkdirs();
				File.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			SecJsonObject.addProperty("displayname", "Hunter");
			{
				Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
				try {
					FileWriter fileWriter = new FileWriter(File);
					fileWriter.write(mainGSONBuilderVariable.toJson(SecJsonObject));
					fileWriter.close();
				} catch (IOException exception) {
					exception.printStackTrace();
				}
			}
		}
		File = new File((FMLPaths.GAMEDIR.get().toString() + "/config/EriniumJobs/jobs/"), File.separator + "farmer.json");
		if (!File.exists()) {
			try {
				File.getParentFile().mkdirs();
				File.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			ThirdJsonObject.addProperty("displayname", "Farmeur");
			{
				Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
				try {
					FileWriter fileWriter = new FileWriter(File);
					fileWriter.write(mainGSONBuilderVariable.toJson(ThirdJsonObject));
					fileWriter.close();
				} catch (IOException exception) {
					exception.printStackTrace();
				}
			}
		}
		File = new File((FMLPaths.GAMEDIR.get().toString() + "/config/EriniumJobs/jobs/"), File.separator + "alchimist.json");
		if (!File.exists()) {
			try {
				File.getParentFile().mkdirs();
				File.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			FourthJsonObject.addProperty("displayname", "Alchimiste");
			{
				Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
				try {
					FileWriter fileWriter = new FileWriter(File);
					fileWriter.write(mainGSONBuilderVariable.toJson(FourthJsonObject));
					fileWriter.close();
				} catch (IOException exception) {
					exception.printStackTrace();
				}
			}
		}
	}
}
