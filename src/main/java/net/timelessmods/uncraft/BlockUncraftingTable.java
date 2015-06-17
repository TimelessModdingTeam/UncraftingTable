package net.timelessmods.uncraft;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockUncraftingTable extends Block {

    public BlockUncraftingTable()
    {
        super(Material.rock);
        setUnlocalizedName("uncrafting_table");
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }
}
