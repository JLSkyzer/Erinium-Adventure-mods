package fr.eriniumgroups.erinium.jobs.client.gui;

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

import fr.eriniumgroups.erinium.jobs.world.inventory.ConfigPage1Menu;
import fr.eriniumgroups.erinium.jobs.network.ConfigPage1ButtonMessage;

import com.mojang.blaze3d.systems.RenderSystem;

public class ConfigPage1Screen extends AbstractContainerScreen<ConfigPage1Menu> {
	private final static HashMap<String, Object> guistate = ConfigPage1Menu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_won_xp;

	public ConfigPage1Screen(ConfigPage1Menu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("eriniumjobs:textures/screens/config_page_1.png");

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
		guiGraphics.drawString(this.font, Component.translatable("gui.eriniumjobs.config_page_1.label_overlay_config"), 51, 7, -16777216, false);
	}

	@Override
	public void init() {
		super.init();
		button_won_xp = Button.builder(Component.translatable("gui.eriniumjobs.config_page_1.button_won_xp"), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new ConfigPage1ButtonMessage(0, x, y, z));
				ConfigPage1ButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 6, this.topPos + 25, 54, 20).build();
		guistate.put("button:button_won_xp", button_won_xp);
		this.addRenderableWidget(button_won_xp);
	}
}
