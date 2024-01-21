package fr.eriniumgroups.erinium.factionmod.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;

import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;
import fr.eriniumgroups.erinium.factionmod.configuration.ConfigConfiguration;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.StringArgumentType;

import com.google.gson.JsonObject;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

public class FactionCreateProcedure {
	public static void execute(LevelAccessor world, CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		String ID = "";
		String Displayname = "";
		boolean duplicatedname = false;
		double count = 0;
		File file = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject PlayerJsonObject = new com.google.gson.JsonObject();
		if (!TargetEntityHaveFactionProcedure.execute(entity)) {
			ID = (StringArgumentType.getString(arguments, "id")).replaceAll("[^a-zA-Z0-9_\\- ]", "");
			Displayname = (StringArgumentType.getString(arguments, "displayname")).replaceAll("[^a-zA-Z0-9_\\- ]", "");
			if ((ID).length() <= 20) {
				if ((Displayname).length() <= 20) {
					duplicatedname = false;
					String cheminDossierParent = (FMLPaths.GAMEDIR.get().toString() + "/Faction_list/");
					java.io.File dossierParent = new java.io.File(cheminDossierParent);
					if (dossierParent.exists() && dossierParent.isDirectory()) {
						java.io.File[] sousDossiers = dossierParent.listFiles();
						// Parcours tous les sous-dossiers du dossier parent
						for (java.io.File currentFolder : sousDossiers) {
							if (currentFolder.isDirectory()) {
								// ...
								if ((currentFolder.getName()).equals(ID)) {
									duplicatedname = true;
								}
							}
						}
					} else {
						System.out.println("Le dossier parent n'existe pas ou n'est pas un dossier valide.");
					}
					if (!duplicatedname) {
						if (TargetEntityHaveItemNeedToCreateProcedure.execute(world, entity)) {
							if (entity instanceof Player _player) {
								ItemStack _stktoremove = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(ConfigConfiguration.ITEM_NEED_CREATE.get())));
								_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) (double) ConfigConfiguration.ITEM_NUMBER_CREATE.get(), _player.inventoryMenu.getCraftSlots());
							}
							file = new File((FMLPaths.GAMEDIR.get().toString() + "/Faction_list/" + ID + "/"), File.separator + "global_informations.json");
							try {
								file.getParentFile().mkdirs();
								file.createNewFile();
							} catch (IOException exception) {
								exception.printStackTrace();
							}
							JsonObject.addProperty("faction_displayname", Displayname);
							JsonObject.addProperty("power", ReturnTargetEntityPowerProcedure.execute(entity));
							JsonObject.addProperty("max_power", 10);
							JsonObject.addProperty("faction_desc", "No description");
							JsonObject.addProperty("member_count", (entity.getUUID().toString() + ", "));
							JsonObject.addProperty("claims", 0);
							{
								Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
								try {
									FileWriter fileWriter = new FileWriter(file);
									fileWriter.write(mainGSONBuilderVariable.toJson(JsonObject));
									fileWriter.close();
								} catch (IOException exception) {
									exception.printStackTrace();
								}
							}
							{
								String _setval = ID;
								entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.faction_name = _setval;
									capability.syncPlayerVariables(entity);
								});
							}
							{
								String _setval = Displayname;
								entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.faction_displayname = _setval;
									capability.syncPlayerVariables(entity);
								});
							}
							{
								String _setval = "Chef";
								entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.faction_rank = _setval;
									capability.syncPlayerVariables(entity);
								});
							}
							file = ReturnTargetEntityPathProcedure.execute(entity);
							{
								try {
									BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
									StringBuilder jsonstringbuilder = new StringBuilder();
									String line;
									while ((line = bufferedReader.readLine()) != null) {
										jsonstringbuilder.append(line);
									}
									bufferedReader.close();
									PlayerJsonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
									PlayerJsonObject.addProperty("faction", ((entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).faction_name));
									PlayerJsonObject.addProperty("faction_rank", ((entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).faction_rank));
									{
										Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
										try {
											FileWriter fileWriter = new FileWriter(file);
											fileWriter.write(mainGSONBuilderVariable.toJson(PlayerJsonObject));
											fileWriter.close();
										} catch (IOException exception) {
											exception.printStackTrace();
										}
									}
									FactionCreateRankProcedure.execute(entity);
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
							if (entity instanceof Player _player && !_player.level().isClientSide())
								_player.displayClientMessage(Component.literal(("\u00A7cVous avez cr\u00E9er la faction : \u00A7e"
										+ (entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).faction_name + " \u00A7cavec succ\u00E8s !")), false);
							if (!world.isClientSide() && world.getServer() != null)
								world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(("\u00A7a" + entity.getDisplayName().getString() + " \u00A7ea cr\u00E9er la faction \u00A7b"
										+ (entity.getCapability(EriniumFactionModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumFactionModVariables.PlayerVariables())).faction_displayname)), false);
						} else {
							if (entity instanceof Player _player && !_player.level().isClientSide())
								_player.displayClientMessage(Component.literal(("\u00A7cYou need " + (double) ConfigConfiguration.ITEM_NUMBER_CREATE.get() + "x " + ConfigConfiguration.ITEM_NEED_CREATE.get() + " !")), false);
						}
					} else {
						if (entity instanceof Player _player && !_player.level().isClientSide())
							_player.displayClientMessage(Component.literal(("\u00A7c" + Component.translatable("faction.error.factionalreadyexist").getString())), false);
					}
				} else {
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("\u00A7c" + Component.translatable("faction.error.displaynamecharcount").getString())), false);
				}
			} else {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(("\u00A7c" + Component.translatable("faction.error.idcharcount").getString())), false);
			}
		} else {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(("\u00A7c" + Component.translatable("faction.error.alreadyhavefaction").getString())), false);
		}
	}
}
