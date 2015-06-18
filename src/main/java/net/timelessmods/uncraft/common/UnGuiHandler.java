package net.timelessmods.uncraft.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.timelessmods.uncraft.client.GuiUncraftingTable;

public class UnGuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if(world.getBlockState(new BlockPos(x,y,z)).getBlock() == UncraftingTable.instance.uncraftingTable) {
            if(id == 0) {
                ContainerUncraftingTable c = new ContainerUncraftingTable(player.inventory, world, x, y, z);
                return c;
            }
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if(world.getBlockState(new BlockPos(x,y,z)).getBlock() == UncraftingTable.instance.uncraftingTable) {
            return new GuiUncraftingTable(player.inventory, world, x, y, z);
        }
        return null;
    }

}
