
package fr.eriniumgroups.erinium.jobs.network;

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

import fr.eriniumgroups.erinium.jobs.world.inventory.WonXpOverlayConfigMenu;
import fr.eriniumgroups.erinium.jobs.procedures.UpProcedure;
import fr.eriniumgroups.erinium.jobs.procedures.RightProcedure;
import fr.eriniumgroups.erinium.jobs.procedures.ResetProcedure;
import fr.eriniumgroups.erinium.jobs.procedures.LeftProcedure;
import fr.eriniumgroups.erinium.jobs.procedures.DownProcedure;
import fr.eriniumgroups.erinium.jobs.EriniumjobsMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class WonXpOverlayConfigButtonMessage {
	private final int buttonID, x, y, z;

	public WonXpOverlayConfigButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
	}

	public WonXpOverlayConfigButtonMessage(int buttonID, int x, int y, int z) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static void buffer(WonXpOverlayConfigButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static void handler(WonXpOverlayConfigButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
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
		HashMap guistate = WonXpOverlayConfigMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			DownProcedure.execute(entity);
		}
		if (buttonID == 1) {

			ResetProcedure.execute(entity);
		}
		if (buttonID == 2) {

			LeftProcedure.execute(entity);
		}
		if (buttonID == 3) {

			RightProcedure.execute(entity);
		}
		if (buttonID == 4) {

			UpProcedure.execute(entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		EriniumjobsMod.addNetworkMessage(WonXpOverlayConfigButtonMessage.class, WonXpOverlayConfigButtonMessage::buffer, WonXpOverlayConfigButtonMessage::new, WonXpOverlayConfigButtonMessage::handler);
	}
}
