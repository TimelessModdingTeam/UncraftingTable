package net.timelessmods.uncraft;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import org.apache.logging.log4j.Logger;

@Mod(modid = UncraftingTable.MODID, name = "UncraftingTable", version = UncraftingTable.VERSION)
/**
 * Principal class of the mod. Used to handle crafting of the table & some of the new achievements.
 * @author jglrxavpok
 */
public class UncraftingTable
{

    public static final String  MODID      = "uncraftingtable";
    public static final String  VERSION    = "${version}";

    @Instance(MODID)
    public static UncraftingTable instance;

    /**
     * The block. Obviously :)
     */
    public Block                uncraftingTable;
    public UnGuiHandler         guiHandler = new UnGuiHandler();

    private Logger              logger;
    private Configuration       config;

    public Logger getLogger()
    {
        return logger;
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(ItemBlock.getItemFromBlock(uncraftingTable), 0, new ModelResourceLocation(UncraftingTable.MODID + ":" + "uncrafting_table", "inventory"));

        NetworkRegistry.INSTANCE.registerGuiHandler(this, guiHandler);
        DefaultsRecipeHandlers.load();

        logger.info("Uncrafting Table has been correctly initialized!");
    }

    @SubscribeEvent
    public void onUncrafting(UncraftingEvent event)
    {
    }

    @SubscribeEvent
    public void onSuccessedUncrafting(SuccessedUncraftingEvent event)
    {
    }

    public void saveProperties()
    {
        try
        {
            config.save();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();

        config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        config.save();

        MinecraftForge.EVENT_BUS.register(this);
        uncraftingTable = new BlockUncraftingTable();
        GameRegistry.registerBlock(uncraftingTable, "uncrafting_table");
        GameRegistry.addShapedRecipe(new ItemStack(uncraftingTable), "SSS", "SXS", "SSS", 'X', Blocks.crafting_table, 'S', Blocks.cobblestone);
    }

}
