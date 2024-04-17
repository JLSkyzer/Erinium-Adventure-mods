
package fr.eriniumgroup.eriniumadventure.base.network;

import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;

import fr.eriniumgroup.eriniumadventure.base.procedures.LaunchOnKeyPressedProcedure;
import fr.eriniumgroup.eriniumadventure.base.EriniumAdventureMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record LaunchMessage(int type, int pressedms) implements CustomPacketPayload {
	public static final ResourceLocation ID = new ResourceLocation(EriniumAdventureMod.MODID, "key_launch");

	public LaunchMessage(FriendlyByteBuf buffer) {
		this(buffer.readInt(), buffer.readInt());
	}

	@Override
	public void write(final FriendlyByteBuf buffer) {
		buffer.writeInt(type);
		buffer.writeInt(pressedms);
	}

	@Override
	public ResourceLocation id() {
		return ID;
	}

	public static void handleData(final LaunchMessage message, final PlayPayloadContext context) {
		if (context.flow() == PacketFlow.SERVERBOUND) {
			context.workHandler().submitAsync(() -> {
				pressAction(context.player().get(), message.type, message.pressedms);
			}).exceptionally(e -> {
				context.packetHandler().disconnect(Component.literal(e.getMessage()));
				return null;
			});
		}
	}

	public static void pressAction(Player entity, int type, int pressedms) {
		Level world = entity.level();
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(entity.blockPosition()))
			return;
		if (type == 0) {

			LaunchOnKeyPressedProcedure.execute(world, x, y, z, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		EriniumAdventureMod.addNetworkMessage(LaunchMessage.ID, LaunchMessage::new, LaunchMessage::handleData);
	}
}
