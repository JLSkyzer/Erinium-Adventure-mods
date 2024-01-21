package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;

import com.google.gson.JsonObject;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

@Mod.EventBusSubscriber
public class PlayerTickProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.level(), event.player);
		}
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		File File = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject SecJsonObject = new com.google.gson.JsonObject();
		boolean upFactionPower = false;
		if (!(new Object() {
			private String getChunk(int chunkX, int chunkZ) {
				ChunkPos chunkpos = new ChunkPos(new BlockPos(chunkX, 0, chunkZ));
				return new String(chunkpos.getRegionLocalX() + "-" + chunkpos.getRegionLocalZ());
			}
		}.getChunk((int) (entity.getX()), (int) (entity.getZ()))).equals((entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).current_chunk)) {
			{
				String _setval = new Object() {
					private String getChunk(int chunkX, int chunkZ) {
						ChunkPos chunkpos = new ChunkPos(new BlockPos(chunkX, 0, chunkZ));
						return new String(chunkpos.getRegionLocalX() + "-" + chunkpos.getRegionLocalZ());
					}
				}.getChunk((int) (entity.getX()), (int) (entity.getZ()));
				entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.current_chunk = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		if (!(new Object() {
			private String getRegion(int chunkX, int chunkZ) {
				ChunkPos chunkpos = new ChunkPos(new BlockPos(chunkX, 0, chunkZ));
				return new String("r." + chunkpos.getRegionX() + "." + chunkpos.getRegionZ());
			}
		}.getRegion((int) (entity.getX()), (int) (entity.getX()))).equals((entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).current_region)) {
			{
				String _setval = new Object() {
					private String getRegion(int chunkX, int chunkZ) {
						ChunkPos chunkpos = new ChunkPos(new BlockPos(chunkX, 0, chunkZ));
						return new String("r." + chunkpos.getRegionX() + "." + chunkpos.getRegionZ());
					}
				}.getRegion((int) (entity.getX()), (int) (entity.getX()));
				entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.current_region = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		if ((entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).Invite_timer > 0) {
			{
				double _setval = (entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).Invite_timer - 1;
				entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.Invite_timer = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		if ((entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).power_timer > 0) {
			{
				double _setval = (entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).power_timer - 1;
				entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.power_timer = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else {
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
					JsonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
					if (JsonObject.get("power").getAsDouble() < 10) {
						JsonObject.addProperty("power", (JsonObject.get("power").getAsDouble() + 1));
						{
							Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
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
							SecJsonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
							SecJsonObject.addProperty("power", (SecJsonObject.get("power").getAsDouble() + 1));
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
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
			{
				double _setval = 12000;
				entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.power_timer = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
