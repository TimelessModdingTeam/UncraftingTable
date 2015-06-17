package net.timelessmods.uncraft;

import java.awt.*;

import net.minecraft.client.gui.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.client.resources.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;
import net.minecraft.world.*;

import org.lwjgl.opengl.*;

/**
 * The client-visible part of the interface.
 * Can be inverted by a redstone power.
 * @author jglrxavpok
 *
 */
public class GuiUncraftingTable extends GuiContainer
{

    public ContainerUncraftingTable container;
    private String                  blockName;
    private World                   worldObj;
    private int                     x;
    private int                     z;
    private int                     y;
    private EntityPlayer            player;
    public static final ResourceLocation background = new ResourceLocation(UncraftingTable.MODID, "textures/gui/container/uncrafting_gui.png");

    public GuiUncraftingTable(InventoryPlayer playerInventory, World world, int x, int y, int z)
    {
        super(new ContainerUncraftingTable(playerInventory, world, x, y, z));
        container = (ContainerUncraftingTable) inventorySlots;
        this.blockName = I18n.format("tile.uncrafting_table.name");
        this.worldObj = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.player = playerInventory.player;
    }

    public void actionPerformed(GuiButton button)
    {
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        GL11.glDisable(GL11.GL_LIGHTING);
        int xSize = this.xSize;
        int ySize = this.ySize;
        fontRendererObj.drawString(blockName, xSize / 2 - fontRendererObj.getStringWidth(blockName) / 2 + 1, 5, 4210752);
        fontRendererObj.drawString(I18n.format("container.inventory"), 6, ySize - 96 + 2, 4210752);

        Color darkGreen = new Color(75, 245, 75);
        String string1 = I18n.format("uncrafting.compute") + ":";
        fontRendererObj.drawString(EnumChatFormatting.DARK_GRAY + string1 + EnumChatFormatting.RESET, 24 - fontRendererObj.getStringWidth(string1) / 2 + 1, 22, 0);
        fontRendererObj.drawString(EnumChatFormatting.GRAY + string1 + EnumChatFormatting.RESET, 24 - fontRendererObj.getStringWidth(string1) / 2, 21, 0);

        String string = container.result;
        if(string != null)
        {
            ContainerUncraftingTable.State msgType = container.type;
            EnumChatFormatting format = EnumChatFormatting.GREEN;
            EnumChatFormatting shadowFormat = EnumChatFormatting.DARK_GRAY;
            if(msgType == ContainerUncraftingTable.State.ERROR)
            {
                format = EnumChatFormatting.WHITE;
                shadowFormat = EnumChatFormatting.DARK_RED;
            }

            fontRendererObj.drawString(shadowFormat + string + EnumChatFormatting.RESET, 6 + 1, ySize - 95 + 2 - fontRendererObj.FONT_HEIGHT, 0);

            fontRendererObj.drawString(format + string + EnumChatFormatting.RESET, 6, ySize - 96 + 2 - fontRendererObj.FONT_HEIGHT, 0);
        }
        GL11.glEnable(GL11.GL_LIGHTING);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {
        GL11.glPushMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

        this.mc.renderEngine.bindTexture(background);

        int k = width / 2 - xSize / 2;
        int l = height / 2 - ySize / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        GL11.glPopMatrix();
    }

}
