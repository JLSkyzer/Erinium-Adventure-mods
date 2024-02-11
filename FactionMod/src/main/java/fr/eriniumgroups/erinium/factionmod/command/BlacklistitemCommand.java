
package fr.eriniumgroups.erinium.factionmod.command;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.common.util.FakePlayerFactory;

import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.commands.arguments.item.ItemArgument;
import net.minecraft.commands.Commands;

import fr.eriniumgroups.erinium.factionmod.procedures.OpenBlacklistItemGuiProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.BlackListItemRemoveProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.BlackListItemAddProcedure;

@Mod.EventBusSubscriber
public class BlacklistitemCommand {
	@SubscribeEvent
	public static void registerCommand(RegisterCommandsEvent event) {
		event.getDispatcher().register(Commands.literal("blacklistitem")

				.then(Commands.literal("add").then(Commands.argument("item", ItemArgument.item(event.getBuildContext())).executes(arguments -> {
					ServerLevel world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null)
						entity = FakePlayerFactory.getMinecraft(world);
					Direction direction = entity.getDirection();

					BlackListItemAddProcedure.execute(arguments, entity);
					return 0;
				}))).then(Commands.literal("remove").then(Commands.argument("item", ItemArgument.item(event.getBuildContext())).executes(arguments -> {
					ServerLevel world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null)
						entity = FakePlayerFactory.getMinecraft(world);
					Direction direction = entity.getDirection();

					BlackListItemRemoveProcedure.execute(arguments, entity);
					return 0;
				}))).then(Commands.literal("list").executes(arguments -> {
					ServerLevel world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null)
						entity = FakePlayerFactory.getMinecraft(world);
					Direction direction = entity.getDirection();

					OpenBlacklistItemGuiProcedure.execute(world, x, y, z, entity);
					return 0;
				})));
	}
}
