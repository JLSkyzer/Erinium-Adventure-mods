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

import fr.eriniumgroups.erinium.jobs.world.inventory.UnlockedGuiMenu;
import fr.eriniumgroups.erinium.jobs.procedures.GetPageProcedure;
import fr.eriniumgroups.erinium.jobs.network.UnlockedGuiButtonMessage;
import fr.eriniumgroups.erinium.jobs.EriniumjobsMod;

import com.mojang.blaze3d.systems.RenderSystem;

public class UnlockedGuiScreen extends AbstractContainerScreen<UnlockedGuiMenu> {
	private final static HashMap<String, Object> guistate = UnlockedGuiMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_empty;
	Button button_empty1;

	public UnlockedGuiScreen(UnlockedGuiMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 181;
	}

	private static final ResourceLocation texture = new ResourceLocation("eriniumjobs:textures/screens/unlocked_gui.png");

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
		guiGraphics.drawString(this.font, Component.translatable("gui.eriniumjobs.unlocked_gui.label_earn_xp"), 123, 5, -3407872, false);
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
		button_empty = Button.builder(Component.translatable("gui.eriniumjobs.unlocked_gui.button_empty"), e -> {
			if (true) {
				EriniumjobsMod.PACKET_HANDLER.sendToServer(new UnlockedGuiButtonMessage(0, x, y, z));
				UnlockedGuiButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 6, this.topPos + 68, 35, 20).build();
		guistate.put("button:button_empty", button_empty);
		this.addRenderableWidget(button_empty);
		button_empty1 = Button.builder(Component.translatable("gui.eriniumjobs.unlocked_gui.button_empty1"), e -> {
			if (true) {
				EriniumjobsMod.PACKET_HANDLER.sendToServer(new UnlockedGuiButtonMessage(1, x, y, z));
				UnlockedGuiButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 132, this.topPos + 68, 35, 20).build();
		guistate.put("button:button_empty1", button_empty1);
		this.addRenderableWidget(button_empty1);
	}
}
