package fr.eriniumgroups.erinium.factionmod.procedures;

import net.neoforged.fml.loading.FMLPaths;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.BuiltInRegistries;
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
								ItemStack _stktoremove = new ItemStack(BuiltInRegistries.ITEM.get(new ResourceLocation(((ConfigConfiguration.ITEM_NEED_CREATE.get())).toLowerCase(java.util.Locale.ENGLISH))));
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
							JsonObject.addProperty("max_power", ((double) ConfigConfiguration.MAX_POWER.get()));
							JsonObject.addProperty("faction_desc", "No description");
							JsonObject.addProperty("member_count", (entity.getUUID().toString() + ", "));
							JsonObject.addProperty("claims", 0);
							{
								com.google.gson.Gson mainGSONBuilderVariable = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
								try {
									FileWriter fileWriter = new FileWriter(file);
									fileWriter.write(mainGSONBuilderVariable.toJson(JsonObject));
									fileWriter.close();
								} catch (IOException exception) {
									exception.printStackTrace();
								}
							}
							{
								EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
								_vars.faction_name = ID;
								_vars.syncPlayerVariables(entity);
							}
							{
								EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
								_vars.faction_displayname = Displayname;
								_vars.syncPlayerVariables(entity);
							}
							{
								EriniumFactionModVariables.PlayerVariables _vars = entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES);
								_vars.faction_rank = "Chef";
								_vars.syncPlayerVariables(entity);
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
									PlayerJsonObject = new com.google.gson.Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
									PlayerJsonObject.addProperty("faction", entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_name);
									PlayerJsonObject.addProperty("faction_rank", entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_rank);
									{
										com.google.gson.Gson mainGSONBuilderVariable = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
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
								_player.displayClientMessage(Component.literal(("\u00A7cCreated faction : \u00A7e" + entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_name + " \u00A7cwith succes !")), false);
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
