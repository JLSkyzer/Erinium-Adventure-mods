package fr.eriniumgroups.erinium.factionmod.procedures;

import net.neoforged.fml.loading.FMLPaths;

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

public class OfficierPermProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		File file = new File("");
		List<Object> myArray = new ArrayList<>();
		double count = 0;
		{
			EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
			_vars.temp_perm_path = FMLPaths.GAMEDIR.get().toString() + "/Faction_list/" + entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_name + "/permissions/";
			_vars.syncPlayerVariables(entity);
		}
		{
			EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
			_vars.temp_perm_file = "Officier";
			_vars.syncPlayerVariables(entity);
		}
		{
			EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
			_vars.temp_perm_list = "";
			_vars.syncPlayerVariables(entity);
		}
		GetAllPermissionsProcedure.execute(entity);
		if (entity instanceof ServerPlayer _ent) {
			BlockPos _bpos = BlockPos.containing(x, y, z);
			_ent.openMenu(new MenuProvider() {
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
