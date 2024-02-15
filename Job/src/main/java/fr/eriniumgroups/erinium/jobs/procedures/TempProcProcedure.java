package fr.eriniumgroups.erinium.jobs.procedures;

import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import fr.eriniumgroups.erinium.jobs.network.EriniumjobsModVariables;
import fr.eriniumgroups.erinium.jobs.EriniumjobsMod;

import com.google.gson.Gson;

public class TempProcProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		com.google.gson.JsonObject ClearJsonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject SecJsonObject = new com.google.gson.JsonObject();
		double return_level = 0;
		double whilecount = 0;
		double Count = 0;
		double slot_count = 0;
		ItemStack tempItem = ItemStack.EMPTY;
		File File = new File("");
		String object = "";
		File = new File((FMLPaths.GAMEDIR.get().toString() + "/EriniumJobs/player_information/" + entity.getUUID().toString()), File.separator + "miner.json");
		if (File.exists()) {
			{
				try {
					BufferedReader bufferedReader = new BufferedReader(new FileReader(File));
					StringBuilder jsonstringbuilder = new StringBuilder();
					String line;
					while ((line = bufferedReader.readLine()) != null) {
						jsonstringbuilder.append(line);
					}
					bufferedReader.close();
					SecJsonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
					return_level = SecJsonObject.get("level").getAsDouble();
					{
						double _setval = SecJsonObject.get("xp_multiplier").getAsDouble();
						entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.won_xp_multiplier_base = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					EriniumjobsMod.LOGGER.fatal(("" + SecJsonObject.get("xp_multiplier").getAsDouble()));
					EriniumjobsMod.LOGGER.fatal(("" + SecJsonObject.get("level").getAsDouble()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
