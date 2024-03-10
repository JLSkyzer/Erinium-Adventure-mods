
package fr.eriniumgroups.erinium.auctionhouse.network;

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

import fr.eriniumgroups.erinium.auctionhouse.world.inventory.ThemeSelect0Menu;
import fr.eriniumgroups.erinium.auctionhouse.procedures.WhiteProcedure;
import fr.eriniumgroups.erinium.auctionhouse.procedures.RedProcedure;
import fr.eriniumgroups.erinium.auctionhouse.procedures.DarkProcedure;
import fr.eriniumgroups.erinium.auctionhouse.procedures.BlueroyalpurpleProcedure;
import fr.eriniumgroups.erinium.auctionhouse.EriniumAhMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ThemeSelect0ButtonMessage {
	private final int buttonID, x, y, z;

	public ThemeSelect0ButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
	}

	public ThemeSelect0ButtonMessage(int buttonID, int x, int y, int z) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static void buffer(ThemeSelect0ButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static void handler(ThemeSelect0ButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
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
		EriniumAhMod.addNetworkMessage(ThemeSelect0ButtonMessage.class, ThemeSelect0ButtonMessage::buffer, ThemeSelect0ButtonMessage::new, ThemeSelect0ButtonMessage::handler);
	}
}
