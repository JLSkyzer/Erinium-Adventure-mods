package fr.eriniumgroups.erinium.jobs.client.gui;

import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.GuiGraphics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.eriniumgroups.erinium.jobs.world.inventory.EarnXpMakerMenu;
import fr.eriniumgroups.erinium.jobs.procedures.EarnXpMakerTooltipValueProcedure;

import com.mojang.blaze3d.systems.RenderSystem;

public class EarnXpMakerScreen extends AbstractContainerScreen<EarnXpMakerMenu> {
	private final static HashMap<String, Object> guistate = EarnXpMakerMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox job_id;
	EditBox min_level;
	EditBox max_level;
	EditBox type;

	public EarnXpMakerScreen(EarnXpMakerMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 226;
	}

	private static final ResourceLocation texture = new ResourceLocation("eriniumjobs:textures/screens/earn_xp_maker.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		job_id.render(guiGraphics, mouseX, mouseY, partialTicks);
		min_level.render(guiGraphics, mouseX, mouseY, partialTicks);
		max_level.render(guiGraphics, mouseX, mouseY, partialTicks);
		type.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
		List<Component> component = new ArrayList<>();
		Boolean validposition = false;
		if (mouseX > leftPos + 78 && mouseX < leftPos + 96 && mouseY > topPos + 109 && mouseY < topPos + 127)
			validposition = true;
			component.add(Component.literal("Bonjour"));
			component.add(Component.literal("Hey yo"));

			if (validposition){
				guiGraphics.renderTooltip(font, component, new ItemStack(Items.DIRT).getTooltipImage(), mouseX, mouseY);
			}
		validposition = false;
		component.clear();
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		guiGraphics.blit(new ResourceLocation("eriniumjobs:textures/screens/info.png"), this.leftPos + 78, this.topPos + 109, 0, 0, 16, 16, 16, 16);

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
		if (min_level.isFocused())
			return min_level.keyPressed(key, b, c);
		if (max_level.isFocused())
			return max_level.keyPressed(key, b, c);
		if (type.isFocused())
			return type.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		job_id.tick();
		min_level.tick();
		max_level.tick();
		type.tick();
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.eriniumjobs.earn_xp_maker.label_earnxp_creator"), 6, 10, -65536, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.eriniumjobs.earn_xp_maker.label_job_id"), 6, 28, -16777012, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.eriniumjobs.earn_xp_maker.label_min_level"), 6, 64, -16776961, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.eriniumjobs.earn_xp_maker.label_max_level"), 96, 64, -16776961, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.eriniumjobs.earn_xp_maker.label_type"), 6, 100, -16776961, false);
	}

	@Override
	public void onClose() {
		super.onClose();
	}

	@Override
	public void init() {
		super.init();
		job_id = new EditBox(this.font, this.leftPos + 6, this.topPos + 37, 72, 20, Component.translatable("gui.eriniumjobs.earn_xp_maker.job_id"));
		job_id.setMaxLength(32767);
		guistate.put("text:job_id", job_id);
		this.addWidget(this.job_id);
		min_level = new EditBox(this.font, this.leftPos + 6, this.topPos + 73, 72, 20, Component.translatable("gui.eriniumjobs.earn_xp_maker.min_level"));
		min_level.setMaxLength(32767);
		guistate.put("text:min_level", min_level);
		this.addWidget(this.min_level);
		max_level = new EditBox(this.font, this.leftPos + 96, this.topPos + 73, 72, 20, Component.translatable("gui.eriniumjobs.earn_xp_maker.max_level"));
		max_level.setMaxLength(32767);
		guistate.put("text:max_level", max_level);
		this.addWidget(this.max_level);
		type = new EditBox(this.font, this.leftPos + 6, this.topPos + 109, 72, 20, Component.translatable("gui.eriniumjobs.earn_xp_maker.type"));
		type.setMaxLength(32767);
		guistate.put("text:type", type);
		this.addWidget(this.type);
	}
}
