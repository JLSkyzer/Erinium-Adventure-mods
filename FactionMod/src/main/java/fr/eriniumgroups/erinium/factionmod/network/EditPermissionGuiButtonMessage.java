
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

import fr.eriniumgroups.erinium.factionmod.world.inventory.EditPermissionGuiMenu;
import fr.eriniumgroups.erinium.factionmod.procedures.ValidateBtnProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.ChangeWarpProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.ChangeUnclaimProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.ChangeSetwarpProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.ChangeSethomeProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.ChangePromoteProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.ChangePoseProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.ChangeModifyProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.ChangeKickProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.ChangeInviteProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.ChangeInteractProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.ChangeHomeProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.ChangeDemoteProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.ChangeDelwarpProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.ChangeDelhomeProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.ChangeClaimProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.ChangeBreakProcedure;
import fr.eriniumgroups.erinium.factionmod.EriniumFactionMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EditPermissionGuiButtonMessage {
	private final int buttonID, x, y, z;

	public EditPermissionGuiButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
	}

	public EditPermissionGuiButtonMessage(int buttonID, int x, int y, int z) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static void buffer(EditPermissionGuiButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static void handler(EditPermissionGuiButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
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
		HashMap guistate = EditPermissionGuiMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			ValidateBtnProcedure.execute(entity);
		}
		if (buttonID == 1) {

			ChangeInteractProcedure.execute(entity);
		}
		if (buttonID == 2) {

			ChangeBreakProcedure.execute(entity);
		}
		if (buttonID == 3) {

			ChangePoseProcedure.execute(entity);
		}
		if (buttonID == 4) {

			ChangeKickProcedure.execute(entity);
		}
		if (buttonID == 5) {

			ChangeInviteProcedure.execute(entity);
		}
		if (buttonID == 6) {

			ChangePromoteProcedure.execute(entity);
		}
		if (buttonID == 7) {

			ChangeDemoteProcedure.execute(entity);
		}
		if (buttonID == 8) {

			ChangeClaimProcedure.execute(entity);
		}
		if (buttonID == 9) {

			ChangeUnclaimProcedure.execute(entity);
		}
		if (buttonID == 10) {

			ChangeSetwarpProcedure.execute(entity);
		}
		if (buttonID == 11) {

			ChangeDelwarpProcedure.execute(entity);
		}
		if (buttonID == 12) {

			ChangeSethomeProcedure.execute(entity);
		}
		if (buttonID == 13) {

			ChangeDelhomeProcedure.execute(entity);
		}
		if (buttonID == 14) {

			ChangeModifyProcedure.execute(entity);
		}
		if (buttonID == 15) {

			ChangeHomeProcedure.execute(entity);
		}
		if (buttonID == 16) {

			ChangeWarpProcedure.execute(entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		EriniumFactionMod.addNetworkMessage(EditPermissionGuiButtonMessage.class, EditPermissionGuiButtonMessage::buffer, EditPermissionGuiButtonMessage::new, EditPermissionGuiButtonMessage::handler);
	}
}
