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

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraft.client.Minecraft;

import java.util.function.Supplier;

import fr.eriniumgroups.erinium.factionmod.EriniumFactionMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EriniumFactionModVariables {
	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
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
			if (!event.getEntity().level().isClientSide())
				((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
			if (!event.getEntity().level().isClientSide())
				((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
		}

		@SubscribeEvent
		public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (!event.getEntity().level().isClientSide())
				((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
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
			if (!event.isWasDeath()) {
				clone.temp_perm_path = original.temp_perm_path;
			}
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

		public void syncPlayerVariables(Entity entity) {
			if (entity instanceof ServerPlayer serverPlayer)
				EriniumFactionMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new PlayerVariablesSyncMessage(this));
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
		}
	}

	public static class PlayerVariablesSyncMessage {
		public PlayerVariables data;

		public PlayerVariablesSyncMessage(FriendlyByteBuf buffer) {
			this.data = new PlayerVariables();
			this.data.readNBT(buffer.readNbt());
		}

		public PlayerVariablesSyncMessage(PlayerVariables data) {
			this.data = data;
		}

		public static void buffer(PlayerVariablesSyncMessage message, FriendlyByteBuf buffer) {
			buffer.writeNbt((CompoundTag) message.data.writeNBT());
		}

		public static void handler(PlayerVariablesSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					PlayerVariables variables = ((PlayerVariables) Minecraft.getInstance().player.getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
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
				}
			});
			context.setPacketHandled(true);
		}
	}
}
