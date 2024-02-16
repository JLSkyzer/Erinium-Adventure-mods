package fr.eriniumgroups.erinium.jobs.procedures;

import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.world.item.ItemStack;
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

import com.google.gson.JsonObject;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

@Mod.EventBusSubscriber
public class SmeltItemProcedure {
	@SubscribeEvent
	public static void onItemSmelted(PlayerEvent.ItemSmeltedEvent event) {
		execute(event, event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity(), event.getSmelting());
	}

	public static void execute(double x, double y, double z, Entity entity, ItemStack itemstack) {
		execute(null, x, y, z, entity, itemstack);
	}

	private static void execute(@Nullable Event event, double x, double y, double z, Entity entity, ItemStack itemstack) {
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
			File = EarnXpGetItemStackPathProcedure.execute(itemstack);
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
						ThirdJsonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
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
						SecJsonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
						if (SecJsonObject.get("level").getAsDouble() < 100) {
							if ((type).equals("SMELTED")) {
								if (min_level <= SecJsonObject.get("level").getAsDouble() && max_level >= SecJsonObject.get("level").getAsDouble()) {
									SecJsonObject.addProperty("xp", (SecJsonObject.get("xp").getAsDouble() + xp * itemstack.getCount() * SecJsonObject.get("xp_multiplier").getAsDouble()));
									{
										double _setval = 60;
										entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
											capability.won_xp_timer = _setval;
											capability.syncPlayerVariables(entity);
										});
									}
									{
										String _setval = "\u00A7a+" + new java.text.DecimalFormat("#,###.##").format(xp * itemstack.getCount() * SecJsonObject.get("xp_multiplier").getAsDouble()) + " \u00A72(" + jobDisplayName + ")";
										entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
											capability.won_xp_message = _setval;
											capability.syncPlayerVariables(entity);
										});
									}
									{
										String _setval = "\u00A7e" + new java.text.DecimalFormat("#,###.##").format(SecJsonObject.get("xp").getAsDouble()) + " \u00A7f/ \u00A76"
												+ new java.text.DecimalFormat("#,###.##").format(SecJsonObject.get("cap_xp").getAsDouble());
										entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
											capability.won_xp_message_2 = _setval;
											capability.syncPlayerVariables(entity);
										});
									}
									if (SecJsonObject.get("xp").getAsDouble() >= SecJsonObject.get("cap_xp").getAsDouble() && SecJsonObject.get("level").getAsDouble() < 100) {
										while (SecJsonObject.get("xp").getAsDouble() >= SecJsonObject.get("cap_xp").getAsDouble() && SecJsonObject.get("level").getAsDouble() < 100) {
											SecJsonObject.addProperty("old_cap_xp", SecJsonObject.get("cap_xp").getAsDouble());
											SecJsonObject.addProperty("cap_xp",
													(SecJsonObject.get("cap_xp").getAsDouble() * (entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumjobsModVariables.PlayerVariables())).xp_multiplier));
											SecJsonObject.addProperty("xp_multiplier", (SecJsonObject.get("xp_multiplier").getAsDouble()
													* (entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumjobsModVariables.PlayerVariables())).won_xp_multiplier));
											SecJsonObject.addProperty("xp", (SecJsonObject.get("xp").getAsDouble() - SecJsonObject.get("old_cap_xp").getAsDouble()));
											SecJsonObject.addProperty("level", (SecJsonObject.get("level").getAsDouble() + 1));
											if (entity instanceof Player _player && !_player.level().isClientSide())
												_player.displayClientMessage(Component.literal((Component.translatable("jobs.message.reachlevel.message1").getString() + "" + jobDisplayName
														+ Component.translatable("jobs.message.reachlevel.message2").getString() + new java.text.DecimalFormat("##").format(SecJsonObject.get("level").getAsDouble()))), false);
											ReachLevelProcedure.execute(entity);
										}
									}
									{
										Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
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
