package fr.eriniumgroup.eriniumadventure.automation.procedures;

import org.checkerframework.checker.units.qual.s;

import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.energy.IEnergyStorage;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.fml.loading.FMLPaths;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.tags.BlockTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

public class FarmerBlockUpdateTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		String modid = "";
		String blockid = "";
		File file = new File("");
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		double result_rdm = 0;
		double seeds_rdm = 0;
		double while_nbr = 0;
		double while_nbr_2 = 0;
		boolean finish_loop = false;
		boolean finish_loop_2 = false;
		modid = new Object() {
			private String split(String text, String space, int index) {
				String s = text.split(space)[index];
				return s;
			}
		}.split((BuiltInRegistries.BLOCK.getKey((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock()).toString()), ":", (int) 0);
		blockid = new Object() {
			private String split(String text, String space, int index) {
				String s = text.split(space)[index];
				return s;
			}
		}.split((BuiltInRegistries.BLOCK.getKey((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock()).toString()), ":", (int) 1);
		file = new File((FMLPaths.GAMEDIR.get().toString() + "/config/eriniumAutomation/Farmer/" + modid + "/"), File.separator + (blockid + ".json"));
		if (file.exists()) {
			finish_loop = false;
			finish_loop_2 = false;
			if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).is(BlockTags.create(new ResourceLocation("minecraft:crops"))) || (world.getBlockState(BlockPos.containing(x, y + 1, z))).is(BlockTags.create(new ResourceLocation("forge:crops")))
					|| (world.getBlockState(BlockPos.containing(x, y + 1, z))).is(BlockTags.create(new ResourceLocation("mysticalagriculture:crops")))) {
				if (((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock().getStateDefinition().getProperty("age") instanceof IntegerProperty _getip16
						? (world.getBlockState(BlockPos.containing(x, y + 1, z))).getValue(_getip16)
						: -1) == 7) {
					if (new Object() {
						public int getEnergyStored(LevelAccessor level, BlockPos pos) {
							if (level instanceof ILevelExtension _ext) {
								IEnergyStorage _entityStorage = _ext.getCapability(Capabilities.EnergyStorage.BLOCK, pos, null);
								if (_entityStorage != null)
									return _entityStorage.getEnergyStored();
							}
							return 0;
						}
					}.getEnergyStored(world, BlockPos.containing(x, y, z)) >= 10000) {
						if (world instanceof ILevelExtension _ext) {
							IEnergyStorage _entityStorage = _ext.getCapability(Capabilities.EnergyStorage.BLOCK, BlockPos.containing(x, y, z), null);
							if (_entityStorage != null)
								_entityStorage.extractEnergy(10000, false);
						}
						{
							try {
								BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
								StringBuilder jsonstringbuilder = new StringBuilder();
								String line;
								while ((line = bufferedReader.readLine()) != null) {
									jsonstringbuilder.append(line);
								}
								bufferedReader.close();
								JsonObject = new com.google.gson.Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
								result_rdm = Mth.nextInt(RandomSource.create(), (int) JsonObject.get("result_min").getAsDouble(), (int) JsonObject.get("result_max").getAsDouble());
								seeds_rdm = Mth.nextInt(RandomSource.create(), (int) JsonObject.get("seed_min").getAsDouble(), (int) JsonObject.get("seed_max").getAsDouble());
								while_nbr = 0;
								while_nbr_2 = 3;
								while (while_nbr <= 2) {
									if (((new Object() {
										public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
											if (world instanceof ILevelExtension _ext) {
												IItemHandler _itemHandler = _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
												if (_itemHandler != null)
													return _itemHandler.getStackInSlot(slotid).copy();
											}
											return ItemStack.EMPTY;
										}
									}.getItemStack(world, BlockPos.containing(x, y, z), (int) while_nbr)).getItem() == BuiltInRegistries.ITEM.get(new ResourceLocation((JsonObject.get("seed").getAsString()).toLowerCase(java.util.Locale.ENGLISH)))
											|| (new Object() {
												public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
													if (world instanceof ILevelExtension _ext) {
														IItemHandler _itemHandler = _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
														if (_itemHandler != null)
															return _itemHandler.getStackInSlot(slotid).copy();
													}
													return ItemStack.EMPTY;
												}
											}.getItemStack(world, BlockPos.containing(x, y, z), (int) while_nbr)).getItem() == Blocks.AIR.asItem()) && new Object() {
												public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
													if (world instanceof ILevelExtension _ext) {
														IItemHandler _itemHandler = _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
														if (_itemHandler != null)
															return _itemHandler.getStackInSlot(slotid).getCount();
													}
													return 0;
												}
											}.getAmount(world, BlockPos.containing(x, y, z), (int) while_nbr) + seeds_rdm <= 64) {
										if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
											ItemStack _setstack = new ItemStack(BuiltInRegistries.ITEM.get(new ResourceLocation((JsonObject.get("seed").getAsString()).toLowerCase(java.util.Locale.ENGLISH)))).copy();
											_setstack.setCount((int) (new Object() {
												public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
													if (world instanceof ILevelExtension _ext) {
														IItemHandler _itemHandler = _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
														if (_itemHandler != null)
															return _itemHandler.getStackInSlot(slotid).getCount();
													}
													return 0;
												}
											}.getAmount(world, BlockPos.containing(x, y, z), (int) while_nbr) + seeds_rdm));
											_itemHandlerModifiable.setStackInSlot((int) while_nbr, _setstack);
										}
										finish_loop = true;
										break;
									} else {
										while_nbr = while_nbr + 1;
									}
								}
								while (while_nbr_2 <= 5) {
									if (((new Object() {
										public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
											if (world instanceof ILevelExtension _ext) {
												IItemHandler _itemHandler = _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
												if (_itemHandler != null)
													return _itemHandler.getStackInSlot(slotid).copy();
											}
											return ItemStack.EMPTY;
										}
									}.getItemStack(world, BlockPos.containing(x, y, z), (int) while_nbr_2)).getItem() == BuiltInRegistries.ITEM.get(new ResourceLocation((JsonObject.get("result").getAsString()).toLowerCase(java.util.Locale.ENGLISH)))
											|| (new Object() {
												public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
													if (world instanceof ILevelExtension _ext) {
														IItemHandler _itemHandler = _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
														if (_itemHandler != null)
															return _itemHandler.getStackInSlot(slotid).copy();
													}
													return ItemStack.EMPTY;
												}
											}.getItemStack(world, BlockPos.containing(x, y, z), (int) while_nbr_2)).getItem() == Blocks.AIR.asItem()) && new Object() {
												public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
													if (world instanceof ILevelExtension _ext) {
														IItemHandler _itemHandler = _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
														if (_itemHandler != null)
															return _itemHandler.getStackInSlot(slotid).getCount();
													}
													return 0;
												}
											}.getAmount(world, BlockPos.containing(x, y, z), (int) while_nbr_2) + result_rdm <= 64) {
										if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
											ItemStack _setstack = new ItemStack(BuiltInRegistries.ITEM.get(new ResourceLocation((JsonObject.get("result").getAsString()).toLowerCase(java.util.Locale.ENGLISH)))).copy();
											_setstack.setCount((int) (new Object() {
												public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
													if (world instanceof ILevelExtension _ext) {
														IItemHandler _itemHandler = _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
														if (_itemHandler != null)
															return _itemHandler.getStackInSlot(slotid).getCount();
													}
													return 0;
												}
											}.getAmount(world, BlockPos.containing(x, y, z), (int) while_nbr_2) + result_rdm));
											_itemHandlerModifiable.setStackInSlot((int) while_nbr_2, _setstack);
										}
										finish_loop_2 = true;
										break;
									} else {
										while_nbr_2 = while_nbr_2 + 1;
									}
								}
								if (!finish_loop) {
									for (int index2 = 0; index2 < (int) seeds_rdm; index2++) {
										if (world instanceof ServerLevel _level) {
											ItemEntity entityToSpawn = new ItemEntity(_level, x, (y + 1), z,
													new ItemStack(BuiltInRegistries.ITEM.get(new ResourceLocation((JsonObject.get("seed").getAsString()).toLowerCase(java.util.Locale.ENGLISH)))));
											entityToSpawn.setPickUpDelay(10);
											_level.addFreshEntity(entityToSpawn);
										}
									}
									finish_loop = true;
								}
								if (!finish_loop_2) {
									for (int index3 = 0; index3 < (int) result_rdm; index3++) {
										if (world instanceof ServerLevel _level) {
											ItemEntity entityToSpawn = new ItemEntity(_level, x, (y + 1), z,
													new ItemStack(BuiltInRegistries.ITEM.get(new ResourceLocation((JsonObject.get("result").getAsString()).toLowerCase(java.util.Locale.ENGLISH)))));
											entityToSpawn.setPickUpDelay(10);
											_level.addFreshEntity(entityToSpawn);
										}
									}
									finish_loop_2 = true;
								}
								world.setBlock(BlockPos.containing(x, y + 1, z), BuiltInRegistries.BLOCK.get(new ResourceLocation((JsonObject.get("replaced_block").getAsString()).toLowerCase(java.util.Locale.ENGLISH))).defaultBlockState(), 3);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			} else {
				if (new Object() {
					public int getEnergyStored(LevelAccessor level, BlockPos pos) {
						if (level instanceof ILevelExtension _ext) {
							IEnergyStorage _entityStorage = _ext.getCapability(Capabilities.EnergyStorage.BLOCK, pos, null);
							if (_entityStorage != null)
								return _entityStorage.getEnergyStored();
						}
						return 0;
					}
				}.getEnergyStored(world, BlockPos.containing(x, y, z)) >= 10000) {
					if (world instanceof ILevelExtension _ext) {
						IEnergyStorage _entityStorage = _ext.getCapability(Capabilities.EnergyStorage.BLOCK, BlockPos.containing(x, y, z), null);
						if (_entityStorage != null)
							_entityStorage.extractEnergy(10000, false);
					}
					{
						try {
							BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
							StringBuilder jsonstringbuilder = new StringBuilder();
							String line;
							while ((line = bufferedReader.readLine()) != null) {
								jsonstringbuilder.append(line);
							}
							bufferedReader.close();
							JsonObject = new com.google.gson.Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
							result_rdm = Mth.nextInt(RandomSource.create(), (int) JsonObject.get("result_min").getAsDouble(), (int) JsonObject.get("result_max").getAsDouble());
							seeds_rdm = Mth.nextInt(RandomSource.create(), (int) JsonObject.get("seed_min").getAsDouble(), (int) JsonObject.get("seed_max").getAsDouble());
							while_nbr = 0;
							while_nbr_2 = 3;
							while (while_nbr <= 2) {
								if (((new Object() {
									public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
										if (world instanceof ILevelExtension _ext) {
											IItemHandler _itemHandler = _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
											if (_itemHandler != null)
												return _itemHandler.getStackInSlot(slotid).copy();
										}
										return ItemStack.EMPTY;
									}
								}.getItemStack(world, BlockPos.containing(x, y, z), (int) while_nbr)).getItem() == BuiltInRegistries.ITEM.get(new ResourceLocation((JsonObject.get("seed").getAsString()).toLowerCase(java.util.Locale.ENGLISH)))
										|| (new Object() {
											public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
												if (world instanceof ILevelExtension _ext) {
													IItemHandler _itemHandler = _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
													if (_itemHandler != null)
														return _itemHandler.getStackInSlot(slotid).copy();
												}
												return ItemStack.EMPTY;
											}
										}.getItemStack(world, BlockPos.containing(x, y, z), (int) while_nbr)).getItem() == Blocks.AIR.asItem()) && new Object() {
											public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
												if (world instanceof ILevelExtension _ext) {
													IItemHandler _itemHandler = _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
													if (_itemHandler != null)
														return _itemHandler.getStackInSlot(slotid).getCount();
												}
												return 0;
											}
										}.getAmount(world, BlockPos.containing(x, y, z), (int) while_nbr) + seeds_rdm <= 64) {
									if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
										ItemStack _setstack = new ItemStack(BuiltInRegistries.ITEM.get(new ResourceLocation((JsonObject.get("seed").getAsString()).toLowerCase(java.util.Locale.ENGLISH)))).copy();
										_setstack.setCount((int) (new Object() {
											public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
												if (world instanceof ILevelExtension _ext) {
													IItemHandler _itemHandler = _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
													if (_itemHandler != null)
														return _itemHandler.getStackInSlot(slotid).getCount();
												}
												return 0;
											}
										}.getAmount(world, BlockPos.containing(x, y, z), (int) while_nbr) + seeds_rdm));
										_itemHandlerModifiable.setStackInSlot((int) while_nbr, _setstack);
									}
									finish_loop = true;
									break;
								} else {
									while_nbr = while_nbr + 1;
								}
							}
							while (while_nbr_2 <= 5) {
								if (((new Object() {
									public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
										if (world instanceof ILevelExtension _ext) {
											IItemHandler _itemHandler = _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
											if (_itemHandler != null)
												return _itemHandler.getStackInSlot(slotid).copy();
										}
										return ItemStack.EMPTY;
									}
								}.getItemStack(world, BlockPos.containing(x, y, z), (int) while_nbr_2)).getItem() == BuiltInRegistries.ITEM.get(new ResourceLocation((JsonObject.get("result").getAsString()).toLowerCase(java.util.Locale.ENGLISH)))
										|| (new Object() {
											public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
												if (world instanceof ILevelExtension _ext) {
													IItemHandler _itemHandler = _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
													if (_itemHandler != null)
														return _itemHandler.getStackInSlot(slotid).copy();
												}
												return ItemStack.EMPTY;
											}
										}.getItemStack(world, BlockPos.containing(x, y, z), (int) while_nbr_2)).getItem() == Blocks.AIR.asItem()) && new Object() {
											public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
												if (world instanceof ILevelExtension _ext) {
													IItemHandler _itemHandler = _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
													if (_itemHandler != null)
														return _itemHandler.getStackInSlot(slotid).getCount();
												}
												return 0;
											}
										}.getAmount(world, BlockPos.containing(x, y, z), (int) while_nbr_2) + result_rdm <= 64) {
									if (world instanceof ILevelExtension _ext && _ext.getCapability(Capabilities.ItemHandler.BLOCK, BlockPos.containing(x, y, z), null) instanceof IItemHandlerModifiable _itemHandlerModifiable) {
										ItemStack _setstack = new ItemStack(BuiltInRegistries.ITEM.get(new ResourceLocation((JsonObject.get("result").getAsString()).toLowerCase(java.util.Locale.ENGLISH)))).copy();
										_setstack.setCount((int) (new Object() {
											public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
												if (world instanceof ILevelExtension _ext) {
													IItemHandler _itemHandler = _ext.getCapability(Capabilities.ItemHandler.BLOCK, pos, null);
													if (_itemHandler != null)
														return _itemHandler.getStackInSlot(slotid).getCount();
												}
												return 0;
											}
										}.getAmount(world, BlockPos.containing(x, y, z), (int) while_nbr_2) + result_rdm));
										_itemHandlerModifiable.setStackInSlot((int) while_nbr_2, _setstack);
									}
									finish_loop_2 = true;
									break;
								} else {
									while_nbr_2 = while_nbr_2 + 1;
								}
							}
							if (!finish_loop) {
								for (int index6 = 0; index6 < (int) seeds_rdm; index6++) {
									if (world instanceof ServerLevel _level) {
										ItemEntity entityToSpawn = new ItemEntity(_level, x, (y + 1), z, new ItemStack(BuiltInRegistries.ITEM.get(new ResourceLocation((JsonObject.get("seed").getAsString()).toLowerCase(java.util.Locale.ENGLISH)))));
										entityToSpawn.setPickUpDelay(10);
										_level.addFreshEntity(entityToSpawn);
									}
								}
								finish_loop = true;
							}
							if (!finish_loop_2) {
								for (int index7 = 0; index7 < (int) result_rdm; index7++) {
									if (world instanceof ServerLevel _level) {
										ItemEntity entityToSpawn = new ItemEntity(_level, x, (y + 1), z, new ItemStack(BuiltInRegistries.ITEM.get(new ResourceLocation((JsonObject.get("result").getAsString()).toLowerCase(java.util.Locale.ENGLISH)))));
										entityToSpawn.setPickUpDelay(10);
										_level.addFreshEntity(entityToSpawn);
									}
								}
								finish_loop_2 = true;
							}
							world.setBlock(BlockPos.containing(x, y + 1, z), BuiltInRegistries.BLOCK.get(new ResourceLocation((JsonObject.get("replaced_block").getAsString()).toLowerCase(java.util.Locale.ENGLISH))).defaultBlockState(), 3);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
}
