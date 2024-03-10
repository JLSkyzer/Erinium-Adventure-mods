package fr.eriniumgroups.erinium.factionmod.network;

import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraft.client.Minecraft;

import java.util.function.Supplier;
import java.util.ArrayList;

import fr.eriniumgroups.erinium.factionmod.EriniumFactionMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EriniumFactionModVariables {
	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		EriniumFactionMod.addNetworkMessage(SavedDataSyncMessage.class, SavedDataSyncMessage::buffer, SavedDataSyncMessage::new, SavedDataSyncMessage::handler);
		EriniumFactionMod.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new, PlayerVariablesSyncMessage::handler);
	}

	@SubscribeEvent
	public static void init(RegisterCapabilitiesEvent event) {
		event.register(PlayerVariables.class);
	}

	@Mod.EventBusSubscriber
	public static class EventBusVariableHandlers {
		@SubscribeEvent
		public static void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
			if (!event.getEntity().level().isClientSide()) {
				for (Entity entityiterator : new ArrayList<>(event.getEntity().level().players())) {
					((PlayerVariables) entityiterator.getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(entityiterator);
				}
			}
		}

		@SubscribeEvent
		public static void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
			if (!event.getEntity().level().isClientSide()) {
				for (Entity entityiterator : new ArrayList<>(event.getEntity().level().players())) {
					((PlayerVariables) entityiterator.getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(entityiterator);
				}
			}
		}

		@SubscribeEvent
		public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (!event.getEntity().level().isClientSide()) {
				for (Entity entityiterator : new ArrayList<>(event.getEntity().level().players())) {
					((PlayerVariables) entityiterator.getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(entityiterator);
				}
			}
		}

		@SubscribeEvent
		public static void clonePlayer(PlayerEvent.Clone event) {
			event.getOriginal().revive();
			PlayerVariables original = ((PlayerVariables) event.getOriginal().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
			PlayerVariables clone = ((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
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
			if (!event.getEntity().level().isClientSide()) {
				for (Entity entityiterator : new ArrayList<>(event.getEntity().level().players())) {
					((PlayerVariables) entityiterator.getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(entityiterator);
				}
			}
		}

		@SubscribeEvent
		public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
			if (!event.getEntity().level().isClientSide()) {
				SavedData mapdata = MapVariables.get(event.getEntity().level());
				SavedData worlddata = WorldVariables.get(event.getEntity().level());
				if (mapdata != null)
					EriniumFactionMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getEntity()), new SavedDataSyncMessage(0, mapdata));
				if (worlddata != null)
					EriniumFactionMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getEntity()), new SavedDataSyncMessage(1, worlddata));
			}
		}

		@SubscribeEvent
		public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (!event.getEntity().level().isClientSide()) {
				SavedData worlddata = WorldVariables.get(event.getEntity().level());
				if (worlddata != null)
					EriniumFactionMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getEntity()), new SavedDataSyncMessage(1, worlddata));
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
				EriniumFactionMod.PACKET_HANDLER.send(PacketDistributor.DIMENSION.with(level::dimension), new SavedDataSyncMessage(1, this));
		}

		static WorldVariables clientSide = new WorldVariables();

		public static WorldVariables get(LevelAccessor world) {
			if (world instanceof ServerLevel level) {
				return level.getDataStorage().computeIfAbsent(e -> WorldVariables.load(e), WorldVariables::new, DATA_NAME);
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
				EriniumFactionMod.PACKET_HANDLER.send(PacketDistributor.ALL.noArg(), new SavedDataSyncMessage(0, this));
		}

		static MapVariables clientSide = new MapVariables();

		public static MapVariables get(LevelAccessor world) {
			if (world instanceof ServerLevelAccessor serverLevelAcc) {
				return serverLevelAcc.getLevel().getServer().getLevel(Level.OVERWORLD).getDataStorage().computeIfAbsent(e -> MapVariables.load(e), MapVariables::new, DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class SavedDataSyncMessage {
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

		public static void buffer(SavedDataSyncMessage message, FriendlyByteBuf buffer) {
			buffer.writeInt(message.type);
			if (message.data != null)
				buffer.writeNbt(message.data.save(new CompoundTag()));
		}

		public static void handler(SavedDataSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer() && message.data != null) {
					if (message.type == 0)
						MapVariables.clientSide = (MapVariables) message.data;
					else
						WorldVariables.clientSide = (WorldVariables) message.data;
				}
			});
			context.setPacketHandled(true);
		}
	}

	public static final Capability<PlayerVariables> PLAYER_VARIABLES_CAPABILITY = CapabilityManager.get(new CapabilityToken<PlayerVariables>() {
	});

	@Mod.EventBusSubscriber
	private static class PlayerVariablesProvider implements ICapabilitySerializable<Tag> {
		@SubscribeEvent
		public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
			if (event.getObject() instanceof Player && !(event.getObject() instanceof FakePlayer))
				event.addCapability(new ResourceLocation("erinium_faction", "player_variables"), new PlayerVariablesProvider());
		}

		private final PlayerVariables playerVariables = new PlayerVariables();
		private final LazyOptional<PlayerVariables> instance = LazyOptional.of(() -> playerVariables);

		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
			return cap == PLAYER_VARIABLES_CAPABILITY ? instance.cast() : LazyOptional.empty();
		}

		@Override
		public Tag serializeNBT() {
			return playerVariables.writeNBT();
		}

		@Override
		public void deserializeNBT(Tag nbt) {
			playerVariables.readNBT(nbt);
		}
	}

	public static class PlayerVariables {
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

		public void syncPlayerVariables(Entity entity) {
			if (entity instanceof ServerPlayer serverPlayer)
				EriniumFactionMod.PACKET_HANDLER.send(PacketDistributor.DIMENSION.with(entity.level()::dimension), new PlayerVariablesSyncMessage(this, entity.getId()));
		}

		public Tag writeNBT() {
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

		public void readNBT(Tag Tag) {
			CompoundTag nbt = (CompoundTag) Tag;
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
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		EriniumFactionMod.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new, PlayerVariablesSyncMessage::handler);
	}

	public static class PlayerVariablesSyncMessage {
		private final int target;
		private final PlayerVariables data;

		public PlayerVariablesSyncMessage(FriendlyByteBuf buffer) {
			this.data = new PlayerVariables();
			this.data.readNBT(buffer.readNbt());
			this.target = buffer.readInt();
		}

		public PlayerVariablesSyncMessage(PlayerVariables data, int entityid) {
			this.data = data;
			this.target = entityid;
		}

		public static void buffer(PlayerVariablesSyncMessage message, FriendlyByteBuf buffer) {
			buffer.writeNbt((CompoundTag) message.data.writeNBT());
			buffer.writeInt(message.target);
		}

		public static void handler(PlayerVariablesSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					PlayerVariables variables = ((PlayerVariables) Minecraft.getInstance().player.level().getEntity(message.target).getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
					variables.faction_name = message.data.faction_name;
					variables.current_chunk = message.data.current_chunk;
					variables.current_region = message.data.current_region;
					variables.faction_displayname = message.data.faction_displayname;
					variables.faction_rank = message.data.faction_rank;
					variables.temp_perm_path = message.data.temp_perm_path;
					variables.temp_perm_file = message.data.temp_perm_file;
					variables.rank = message.data.rank;
					variables.temp_count = message.data.temp_count;
					variables.temp_text = message.data.temp_text;
					variables.Invite_timer = message.data.Invite_timer;
					variables.InvitedBy = message.data.InvitedBy;
					variables.power_timer = message.data.power_timer;
					variables.temp_perm_list = message.data.temp_perm_list;
					variables.last_owned = message.data.last_owned;
					variables.FMapToggle = message.data.FMapToggle;
					variables.temp_x = message.data.temp_x;
					variables.temp_y = message.data.temp_y;
					variables.temp_z = message.data.temp_z;
					variables.teleport_cooldown = message.data.teleport_cooldown;
					variables.teleported = message.data.teleported;
					variables.blacklist_item_page = message.data.blacklist_item_page;
					variables.BL_Item_page_initialised = message.data.BL_Item_page_initialised;
					variables.bypass_claim = message.data.bypass_claim;
					variables.CurrentlyDead = message.data.CurrentlyDead;
				}
			});
			context.setPacketHandled(true);
		}
	}
}
