package fr.eriniumgroups.erinium.jobs.network;

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

import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.client.Minecraft;

import java.util.function.Supplier;

import fr.eriniumgroups.erinium.jobs.EriniumjobsMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EriniumjobsModVariables {
	public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, EriniumjobsMod.MODID);
	public static final Supplier<AttachmentType<PlayerVariables>> PLAYER_VARIABLES = ATTACHMENT_TYPES.register("player_variables", () -> AttachmentType.serializable(() -> new PlayerVariables()).build());
	public static double temp = 0;

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		EriniumjobsMod.addNetworkMessage(PlayerVariablesSyncMessage.ID, PlayerVariablesSyncMessage::new, PlayerVariablesSyncMessage::handleData);
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
			event.getEntity().setData(PLAYER_VARIABLES, clone);
		}
	}

	public static class PlayerVariables implements INBTSerializable<CompoundTag> {
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

		@Override
		public CompoundTag serializeNBT() {
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

		@Override
		public void deserializeNBT(CompoundTag nbt) {
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

		public void syncPlayerVariables(Entity entity) {
			if (entity instanceof ServerPlayer serverPlayer)
				PacketDistributor.PLAYER.with(serverPlayer).send(new PlayerVariablesSyncMessage(this));
		}
	}

	public record PlayerVariablesSyncMessage(PlayerVariables data) implements CustomPacketPayload {
		public static final ResourceLocation ID = new ResourceLocation(EriniumjobsMod.MODID, "player_variables_sync");

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
