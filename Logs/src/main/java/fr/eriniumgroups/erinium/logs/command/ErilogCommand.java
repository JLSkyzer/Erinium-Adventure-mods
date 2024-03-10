
package fr.eriniumgroups.erinium.logs.command;

import org.checkerframework.checker.units.qual.s;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.common.util.FakePlayerFactory;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.commands.arguments.MessageArgument;
import net.minecraft.commands.Commands;

import fr.eriniumgroups.erinium.logs.procedures.ErilogWarnProcedure;
import fr.eriniumgroups.erinium.logs.procedures.ErilogSuccessProcedure;
import fr.eriniumgroups.erinium.logs.procedures.ErilogInfoProcedure;
import fr.eriniumgroups.erinium.logs.procedures.ErilogErrorProcedure;

import com.mojang.brigadier.arguments.StringArgumentType;

@Mod.EventBusSubscriber
public class ErilogCommand {
	@SubscribeEvent
	public static void registerCommand(RegisterCommandsEvent event) {
		event.getDispatcher().register(
				Commands.literal("erilog").requires(s -> s.hasPermission(3)).then(Commands.argument("type", StringArgumentType.word()).then(Commands.literal("info").then(Commands.argument("message", MessageArgument.message()).executes(arguments -> {
					Level world = arguments.getSource().getUnsidedLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null && world instanceof ServerLevel _servLevel)
						entity = FakePlayerFactory.getMinecraft(_servLevel);
					Direction direction = Direction.DOWN;
					if (entity != null)
						direction = entity.getDirection();

					ErilogInfoProcedure.execute(arguments);
					return 0;
				}))).then(Commands.literal("warn").then(Commands.argument("message", MessageArgument.message()).executes(arguments -> {
					Level world = arguments.getSource().getUnsidedLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null && world instanceof ServerLevel _servLevel)
						entity = FakePlayerFactory.getMinecraft(_servLevel);
					Direction direction = Direction.DOWN;
					if (entity != null)
						direction = entity.getDirection();

					ErilogWarnProcedure.execute(arguments);
					return 0;
				}))).then(Commands.literal("error").then(Commands.argument("message", MessageArgument.message()).executes(arguments -> {
					Level world = arguments.getSource().getUnsidedLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null && world instanceof ServerLevel _servLevel)
						entity = FakePlayerFactory.getMinecraft(_servLevel);
					Direction direction = Direction.DOWN;
					if (entity != null)
						direction = entity.getDirection();

					ErilogErrorProcedure.execute(arguments);
					return 0;
				}))).then(Commands.literal("success").then(Commands.argument("message", MessageArgument.message()).executes(arguments -> {
					Level world = arguments.getSource().getUnsidedLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null && world instanceof ServerLevel _servLevel)
						entity = FakePlayerFactory.getMinecraft(_servLevel);
					Direction direction = Direction.DOWN;
					if (entity != null)
						direction = entity.getDirection();

					ErilogSuccessProcedure.execute(arguments);
					return 0;
				})))));
	}
}
