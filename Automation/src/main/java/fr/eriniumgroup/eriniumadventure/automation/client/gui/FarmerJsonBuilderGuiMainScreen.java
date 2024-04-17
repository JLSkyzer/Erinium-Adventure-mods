package fr.eriniumgroup.eriniumadventure.automation.client.gui;

import net.neoforged.neoforge.network.PacketDistributor;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import java.util.HashMap;

import fr.eriniumgroup.eriniumadventure.automation.world.inventory.FarmerJsonBuilderGuiMainMenu;
import fr.eriniumgroup.eriniumadventure.automation.network.FarmerJsonBuilderGuiMainButtonMessage;

import com.mojang.blaze3d.systems.RenderSystem;

public class FarmerJsonBuilderGuiMainScreen extends AbstractContainerScreen<FarmerJsonBuilderGuiMainMenu> {
	private final static HashMap<String, Object> guistate = FarmerJsonBuilderGuiMainMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_one_block_crop;
	Button button_multiple_blocks_crop;

	public FarmerJsonBuilderGuiMainScreen(FarmerJsonBuilderGuiMainMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("erinium_automation:textures/screens/farmer_json_builder_gui_main.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.erinium_automation.farmer_json_builder_gui_main.label_choose_your_block"), 42, 7, -16777216, false);
	}

	@Override
	public void init() {
		super.init();
		button_one_block_crop = Button.builder(Component.translatable("gui.erinium_automation.farmer_json_builder_gui_main.button_one_block_crop"), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new FarmerJsonBuilderGuiMainButtonMessage(0, x, y, z));
				FarmerJsonBuilderGuiMainButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 33, this.topPos + 25, 108, 20).build();
		guistate.put("button:button_one_block_crop", button_one_block_crop);
		this.addRenderableWidget(button_one_block_crop);
		button_multiple_blocks_crop = Button.builder(Component.translatable("gui.erinium_automation.farmer_json_builder_gui_main.button_multiple_blocks_crop"), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new FarmerJsonBuilderGuiMainButtonMessage(1, x, y, z));
				FarmerJsonBuilderGuiMainButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 33, this.topPos + 52, 108, 20).build();
		guistate.put("button:button_multiple_blocks_crop", button_multiple_blocks_crop);
		this.addRenderableWidget(button_multiple_blocks_crop);
	}
}
