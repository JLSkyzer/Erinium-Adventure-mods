package fr.eriniumgroups.erinium.auctionhouse.client.gui;

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

import fr.eriniumgroups.erinium.auctionhouse.world.inventory.ThemeSelect0Menu;
import fr.eriniumgroups.erinium.auctionhouse.network.ThemeSelect0ButtonMessage;

import com.mojang.blaze3d.systems.RenderSystem;

public class ThemeSelect0Screen extends AbstractContainerScreen<ThemeSelect0Menu> {
	private final static HashMap<String, Object> guistate = ThemeSelect0Menu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	ImageButton imagebutton_dark_btn;
	ImageButton imagebutton_white_btn;
	ImageButton imagebutton_red_btn;
	ImageButton imagebutton_blueroyal_purple_bnt;

	public ThemeSelect0Screen(ThemeSelect0Menu container, Inventory inventory, Component text) {
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
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		guiGraphics.blit(new ResourceLocation("erinium_ah:textures/screens/auctionthemeselect_base.png"), this.leftPos + -126, this.topPos + -37, 0, 0, 428, 240, 428, 240);

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
		guiGraphics.drawString(this.font, Component.translatable("gui.erinium_ah.theme_select_0.label_dark"), -125, 4, -3407872, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.erinium_ah.theme_select_0.label_white"), 18, 4, -3407872, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.erinium_ah.theme_select_0.label_red"), 161, 4, -3407872, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.erinium_ah.theme_select_0.label_blue_royal_purple"), -125, 122, -3407872, false);
	}

	@Override
	public void init() {
		super.init();
		imagebutton_dark_btn = new ImageButton(this.leftPos + -124, this.topPos + 15, 138, 68, new WidgetSprites(new ResourceLocation("erinium_ah:textures/screens/dark_btn.png"), new ResourceLocation("erinium_ah:textures/screens/dark_btn.png")),
				e -> {
					if (true) {
						PacketDistributor.SERVER.noArg().send(new ThemeSelect0ButtonMessage(0, x, y, z));
						ThemeSelect0ButtonMessage.handleButtonAction(entity, 0, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int x, int y, float partialTicks) {
				guiGraphics.blit(sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		guistate.put("button:imagebutton_dark_btn", imagebutton_dark_btn);
		this.addRenderableWidget(imagebutton_dark_btn);
		imagebutton_white_btn = new ImageButton(this.leftPos + 19, this.topPos + 15, 138, 68, new WidgetSprites(new ResourceLocation("erinium_ah:textures/screens/white_btn.png"), new ResourceLocation("erinium_ah:textures/screens/white_btn.png")),
				e -> {
					if (true) {
						PacketDistributor.SERVER.noArg().send(new ThemeSelect0ButtonMessage(1, x, y, z));
						ThemeSelect0ButtonMessage.handleButtonAction(entity, 1, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int x, int y, float partialTicks) {
				guiGraphics.blit(sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		guistate.put("button:imagebutton_white_btn", imagebutton_white_btn);
		this.addRenderableWidget(imagebutton_white_btn);
		imagebutton_red_btn = new ImageButton(this.leftPos + 162, this.topPos + 15, 138, 68, new WidgetSprites(new ResourceLocation("erinium_ah:textures/screens/red_btn.png"), new ResourceLocation("erinium_ah:textures/screens/red_btn.png")), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new ThemeSelect0ButtonMessage(2, x, y, z));
				ThemeSelect0ButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int x, int y, float partialTicks) {
				guiGraphics.blit(sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		guistate.put("button:imagebutton_red_btn", imagebutton_red_btn);
		this.addRenderableWidget(imagebutton_red_btn);
		imagebutton_blueroyal_purple_bnt = new ImageButton(this.leftPos + -124, this.topPos + 133, 138, 68,
				new WidgetSprites(new ResourceLocation("erinium_ah:textures/screens/blueroyal_purple_bnt.png"), new ResourceLocation("erinium_ah:textures/screens/blueroyal_purple_bnt.png")), e -> {
					if (true) {
						PacketDistributor.SERVER.noArg().send(new ThemeSelect0ButtonMessage(3, x, y, z));
						ThemeSelect0ButtonMessage.handleButtonAction(entity, 3, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int x, int y, float partialTicks) {
				guiGraphics.blit(sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		guistate.put("button:imagebutton_blueroyal_purple_bnt", imagebutton_blueroyal_purple_bnt);
		this.addRenderableWidget(imagebutton_blueroyal_purple_bnt);
	}
}
