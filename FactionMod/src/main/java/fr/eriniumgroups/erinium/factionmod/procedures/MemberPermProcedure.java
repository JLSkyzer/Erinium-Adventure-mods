package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.MenuProvider;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import java.util.List;
import java.util.ArrayList;

import java.io.File;

import io.netty.buffer.Unpooled;

import fr.eriniumgroups.erinium.factionmod.world.inventory.EditPermissionGuiMenu;
import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;

import com.google.gson.JsonObject;

public class MemberPermProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		File file = new File("");
		List<Object> myArray = new ArrayList<>();
		double count = 0;
		{
			String _setval = FMLPaths.GAMEDIR.get().toString() + "/Faction_list/" + (entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).faction_name
					+ "/permissions/";
			entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.temp_perm_path = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			String _setval = "Member";
			entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.temp_perm_file = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			String _setval = "";
			entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.temp_perm_list = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		GetAllPermissionsProcedure.execute(entity);
		if (entity instanceof ServerPlayer _ent) {
			BlockPos _bpos = BlockPos.containing(x, y, z);
			NetworkHooks.openScreen((ServerPlayer) _ent, new MenuProvider() {
				@Override
				public Component getDisplayName() {
					return Component.literal("EditPermissionGui");
				}

				@Override
				public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
					return new EditPermissionGuiMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
				}
			}, _bpos);
		}
	}
}
