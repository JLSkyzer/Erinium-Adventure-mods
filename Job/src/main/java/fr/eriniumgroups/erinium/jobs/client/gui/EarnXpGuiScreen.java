package fr.eriniumgroups.erinium.jobs.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import java.util.HashMap;

import fr.eriniumgroups.erinium.jobs.world.inventory.EarnXpGuiMenu;
import fr.eriniumgroups.erinium.jobs.procedures.GetPageProcedure;
import fr.eriniumgroups.erinium.jobs.network.EarnXpGuiButtonMessage;
import fr.eriniumgroups.erinium.jobs.EriniumjobsMod;

import com.mojang.blaze3d.systems.RenderSystem;

public class EarnXpGuiScreen extends AbstractContainerScreen<EarnXpGuiMenu> {
	private final static HashMap<String, Object> guistate = EarnXpGuiMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_empty;
	Button button_empty1;

	public EarnXpGuiScreen(EarnXpGuiMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 181;
	}

	private static final ResourceLocation texture = new ResourceLocation("eriniumjobs:textures/screens/earn_xp_gui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
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
	public void containerTick() {
		super.containerTick();
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.eriniumjobs.earn_xp_gui.label_earn_xp"), 132, 5, -3407872, false);
		guiGraphics.drawString(this.font,

				GetPageProcedure.execute(entity), 6, 5, -12829636, false);
	}

	@Override
	public void onClose() {
		super.onClose();
	}

	@Override
	public void init() {
		super.init();
		button_empty = Button.builder(Component.translatable("gui.eriniumjobs.earn_xp_gui.button_empty"), e -> {
			if (true) {
				EriniumjobsMod.PACKET_HANDLER.sendToServer(new EarnXpGuiButtonMessage(0, x, y, z));
				EarnXpGuiButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 6, this.topPos + 68, 35, 20).build();
		guistate.put("button:button_empty", button_empty);
		this.addRenderableWidget(button_empty);
		button_empty1 = Button.builder(Component.translatable("gui.eriniumjobs.earn_xp_gui.button_empty1"), e -> {
			if (true) {
				EriniumjobsMod.PACKET_HANDLER.sendToServer(new EarnXpGuiButtonMessage(1, x, y, z));
				EarnXpGuiButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 132, this.topPos + 68, 35, 20).build();
		guistate.put("button:button_empty1", button_empty1);
		this.addRenderableWidget(button_empty1);
	}
}
