package fr.eriniumgroups.erinium.jobs.procedures;

import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;

import javax.annotation.Nullable;

import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import fr.eriniumgroups.erinium.jobs.network.EriniumjobsModVariables;

@Mod.EventBusSubscriber
public class BreakBlockProcedure {
	@SubscribeEvent
	public static void onBlockBreak(BlockEvent.BreakEvent event) {
		execute(event, event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), event.getPlayer());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		File File = new File("");
		double return_level = 0;
		double min_level = 0;
		double max_level = 0;
		double xp = 0;
		double return_multiplier = 0;
		String job_id = "";
		String type = "";
		String jobDisplayName = "";
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject SecJsonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject ThirdJsonObject = new com.google.gson.JsonObject();
		if (entity instanceof Player || entity instanceof ServerPlayer) {
			File = EarnXpGetBlockPathProcedure.execute(world, x, y, z);
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
						job_id = JsonObject.get("job_id").getAsString();
						min_level = JsonObject.get("min-level").getAsDouble();
						max_level = JsonObject.get("max-level").getAsDouble();
						type = JsonObject.get("type").getAsString();
						xp = JsonObject.get("xp").getAsDouble();
					} catch (IOException e) {
						e.printStackTrace();
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
						ThirdJsonObject = new com.google.gson.Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
						jobDisplayName = ThirdJsonObject.get("displayname").getAsString();
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
						SecJsonObject = new com.google.gson.Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
						if (SecJsonObject.get("level").getAsDouble() < 100) {
							if ((type).equals("BREAK")) {
								if (min_level <= SecJsonObject.get("level").getAsDouble() && max_level >= SecJsonObject.get("level").getAsDouble()) {
									SecJsonObject.addProperty("xp", (SecJsonObject.get("xp").getAsDouble() + xp * SecJsonObject.get("xp_multiplier").getAsDouble()));
									{
										EriniumjobsModVariables.PlayerVariables _vars = entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES);
										_vars.won_xp_timer = 60;
										_vars.syncPlayerVariables(entity);
									}
									{
										EriniumjobsModVariables.PlayerVariables _vars = entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES);
										_vars.won_xp_message = "\u00A7a+" + new java.text.DecimalFormat("#,###.##").format(xp * SecJsonObject.get("xp_multiplier").getAsDouble()) + " \u00A72(" + jobDisplayName + ")";
										_vars.syncPlayerVariables(entity);
									}
									{
										EriniumjobsModVariables.PlayerVariables _vars = entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES);
										_vars.won_xp_message_2 = "\u00A7e" + new java.text.DecimalFormat("#,###.##").format(SecJsonObject.get("xp").getAsDouble()) + " \u00A7f/ \u00A76"
												+ new java.text.DecimalFormat("#,###.##").format(SecJsonObject.get("cap_xp").getAsDouble());
										_vars.syncPlayerVariables(entity);
									}
									if (SecJsonObject.get("xp").getAsDouble() >= SecJsonObject.get("cap_xp").getAsDouble() && SecJsonObject.get("level").getAsDouble() < 100) {
										while (SecJsonObject.get("xp").getAsDouble() >= SecJsonObject.get("cap_xp").getAsDouble() && SecJsonObject.get("level").getAsDouble() < 100) {
											SecJsonObject.addProperty("old_cap_xp", SecJsonObject.get("cap_xp").getAsDouble());
											SecJsonObject.addProperty("cap_xp", (SecJsonObject.get("cap_xp").getAsDouble() * entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES).xp_multiplier));
											SecJsonObject.addProperty("xp_multiplier", (SecJsonObject.get("xp_multiplier").getAsDouble() * entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES).won_xp_multiplier));
											SecJsonObject.addProperty("xp", (SecJsonObject.get("xp").getAsDouble() - SecJsonObject.get("old_cap_xp").getAsDouble()));
											SecJsonObject.addProperty("level", (SecJsonObject.get("level").getAsDouble() + 1));
											if (entity instanceof Player _player && !_player.level().isClientSide())
												_player.displayClientMessage(Component.literal((Component.translatable("jobs.message.reachlevel.message1").getString() + "" + jobDisplayName
														+ Component.translatable("jobs.message.reachlevel.message2").getString() + new java.text.DecimalFormat("##").format(SecJsonObject.get("level").getAsDouble()))), false);
											ReachLevelProcedure.execute(entity);
										}
									}
									{
										com.google.gson.Gson mainGSONBuilderVariable = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
										try {
											FileWriter fileWriter = new FileWriter(File);
											fileWriter.write(mainGSONBuilderVariable.toJson(SecJsonObject));
											fileWriter.close();
										} catch (IOException exception) {
											exception.printStackTrace();
										}
									}
								}
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
