package net.timelessmods.uncraft;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockUncraftingTable extends Block {

    public BlockUncraftingTable() {
        super(Material.rock);
        setUnlocalizedName("uncrafting_table");
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
        player.openGui(UncraftingTable.instance, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }
}
