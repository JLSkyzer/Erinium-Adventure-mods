package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;

public class FactionHomeProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		File File = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		if (TargetEntityHaveFactionProcedure.execute(entity)) {
			if (PlayerCanHomeProcedure.execute(entity) || TargetEntityIsChefProcedure.execute(entity)) {
				File = EntityFactionHomeProcedure.execute(entity);
				if (File.exists()) {
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("\u00A7a" + "TP in 5 sec")), false);
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
							{
								EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
								_vars.temp_x = JsonObject.get("x").getAsDouble();
								_vars.syncPlayerVariables(entity);
							}
							{
								EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
								_vars.temp_y = JsonObject.get("y").getAsDouble();
								_vars.syncPlayerVariables(entity);
							}
							{
								EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
								_vars.temp_z = JsonObject.get("z").getAsDouble();
								_vars.syncPlayerVariables(entity);
							}
							{
								EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
								_vars.teleport_cooldown = 100;
								_vars.syncPlayerVariables(entity);
							}
							{
								EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
								_vars.teleported = true;
								_vars.syncPlayerVariables(entity);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				} else {
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("\u00A7c" + Component.translatable("faction.message.home.noexist").getString())), false);
				}
			}
		}
	}
}
