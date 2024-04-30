package fr.eriniumgroups.erinium.jobs.client.gui;

import net.neoforged.neoforge.network.PacketDistributor;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.GuiGraphics;

import java.util.HashMap;

import fr.eriniumgroups.erinium.jobs.world.inventory.WonXpOverlayConfigMenu;
import fr.eriniumgroups.erinium.jobs.network.WonXpOverlayConfigButtonMessage;

import com.mojang.blaze3d.systems.RenderSystem;

public class WonXpOverlayConfigScreen extends AbstractContainerScreen<WonXpOverlayConfigMenu> {
	private final static HashMap<String, Object> guistate = WonXpOverlayConfigMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	ImageButton imagebutton_down;
	ImageButton imagebutton_reset;
	ImageButton imagebutton_left;
	ImageButton imagebutton_right;
	ImageButton imagebutton_up;

	public WonXpOverlayConfigScreen(WonXpOverlayConfigMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 0);
		/*RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();*/
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
	}

	@Override
	public void init() {
		super.init();
		imagebutton_down = new ImageButton(this.leftPos + 79, this.topPos + 187, 16, 16, new WidgetSprites(new ResourceLocation("eriniumjobs:textures/screens/down.png"), new ResourceLocation("eriniumjobs:textures/screens/down_hover.png")), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new WonXpOverlayConfigButtonMessage(0, x, y, z));
				WonXpOverlayConfigButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int x, int y, float partialTicks) {
				guiGraphics.blit(sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		guistate.put("button:imagebutton_down", imagebutton_down);
		this.addRenderableWidget(imagebutton_down);
		imagebutton_reset = new ImageButton(this.leftPos + 79, this.topPos + 171, 16, 16, new WidgetSprites(new ResourceLocation("eriniumjobs:textures/screens/reset.png"), new ResourceLocation("eriniumjobs:textures/screens/reset_hover.png")), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new WonXpOverlayConfigButtonMessage(1, x, y, z));
				WonXpOverlayConfigButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int x, int y, float partialTicks) {
				guiGraphics.blit(sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		guistate.put("button:imagebutton_reset", imagebutton_reset);
		this.addRenderableWidget(imagebutton_reset);
		imagebutton_left = new ImageButton(this.leftPos + 63, this.topPos + 171, 16, 16, new WidgetSprites(new ResourceLocation("eriniumjobs:textures/screens/left.png"), new ResourceLocation("eriniumjobs:textures/screens/left_hover.png")), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new WonXpOverlayConfigButtonMessage(2, x, y, z));
				WonXpOverlayConfigButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int x, int y, float partialTicks) {
				guiGraphics.blit(sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		guistate.put("button:imagebutton_left", imagebutton_left);
		this.addRenderableWidget(imagebutton_left);
		imagebutton_right = new ImageButton(this.leftPos + 95, this.topPos + 171, 16, 16, new WidgetSprites(new ResourceLocation("eriniumjobs:textures/screens/right.png"), new ResourceLocation("eriniumjobs:textures/screens/right_hover.png")), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new WonXpOverlayConfigButtonMessage(3, x, y, z));
				WonXpOverlayConfigButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int x, int y, float partialTicks) {
				guiGraphics.blit(sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		guistate.put("button:imagebutton_right", imagebutton_right);
		this.addRenderableWidget(imagebutton_right);
		imagebutton_up = new ImageButton(this.leftPos + 79, this.topPos + 155, 16, 16, new WidgetSprites(new ResourceLocation("eriniumjobs:textures/screens/up.png"), new ResourceLocation("eriniumjobs:textures/screens/up_hover.png")), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new WonXpOverlayConfigButtonMessage(4, x, y, z));
				WonXpOverlayConfigButtonMessage.handleButtonAction(entity, 4, x, y, z);
			}
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int x, int y, float partialTicks) {
				guiGraphics.blit(sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		guistate.put("button:imagebutton_up", imagebutton_up);
		this.addRenderableWidget(imagebutton_up);
	}
}
