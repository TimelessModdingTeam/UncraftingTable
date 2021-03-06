package net.timelessmods.uncraft.common.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.timelessmods.uncraft.common.UncraftingTable;

/**
 * Main part of the Uncrafting Table. The manager is used to parse the existing recipes and find the correct one depending on the given stack.
 */
public class UncraftingManager {

	private static HashMap<Class<? extends IRecipe>, RecipeHandler>	uncraftingHandlers = new HashMap<Class<? extends IRecipe>, RecipeHandler>();

	public static List<Integer> getStackSizeNeeded(ItemStack item) {
		List<?> crafts = CraftingManager.getInstance().getRecipeList();
		List<Integer> list = new ArrayList<>();
		for(int i = 0;i<crafts.size();i++) {
			IRecipe r = (IRecipe) crafts.get(i);
			if(r != null) {
				ItemStack s = r.getRecipeOutput();
				if(s!=null) {
					if(s.getItem() == item.getItem() && s.getItemDamage() == item.getItemDamage()) {
						list.add(s.stackSize);
					}
				}
			}
		}
		return list;
	}
	
	public static List<ItemStack[]> getUncraftResults(ItemStack item) {
		List<?> crafts = CraftingManager.getInstance().getRecipeList();
		List<ItemStack[]> list = new ArrayList<>();
		for(int i = 0;i<crafts.size();i++) {
			IRecipe r = (IRecipe) crafts.get(i);
			if(r != null) {
				ItemStack s = r.getRecipeOutput();
				if(s!=null) {
					if(s.getItem() == item.getItem() && s.stackSize <= item.stackSize) {
						RecipeHandler handler = uncraftingHandlers.get(r.getClass());
						if(handler != null) {
							list.add(handler.getCraftingGrid(r));
						} else {
							UncraftingTable.instance.getLogger().error("[Uncrafting Table] Unknown recipe type: "+r.getClass().getCanonicalName());
						}
					}
				}
			}
		}
		return list;
	}
	
	public static void setRecipeHandler(Class<? extends IRecipe> recipe, RecipeHandler handler) {
		uncraftingHandlers.put(recipe, handler);
	}
	
}
