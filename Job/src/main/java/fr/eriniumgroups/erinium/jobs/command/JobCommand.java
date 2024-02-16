
package fr.eriniumgroups.erinium.jobs.command;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.common.util.FakePlayerFactory;

import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.commands.Commands;

import fr.eriniumgroups.erinium.jobs.procedures.OpenUnlockedGuiProcedure;
import fr.eriniumgroups.erinium.jobs.procedures.OpenEarnXpMakerProcedure;
import fr.eriniumgroups.erinium.jobs.procedures.OpenEarnXpGuiProcedure;
import fr.eriniumgroups.erinium.jobs.procedures.JobListProcedure;
import fr.eriniumgroups.erinium.jobs.procedures.CreateJobProcedure;

import com.mojang.brigadier.arguments.StringArgumentType;

@Mod.EventBusSubscriber
public class JobCommand {
	@SubscribeEvent
	public static void registerCommand(RegisterCommandsEvent event) {
		event.getDispatcher().register(Commands.literal("job")

				.then(Commands.literal("list").executes(arguments -> {
					ServerLevel world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null)
						entity = FakePlayerFactory.getMinecraft(world);
					Direction direction = entity.getDirection();

					JobListProcedure.execute(world, x, y, z, entity);
					return 0;
				})).then(Commands.literal("earnxp").then(Commands.argument("jobid", StringArgumentType.word()).executes(arguments -> {
					ServerLevel world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null)
						entity = FakePlayerFactory.getMinecraft(world);
					Direction direction = entity.getDirection();

					OpenEarnXpGuiProcedure.execute(world, x, y, z, arguments, entity);
					return 0;
				}))).then(Commands.literal("unlocked").then(Commands.argument("jobid", StringArgumentType.word()).executes(arguments -> {
					ServerLevel world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null)
						entity = FakePlayerFactory.getMinecraft(world);
					Direction direction = entity.getDirection();

					OpenUnlockedGuiProcedure.execute(world, x, y, z, arguments, entity);
					return 0;
				}))).then(Commands.literal("create").then(Commands.literal("job").then(Commands.argument("jobid", StringArgumentType.word()).then(Commands.argument("jobdisplayname", StringArgumentType.string()).executes(arguments -> {
					ServerLevel world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null)
						entity = FakePlayerFactory.getMinecraft(world);
					Direction direction = entity.getDirection();

					CreateJobProcedure.execute(arguments, entity);
					return 0;
				})))).then(Commands.literal("earnxp").executes(arguments -> {
					ServerLevel world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null)
						entity = FakePlayerFactory.getMinecraft(world);
					Direction direction = entity.getDirection();

					OpenEarnXpMakerProcedure.execute(world, x, y, z, entity);
					return 0;
				})).then(Commands.literal("required"))));
	}
}
