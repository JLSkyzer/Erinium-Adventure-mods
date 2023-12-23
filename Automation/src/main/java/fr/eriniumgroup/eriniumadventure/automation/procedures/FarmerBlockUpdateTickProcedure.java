package fr.eriniumgroup.eriniumadventure.automation.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.tags.BlockTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicInteger;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import com.google.gson.JsonObject;
import com.google.gson.Gson;

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
		modid = ((ForgeRegistries.BLOCKS.getKey((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock()).toString())).split(":")[0];
		blockid = ((ForgeRegistries.BLOCKS.getKey((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock()).toString())).split(":")[1];
		file = new File((FMLPaths.GAMEDIR.get().toString() + "/config/eriniumAutomation/Farmer/" + modid + "/"), File.separator + (blockid + ".json"));
		if (file.exists()) {
			finish_loop = false;
			finish_loop_2 = false;
			if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).is(BlockTags.create(new ResourceLocation("minecraft:crops")))
					|| (world.getBlockState(BlockPos.containing(x, y + 1, z))).is(BlockTags.create(new ResourceLocation("forge:crops")))) {
				if (((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock().getStateDefinition().getProperty("age") instanceof IntegerProperty _getip14
						? (world.getBlockState(BlockPos.containing(x, y + 1, z))).getValue(_getip14)
						: -1) == 7) {
					{
						try {
							BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
							StringBuilder jsonstringbuilder = new StringBuilder();
							String line;
							while ((line = bufferedReader.readLine()) != null) {
								jsonstringbuilder.append(line);
							}
							bufferedReader.close();
							JsonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
							result_rdm = Mth.nextInt(RandomSource.create(), (int) JsonObject.get("result_min").getAsDouble(), (int) JsonObject.get("result_max").getAsDouble());
							seeds_rdm = Mth.nextInt(RandomSource.create(), (int) JsonObject.get("seed_min").getAsDouble(), (int) JsonObject.get("seed_max").getAsDouble());
							while_nbr = 0;
							while_nbr_2 = 3;
							while (while_nbr <= 2) {
								if (((new Object() {
									public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
										AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
										BlockEntity _ent = world.getBlockEntity(pos);
										if (_ent != null)
											_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
										return _retval.get();
									}
								}.getItemStack(world, BlockPos.containing(x, y, z), (int) while_nbr)).getItem() == ForgeRegistries.ITEMS.getValue(new ResourceLocation((JsonObject.get("seed").getAsString()).toLowerCase(java.util.Locale.ENGLISH)))
										|| (new Object() {
											public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												BlockEntity _ent = world.getBlockEntity(pos);
												if (_ent != null)
													_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
												return _retval.get();
											}
										}.getItemStack(world, BlockPos.containing(x, y, z), (int) while_nbr)).getItem() == Blocks.AIR.asItem()) && new Object() {
											public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
												AtomicInteger _retval = new AtomicInteger(0);
												BlockEntity _ent = world.getBlockEntity(pos);
												if (_ent != null)
													_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
												return _retval.get();
											}
										}.getAmount(world, BlockPos.containing(x, y, z), (int) while_nbr) + seeds_rdm <= 64) {
									{
										BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
										if (_ent != null) {
											final int _slotid = (int) while_nbr;
											final ItemStack _setstack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation((JsonObject.get("seed").getAsString()).toLowerCase(java.util.Locale.ENGLISH))));
											_setstack.setCount((int) (new Object() {
												public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
													AtomicInteger _retval = new AtomicInteger(0);
													BlockEntity _ent = world.getBlockEntity(pos);
													if (_ent != null)
														_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
													return _retval.get();
												}
											}.getAmount(world, BlockPos.containing(x, y, z), (int) while_nbr) + seeds_rdm));
											_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
												if (capability instanceof IItemHandlerModifiable)
													((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
											});
										}
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
										AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
										BlockEntity _ent = world.getBlockEntity(pos);
										if (_ent != null)
											_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
										return _retval.get();
									}
								}.getItemStack(world, BlockPos.containing(x, y, z), (int) while_nbr_2)).getItem() == ForgeRegistries.ITEMS.getValue(new ResourceLocation((JsonObject.get("result").getAsString()).toLowerCase(java.util.Locale.ENGLISH)))
										|| (new Object() {
											public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
												AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
												BlockEntity _ent = world.getBlockEntity(pos);
												if (_ent != null)
													_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
												return _retval.get();
											}
										}.getItemStack(world, BlockPos.containing(x, y, z), (int) while_nbr_2)).getItem() == Blocks.AIR.asItem()) && new Object() {
											public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
												AtomicInteger _retval = new AtomicInteger(0);
												BlockEntity _ent = world.getBlockEntity(pos);
												if (_ent != null)
													_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
												return _retval.get();
											}
										}.getAmount(world, BlockPos.containing(x, y, z), (int) while_nbr_2) + result_rdm <= 64) {
									{
										BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
										if (_ent != null) {
											final int _slotid = (int) while_nbr_2;
											final ItemStack _setstack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation((JsonObject.get("result").getAsString()).toLowerCase(java.util.Locale.ENGLISH))));
											_setstack.setCount((int) (new Object() {
												public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
													AtomicInteger _retval = new AtomicInteger(0);
													BlockEntity _ent = world.getBlockEntity(pos);
													if (_ent != null)
														_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
													return _retval.get();
												}
											}.getAmount(world, BlockPos.containing(x, y, z), (int) while_nbr_2) + result_rdm));
											_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
												if (capability instanceof IItemHandlerModifiable)
													((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
											});
										}
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
												new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation((JsonObject.get("seed").getAsString()).toLowerCase(java.util.Locale.ENGLISH)))));
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
												new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation((JsonObject.get("result").getAsString()).toLowerCase(java.util.Locale.ENGLISH)))));
										entityToSpawn.setPickUpDelay(10);
										_level.addFreshEntity(entityToSpawn);
									}
								}
								finish_loop_2 = true;
							}
							world.setBlock(BlockPos.containing(x, y + 1, z), ForgeRegistries.BLOCKS.getValue(new ResourceLocation((JsonObject.get("replaced_block").getAsString()).toLowerCase(java.util.Locale.ENGLISH))).defaultBlockState(), 3);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			} else {
				{
					try {
						BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
						StringBuilder jsonstringbuilder = new StringBuilder();
						String line;
						while ((line = bufferedReader.readLine()) != null) {
							jsonstringbuilder.append(line);
						}
						bufferedReader.close();
						JsonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
						result_rdm = Mth.nextInt(RandomSource.create(), (int) JsonObject.get("result_min").getAsDouble(), (int) JsonObject.get("result_max").getAsDouble());
						seeds_rdm = Mth.nextInt(RandomSource.create(), (int) JsonObject.get("seed_min").getAsDouble(), (int) JsonObject.get("seed_max").getAsDouble());
						while_nbr = 0;
						while_nbr_2 = 3;
						while (while_nbr <= 2) {
							if (((new Object() {
								public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
									AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
									BlockEntity _ent = world.getBlockEntity(pos);
									if (_ent != null)
										_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
									return _retval.get();
								}
							}.getItemStack(world, BlockPos.containing(x, y, z), (int) while_nbr)).getItem() == ForgeRegistries.ITEMS.getValue(new ResourceLocation((JsonObject.get("seed").getAsString()).toLowerCase(java.util.Locale.ENGLISH)))
									|| (new Object() {
										public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
											AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
											BlockEntity _ent = world.getBlockEntity(pos);
											if (_ent != null)
												_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
											return _retval.get();
										}
									}.getItemStack(world, BlockPos.containing(x, y, z), (int) while_nbr)).getItem() == Blocks.AIR.asItem()) && new Object() {
										public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
											AtomicInteger _retval = new AtomicInteger(0);
											BlockEntity _ent = world.getBlockEntity(pos);
											if (_ent != null)
												_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
											return _retval.get();
										}
									}.getAmount(world, BlockPos.containing(x, y, z), (int) while_nbr) + seeds_rdm <= 64) {
								{
									BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
									if (_ent != null) {
										final int _slotid = (int) while_nbr;
										final ItemStack _setstack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation((JsonObject.get("seed").getAsString()).toLowerCase(java.util.Locale.ENGLISH))));
										_setstack.setCount((int) (new Object() {
											public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
												AtomicInteger _retval = new AtomicInteger(0);
												BlockEntity _ent = world.getBlockEntity(pos);
												if (_ent != null)
													_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
												return _retval.get();
											}
										}.getAmount(world, BlockPos.containing(x, y, z), (int) while_nbr) + seeds_rdm));
										_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
											if (capability instanceof IItemHandlerModifiable)
												((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
										});
									}
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
									AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
									BlockEntity _ent = world.getBlockEntity(pos);
									if (_ent != null)
										_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
									return _retval.get();
								}
							}.getItemStack(world, BlockPos.containing(x, y, z), (int) while_nbr_2)).getItem() == ForgeRegistries.ITEMS.getValue(new ResourceLocation((JsonObject.get("result").getAsString()).toLowerCase(java.util.Locale.ENGLISH)))
									|| (new Object() {
										public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
											AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
											BlockEntity _ent = world.getBlockEntity(pos);
											if (_ent != null)
												_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).copy()));
											return _retval.get();
										}
									}.getItemStack(world, BlockPos.containing(x, y, z), (int) while_nbr_2)).getItem() == Blocks.AIR.asItem()) && new Object() {
										public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
											AtomicInteger _retval = new AtomicInteger(0);
											BlockEntity _ent = world.getBlockEntity(pos);
											if (_ent != null)
												_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
											return _retval.get();
										}
									}.getAmount(world, BlockPos.containing(x, y, z), (int) while_nbr_2) + result_rdm <= 64) {
								{
									BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
									if (_ent != null) {
										final int _slotid = (int) while_nbr_2;
										final ItemStack _setstack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation((JsonObject.get("result").getAsString()).toLowerCase(java.util.Locale.ENGLISH))));
										_setstack.setCount((int) (new Object() {
											public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
												AtomicInteger _retval = new AtomicInteger(0);
												BlockEntity _ent = world.getBlockEntity(pos);
												if (_ent != null)
													_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> _retval.set(capability.getStackInSlot(slotid).getCount()));
												return _retval.get();
											}
										}.getAmount(world, BlockPos.containing(x, y, z), (int) while_nbr_2) + result_rdm));
										_ent.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
											if (capability instanceof IItemHandlerModifiable)
												((IItemHandlerModifiable) capability).setStackInSlot(_slotid, _setstack);
										});
									}
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
									ItemEntity entityToSpawn = new ItemEntity(_level, x, (y + 1), z, new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation((JsonObject.get("seed").getAsString()).toLowerCase(java.util.Locale.ENGLISH)))));
									entityToSpawn.setPickUpDelay(10);
									_level.addFreshEntity(entityToSpawn);
								}
							}
							finish_loop = true;
						}
						if (!finish_loop_2) {
							for (int index7 = 0; index7 < (int) result_rdm; index7++) {
								if (world instanceof ServerLevel _level) {
									ItemEntity entityToSpawn = new ItemEntity(_level, x, (y + 1), z, new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation((JsonObject.get("result").getAsString()).toLowerCase(java.util.Locale.ENGLISH)))));
									entityToSpawn.setPickUpDelay(10);
									_level.addFreshEntity(entityToSpawn);
								}
							}
							finish_loop_2 = true;
						}
						world.setBlock(BlockPos.containing(x, y + 1, z), ForgeRegistries.BLOCKS.getValue(new ResourceLocation((JsonObject.get("replaced_block").getAsString()).toLowerCase(java.util.Locale.ENGLISH))).defaultBlockState(), 3);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
