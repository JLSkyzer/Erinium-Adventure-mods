package fr.eriniumgroups.erinium.jobs.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import java.util.function.Supplier;
import java.util.Map;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import fr.eriniumgroups.erinium.jobs.network.EriniumjobsModVariables;

import com.google.gson.JsonObject;
import com.google.gson.Gson;

public class UnlockedTickProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		ItemStack tempItem = ItemStack.EMPTY;
		String object = "";
		File File = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject SecJsonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject LevelJsonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject ClearJsonObject = new com.google.gson.JsonObject();
		double whilecount = 0;
		double Count = 0;
		double slot_count = 0;
		double return_level = 0;
		double return_xp_multiplier = 0;
		if (!(entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumjobsModVariables.PlayerVariables())).wonxp_initialised) {
			File = new File((FMLPaths.GAMEDIR.get().toString() + "/EriniumJobs/player_information/" + entity.getUUID().toString()),
					File.separator + ((entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumjobsModVariables.PlayerVariables())).temp_job_id + ".json"));
			if (File.exists()) {
				{
					try {
						BufferedReader bufferedReader = new BufferedReader(new FileReader(File));
						StringBuilder jsonstringbuilder = new StringBuilder();
						String line;
						while ((line = bufferedReader.readLine()) != null) {
							jsonstringbuilder.append(line);
						}
						bufferedReader.close();
						LevelJsonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
						return_level = LevelJsonObject.get("level").getAsDouble();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			String cheminDossierParent = (FMLPaths.GAMEDIR.get().toString() + "/config/EriniumJobs/Required/");
			java.io.File dossierParent = new java.io.File(cheminDossierParent);
			if (dossierParent.exists() && dossierParent.isDirectory()) {
				java.io.File[] sousDossiers = dossierParent.listFiles();
				// Parcours tous les sous-dossiers du dossier parent
				for (java.io.File currentFolder : sousDossiers) {
					if (currentFolder.isDirectory()) {
						// ...
						String cheminDossier = (FMLPaths.GAMEDIR.get().toString() + "/config/EriniumJobs/Required/" + currentFolder.getName() + "/");
						java.io.File dossier = new java.io.File(cheminDossier);
						if (dossier.exists() && dossier.isDirectory()) {
							java.io.File[] fichiers = dossier.listFiles();
							// Parcours tous les fichiers du dossier
							for (java.io.File currentFile : fichiers) {
								// ...
								File = new File((FMLPaths.GAMEDIR.get().toString() + "/config/EriniumJobs/Required/" + currentFolder.getName() + "/"), File.separator + currentFile.getName());
								if (File.exists()) {
									{
										try {
											BufferedReader bufferedReader = new BufferedReader(new FileReader(File));
											StringBuilder jsonstringbuilder = new StringBuilder();
											String line;
											while ((line = bufferedReader.readLine()) != null) {
												jsonstringbuilder.append(line);
											}
											bufferedReader.close();
											JsonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
											if ((JsonObject.get("job_id").getAsString()).equals((entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumjobsModVariables.PlayerVariables())).temp_job_id)) {
												object = object + "" + currentFolder.getName() + ":" + currentFile.getName().replace(".json", "") + ", ";
											}
										} catch (IOException e) {
											e.printStackTrace();
										}
									}
								}
							}
						} else {
							System.out.println("Le dossier n'existe pas ou n'est pas un dossier valide.");
						}
					}
				}
			} else {
				System.out.println("Le dossier parent n'existe pas ou n'est pas un dossier valide.");
			}
			Count = (entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumjobsModVariables.PlayerVariables())).wonxp_page * 18;
			whilecount = 0;
			if (Count > 0) {
				for (int index0 = 0; index0 < (int) Count; index0++) {
					if (!((object).length() == 0)) {
						object = object.replace(new Object() {
							private String split(String text, String space, int index) {
								String s = text.split(space)[index];
								return s;
							}
						}.split(object, ", ", (int) whilecount) + ", ", "");
					} else {
						break;
					}
				}
			}
			slot_count = 0;
			for (int index1 = 0; index1 < 18; index1++) {
				if ((object).length() > 0) {
					File = new File((FMLPaths.GAMEDIR.get().toString() + "/config/EriniumJobs/Required/" + new Object() {
						private String split(String text, String space, int index) {
							String s = text.split(space)[index];
							return s;
						}
					}.split(new Object() {
						private String split(String text, String space, int index) {
							String s = text.split(space)[index];
							return s;
						}
					}.split(object, ", ", (int) 0), ":", (int) 0) + "/"), File.separator + (new Object() {
						private String split(String text, String space, int index) {
							String s = text.split(space)[index];
							return s;
						}
					}.split(new Object() {
						private String split(String text, String space, int index) {
							String s = text.split(space)[index];
							return s;
						}
					}.split(object, ", ", (int) 0), ":", (int) 1) + ".json"));
					if (File.exists()) {
						{
							try {
								BufferedReader bufferedReader = new BufferedReader(new FileReader(File));
								StringBuilder jsonstringbuilder = new StringBuilder();
								String line;
								while ((line = bufferedReader.readLine()) != null) {
									jsonstringbuilder.append(line);
								}
								bufferedReader.close();
								SecJsonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
								if (!(ForgeRegistries.ITEMS.getValue(new ResourceLocation((new Object() {
									private String split(String text, String space, int index) {
										String s = text.split(space)[index];
										return s;
									}
								}.split(object, ", ", (int) 0)).toLowerCase(java.util.Locale.ENGLISH))) == Blocks.AIR.asItem())) {
									tempItem = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation((new Object() {
										private String split(String text, String space, int index) {
											String s = text.split(space)[index];
											return s;
										}
									}.split(object, ", ", (int) 0)).toLowerCase(java.util.Locale.ENGLISH))));
									if (SecJsonObject.get("level").getAsDouble() > return_level) {
										tempItem.setHoverName(
												Component.literal(("\u00A74" + tempItem.getDisplayName().getString() + " \u00A7eLevel : \u00A76\u00A7l" + new java.text.DecimalFormat("##").format(SecJsonObject.get("level").getAsDouble()))));
									} else {
										tempItem.setHoverName(
												Component.literal(("\u00A7a" + tempItem.getDisplayName().getString() + " \u00A7eLevel : \u00A76\u00A7l" + new java.text.DecimalFormat("##").format(SecJsonObject.get("level").getAsDouble()))));
									}
									if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack _setstack = tempItem;
										_setstack.setCount(1);
										((Slot) _slots.get((int) slot_count)).set(_setstack);
										_player.containerMenu.broadcastChanges();
									}
								} else {
									if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack _setstack = new ItemStack(Blocks.AIR);
										_setstack.setCount(1);
										((Slot) _slots.get((int) slot_count)).set(_setstack);
										_player.containerMenu.broadcastChanges();
									}
								}
								object = object.replace(new Object() {
									private String split(String text, String space, int index) {
										String s = text.split(space)[index];
										return s;
									}
								}.split(object, ", ", (int) 0) + ", ", "");
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
					SecJsonObject = ClearJsonObject;
				} else {
					if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
						ItemStack _setstack = new ItemStack(Blocks.AIR);
						_setstack.setCount(1);
						((Slot) _slots.get((int) slot_count)).set(_setstack);
						_player.containerMenu.broadcastChanges();
					}
				}
				slot_count = slot_count + 1;
			}
			{
				boolean _setval = true;
				entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.wonxp_initialised = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
