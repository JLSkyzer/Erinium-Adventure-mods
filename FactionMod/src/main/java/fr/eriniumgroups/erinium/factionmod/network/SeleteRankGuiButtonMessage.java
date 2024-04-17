
package fr.eriniumgroups.erinium.factionmod.network;

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

import fr.eriniumgroups.erinium.factionmod.world.inventory.SeleteRankGuiMenu;
import fr.eriniumgroups.erinium.factionmod.procedures.RecruitPermProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.OfficierPermProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.MemberPermProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.AncientPermProcedure;
import fr.eriniumgroups.erinium.factionmod.EriniumFactionMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record SeleteRankGuiButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {

	public static final ResourceLocation ID = new ResourceLocation(EriniumFactionMod.MODID, "selete_rank_gui_buttons");
	public SeleteRankGuiButtonMessage(FriendlyByteBuf buffer) {
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

	public static void handleData(final SeleteRankGuiButtonMessage message, final PlayPayloadContext context) {
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
		EriniumFactionMod.addNetworkMessage(SeleteRankGuiButtonMessage.ID, SeleteRankGuiButtonMessage::new, SeleteRankGuiButtonMessage::handleData);
	}
}
