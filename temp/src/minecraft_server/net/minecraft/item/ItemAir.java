package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemAir extends Item {
   private final Block field_190904_a;

   public ItemAir(Block p_i47264_1_) {
      this.field_190904_a = p_i47264_1_;
   }

   public String func_77667_c(ItemStack p_77667_1_) {
      return this.field_190904_a.func_149739_a();
   }

   public String func_77658_a() {
      return this.field_190904_a.func_149739_a();
   }
}
