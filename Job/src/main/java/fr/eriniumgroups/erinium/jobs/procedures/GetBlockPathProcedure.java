package fr.eriniumgroups.erinium.jobs.procedures;

import org.checkerframework.checker.units.qual.s;

import net.neoforged.fml.loading.FMLPaths;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import java.io.File;

public class GetBlockPathProcedure {
	public static File execute(LevelAccessor world, double x, double y, double z) {
		com.google.gson.JsonObject ClearJsonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject SecJsonObject = new com.google.gson.JsonObject();
		double return_level = 0;
		double whilecount = 0;
		double Count = 0;
		double slot_count = 0;
		ItemStack tempItem = ItemStack.EMPTY;
		File File = new File("");
		String object = "";
		return new File((FMLPaths.GAMEDIR.get().toString() + "/config/EriniumJobs/Required/" + new Object() {
			private String split(String text, String space, int index) {
				String s = text.split(space)[index];
				return s;
			}
		}.split((BuiltInRegistries.BLOCK.getKey((world.getBlockState(BlockPos.containing(x, y, z))).getBlock()).toString()), ":", (int) 0) + "/"), File.separator + (new Object() {
			private String split(String text, String space, int index) {
				String s = text.split(space)[index];
				return s;
			}
		}.split((BuiltInRegistries.BLOCK.getKey((world.getBlockState(BlockPos.containing(x, y, z))).getBlock()).toString()), ":", (int) 1) + ".json"));
	}
}
