package fr.eriniumgroups.erinium.factionmod.procedures;

import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import javax.annotation.Nullable;

import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;
import fr.eriniumgroups.erinium.factionmod.configuration.ConfigConfiguration;

@Mod.EventBusSubscriber
public class PlayerTickProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.level(), event.player.getX(), event.player.getY(), event.player.getZ(), event.player);
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		File File = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject SecJsonObject = new com.google.gson.JsonObject();
		boolean upFactionPower = false;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) > 0) {
			if (!(new Object() {
				private String getChunk(int chunkX, int chunkZ) {
					ChunkPos chunkpos = new ChunkPos(new BlockPos(chunkX, 0, chunkZ));
					return new String(chunkpos.getRegionLocalX() + "-" + chunkpos.getRegionLocalZ());
				}
			}.getChunk((int) (entity.getX()), (int) (entity.getZ()))).equals(entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).current_chunk)) {
				{
					EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
					_vars.current_chunk = new Object() {
						private String getChunk(int chunkX, int chunkZ) {
							ChunkPos chunkpos = new ChunkPos(new BlockPos(chunkX, 0, chunkZ));
							return new String(chunkpos.getRegionLocalX() + "-" + chunkpos.getRegionLocalZ());
						}
					}.getChunk((int) (entity.getX()), (int) (entity.getZ()));
					_vars.syncPlayerVariables(entity);
				}
				if (!(entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).last_owned).equals(ReturnOwnedFactiionProcedure.execute(world, entity))) {
					{
						EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
						_vars.last_owned = ReturnOwnedFactiionProcedure.execute(world, entity);
						_vars.syncPlayerVariables(entity);
					}
					if (world instanceof ServerLevel _level)
						_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
								("title " + entity.getDisplayName().getString() + " times 10 40 10"));
					if ((entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).last_owned).equals("Safezone")) {
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									("title " + entity.getDisplayName().getString() + " subtitle \"\u00A7e" + Component.translatable("safezone.desc").getString() + "\""));
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									("title " + entity.getDisplayName().getString() + " title \"" + "\u00A72Safezone" + "\""));
						if (entity instanceof Player _player && !_player.level().isClientSide())
							_player.displayClientMessage(Component.literal(("\u00A7e" + Component.translatable("faction.message.claim.entered").getString() + "\u00A7a" + "\u00A72Safezone")), false);
					} else if ((entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).last_owned).equals("Warzone")) {
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									("title " + entity.getDisplayName().getString() + " subtitle \"\u00A7e" + Component.translatable("warzone.desc").getString() + "\""));
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									("title " + entity.getDisplayName().getString() + " title \"" + "\u00A74Warzone" + "\""));
						if (entity instanceof Player _player && !_player.level().isClientSide())
							_player.displayClientMessage(Component.literal(("\u00A7e" + Component.translatable("faction.message.claim.entered").getString() + "\u00A7a" + "\u00A74Warzone")), false);
					} else if ((entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).last_owned).equals("wilderness")) {
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									("title " + entity.getDisplayName().getString() + " subtitle \"\u00A7e" + Component.translatable("wilderness.desc").getString() + "\""));
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									("title " + entity.getDisplayName().getString() + " title \"" + "\u00A7aWilderness" + "\""));
						if (entity instanceof Player _player && !_player.level().isClientSide())
							_player.displayClientMessage(Component.literal(("\u00A7e" + Component.translatable("faction.message.claim.entered").getString() + "\u00A7a" + "\u00A7aWilderness")), false);
					} else {
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									("title " + entity.getDisplayName().getString() + " subtitle \"\u00A7e" + GetFactionDescAtChunkProcedure.execute(entity) + "\""));
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									("title " + entity.getDisplayName().getString() + " title \"" + "\u00A7b" + GetFactionDisplaynameAtChunkProcedure.execute(entity) + "\""));
						if (entity instanceof Player _player && !_player.level().isClientSide())
							_player.displayClientMessage(Component.literal(("\u00A7e" + Component.translatable("faction.message.claim.entered").getString() + "\u00A7a" + entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).last_owned)),
									false);
					}
				}
				if (entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).FMapToggle) {
					FactionMapProcedure.execute(world, entity);
				}
			}
		}
		if (!(new Object() {
			private String getRegion(int chunkX, int chunkZ) {
				ChunkPos chunkpos = new ChunkPos(new BlockPos(chunkX, 0, chunkZ));
				return new String("r." + chunkpos.getRegionX() + "." + chunkpos.getRegionZ());
			}
		}.getRegion((int) (entity.getX()), (int) (entity.getZ()))).equals(entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).current_region)) {
			{
				EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
				_vars.current_region = new Object() {
					private String getRegion(int chunkX, int chunkZ) {
						ChunkPos chunkpos = new ChunkPos(new BlockPos(chunkX, 0, chunkZ));
						return new String("r." + chunkpos.getRegionX() + "." + chunkpos.getRegionZ());
					}
				}.getRegion((int) (entity.getX()), (int) (entity.getZ()));
				_vars.syncPlayerVariables(entity);
			}
		}
		if (entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).Invite_timer > 0) {
			{
				EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
				_vars.Invite_timer = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).Invite_timer - 1;
				_vars.syncPlayerVariables(entity);
			}
		}
		if (entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).power_timer > 0) {
			{
				EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
				_vars.power_timer = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).power_timer - 1;
				_vars.syncPlayerVariables(entity);
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
					JsonObject = new com.google.gson.Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
					if (JsonObject.get("power").getAsDouble() < (double) ConfigConfiguration.MAX_POWER.get()) {
						JsonObject.addProperty("power", (JsonObject.get("power").getAsDouble() + 1));
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
							SecJsonObject = new com.google.gson.Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
							SecJsonObject.addProperty("power", (SecJsonObject.get("power").getAsDouble() + 1));
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
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
			{
				EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
				_vars.power_timer = 12000;
				_vars.syncPlayerVariables(entity);
			}
		}
		if (entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).teleport_cooldown > 0) {
			{
				EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
				_vars.teleport_cooldown = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).teleport_cooldown - 1;
				_vars.syncPlayerVariables(entity);
			}
		} else {
			if (entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).teleported) {
				{
					Entity _ent = entity;
					_ent.teleportTo(entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).temp_x, entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).temp_y, entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).temp_z);
					if (_ent instanceof ServerPlayer _serverPlayer)
						_serverPlayer.connection.teleport(entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).temp_x, entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).temp_y,
								entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).temp_z, _ent.getYRot(), _ent.getXRot());
				}
				{
					EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
					_vars.teleported = false;
					_vars.syncPlayerVariables(entity);
				}
			}
		}
	}
}
