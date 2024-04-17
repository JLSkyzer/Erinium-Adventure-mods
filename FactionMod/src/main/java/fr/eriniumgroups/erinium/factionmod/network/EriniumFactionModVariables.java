package fr.eriniumgroups.erinium.factionmod.network;

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

import fr.eriniumgroups.erinium.factionmod.EriniumFactionMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EriniumFactionModVariables {
	public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, EriniumFactionMod.MODID);
	public static final Supplier<AttachmentType<PlayerVariables>> PLAYER_VARIABLES = ATTACHMENT_TYPES.register("player_variables", () -> AttachmentType.serializable(() -> new PlayerVariables()).build());

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		EriniumFactionMod.addNetworkMessage(SavedDataSyncMessage.ID, SavedDataSyncMessage::new, SavedDataSyncMessage::handleData);
		EriniumFactionMod.addNetworkMessage(PlayerVariablesSyncMessage.ID, PlayerVariablesSyncMessage::new, PlayerVariablesSyncMessage::handleData);
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
			clone.faction_name = original.faction_name;
			clone.current_chunk = original.current_chunk;
			clone.current_region = original.current_region;
			clone.faction_displayname = original.faction_displayname;
			clone.faction_rank = original.faction_rank;
			clone.temp_perm_file = original.temp_perm_file;
			clone.rank = original.rank;
			clone.temp_count = original.temp_count;
			clone.temp_text = original.temp_text;
			clone.Invite_timer = original.Invite_timer;
			clone.InvitedBy = original.InvitedBy;
			clone.power_timer = original.power_timer;
			clone.temp_perm_list = original.temp_perm_list;
			clone.last_owned = original.last_owned;
			clone.blacklist_item_page = original.blacklist_item_page;
			clone.BL_Item_page_initialised = original.BL_Item_page_initialised;
			clone.bypass_claim = original.bypass_claim;
			clone.CurrentlyDead = original.CurrentlyDead;
			if (!event.isWasDeath()) {
				clone.temp_perm_path = original.temp_perm_path;
				clone.FMapToggle = original.FMapToggle;
				clone.temp_x = original.temp_x;
				clone.temp_y = original.temp_y;
				clone.temp_z = original.temp_z;
				clone.teleport_cooldown = original.teleport_cooldown;
				clone.teleported = original.teleported;
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
		public static final String DATA_NAME = "erinium_faction_worldvars";

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
		public static final String DATA_NAME = "erinium_faction_mapvars";
		public boolean custom_chat = false;

		public static MapVariables load(CompoundTag tag) {
			MapVariables data = new MapVariables();
			data.read(tag);
			return data;
		}

		public void read(CompoundTag nbt) {
			custom_chat = nbt.getBoolean("custom_chat");
		}

		@Override
		public CompoundTag save(CompoundTag nbt) {
			nbt.putBoolean("custom_chat", custom_chat);
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
		public static final ResourceLocation ID = new ResourceLocation(EriniumFactionMod.MODID, "saved_data_sync");
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
		public String faction_name = "wilderness";
		public String current_chunk = "\"\"";
		public String current_region = "\"\"";
		public String faction_displayname = "Wilderness";
		public String faction_rank = "\"\"";
		public String temp_perm_path = "\"\"";
		public String temp_perm_file = "\"\"";
		public String rank = "\u00A7eMembre";
		public double temp_count = 0;
		public String temp_text = "\"\"";
		public double Invite_timer = 0;
		public String InvitedBy = "\"\"";
		public double power_timer = 12000.0;
		public String temp_perm_list = "\"\"";
		public String last_owned = "";
		public boolean FMapToggle = false;
		public double temp_x = 0;
		public double temp_y = 0;
		public double temp_z = 0;
		public double teleport_cooldown = 0;
		public boolean teleported = false;
		public double blacklist_item_page = 0;
		public boolean BL_Item_page_initialised = false;
		public boolean bypass_claim = false;
		public boolean CurrentlyDead = false;

		@Override
		public CompoundTag serializeNBT() {
			CompoundTag nbt = new CompoundTag();
			nbt.putString("faction_name", faction_name);
			nbt.putString("current_chunk", current_chunk);
			nbt.putString("current_region", current_region);
			nbt.putString("faction_displayname", faction_displayname);
			nbt.putString("faction_rank", faction_rank);
			nbt.putString("temp_perm_path", temp_perm_path);
			nbt.putString("temp_perm_file", temp_perm_file);
			nbt.putString("rank", rank);
			nbt.putDouble("temp_count", temp_count);
			nbt.putString("temp_text", temp_text);
			nbt.putDouble("Invite_timer", Invite_timer);
			nbt.putString("InvitedBy", InvitedBy);
			nbt.putDouble("power_timer", power_timer);
			nbt.putString("temp_perm_list", temp_perm_list);
			nbt.putString("last_owned", last_owned);
			nbt.putBoolean("FMapToggle", FMapToggle);
			nbt.putDouble("temp_x", temp_x);
			nbt.putDouble("temp_y", temp_y);
			nbt.putDouble("temp_z", temp_z);
			nbt.putDouble("teleport_cooldown", teleport_cooldown);
			nbt.putBoolean("teleported", teleported);
			nbt.putDouble("blacklist_item_page", blacklist_item_page);
			nbt.putBoolean("BL_Item_page_initialised", BL_Item_page_initialised);
			nbt.putBoolean("bypass_claim", bypass_claim);
			nbt.putBoolean("CurrentlyDead", CurrentlyDead);
			return nbt;
		}

		@Override
		public void deserializeNBT(CompoundTag nbt) {
			faction_name = nbt.getString("faction_name");
			current_chunk = nbt.getString("current_chunk");
			current_region = nbt.getString("current_region");
			faction_displayname = nbt.getString("faction_displayname");
			faction_rank = nbt.getString("faction_rank");
			temp_perm_path = nbt.getString("temp_perm_path");
			temp_perm_file = nbt.getString("temp_perm_file");
			rank = nbt.getString("rank");
			temp_count = nbt.getDouble("temp_count");
			temp_text = nbt.getString("temp_text");
			Invite_timer = nbt.getDouble("Invite_timer");
			InvitedBy = nbt.getString("InvitedBy");
			power_timer = nbt.getDouble("power_timer");
			temp_perm_list = nbt.getString("temp_perm_list");
			last_owned = nbt.getString("last_owned");
			FMapToggle = nbt.getBoolean("FMapToggle");
			temp_x = nbt.getDouble("temp_x");
			temp_y = nbt.getDouble("temp_y");
			temp_z = nbt.getDouble("temp_z");
			teleport_cooldown = nbt.getDouble("teleport_cooldown");
			teleported = nbt.getBoolean("teleported");
			blacklist_item_page = nbt.getDouble("blacklist_item_page");
			BL_Item_page_initialised = nbt.getBoolean("BL_Item_page_initialised");
			bypass_claim = nbt.getBoolean("bypass_claim");
			CurrentlyDead = nbt.getBoolean("CurrentlyDead");
		}

		public void syncPlayerVariables(Entity entity) {
			if (entity instanceof ServerPlayer serverPlayer)
				PacketDistributor.PLAYER.with(serverPlayer).send(new PlayerVariablesSyncMessage(this));
		}
	}

	public record PlayerVariablesSyncMessage(PlayerVariables data) implements CustomPacketPayload {
		public static final ResourceLocation ID = new ResourceLocation(EriniumFactionMod.MODID, "player_variables_sync");

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
