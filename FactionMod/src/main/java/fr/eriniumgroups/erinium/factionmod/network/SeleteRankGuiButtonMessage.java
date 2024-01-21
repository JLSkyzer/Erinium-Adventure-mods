
package fr.eriniumgroups.erinium.factionmod.network;

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

import fr.eriniumgroups.erinium.factionmod.world.inventory.SeleteRankGuiMenu;
import fr.eriniumgroups.erinium.factionmod.procedures.RecruitPermProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.OfficierPermProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.MemberPermProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.AncientPermProcedure;
import fr.eriniumgroups.erinium.factionmod.EriniumFactionMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SeleteRankGuiButtonMessage {
	private final int buttonID, x, y, z;

	public SeleteRankGuiButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
	}

	public SeleteRankGuiButtonMessage(int buttonID, int x, int y, int z) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static void buffer(SeleteRankGuiButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static void handler(SeleteRankGuiButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
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
		HashMap guistate = SeleteRankGuiMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			RecruitPermProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 1) {

			MemberPermProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 2) {

			AncientPermProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 3) {

			OfficierPermProcedure.execute(world, x, y, z, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		EriniumFactionMod.addNetworkMessage(SeleteRankGuiButtonMessage.class, SeleteRankGuiButtonMessage::buffer, SeleteRankGuiButtonMessage::new, SeleteRankGuiButtonMessage::handler);
	}
}
