package fr.eriniumgroups.erinium.jobs.procedures;

import org.checkerframework.checker.units.qual.s;

import net.neoforged.fml.loading.FMLPaths;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.function.Supplier;
import java.util.Map;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import fr.eriniumgroups.erinium.jobs.network.EriniumjobsModVariables;

public class EarnXpTickProcedure {
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
		if (!entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES).wonxp_initialised) {
			File = new File((FMLPaths.GAMEDIR.get().toString() + "/EriniumJobs/player_information/" + entity.getUUID().toString()), File.separator + (entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES).temp_job_id + ".json"));
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
						LevelJsonObject = new com.google.gson.Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
						return_level = LevelJsonObject.get("level").getAsDouble();
						return_xp_multiplier = LevelJsonObject.get("xp_multiplier").getAsDouble();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			String cheminDossierParent = (FMLPaths.GAMEDIR.get().toString() + "/config/EriniumJobs/EarnXp/");
			java.io.File dossierParent = new java.io.File(cheminDossierParent);
			if (dossierParent.exists() && dossierParent.isDirectory()) {
				java.io.File[] sousDossiers = dossierParent.listFiles();
				// Parcours tous les sous-dossiers du dossier parent
				for (java.io.File currentFolder : sousDossiers) {
					if (currentFolder.isDirectory()) {
						// ...
						String cheminDossier = (FMLPaths.GAMEDIR.get().toString() + "/config/EriniumJobs/EarnXp/" + currentFolder.getName() + "/");
						java.io.File dossier = new java.io.File(cheminDossier);
						if (dossier.exists() && dossier.isDirectory()) {
							java.io.File[] fichiers = dossier.listFiles();
							// Parcours tous les fichiers du dossier
							for (java.io.File currentFile : fichiers) {
								// ...
								File = new File((FMLPaths.GAMEDIR.get().toString() + "/config/EriniumJobs/EarnXp/" + currentFolder.getName() + "/"), File.separator + currentFile.getName());
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
											JsonObject = new com.google.gson.Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
											if ((JsonObject.get("job_id").getAsString()).equals(entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES).temp_job_id)) {
												if (JsonObject.get("min-level").getAsDouble() <= return_level && JsonObject.get("max-level").getAsDouble() >= return_level) {
													object = object + "" + currentFolder.getName() + ":" + currentFile.getName().replace(".json", "") + ", ";
												}
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
			Count = entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES).wonxp_page * 18;
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
					File = new File((FMLPaths.GAMEDIR.get().toString() + "/config/EriniumJobs/EarnXp/" + new Object() {
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
								SecJsonObject = new com.google.gson.Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
								if (!(BuiltInRegistries.ITEM.get(new ResourceLocation((new Object() {
									private String split(String text, String space, int index) {
										String s = text.split(space)[index];
										return s;
									}
								}.split(object, ", ", (int) 0)).toLowerCase(java.util.Locale.ENGLISH))) == Blocks.AIR.asItem())) {
									tempItem = new ItemStack(BuiltInRegistries.ITEM.get(new ResourceLocation((new Object() {
										private String split(String text, String space, int index) {
											String s = text.split(space)[index];
											return s;
										}
									}.split(object, ", ", (int) 0)).toLowerCase(java.util.Locale.ENGLISH))));
									tempItem.setHoverName(Component.literal(("\u00A7eType : \u00A7a" + SecJsonObject.get("type").getAsString() + " \u00A7eLevel : \u00A7b"
											+ new java.text.DecimalFormat("##.##").format(SecJsonObject.get("min-level").getAsDouble()) + "->" + new java.text.DecimalFormat("##.##").format(SecJsonObject.get("max-level").getAsDouble())
											+ " \u00A7eXp : \u00A76" + new java.text.DecimalFormat("##.##").format(SecJsonObject.get("xp").getAsDouble() * return_xp_multiplier) + "xp")));
									if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack _setstack = tempItem.copy();
										_setstack.setCount(1);
										((Slot) _slots.get((int) slot_count)).set(_setstack);
										_player.containerMenu.broadcastChanges();
									}
								} else {
									if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
										ItemStack _setstack = new ItemStack(Blocks.AIR).copy();
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
						ItemStack _setstack = new ItemStack(Blocks.AIR).copy();
						_setstack.setCount(1);
						((Slot) _slots.get((int) slot_count)).set(_setstack);
						_player.containerMenu.broadcastChanges();
					}
				}
				slot_count = slot_count + 1;
			}
			{
				EriniumjobsModVariables.PlayerVariables _vars = entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES);
				_vars.wonxp_initialised = true;
				_vars.syncPlayerVariables(entity);
			}
		}
	}
}
