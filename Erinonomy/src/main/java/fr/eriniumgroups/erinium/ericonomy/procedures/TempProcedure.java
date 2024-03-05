package fr.eriniumgroups.erinium.ericonomy.procedures;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import fr.eriniumgroups.erinium.ericonomy.configuration.ServerConfigConfiguration;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraftforge.fml.ModList;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.*;

public class TempProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;

	}
}
