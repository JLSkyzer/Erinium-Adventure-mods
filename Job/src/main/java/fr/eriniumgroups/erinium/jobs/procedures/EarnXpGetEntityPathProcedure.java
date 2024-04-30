package fr.eriniumgroups.erinium.jobs.procedures;

import org.checkerframework.checker.units.qual.s;

import net.neoforged.fml.loading.FMLPaths;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.registries.BuiltInRegistries;

import java.io.File;

public class EarnXpGetEntityPathProcedure {
	public static File execute(Entity entity) {
		if (entity == null)
			return new File("");
		com.google.gson.JsonObject ClearJsonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject SecJsonObject = new com.google.gson.JsonObject();
		double return_level = 0;
		double whilecount = 0;
		double Count = 0;
		double slot_count = 0;
		ItemStack tempItem = ItemStack.EMPTY;
		File File = new File("");
		String object = "";
		return new File((FMLPaths.GAMEDIR.get().toString() + "/config/EriniumJobs/EarnXp/" + new Object() {
			private String split(String text, String space, int index) {
				String s = text.split(space)[index];
				return s;
			}
		}.split((BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).toString()), ":", (int) 0) + "/"), File.separator + (new Object() {
			private String split(String text, String space, int index) {
				String s = text.split(space)[index];
				return s;
			}
		}.split((BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).toString()), ":", (int) 1) + ".json"));
	}
}
