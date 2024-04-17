package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

import fr.eriniumgroup.eriniumadventure.base.network.EriniumAdventureModVariables;

@Mod.EventBusSubscriber
public class JoinTheWorldProcedure {
	@SubscribeEvent
	public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		execute(event, event.getEntity());
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		File file = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		if (entity.isAlive()) {
			if (entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES).max_health == 0) {
				{
					EriniumAdventureModVariables.PlayerVariables _vars = entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES);
					_vars.max_health = 20;
					_vars.syncPlayerVariables(entity);
				}
			}
			if (entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES).health == 0) {
				{
					EriniumAdventureModVariables.PlayerVariables _vars = entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES);
					_vars.health = entity.getData(EriniumAdventureModVariables.PLAYER_VARIABLES).max_health;
					_vars.syncPlayerVariables(entity);
				}
			}
			file = new File((FMLPaths.GAMEDIR.get().toString() + "/EriniumAdventure/permissions/"), File.separator + (entity.getUUID().toString() + ".json"));
			if (!file.exists()) {
				try {
					file.getParentFile().mkdirs();
					file.createNewFile();
				} catch (IOException exception) {
					exception.printStackTrace();
				}
				JsonObject.addProperty("stats.admin.command", false);
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
			}
		}
	}
}
