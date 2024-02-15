package fr.eriniumgroups.erinium.jobs.procedures;

import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;

import javax.annotation.Nullable;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import com.google.gson.JsonObject;
import com.google.gson.Gson;

@Mod.EventBusSubscriber
public class UseItemProcedure {
	@SubscribeEvent
	public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
		if (event.getHand() != event.getEntity().getUsedItemHand())
			return;
		execute(event, event.getEntity());
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		File File = new File("");
		double required_level = 0;
		String job_id = "";
		String jsonDisplayName = "";
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject SecJsonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject ThirdJsonObject = new com.google.gson.JsonObject();
		if (entity instanceof Player || entity instanceof ServerPlayer) {
			File = GetMainHandItemProcedure.execute(entity);
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
						JsonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
						job_id = JsonObject.get("job_id").getAsString();
						required_level = JsonObject.get("level").getAsDouble();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else {
				File = GetOffHandItemProcedure.execute(entity);
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
							JsonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
							job_id = JsonObject.get("job_id").getAsString();
							required_level = JsonObject.get("level").getAsDouble();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
			File = new File((FMLPaths.GAMEDIR.get().toString() + "/config/EriniumJobs/jobs/"), File.separator + (job_id + ".json"));
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
						jsonDisplayName = SecJsonObject.get("displayname").getAsString();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			File = new File((FMLPaths.GAMEDIR.get().toString() + "/EriniumJobs/player_information/" + entity.getUUID().toString()), File.separator + (job_id + ".json"));
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
						ThirdJsonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
						if (!(ThirdJsonObject.get("level").getAsDouble() >= required_level)) {
							if (event != null && event.isCancelable()) {
								event.setCanceled(true);
							}
							if (entity instanceof Player _player && !_player.level().isClientSide())
								_player.displayClientMessage(Component.literal((Component.translatable("jobs.message.required.message1").getString() + "" + new java.text.DecimalFormat("###").format(required_level)
										+ Component.translatable("jobs.message.required.message2").getString() + jsonDisplayName)), false);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
