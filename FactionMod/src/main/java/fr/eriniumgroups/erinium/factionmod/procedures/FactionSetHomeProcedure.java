package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

public class FactionSetHomeProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		File File = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		if (TargetEntityHaveFactionProcedure.execute(entity)) {
			if (PlayerCanSethomeProcedure.execute(entity) || TargetEntityIsChefProcedure.execute(entity)) {
				File = EntityFactionHomeProcedure.execute(entity);
				if (!File.exists()) {
					try {
						File.getParentFile().mkdirs();
						File.createNewFile();
					} catch (IOException exception) {
						exception.printStackTrace();
					}
					JsonObject.addProperty("x", (entity.getX()));
					JsonObject.addProperty("y", (entity.getY()));
					JsonObject.addProperty("z", (entity.getZ()));
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
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("\u00A7a" + Component.translatable("faction.message.sethome.succeful").getString())), false);
				} else {
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("\u00A7c" + Component.translatable("faction.message.home.alreadyexist").getString())), false);
				}
			}
		}
	}
}
