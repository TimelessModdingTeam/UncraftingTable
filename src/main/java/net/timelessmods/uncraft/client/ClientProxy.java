package net.timelessmods.uncraft.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.timelessmods.uncraft.UncraftingTable;
import net.timelessmods.uncraft.common.CommonProxy;

public class ClientProxy extends CommonProxy {
    @Override
    public void postInit() {
        super.postInit();
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(ItemBlock.getItemFromBlock(UncraftingTable.instance.uncraftingTable), 0, new ModelResourceLocation(UncraftingTable.MODID + ":" + "uncrafting_table", "inventory"));
    }
}
