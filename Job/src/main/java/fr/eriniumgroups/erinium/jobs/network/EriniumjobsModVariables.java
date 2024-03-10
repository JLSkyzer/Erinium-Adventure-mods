package fr.eriniumgroups.erinium.jobs.network;

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
import java.util.ArrayList;

import fr.eriniumgroups.erinium.jobs.EriniumjobsMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EriniumjobsModVariables {
	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		EriniumjobsMod.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new, PlayerVariablesSyncMessage::handler);
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
			clone.wonxp_page = original.wonxp_page;
			clone.wonxp_initialised = original.wonxp_initialised;
			clone.player_xp = original.player_xp;
			clone.player_cap_xp = original.player_cap_xp;
			clone.player_lvl = original.player_lvl;
			clone.xp_multiplier = original.xp_multiplier;
			clone.won_xp_multiplier = original.won_xp_multiplier;
			clone.old_player_cap_xp = original.old_player_cap_xp;
			clone.won_xp_timer = original.won_xp_timer;
			clone.won_xp_message = original.won_xp_message;
			clone.won_xp_message_2 = original.won_xp_message_2;
			clone.won_xp_x = original.won_xp_x;
			clone.won_xp_y = original.won_xp_y;
			clone.won_xp_config = original.won_xp_config;
			clone.won_xp_percent_x = original.won_xp_percent_x;
			clone.won_xp_percent_y = original.won_xp_percent_y;
			clone.FirstJoined = original.FirstJoined;
			clone.windows_width = original.windows_width;
			clone.windows_height = original.windows_height;
			clone.temp_job_id = original.temp_job_id;
			clone.temp_parametter = original.temp_parametter;
			clone.won_xp_multiplier_base = original.won_xp_multiplier_base;
			if (!event.isWasDeath()) {
			}
			if (!event.getEntity().level().isClientSide()) {
				for (Entity entityiterator : new ArrayList<>(event.getEntity().level().players())) {
					((PlayerVariables) entityiterator.getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(entityiterator);
				}
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
				event.addCapability(new ResourceLocation("eriniumjobs", "player_variables"), new PlayerVariablesProvider());
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
		public double wonxp_page = 0;
		public boolean wonxp_initialised = false;
		public double player_xp = 0;
		public double player_cap_xp = 500.0;
		public double player_lvl = 0;
		public double xp_multiplier = 0;
		public double won_xp_multiplier = 0;
		public double old_player_cap_xp = 0;
		public double won_xp_timer = 0;
		public String won_xp_message = "\"\"";
		public String won_xp_message_2 = "\"\"";
		public double won_xp_x = 70.0;
		public double won_xp_y = 15.0;
		public boolean won_xp_config = false;
		public double won_xp_percent_x = 0;
		public double won_xp_percent_y = 0;
		public boolean FirstJoined = false;
		public double windows_width = 0;
		public double windows_height = 0;
		public String temp_job_id = "\"\"";
		public String temp_parametter = "\"\"";
		public double won_xp_multiplier_base = 0;

		public void syncPlayerVariables(Entity entity) {
			if (entity instanceof ServerPlayer serverPlayer)
				EriniumjobsMod.PACKET_HANDLER.send(PacketDistributor.DIMENSION.with(entity.level()::dimension), new PlayerVariablesSyncMessage(this, entity.getId()));
		}

		public Tag writeNBT() {
			CompoundTag nbt = new CompoundTag();
			nbt.putDouble("wonxp_page", wonxp_page);
			nbt.putBoolean("wonxp_initialised", wonxp_initialised);
			nbt.putDouble("player_xp", player_xp);
			nbt.putDouble("player_cap_xp", player_cap_xp);
			nbt.putDouble("player_lvl", player_lvl);
			nbt.putDouble("xp_multiplier", xp_multiplier);
			nbt.putDouble("won_xp_multiplier", won_xp_multiplier);
			nbt.putDouble("old_player_cap_xp", old_player_cap_xp);
			nbt.putDouble("won_xp_timer", won_xp_timer);
			nbt.putString("won_xp_message", won_xp_message);
			nbt.putString("won_xp_message_2", won_xp_message_2);
			nbt.putDouble("won_xp_x", won_xp_x);
			nbt.putDouble("won_xp_y", won_xp_y);
			nbt.putBoolean("won_xp_config", won_xp_config);
			nbt.putDouble("won_xp_percent_x", won_xp_percent_x);
			nbt.putDouble("won_xp_percent_y", won_xp_percent_y);
			nbt.putBoolean("FirstJoined", FirstJoined);
			nbt.putDouble("windows_width", windows_width);
			nbt.putDouble("windows_height", windows_height);
			nbt.putString("temp_job_id", temp_job_id);
			nbt.putString("temp_parametter", temp_parametter);
			nbt.putDouble("won_xp_multiplier_base", won_xp_multiplier_base);
			return nbt;
		}

		public void readNBT(Tag Tag) {
			CompoundTag nbt = (CompoundTag) Tag;
			wonxp_page = nbt.getDouble("wonxp_page");
			wonxp_initialised = nbt.getBoolean("wonxp_initialised");
			player_xp = nbt.getDouble("player_xp");
			player_cap_xp = nbt.getDouble("player_cap_xp");
			player_lvl = nbt.getDouble("player_lvl");
			xp_multiplier = nbt.getDouble("xp_multiplier");
			won_xp_multiplier = nbt.getDouble("won_xp_multiplier");
			old_player_cap_xp = nbt.getDouble("old_player_cap_xp");
			won_xp_timer = nbt.getDouble("won_xp_timer");
			won_xp_message = nbt.getString("won_xp_message");
			won_xp_message_2 = nbt.getString("won_xp_message_2");
			won_xp_x = nbt.getDouble("won_xp_x");
			won_xp_y = nbt.getDouble("won_xp_y");
			won_xp_config = nbt.getBoolean("won_xp_config");
			won_xp_percent_x = nbt.getDouble("won_xp_percent_x");
			won_xp_percent_y = nbt.getDouble("won_xp_percent_y");
			FirstJoined = nbt.getBoolean("FirstJoined");
			windows_width = nbt.getDouble("windows_width");
			windows_height = nbt.getDouble("windows_height");
			temp_job_id = nbt.getString("temp_job_id");
			temp_parametter = nbt.getString("temp_parametter");
			won_xp_multiplier_base = nbt.getDouble("won_xp_multiplier_base");
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		EriniumjobsMod.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new, PlayerVariablesSyncMessage::handler);
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
					variables.wonxp_page = message.data.wonxp_page;
					variables.wonxp_initialised = message.data.wonxp_initialised;
					variables.player_xp = message.data.player_xp;
					variables.player_cap_xp = message.data.player_cap_xp;
					variables.player_lvl = message.data.player_lvl;
					variables.xp_multiplier = message.data.xp_multiplier;
					variables.won_xp_multiplier = message.data.won_xp_multiplier;
					variables.old_player_cap_xp = message.data.old_player_cap_xp;
					variables.won_xp_timer = message.data.won_xp_timer;
					variables.won_xp_message = message.data.won_xp_message;
					variables.won_xp_message_2 = message.data.won_xp_message_2;
					variables.won_xp_x = message.data.won_xp_x;
					variables.won_xp_y = message.data.won_xp_y;
					variables.won_xp_config = message.data.won_xp_config;
					variables.won_xp_percent_x = message.data.won_xp_percent_x;
					variables.won_xp_percent_y = message.data.won_xp_percent_y;
					variables.FirstJoined = message.data.FirstJoined;
					variables.windows_width = message.data.windows_width;
					variables.windows_height = message.data.windows_height;
					variables.temp_job_id = message.data.temp_job_id;
					variables.temp_parametter = message.data.temp_parametter;
					variables.won_xp_multiplier_base = message.data.won_xp_multiplier_base;
				}
			});
			context.setPacketHandled(true);
		}
	}
}
