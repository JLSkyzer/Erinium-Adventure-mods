
package fr.eriniumgroups.erinium.factionmod.command;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.common.util.FakePlayerFactory;

import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.Commands;

import fr.eriniumgroups.erinium.factionmod.procedures.FactionStringInfoProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.FactionPromoteOnlineProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.FactionPromoteOfflineProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.FactionPlayerInfoProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.FactionPermProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.FactionMapProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.FactionLeaveProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.FactionKickOnlineProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.FactionKickOfflineProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.FactionJoinProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.FactionInviteProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.FactionInfoProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.FactionDisbandProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.FactionDemoteOnlineProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.FactionDemoteOfflineProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.FactionCreateProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.FUnclaimProcedure;
import fr.eriniumgroups.erinium.factionmod.procedures.FClaimProcedure;

import com.mojang.brigadier.arguments.StringArgumentType;

@Mod.EventBusSubscriber
public class FCommand {
	@SubscribeEvent
	public static void registerCommand(RegisterCommandsEvent event) {
		event.getDispatcher().register(Commands.literal("f")

				.then(Commands.literal("create").then(Commands.argument("id", StringArgumentType.word()).then(Commands.argument("displayname", StringArgumentType.string()).executes(arguments -> {
					ServerLevel world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null)
						entity = FakePlayerFactory.getMinecraft(world);
					Direction direction = entity.getDirection();

					FactionCreateProcedure.execute(world, arguments, entity);
					return 0;
				})))).then(Commands.literal("disband").executes(arguments -> {
					ServerLevel world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null)
						entity = FakePlayerFactory.getMinecraft(world);
					Direction direction = entity.getDirection();

					FactionDisbandProcedure.execute(world, entity);
					return 0;
				})).then(Commands.literal("info").executes(arguments -> {
					ServerLevel world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null)
						entity = FakePlayerFactory.getMinecraft(world);
					Direction direction = entity.getDirection();

					FactionInfoProcedure.execute(entity);
					return 0;
				}).then(Commands.literal("byname").then(Commands.argument("factionName", StringArgumentType.word()).executes(arguments -> {
					ServerLevel world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null)
						entity = FakePlayerFactory.getMinecraft(world);
					Direction direction = entity.getDirection();

					FactionStringInfoProcedure.execute(arguments, entity);
					return 0;
				}))).then(Commands.literal("byplayer").then(Commands.argument("player", EntityArgument.player()).executes(arguments -> {
					ServerLevel world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null)
						entity = FakePlayerFactory.getMinecraft(world);
					Direction direction = entity.getDirection();

					FactionPlayerInfoProcedure.execute(arguments, entity);
					return 0;
				})))).then(Commands.literal("perm").executes(arguments -> {
					ServerLevel world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null)
						entity = FakePlayerFactory.getMinecraft(world);
					Direction direction = entity.getDirection();

					FactionPermProcedure.execute(world, x, y, z, entity);
					return 0;
				})).then(Commands.literal("invite").then(Commands.argument("player", EntityArgument.player()).executes(arguments -> {
					ServerLevel world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null)
						entity = FakePlayerFactory.getMinecraft(world);
					Direction direction = entity.getDirection();

					FactionInviteProcedure.execute(arguments, entity);
					return 0;
				}))).then(Commands.literal("join").then(Commands.argument("factionName", StringArgumentType.word()).executes(arguments -> {
					ServerLevel world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null)
						entity = FakePlayerFactory.getMinecraft(world);
					Direction direction = entity.getDirection();

					FactionJoinProcedure.execute(arguments, entity);
					return 0;
				}))).then(Commands.literal("leave").executes(arguments -> {
					ServerLevel world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null)
						entity = FakePlayerFactory.getMinecraft(world);
					Direction direction = entity.getDirection();

					FactionLeaveProcedure.execute(entity);
					return 0;
				})).then(Commands.literal("kick").then(Commands.literal("onlineplayer").then(Commands.argument("player", EntityArgument.player()).executes(arguments -> {
					ServerLevel world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null)
						entity = FakePlayerFactory.getMinecraft(world);
					Direction direction = entity.getDirection();

					FactionKickOnlineProcedure.execute(arguments, entity);
					return 0;
				}))).then(Commands.literal("offlineplayer").then(Commands.argument("player", StringArgumentType.word()).executes(arguments -> {
					ServerLevel world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null)
						entity = FakePlayerFactory.getMinecraft(world);
					Direction direction = entity.getDirection();

					FactionKickOfflineProcedure.execute(world, arguments, entity);
					return 0;
				})))).then(Commands.literal("promote").then(Commands.literal("onlineplayer").then(Commands.argument("player", EntityArgument.player()).executes(arguments -> {
					ServerLevel world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null)
						entity = FakePlayerFactory.getMinecraft(world);
					Direction direction = entity.getDirection();

					FactionPromoteOnlineProcedure.execute(arguments, entity);
					return 0;
				}))).then(Commands.literal("offlineplayer").then(Commands.argument("player", StringArgumentType.word()).executes(arguments -> {
					ServerLevel world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null)
						entity = FakePlayerFactory.getMinecraft(world);
					Direction direction = entity.getDirection();

					FactionPromoteOfflineProcedure.execute(world, arguments, entity);
					return 0;
				})))).then(Commands.literal("demote").then(Commands.literal("onlineplayer").then(Commands.argument("player", EntityArgument.player()).executes(arguments -> {
					ServerLevel world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null)
						entity = FakePlayerFactory.getMinecraft(world);
					Direction direction = entity.getDirection();

					FactionDemoteOnlineProcedure.execute(arguments, entity);
					return 0;
				}))).then(Commands.literal("offlineplayer").then(Commands.argument("player", StringArgumentType.word()).executes(arguments -> {
					ServerLevel world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null)
						entity = FakePlayerFactory.getMinecraft(world);
					Direction direction = entity.getDirection();

					FactionDemoteOfflineProcedure.execute(world, arguments, entity);
					return 0;
				})))).then(Commands.literal("claim").executes(arguments -> {
					ServerLevel world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null)
						entity = FakePlayerFactory.getMinecraft(world);
					Direction direction = entity.getDirection();

					FClaimProcedure.execute(world, entity);
					return 0;
				})).then(Commands.literal("unclaim").executes(arguments -> {
					ServerLevel world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null)
						entity = FakePlayerFactory.getMinecraft(world);
					Direction direction = entity.getDirection();

					FUnclaimProcedure.execute(world, entity);
					return 0;
				})).then(Commands.literal("map").executes(arguments -> {
					ServerLevel world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null)
						entity = FakePlayerFactory.getMinecraft(world);
					Direction direction = entity.getDirection();

					FactionMapProcedure.execute(world, entity);
					return 0;
				})));
	}
}
