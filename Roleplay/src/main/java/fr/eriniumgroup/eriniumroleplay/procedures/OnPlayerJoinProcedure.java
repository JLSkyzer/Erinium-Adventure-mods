package fr.eriniumgroup.eriniumroleplay.procedures;

import org.checkerframework.checker.units.qual.s;

import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.schedule.Schedule;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import javax.annotation.Nullable;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import fr.eriniumgroup.eriniumroleplay.network.EriniumroleplayModVariables;

@Mod.EventBusSubscriber
public class OnPlayerJoinProcedure {
	@SubscribeEvent
	public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		Entity entityTemp = null;
		File File = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		String Schedule = "";
		if (entity.getData(EriniumroleplayModVariables.PLAYER_VARIABLES).isDead) {
			{
				EriniumroleplayModVariables.PlayerVariables _vars = entity.getData(EriniumroleplayModVariables.PLAYER_VARIABLES);
				_vars.can_call_emergency = true;
				_vars.syncPlayerVariables(entity);
			}
		}
		CreatePlayerFileProcedure.execute(entity);
		File = CreatePlayerFileProcedure.execute(entity);
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
				if (!(JsonObject.get("scheduled_command").getAsString()).isEmpty()) {
					if (JsonObject.get("scheduled_command").getAsString().contains("%newSchedule%")) {
						Schedule = JsonObject.get("scheduled_command").getAsString();
						Schedule = Schedule.replace("/", "");
						while (!(Schedule).isEmpty()) {
							if (world instanceof ServerLevel _level)
								_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
										new Object() {
											private String split(String text, String space, int index) {
												String s = text.split(space)[index];
												return s;
											}
										}.split(Schedule, "%newSchedule%", (int) 0));
							Schedule = Schedule.replace(new Object() {
								private String split(String text, String space, int index) {
									String s = text.split(space)[index];
									return s;
								}
							}.split(Schedule, "%newSchedule%", (int) 0), "");
						}
						JsonObject.addProperty("scheduled_command", "");
					} else {
						Schedule = JsonObject.get("scheduled_command").getAsString();
						Schedule = Schedule.replace("/", "");
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									Schedule);
						JsonObject.addProperty("scheduled_command", "");
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
