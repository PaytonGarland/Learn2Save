package net.minecraft.item;

import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ItemClock extends Item {
   public ItemClock() {
      this.func_185043_a(new ResourceLocation("time"), new IItemPropertyGetter() {
      });
   }
}
