package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;

import com.google.gson.JsonObject;
import com.google.gson.Gson;

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
							JsonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
							{
								double _setval = JsonObject.get("x").getAsDouble();
								entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.temp_x = _setval;
									capability.syncPlayerVariables(entity);
								});
							}
							{
								double _setval = JsonObject.get("y").getAsDouble();
								entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.temp_y = _setval;
									capability.syncPlayerVariables(entity);
								});
							}
							{
								double _setval = JsonObject.get("z").getAsDouble();
								entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.temp_z = _setval;
									capability.syncPlayerVariables(entity);
								});
							}
							{
								double _setval = 100;
								entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.teleport_cooldown = _setval;
									capability.syncPlayerVariables(entity);
								});
							}
							{
								boolean _setval = true;
								entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.teleported = _setval;
									capability.syncPlayerVariables(entity);
								});
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
