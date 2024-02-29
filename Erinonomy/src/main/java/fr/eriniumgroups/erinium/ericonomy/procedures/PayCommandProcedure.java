package fr.eriniumgroups.erinium.ericonomy.procedures;

import net.minecraftforge.fml.ModList;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.DoubleArgumentType;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

public class PayCommandProcedure {
	public static void execute(LevelAccessor world, CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		File file = new File("");
		com.google.gson.JsonObject TargetEntityJsonObject = new com.google.gson.JsonObject();
		com.google.gson.JsonObject CommandEntityJsonObject = new com.google.gson.JsonObject();
		double return_money = 0;
		boolean return_logic = false;
		file = GetTargetEntityMoneyProcedure.execute(entity);
		{
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
				StringBuilder jsonstringbuilder = new StringBuilder();
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					jsonstringbuilder.append(line);
				}
				bufferedReader.close();
				TargetEntityJsonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
				if (TargetEntityJsonObject.get("money").getAsDouble() >= DoubleArgumentType.getDouble(arguments, "amount")) {
					TargetEntityJsonObject.addProperty("money", (TargetEntityJsonObject.get("money").getAsDouble() - DoubleArgumentType.getDouble(arguments, "amount")));
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("\u00A7eEriconomy \u00A7f>> " + Component.translatable("ericonomy.pay.message.send1").getString()
								+ new java.text.DecimalFormat("#,###.##").format(DoubleArgumentType.getDouble(arguments, "amount")) + Component.translatable("ericonomy.pay.message.send2").getString() + (new Object() {
									public Entity getEntity() {
										try {
											return EntityArgument.getEntity(arguments, "player");
										} catch (CommandSyntaxException e) {
											e.printStackTrace();
											return null;
										}
									}
								}.getEntity()).getDisplayName().getString())), false);
					if (ModList.get().isLoaded("erinium_logs")) {
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(0, 0, 0), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									"erilog " + "Ericonomy" + " " + "info" + " "
											+ (entity.getDisplayName().getString() + " send " + new java.text.DecimalFormat("#,###.##").format(DoubleArgumentType.getDouble(arguments, "amount")) + " to " + (new Object() {
												public Entity getEntity() {
													try {
														return EntityArgument.getEntity(arguments, "player");
													} catch (CommandSyntaxException e) {
														e.printStackTrace();
														return null;
													}
												}
											}.getEntity()).getDisplayName().getString()));
					} else {
						System.out.println("Erilog is not installed ! install here : https://github.com/JLSkyzer/Erinium-Adventure-mods/releases/tag/Erilog");
					}
					{
						Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
						try {
							FileWriter fileWriter = new FileWriter(file);
							fileWriter.write(mainGSONBuilderVariable.toJson(TargetEntityJsonObject));
							fileWriter.close();
						} catch (IOException exception) {
							exception.printStackTrace();
						}
					}
					return_logic = true;
				} else {
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("\u00A7eEriconomy \u00A7f>> " + Component.translatable("erinocomy.message.nomoney").getString())), false);
					return_logic = false;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (return_logic) {
			file = GetCommandEntityPathProcedure.execute(arguments);
			{
				try {
					BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
					StringBuilder jsonstringbuilder = new StringBuilder();
					String line;
					while ((line = bufferedReader.readLine()) != null) {
						jsonstringbuilder.append(line);
					}
					bufferedReader.close();
					CommandEntityJsonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
					CommandEntityJsonObject.addProperty("money", (CommandEntityJsonObject.get("money").getAsDouble() + DoubleArgumentType.getDouble(arguments, "amount")));
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("\u00A7eEriconomy \u00A7f>> " + Component.translatable("ericonomy.pay.message.receive1").getString()
								+ new java.text.DecimalFormat("#,###.##").format(DoubleArgumentType.getDouble(arguments, "amount")) + Component.translatable("ericonomy.pay.message.receive2").getString() + entity.getDisplayName().getString())),
								false);
					if (ModList.get().isLoaded("erinium_logs")) {
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(0, 0, 0), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									"erilog " + "Ericonomy" + " " + "info" + " " + ((new Object() {
										public Entity getEntity() {
											try {
												return EntityArgument.getEntity(arguments, "player");
											} catch (CommandSyntaxException e) {
												e.printStackTrace();
												return null;
											}
										}
									}.getEntity()).getDisplayName().getString() + " receive " + new java.text.DecimalFormat("#,###.##").format(DoubleArgumentType.getDouble(arguments, "amount")) + " from " + entity.getDisplayName().getString()));
					} else {
						System.out.println("Erilog is not installed ! install here : https://github.com/JLSkyzer/Erinium-Adventure-mods/releases/tag/Erilog");
					}
					{
						Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
						try {
							FileWriter fileWriter = new FileWriter(file);
							fileWriter.write(mainGSONBuilderVariable.toJson(CommandEntityJsonObject));
							fileWriter.close();
						} catch (IOException exception) {
							exception.printStackTrace();
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
