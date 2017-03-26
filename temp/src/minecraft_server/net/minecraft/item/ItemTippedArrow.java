package net.minecraft.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class ItemTippedArrow extends ItemArrow {
   public EntityArrow func_185052_a(World p_185052_1_, ItemStack p_185052_2_, EntityLivingBase p_185052_3_) {
      EntityTippedArrow entitytippedarrow = new EntityTippedArrow(p_185052_1_, p_185052_3_);
      entitytippedarrow.func_184555_a(p_185052_2_);
      return entitytippedarrow;
   }

   public String func_77653_i(ItemStack p_77653_1_) {
      return I18n.func_74838_a(PotionUtils.func_185191_c(p_77653_1_).func_185174_b("tipped_arrow.effect."));
   }
}
