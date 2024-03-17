package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import com.google.gson.JsonObject;
import com.google.gson.Gson;

public class CanUseAdminStatsCommandProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		File file = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		boolean return_logc = false;
		file = new File((FMLPaths.GAMEDIR.get().toString() + "/EriniumAdventure/permissions/"), File.separator + (entity.getUUID().toString() + ".json"));
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
				return_logc = JsonObject.get("stats.admin.command").getAsBoolean();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (!return_logc) {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal((Component.translatable("eriniumadventure.admin.need").getString())), false);
		}
		return return_logc;
	}
}
