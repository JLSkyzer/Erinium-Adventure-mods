package fr.eriniumgroups.erinium.jobs.procedures;

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
import net.minecraft.commands.CommandSourceStack;

import java.io.File;

import io.netty.buffer.Unpooled;

import fr.eriniumgroups.erinium.jobs.world.inventory.RankInfoMenu;
import fr.eriniumgroups.erinium.jobs.network.EriniumjobsModVariables;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.StringArgumentType;

import com.google.gson.JsonObject;

public class OpenRankInfoProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		File File = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		double temp_number = 0;
		{
			String _setval = StringArgumentType.getString(arguments, "jobid");
			entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.temp_job_id = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		File = new File((FMLPaths.GAMEDIR.get().toString() + "/EriniumJobs/player_information/" + entity.getUUID().toString()),
				File.separator + ((entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumjobsModVariables.PlayerVariables())).temp_job_id + ".json"));
		if (File.exists()) {
			{
				if (entity instanceof ServerPlayer _ent) {
					BlockPos _bpos = BlockPos.containing(x, y, z);
					NetworkHooks.openScreen((ServerPlayer) _ent, new MenuProvider() {
						@Override
						public Component getDisplayName() {
							return Component.literal("RankInfo");
						}

						@Override
						public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
							return new RankInfoMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
						}
					}, _bpos);
				}
			}
		} else {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("\u00A7cJob does not exist"), false);
		}
	}
}
