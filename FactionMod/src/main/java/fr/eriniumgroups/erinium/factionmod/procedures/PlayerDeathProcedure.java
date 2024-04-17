package fr.eriniumgroups.erinium.factionmod.procedures;

import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;

import javax.annotation.Nullable;

import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;

@Mod.EventBusSubscriber
public class PlayerDeathProcedure {
	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity());
		}
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		File File = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		boolean upFactionPower = false;
		if (entity instanceof Player || entity instanceof ServerPlayer) {
			File = ReturnTargetEntityPathProcedure.execute(entity);
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
					if (JsonObject.get("power").getAsDouble() > 0) {
						JsonObject.addProperty("power", (JsonObject.get("power").getAsDouble() - 1));
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
						upFactionPower = true;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (upFactionPower) {
				File = EntityFactionPathProcedure.execute(entity);
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
							JsonObject = new com.google.gson.Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
							JsonObject.addProperty("power", (JsonObject.get("power").getAsDouble() - 1));
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
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
			{
				EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
				_vars.power_timer = 12000;
				_vars.syncPlayerVariables(entity);
			}
			{
				EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
				_vars.CurrentlyDead = true;
				_vars.syncPlayerVariables(entity);
			}
		}
	}
}
