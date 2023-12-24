package fr.eriniumgroup.eriniumadventure.automation.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import java.util.HashMap;

import fr.eriniumgroup.eriniumadventure.automation.world.inventory.OneBlockCropGuiMenu;
import fr.eriniumgroup.eriniumadventure.automation.network.OneBlockCropGuiButtonMessage;
import fr.eriniumgroup.eriniumadventure.automation.EriniumAutomationMod;

import com.mojang.blaze3d.systems.RenderSystem;

public class OneBlockCropGuiScreen extends AbstractContainerScreen<OneBlockCropGuiMenu> {
	private final static HashMap<String, Object> guistate = OneBlockCropGuiMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox id;
	EditBox result_min;
	EditBox result_max;
	EditBox seed_min;
	EditBox seed_max;
	Button button_validate;

	public OneBlockCropGuiScreen(OneBlockCropGuiMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 220;
	}

	private static final ResourceLocation texture = new ResourceLocation("erinium_automation:textures/screens/one_block_crop_gui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		id.render(guiGraphics, mouseX, mouseY, partialTicks);
		result_min.render(guiGraphics, mouseX, mouseY, partialTicks);
		result_max.render(guiGraphics, mouseX, mouseY, partialTicks);
		seed_min.render(guiGraphics, mouseX, mouseY, partialTicks);
		seed_max.render(guiGraphics, mouseX, mouseY, partialTicks);
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
		if (id.isFocused())
			return id.keyPressed(key, b, c);
		if (result_min.isFocused())
			return result_min.keyPressed(key, b, c);
		if (result_max.isFocused())
			return result_max.keyPressed(key, b, c);
		if (seed_min.isFocused())
			return seed_min.keyPressed(key, b, c);
		if (seed_max.isFocused())
			return seed_max.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		id.tick();
		result_min.tick();
		result_max.tick();
		seed_min.tick();
		seed_max.tick();
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.erinium_automation.one_block_crop_gui.label_one_block_crop_configurator"), 15, 7, -16777216, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.erinium_automation.one_block_crop_gui.label_crop_block"), 60, 25, -16777216, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.erinium_automation.one_block_crop_gui.label_result_item"), 6, 61, -16777216, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.erinium_automation.one_block_crop_gui.label_seeds_item"), 114, 61, -16777216, false);
	}

	@Override
	public void onClose() {
		super.onClose();
	}

	@Override
	public void init() {
		super.init();
		id = new EditBox(this.font, this.leftPos + 24, this.topPos + 34, 126, 20, Component.translatable("gui.erinium_automation.one_block_crop_gui.id")) {
			{
				setSuggestion(Component.translatable("gui.erinium_automation.one_block_crop_gui.id").getString());
			}

			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.erinium_automation.one_block_crop_gui.id").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos) {
				super.moveCursorTo(pos);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.erinium_automation.one_block_crop_gui.id").getString());
				else
					setSuggestion(null);
			}
		};
		id.setMaxLength(32767);
		guistate.put("text:id", id);
		this.addWidget(this.id);
		result_min = new EditBox(this.font, this.leftPos + 6, this.topPos + 88, 54, 20, Component.translatable("gui.erinium_automation.one_block_crop_gui.result_min")) {
			{
				setSuggestion(Component.translatable("gui.erinium_automation.one_block_crop_gui.result_min").getString());
			}

			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.erinium_automation.one_block_crop_gui.result_min").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos) {
				super.moveCursorTo(pos);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.erinium_automation.one_block_crop_gui.result_min").getString());
				else
					setSuggestion(null);
			}
		};
		result_min.setMaxLength(32767);
		guistate.put("text:result_min", result_min);
		this.addWidget(this.result_min);
		result_max = new EditBox(this.font, this.leftPos + 6, this.topPos + 106, 54, 20, Component.translatable("gui.erinium_automation.one_block_crop_gui.result_max")) {
			{
				setSuggestion(Component.translatable("gui.erinium_automation.one_block_crop_gui.result_max").getString());
			}

			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.erinium_automation.one_block_crop_gui.result_max").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos) {
				super.moveCursorTo(pos);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.erinium_automation.one_block_crop_gui.result_max").getString());
				else
					setSuggestion(null);
			}
		};
		result_max.setMaxLength(32767);
		guistate.put("text:result_max", result_max);
		this.addWidget(this.result_max);
		seed_min = new EditBox(this.font, this.leftPos + 114, this.topPos + 88, 54, 20, Component.translatable("gui.erinium_automation.one_block_crop_gui.seed_min")) {
			{
				setSuggestion(Component.translatable("gui.erinium_automation.one_block_crop_gui.seed_min").getString());
			}

			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.erinium_automation.one_block_crop_gui.seed_min").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos) {
				super.moveCursorTo(pos);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.erinium_automation.one_block_crop_gui.seed_min").getString());
				else
					setSuggestion(null);
			}
		};
		seed_min.setMaxLength(32767);
		guistate.put("text:seed_min", seed_min);
		this.addWidget(this.seed_min);
		seed_max = new EditBox(this.font, this.leftPos + 114, this.topPos + 106, 54, 20, Component.translatable("gui.erinium_automation.one_block_crop_gui.seed_max")) {
			{
				setSuggestion(Component.translatable("gui.erinium_automation.one_block_crop_gui.seed_max").getString());
			}

			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.erinium_automation.one_block_crop_gui.seed_max").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos) {
				super.moveCursorTo(pos);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.erinium_automation.one_block_crop_gui.seed_max").getString());
				else
					setSuggestion(null);
			}
		};
		seed_max.setMaxLength(32767);
		guistate.put("text:seed_max", seed_max);
		this.addWidget(this.seed_max);
		button_validate = Button.builder(Component.translatable("gui.erinium_automation.one_block_crop_gui.button_validate"), e -> {
			if (true) {
				EriniumAutomationMod.PACKET_HANDLER.sendToServer(new OneBlockCropGuiButtonMessage(0, x, y, z));
				OneBlockCropGuiButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 60, this.topPos + 106, 54, 20).build();
		guistate.put("button:button_validate", button_validate);
		this.addRenderableWidget(button_validate);
	}
}
