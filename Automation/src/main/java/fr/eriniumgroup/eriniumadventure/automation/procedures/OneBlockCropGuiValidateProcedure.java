package fr.eriniumgroup.eriniumadventure.automation.procedures;

import org.checkerframework.checker.units.qual.s;

import net.neoforged.fml.loading.FMLPaths;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.client.gui.components.EditBox;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

public class OneBlockCropGuiValidateProcedure {
	public static void execute(Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		File file = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		String blockid = "";
		String modid = "";
		if (!(BuiltInRegistries.ITEM.get(new ResourceLocation(((guistate.containsKey("text:id") ? ((EditBox) guistate.get("text:id")).getValue() : "")).toLowerCase(java.util.Locale.ENGLISH))) == Blocks.AIR.asItem())) {
			modid = new Object() {
				private String split(String text, String space, int index) {
					String s = text.split(space)[index];
					return s;
				}
			}.split((guistate.containsKey("text:id") ? ((EditBox) guistate.get("text:id")).getValue() : ""), ":", (int) 0);
			blockid = new Object() {
				private String split(String text, String space, int index) {
					String s = text.split(space)[index];
					return s;
				}
			}.split((guistate.containsKey("text:id") ? ((EditBox) guistate.get("text:id")).getValue() : ""), ":", (int) 1);
			if (!((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem() == Blocks.AIR.asItem())
					&& !((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(1)).getItem() : ItemStack.EMPTY).getItem() == Blocks.AIR.asItem())) {
				file = new File((FMLPaths.GAMEDIR.get().toString() + "/config/eriniumAutomation/Farmer/" + modid + "/"), File.separator + (blockid + ".json"));
				if (!file.exists()) {
					try {
						file.getParentFile().mkdirs();
						file.createNewFile();
					} catch (IOException exception) {
						exception.printStackTrace();
					}
					JsonObject.addProperty("result", (BuiltInRegistries.ITEM
							.getKey((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getItem()).toString()));
					JsonObject.addProperty("result_min", new Object() {
						double convert(String s) {
							try {
								return Double.parseDouble(s.trim());
							} catch (Exception e) {
							}
							return 0;
						}
					}.convert(guistate.containsKey("text:result_min") ? ((EditBox) guistate.get("text:result_min")).getValue() : ""));
					JsonObject.addProperty("result_max", new Object() {
						double convert(String s) {
							try {
								return Double.parseDouble(s.trim());
							} catch (Exception e) {
							}
							return 0;
						}
					}.convert(guistate.containsKey("text:result_max") ? ((EditBox) guistate.get("text:result_max")).getValue() : ""));
					JsonObject.addProperty("seed", (BuiltInRegistries.ITEM
							.getKey((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(1)).getItem() : ItemStack.EMPTY).getItem()).toString()));
					JsonObject.addProperty("seed_min", new Object() {
						double convert(String s) {
							try {
								return Double.parseDouble(s.trim());
							} catch (Exception e) {
							}
							return 0;
						}
					}.convert(guistate.containsKey("text:seed_min") ? ((EditBox) guistate.get("text:seed_min")).getValue() : ""));
					JsonObject.addProperty("seed_max", new Object() {
						double convert(String s) {
							try {
								return Double.parseDouble(s.trim());
							} catch (Exception e) {
							}
							return 0;
						}
					}.convert(guistate.containsKey("text:seed_max") ? ((EditBox) guistate.get("text:seed_max")).getValue() : ""));
					JsonObject.addProperty("replaced_block", (guistate.containsKey("text:id") ? ((EditBox) guistate.get("text:id")).getValue() : ""));
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
					if (guistate.get("text:id") instanceof EditBox _tf)
						_tf.setValue("File created !");
					if (guistate.get("text:result_min") instanceof EditBox _tf)
						_tf.setValue("");
					if (guistate.get("text:result_max") instanceof EditBox _tf)
						_tf.setValue("");
					if (guistate.get("text:seed_min") instanceof EditBox _tf)
						_tf.setValue("");
					if (guistate.get("text:seed_max") instanceof EditBox _tf)
						_tf.setValue("");
					if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
						((Slot) _slots.get(0)).set(ItemStack.EMPTY);
						_player.containerMenu.broadcastChanges();
					}
					if (entity instanceof Player _player && _player.containerMenu instanceof Supplier _current && _current.get() instanceof Map _slots) {
						((Slot) _slots.get(1)).set(ItemStack.EMPTY);
						_player.containerMenu.broadcastChanges();
					}
				}
			}
		}
	}
}
