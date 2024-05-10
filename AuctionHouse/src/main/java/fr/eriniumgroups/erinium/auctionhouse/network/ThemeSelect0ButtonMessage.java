
package fr.eriniumgroups.erinium.auctionhouse.network;

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
import net.minecraft.core.BlockPos;

import java.util.HashMap;

import fr.eriniumgroups.erinium.auctionhouse.world.inventory.ThemeSelect0Menu;
import fr.eriniumgroups.erinium.auctionhouse.procedures.WhiteProcedure;
import fr.eriniumgroups.erinium.auctionhouse.procedures.RedProcedure;
import fr.eriniumgroups.erinium.auctionhouse.procedures.DarkProcedure;
import fr.eriniumgroups.erinium.auctionhouse.procedures.BlueroyalpurpleProcedure;
import fr.eriniumgroups.erinium.auctionhouse.EriniumAhMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record ThemeSelect0ButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {

	public static final ResourceLocation ID = new ResourceLocation(EriniumAhMod.MODID, "theme_select_0_buttons");
	public ThemeSelect0ButtonMessage(FriendlyByteBuf buffer) {
		this(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt());
	}

	@Override
	public void write(final FriendlyByteBuf buffer) {
		buffer.writeInt(buttonID);
		buffer.writeInt(x);
		buffer.writeInt(y);
		buffer.writeInt(z);
	}

	@Override
	public ResourceLocation id() {
		return ID;
	}

	public static void handleData(final ThemeSelect0ButtonMessage message, final PlayPayloadContext context) {
		if (context.flow() == PacketFlow.SERVERBOUND) {
			context.workHandler().submitAsync(() -> {
				Player entity = context.player().get();
				int buttonID = message.buttonID;
				int x = message.x;
				int y = message.y;
				int z = message.z;
				handleButtonAction(entity, buttonID, x, y, z);
			}).exceptionally(e -> {
				context.packetHandler().disconnect(Component.literal(e.getMessage()));
				return null;
			});
		}
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level();
		HashMap guistate = ThemeSelect0Menu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			DarkProcedure.execute(entity);
		}
		if (buttonID == 1) {

			WhiteProcedure.execute(entity);
		}
		if (buttonID == 2) {

			RedProcedure.execute(entity);
		}
		if (buttonID == 3) {

			BlueroyalpurpleProcedure.execute(entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		EriniumAhMod.addNetworkMessage(ThemeSelect0ButtonMessage.ID, ThemeSelect0ButtonMessage::new, ThemeSelect0ButtonMessage::handleData);
	}
}
