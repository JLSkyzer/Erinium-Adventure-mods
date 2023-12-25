package fr.eriniumgroup.eriniumadventure.automation.procedures;

import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;

import javax.annotation.Nullable;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class OnStartupProcedure {
	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		execute();
	}

	public static void execute() {
		execute(null);
	}

	private static void execute(@Nullable Event event) {
		File file = new File("");
		com.google.gson.JsonObject jsonObject = new com.google.gson.JsonObject();
		file = new File((FMLPaths.GAMEDIR.get().toString() + "/config/eriniumAutomation/Farmer/mymodID/"), File.separator + "example_id.json");
		if (!file.exists()) {
			try {
				file.getParentFile().mkdirs();
				file.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			jsonObject.addProperty("result", "modID:myItemID");
			jsonObject.addProperty("result_min", 1);
			jsonObject.addProperty("result_max", 3);
			jsonObject.addProperty("seed", "modID:SeedID");
			jsonObject.addProperty("seed_min", 1);
			jsonObject.addProperty("seed_max", 3);
			jsonObject.addProperty("replaced_block", "modID:myStage1CropBlockID");
			{
				Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
				try {
					FileWriter fileWriter = new FileWriter(file);
					fileWriter.write(mainGSONBuilderVariable.toJson(jsonObject));
					fileWriter.close();
				} catch (IOException exception) {
					exception.printStackTrace();
				}
			}
		}
		file = new File((FMLPaths.GAMEDIR.get().toString() + "/config/eriniumAutomation/Farmer/erinium_automation/"), File.separator + "example_id.json");
		if (!file.exists()) {
			try {
				file.getParentFile().mkdirs();
				file.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			jsonObject.addProperty("result", "modID:myItemID");
			jsonObject.addProperty("result_min", 1);
			jsonObject.addProperty("result_max", 3);
			jsonObject.addProperty("seed", "modID:SeedID");
			jsonObject.addProperty("seed_min", 1);
			jsonObject.addProperty("seed_max", 3);
			jsonObject.addProperty("replaced_block", "modID:myStage1CropBlockID");
			{
				Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
				try {
					FileWriter fileWriter = new FileWriter(file);
					fileWriter.write(mainGSONBuilderVariable.toJson(jsonObject));
					fileWriter.close();
				} catch (IOException exception) {
					exception.printStackTrace();
				}
			}
		}
	}
}
