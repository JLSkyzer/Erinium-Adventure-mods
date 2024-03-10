
package fr.eriniumgroups.erinium.auctionhouse.network;

import fr.eriniumgroups.erinium.auctionhouse.procedures.*;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import java.util.function.Supplier;
import java.util.HashMap;

import fr.eriniumgroups.erinium.auctionhouse.world.inventory.AhMainMenuMenu;
import fr.eriniumgroups.erinium.auctionhouse.EriniumAhMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AhMainMenuButtonMessage {
	private final int buttonID, x, y, z;

	public AhMainMenuButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
	}

	public AhMainMenuButtonMessage(int buttonID, int x, int y, int z) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static void buffer(AhMainMenuButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static void handler(AhMainMenuButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			Player entity = context.getSender();
			int buttonID = message.buttonID;
			int x = message.x;
			int y = message.y;
			int z = message.z;
			handleButtonAction(entity, buttonID, x, y, z);
		});
		context.setPacketHandled(true);
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

			Line6BuyProcedure.execute(world, x, y, z, entity);
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
		EriniumAhMod.addNetworkMessage(AhMainMenuButtonMessage.class, AhMainMenuButtonMessage::buffer, AhMainMenuButtonMessage::new, AhMainMenuButtonMessage::handler);
	}
}
