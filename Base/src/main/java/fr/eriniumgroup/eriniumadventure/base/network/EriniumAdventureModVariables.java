package fr.eriniumgroup.eriniumadventure.base.network;

import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.client.Minecraft;

import java.util.function.Supplier;

import fr.eriniumgroup.eriniumadventure.base.EriniumAdventureMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EriniumAdventureModVariables {
	public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, EriniumAdventureMod.MODID);
	public static final Supplier<AttachmentType<PlayerVariables>> PLAYER_VARIABLES = ATTACHMENT_TYPES.register("player_variables", () -> AttachmentType.serializable(() -> new PlayerVariables()).build());

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		EriniumAdventureMod.addNetworkMessage(SavedDataSyncMessage.ID, SavedDataSyncMessage::new, SavedDataSyncMessage::handleData);
		EriniumAdventureMod.addNetworkMessage(PlayerVariablesSyncMessage.ID, PlayerVariablesSyncMessage::new, PlayerVariablesSyncMessage::handleData);
	}

	@Mod.EventBusSubscriber
	public static class EventBusVariableHandlers {
		@SubscribeEvent
		public static void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
			if (event.getEntity() instanceof ServerPlayer player)
				player.getData(PLAYER_VARIABLES).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
			if (event.getEntity() instanceof ServerPlayer player)
				player.getData(PLAYER_VARIABLES).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (event.getEntity() instanceof ServerPlayer player)
				player.getData(PLAYER_VARIABLES).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void clonePlayer(PlayerEvent.Clone event) {
			PlayerVariables original = event.getOriginal().getData(PLAYER_VARIABLES);
			PlayerVariables clone = new PlayerVariables();
			clone.fire_reduction = original.fire_reduction;
			clone.health = original.health;
			clone.max_health = original.max_health;
			clone.health_multiplier = original.health_multiplier;
			clone.synchronisation = original.synchronisation;
			if (!event.isWasDeath()) {
				clone.needToChoose = original.needToChoose;
				clone.stat_initialised = original.stat_initialised;
				clone.Health_regen_tick = original.Health_regen_tick;
				clone.health_damage = original.health_damage;
				clone.Health_regen_cooldown = original.Health_regen_cooldown;
				clone.movement_type = original.movement_type;
				clone.speed = original.speed;
			}
			event.getEntity().setData(PLAYER_VARIABLES, clone);
		}

		@SubscribeEvent
		public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
			if (event.getEntity() instanceof ServerPlayer player) {
				SavedData mapdata = MapVariables.get(event.getEntity().level());
				SavedData worlddata = WorldVariables.get(event.getEntity().level());
				if (mapdata != null)
					PacketDistributor.PLAYER.with(player).send(new SavedDataSyncMessage(0, mapdata));
				if (worlddata != null)
					PacketDistributor.PLAYER.with(player).send(new SavedDataSyncMessage(1, worlddata));
			}
		}

		@SubscribeEvent
		public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (event.getEntity() instanceof ServerPlayer player) {
				SavedData worlddata = WorldVariables.get(event.getEntity().level());
				if (worlddata != null)
					PacketDistributor.PLAYER.with(player).send(new SavedDataSyncMessage(1, worlddata));
			}
		}
	}

	public static class WorldVariables extends SavedData {
		public static final String DATA_NAME = "erinium_adventure_worldvars";

		public static WorldVariables load(CompoundTag tag) {
			WorldVariables data = new WorldVariables();
			data.read(tag);
			return data;
		}

		public void read(CompoundTag nbt) {
		}

		@Override
		public CompoundTag save(CompoundTag nbt) {
			return nbt;
		}

		public void syncData(LevelAccessor world) {
			this.setDirty();
			if (world instanceof Level level && !level.isClientSide())
				PacketDistributor.DIMENSION.with(level.dimension()).send(new SavedDataSyncMessage(1, this));
		}

		static WorldVariables clientSide = new WorldVariables();

		public static WorldVariables get(LevelAccessor world) {
			if (world instanceof ServerLevel level) {
				return level.getDataStorage().computeIfAbsent(new SavedData.Factory<>(WorldVariables::new, WorldVariables::load), DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class MapVariables extends SavedData {
		public static final String DATA_NAME = "erinium_adventure_mapvars";
		public boolean toggle_realtime = false;

		public static MapVariables load(CompoundTag tag) {
			MapVariables data = new MapVariables();
			data.read(tag);
			return data;
		}

		public void read(CompoundTag nbt) {
			toggle_realtime = nbt.getBoolean("toggle_realtime");
		}

		@Override
		public CompoundTag save(CompoundTag nbt) {
			nbt.putBoolean("toggle_realtime", toggle_realtime);
			return nbt;
		}

		public void syncData(LevelAccessor world) {
			this.setDirty();
			if (world instanceof Level && !world.isClientSide())
				PacketDistributor.ALL.noArg().send(new SavedDataSyncMessage(0, this));
		}

		static MapVariables clientSide = new MapVariables();

		public static MapVariables get(LevelAccessor world) {
			if (world instanceof ServerLevelAccessor serverLevelAcc) {
				return serverLevelAcc.getLevel().getServer().getLevel(Level.OVERWORLD).getDataStorage().computeIfAbsent(new SavedData.Factory<>(MapVariables::new, MapVariables::load), DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class SavedDataSyncMessage implements CustomPacketPayload {
		public static final ResourceLocation ID = new ResourceLocation(EriniumAdventureMod.MODID, "saved_data_sync");
		private final int type;
		private SavedData data;

		public SavedDataSyncMessage(FriendlyByteBuf buffer) {
			this.type = buffer.readInt();
			CompoundTag nbt = buffer.readNbt();
			if (nbt != null) {
				this.data = this.type == 0 ? new MapVariables() : new WorldVariables();
				if (this.data instanceof MapVariables mapVariables)
					mapVariables.read(nbt);
				else if (this.data instanceof WorldVariables worldVariables)
					worldVariables.read(nbt);
			}
		}

		public SavedDataSyncMessage(int type, SavedData data) {
			this.type = type;
			this.data = data;
		}

		@Override
		public void write(final FriendlyByteBuf buffer) {
			buffer.writeInt(type);
			if (data != null)
				buffer.writeNbt(data.save(new CompoundTag()));
		}

		@Override
		public ResourceLocation id() {
			return ID;
		}

		public static void handleData(final SavedDataSyncMessage message, final PlayPayloadContext context) {
			if (context.flow() == PacketFlow.CLIENTBOUND && message.data != null) {
				context.workHandler().submitAsync(() -> {
					if (message.type == 0)
						MapVariables.clientSide.read(message.data.save(new CompoundTag()));
					else
						WorldVariables.clientSide.read(message.data.save(new CompoundTag()));
				}).exceptionally(e -> {
					context.packetHandler().disconnect(Component.literal(e.getMessage()));
					return null;
				});
			}
		}
	}

	public static class PlayerVariables implements INBTSerializable<CompoundTag> {
		public double fire_reduction = 0;
		public boolean needToChoose = false;
		public boolean stat_initialised = false;
		public double health = 20.0;
		public double max_health = 20.0;
		public double Health_regen_tick = 20.0;
		public double health_damage = 20.0;
		public double Health_regen_cooldown = 20.0;
		public double health_multiplier = 0;
		public double synchronisation = 6000.0;
		public String movement_type = "HOLD";
		public double speed = 0;

		@Override
		public CompoundTag serializeNBT() {
			CompoundTag nbt = new CompoundTag();
			nbt.putDouble("fire_reduction", fire_reduction);
			nbt.putBoolean("needToChoose", needToChoose);
			nbt.putBoolean("stat_initialised", stat_initialised);
			nbt.putDouble("health", health);
			nbt.putDouble("max_health", max_health);
			nbt.putDouble("Health_regen_tick", Health_regen_tick);
			nbt.putDouble("health_damage", health_damage);
			nbt.putDouble("Health_regen_cooldown", Health_regen_cooldown);
			nbt.putDouble("health_multiplier", health_multiplier);
			nbt.putDouble("synchronisation", synchronisation);
			nbt.putString("movement_type", movement_type);
			nbt.putDouble("speed", speed);
			return nbt;
		}

		@Override
		public void deserializeNBT(CompoundTag nbt) {
			fire_reduction = nbt.getDouble("fire_reduction");
			needToChoose = nbt.getBoolean("needToChoose");
			stat_initialised = nbt.getBoolean("stat_initialised");
			health = nbt.getDouble("health");
			max_health = nbt.getDouble("max_health");
			Health_regen_tick = nbt.getDouble("Health_regen_tick");
			health_damage = nbt.getDouble("health_damage");
			Health_regen_cooldown = nbt.getDouble("Health_regen_cooldown");
			health_multiplier = nbt.getDouble("health_multiplier");
			synchronisation = nbt.getDouble("synchronisation");
			movement_type = nbt.getString("movement_type");
			speed = nbt.getDouble("speed");
		}

		public void syncPlayerVariables(Entity entity) {
			if (entity instanceof ServerPlayer serverPlayer)
				PacketDistributor.PLAYER.with(serverPlayer).send(new PlayerVariablesSyncMessage(this));
		}
	}

	public record PlayerVariablesSyncMessage(PlayerVariables data) implements CustomPacketPayload {
		public static final ResourceLocation ID = new ResourceLocation(EriniumAdventureMod.MODID, "player_variables_sync");

		public PlayerVariablesSyncMessage(FriendlyByteBuf buffer) {
			this(new PlayerVariables());
			this.data.deserializeNBT(buffer.readNbt());
		}

		@Override
		public void write(final FriendlyByteBuf buffer) {
			buffer.writeNbt(data.serializeNBT());
		}

		@Override
		public ResourceLocation id() {
			return ID;
		}

		public static void handleData(final PlayerVariablesSyncMessage message, final PlayPayloadContext context) {
			if (context.flow() == PacketFlow.CLIENTBOUND && message.data != null) {
				context.workHandler().submitAsync(() -> Minecraft.getInstance().player.getData(PLAYER_VARIABLES).deserializeNBT(message.data.serializeNBT())).exceptionally(e -> {
					context.packetHandler().disconnect(Component.literal(e.getMessage()));
					return null;
				});
			}
		}
	}
}
