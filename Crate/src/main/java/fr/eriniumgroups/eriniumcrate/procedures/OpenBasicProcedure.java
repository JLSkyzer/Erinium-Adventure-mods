package fr.eriniumgroups.eriniumcrate.procedures;

import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.fml.ModList;

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

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import io.netty.buffer.Unpooled;

import fr.eriniumgroups.eriniumcrate.world.inventory.LootCreatorGuiMenu;
import fr.eriniumgroups.eriniumcrate.network.EriniumcrateModVariables;

public class OpenBasicProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (new Object() {
			private Boolean getPermission(String perm) {
				if (ModList.get().isLoaded("eriniumrank")) {
					String TempText = perm;
					com.google.gson.JsonObject RankPerm;
					com.google.gson.JsonObject PlayerPerm;
					String rank = new Object() {
						private String getRank() {
							java.io.File file;
							String TempText = "";
							com.google.gson.JsonObject RankPerm = new com.google.gson.JsonObject();
							file = new java.io.File((FMLPaths.GAMEDIR.get().toString() + "/config/eriniumRanks/players/"), java.io.File.separator + (entity.getDisplayName().getString() + ".json"));
							if (file.exists()) {
								try {
									BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
									StringBuilder jsonstringbuilder = new StringBuilder();
									String line;
									while ((line = bufferedReader.readLine()) != null) {
										jsonstringbuilder.append(line);
									}
									bufferedReader.close();
									RankPerm = new com.google.gson.Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
									TempText = RankPerm.get("rank").getAsString();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
							return TempText;
						}
					}.getRank();
					if (entity instanceof Player || entity instanceof ServerPlayer) {
						RankPerm = new Object() {
							public com.google.gson.JsonObject parse() {
								java.io.File File = new java.io.File("");
								com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
								File = new java.io.File((FMLPaths.GAMEDIR.get().toString() + "/config/eriniumRanks/"), java.io.File.separator + TempText + ".json");
								{
									try {
										BufferedReader bufferedReader = new BufferedReader(new FileReader(File));
										StringBuilder jsonstringbuilder = new StringBuilder();
										String line;
										while ((line = bufferedReader.readLine()) != null) {
											jsonstringbuilder.append(line);
										}
										bufferedReader.close();
										JsonObject = new com.google.gson.Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
								return JsonObject;
							}
						}.parse();
						PlayerPerm = new Object() {
							public com.google.gson.JsonObject parse() {
								java.io.File File = new java.io.File("");
								com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
								File = new File((FMLPaths.GAMEDIR.get().toString() + "/config/eriniumRanks/players/"), java.io.File.separator + (entity.getDisplayName().getString() + ".json"));
								{
									try {
										BufferedReader bufferedReader = new BufferedReader(new FileReader(File));
										StringBuilder jsonstringbuilder = new StringBuilder();
										String line;
										while ((line = bufferedReader.readLine()) != null) {
											jsonstringbuilder.append(line);
										}
										bufferedReader.close();
										JsonObject = new com.google.gson.Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
								return JsonObject;
							}
						}.parse();
						if (RankPerm.has((TempText))) {
							if (RankPerm.get((TempText)).getAsBoolean()) {
								return true;
							} else {
								return false;
							}
						} else if (PlayerPerm.has((TempText))) {
							if (PlayerPerm.get((TempText)).getAsBoolean()) {
								return true;
							} else {
								return false;
							}
						}
					}
				}
				return false;
			}
		}.getPermission("eriniumcrate.creator") || (entity instanceof Player _plr ? _plr.getAbilities().instabuild : false)) {
			{
				EriniumcrateModVariables.PlayerVariables _vars = entity.getData(EriniumcrateModVariables.PLAYER_VARIABLES);
				_vars.creator_types = "basic";
				_vars.syncPlayerVariables(entity);
			}
			if (entity instanceof ServerPlayer _ent) {
				BlockPos _bpos = BlockPos.containing(x, y, z);
				_ent.openMenu(new MenuProvider() {
					@Override
					public Component getDisplayName() {
						return Component.literal("LootCreatorGui");
					}

					@Override
					public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
						return new LootCreatorGuiMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
					}
				}, _bpos);
			}
		} else {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("\u00A7cBe on creative or have the permission \u00A7eeriniumcrate.creator \u00A7cwith Eriniuim Rank"), false);
		}
	}
}
