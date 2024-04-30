package fr.eriniumgroups.erinium.jobs.client.gui;

import net.neoforged.neoforge.network.PacketDistributor;

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

import fr.eriniumgroups.erinium.jobs.world.inventory.RequiredMakerMenu;
import fr.eriniumgroups.erinium.jobs.network.RequiredMakerButtonMessage;

import com.mojang.blaze3d.systems.RenderSystem;

public class RequiredMakerScreen extends AbstractContainerScreen<RequiredMakerMenu> {
	private final static HashMap<String, Object> guistate = RequiredMakerMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox job_id;
	EditBox level;
	Button button_validate;

	public RequiredMakerScreen(RequiredMakerMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 226;
	}

	private static final ResourceLocation texture = new ResourceLocation("eriniumjobs:textures/screens/required_maker.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		job_id.render(guiGraphics, mouseX, mouseY, partialTicks);
		level.render(guiGraphics, mouseX, mouseY, partialTicks);
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
		if (job_id.isFocused())
			return job_id.keyPressed(key, b, c);
		if (level.isFocused())
			return level.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.eriniumjobs.required_maker.label_earnxp_creator"), 6, 10, -65536, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.eriniumjobs.required_maker.label_job_id"), 96, 28, -16777012, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.eriniumjobs.required_maker.label_min_level"), 6, 64, -16776961, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.eriniumjobs.required_maker.label_item_block"), 6, 28, -16776961, false);
	}

	@Override
	public void init() {
		super.init();
		job_id = new EditBox(this.font, this.leftPos + 97, this.topPos + 38, 70, 18, Component.translatable("gui.eriniumjobs.required_maker.job_id"));
		job_id.setMaxLength(32767);
		guistate.put("text:job_id", job_id);
		this.addWidget(this.job_id);
		level = new EditBox(this.font, this.leftPos + 7, this.topPos + 74, 70, 18, Component.translatable("gui.eriniumjobs.required_maker.level"));
		level.setMaxLength(32767);
		guistate.put("text:level", level);
		this.addWidget(this.level);
		button_validate = Button.builder(Component.translatable("gui.eriniumjobs.required_maker.button_validate"), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new RequiredMakerButtonMessage(0, x, y, z));
				RequiredMakerButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 96, this.topPos + 73, 72, 20).build();
		guistate.put("button:button_validate", button_validate);
		this.addRenderableWidget(button_validate);
	}
}
