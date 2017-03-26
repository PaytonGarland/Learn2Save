package net.minecraft.item;

import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ItemCompass extends Item {
   public ItemCompass() {
      this.func_185043_a(new ResourceLocation("angle"), new IItemPropertyGetter() {
      });
   }
}
