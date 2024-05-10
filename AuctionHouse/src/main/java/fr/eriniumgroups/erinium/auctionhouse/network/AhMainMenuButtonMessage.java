
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

import fr.eriniumgroups.erinium.auctionhouse.world.inventory.AhMainMenuMenu;
import fr.eriniumgroups.erinium.auctionhouse.procedures.UpProcedure;
import fr.eriniumgroups.erinium.auctionhouse.procedures.SearchBtnProcedure;
import fr.eriniumgroups.erinium.auctionhouse.procedures.Line6ItemNameProcedure;
import fr.eriniumgroups.erinium.auctionhouse.procedures.Line5BuyProcedure;
import fr.eriniumgroups.erinium.auctionhouse.procedures.Line4BuyProcedure;
import fr.eriniumgroups.erinium.auctionhouse.procedures.Line3BuyProcedure;
import fr.eriniumgroups.erinium.auctionhouse.procedures.Line2BuyProcedure;
import fr.eriniumgroups.erinium.auctionhouse.procedures.Line1BuyProcedure;
import fr.eriniumgroups.erinium.auctionhouse.procedures.Line0BuyProcedure;
import fr.eriniumgroups.erinium.auctionhouse.procedures.DownProcedure;
import fr.eriniumgroups.erinium.auctionhouse.EriniumAhMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record AhMainMenuButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {

	public static final ResourceLocation ID = new ResourceLocation(EriniumAhMod.MODID, "ah_main_menu_buttons");
	public AhMainMenuButtonMessage(FriendlyByteBuf buffer) {
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

	public static void handleData(final AhMainMenuButtonMessage message, final PlayPayloadContext context) {
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
		HashMap guistate = AhMainMenuMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			SearchBtnProcedure.execute(entity);
		}
		if (buttonID == 1) {

			Line0BuyProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 2) {

			Line1BuyProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 3) {

			Line2BuyProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 4) {

			Line3BuyProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 5) {

			Line4BuyProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 6) {

			Line5BuyProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 7) {

			Line6ItemNameProcedure.execute(entity);
		}
		if (buttonID == 8) {

			UpProcedure.execute(entity);
		}
		if (buttonID == 9) {

			DownProcedure.execute(entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		EriniumAhMod.addNetworkMessage(AhMainMenuButtonMessage.ID, AhMainMenuButtonMessage::new, AhMainMenuButtonMessage::handleData);
	}
}
