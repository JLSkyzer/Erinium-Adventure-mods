package fr.eriniumgroups.erinium.ericonomy.procedures;

import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import java.util.concurrent.atomic.AtomicReference;

import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import fr.eriniumgroups.erinium.ericonomy.init.EriconomyModItems;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

public class InsetAllMoneyProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		double totalMoney = 0;
		totalMoney = 0;
		{
			AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
			entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(_iitemhandlerref::set);
			if (_iitemhandlerref.get() != null) {
				for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
					ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
					if (itemstackiterator.getItem() == EriconomyModItems.PIECE_1_CENT.get()) {
						if (entity instanceof Player _player) {
							ItemStack _stktoremove = itemstackiterator;
							_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), itemstackiterator.getCount(), _player.inventoryMenu.getCraftSlots());
						}
						totalMoney = totalMoney + itemstackiterator.getCount() * 0.01;
					} else if (itemstackiterator.getItem() == EriconomyModItems.PIECE_5_CENT.get()) {
						if (entity instanceof Player _player) {
							ItemStack _stktoremove = itemstackiterator;
							_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), itemstackiterator.getCount(), _player.inventoryMenu.getCraftSlots());
						}
						totalMoney = totalMoney + itemstackiterator.getCount() * 0.05;
					} else if (itemstackiterator.getItem() == EriconomyModItems.PIECE_10_CENT.get()) {
						if (entity instanceof Player _player) {
							ItemStack _stktoremove = itemstackiterator;
							_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), itemstackiterator.getCount(), _player.inventoryMenu.getCraftSlots());
						}
						totalMoney = totalMoney + itemstackiterator.getCount() * 0.1;
					} else if (itemstackiterator.getItem() == EriconomyModItems.PIECE_20_CENT.get()) {
						if (entity instanceof Player _player) {
							ItemStack _stktoremove = itemstackiterator;
							_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), itemstackiterator.getCount(), _player.inventoryMenu.getCraftSlots());
						}
						totalMoney = totalMoney + itemstackiterator.getCount() * 0.2;
					} else if (itemstackiterator.getItem() == EriconomyModItems.PIECE_50_CENT.get()) {
						if (entity instanceof Player _player) {
							ItemStack _stktoremove = itemstackiterator;
							_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), itemstackiterator.getCount(), _player.inventoryMenu.getCraftSlots());
						}
						totalMoney = totalMoney + itemstackiterator.getCount() * 0.5;
					} else if (itemstackiterator.getItem() == EriconomyModItems.PIECE_1_DOLLARS.get()) {
						if (entity instanceof Player _player) {
							ItemStack _stktoremove = itemstackiterator;
							_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), itemstackiterator.getCount(), _player.inventoryMenu.getCraftSlots());
						}
						totalMoney = totalMoney + itemstackiterator.getCount() * 1;
					} else if (itemstackiterator.getItem() == EriconomyModItems.PIECE_2_DOLLARS.get()) {
						if (entity instanceof Player _player) {
							ItemStack _stktoremove = itemstackiterator;
							_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), itemstackiterator.getCount(), _player.inventoryMenu.getCraftSlots());
						}
						totalMoney = totalMoney + itemstackiterator.getCount() * 2;
					} else if (itemstackiterator.getItem() == EriconomyModItems.PIECE_5_DOLLARS.get()) {
						if (entity instanceof Player _player) {
							ItemStack _stktoremove = itemstackiterator;
							_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), itemstackiterator.getCount(), _player.inventoryMenu.getCraftSlots());
						}
						totalMoney = totalMoney + itemstackiterator.getCount() * 5;
					} else if (itemstackiterator.getItem() == EriconomyModItems.BILL_10_DOLLARS.get()) {
						if (entity instanceof Player _player) {
							ItemStack _stktoremove = itemstackiterator;
							_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), itemstackiterator.getCount(), _player.inventoryMenu.getCraftSlots());
						}
						totalMoney = totalMoney + itemstackiterator.getCount() * 10;
					} else if (itemstackiterator.getItem() == EriconomyModItems.BILLE_20_DOLLARS.get()) {
						if (entity instanceof Player _player) {
							ItemStack _stktoremove = itemstackiterator;
							_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), itemstackiterator.getCount(), _player.inventoryMenu.getCraftSlots());
						}
						totalMoney = totalMoney + itemstackiterator.getCount() * 20;
					} else if (itemstackiterator.getItem() == EriconomyModItems.BILL_50_DOLLARS.get()) {
						if (entity instanceof Player _player) {
							ItemStack _stktoremove = itemstackiterator;
							_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), itemstackiterator.getCount(), _player.inventoryMenu.getCraftSlots());
						}
						totalMoney = totalMoney + itemstackiterator.getCount() * 50;
					} else if (itemstackiterator.getItem() == EriconomyModItems.BILL_100_DOLLARS.get()) {
						if (entity instanceof Player _player) {
							ItemStack _stktoremove = itemstackiterator;
							_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), itemstackiterator.getCount(), _player.inventoryMenu.getCraftSlots());
						}
						totalMoney = totalMoney + itemstackiterator.getCount() * 100;
					} else if (itemstackiterator.getItem() == EriconomyModItems.BILL_200_DOLLARS.get()) {
						if (entity instanceof Player _player) {
							ItemStack _stktoremove = itemstackiterator;
							_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), itemstackiterator.getCount(), _player.inventoryMenu.getCraftSlots());
						}
						totalMoney = totalMoney + itemstackiterator.getCount() * 200;
					} else if (itemstackiterator.getItem() == EriconomyModItems.BILLE_500_DOLLARS.get()) {
						if (entity instanceof Player _player) {
							ItemStack _stktoremove = itemstackiterator;
							_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), itemstackiterator.getCount(), _player.inventoryMenu.getCraftSlots());
						}
						totalMoney = totalMoney + itemstackiterator.getCount() * 500;
					} else if (itemstackiterator.getItem() == EriconomyModItems.BILL_1000_DOLLARS.get()) {
						if (entity instanceof Player _player) {
							ItemStack _stktoremove = itemstackiterator;
							_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), itemstackiterator.getCount(), _player.inventoryMenu.getCraftSlots());
						}
						totalMoney = totalMoney + itemstackiterator.getCount() * 1000;
					}
				}
			}
		}
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(("\u00A7bEriconomy \u00A7f>> \u00A7a+" + new java.text.DecimalFormat("#,###.##").format(totalMoney) + "$")), false);
		if (ModList.get().isLoaded("ericonomy")) {
			// Procedure here
			java.io.File eriFile = new java.io.File("");
			com.google.gson.JsonObject eriJsonObject = new com.google.gson.JsonObject();
			eriFile = new File((FMLPaths.GAMEDIR.get().toString() + "/Ericonomy/accounts/"), File.separator + (entity.getUUID().toString() + ".json"));
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(eriFile));
				StringBuilder jsonstringbuilder = new StringBuilder();
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					jsonstringbuilder.append(line);
				}
				bufferedReader.close();
				eriJsonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
				eriJsonObject.addProperty("money", (eriJsonObject.get("money").getAsDouble() + totalMoney));
				{
					Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
					try {
						FileWriter fileWriter = new FileWriter(eriFile);
						fileWriter.write(mainGSONBuilderVariable.toJson(eriJsonObject));
						fileWriter.close();
					} catch (IOException exception) {
						exception.printStackTrace();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			if (ModList.get().isLoaded("erinium_logs")) {
				if (world instanceof ServerLevel _level)
					_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(0, 0, 0), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
							"erilog " + "GameLoading" + " " + "info" + " " + "Mod Ericonomy not exist");
			} else {
				System.out.println("Erilog is not installed ! install here : https://github.com/JLSkyzer/Erinium-Adventure-mods/releases/tag/Erilog");
			}
		}
	}
}
