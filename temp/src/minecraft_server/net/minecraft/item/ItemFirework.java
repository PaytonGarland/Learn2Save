package net.minecraft.item;

import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemFirework extends Item {
   public EnumActionResult func_180614_a(EntityPlayer p_180614_1_, World p_180614_2_, BlockPos p_180614_3_, EnumHand p_180614_4_, EnumFacing p_180614_5_, float p_180614_6_, float p_180614_7_, float p_180614_8_) {
      if(!p_180614_2_.field_72995_K) {
         ItemStack itemstack = p_180614_1_.func_184586_b(p_180614_4_);
         EntityFireworkRocket entityfireworkrocket = new EntityFireworkRocket(p_180614_2_, (double)((float)p_180614_3_.func_177958_n() + p_180614_6_), (double)((float)p_180614_3_.func_177956_o() + p_180614_7_), (double)((float)p_180614_3_.func_177952_p() + p_180614_8_), itemstack);
         p_180614_2_.func_72838_d(entityfireworkrocket);
         if(!p_180614_1_.field_71075_bZ.field_75098_d) {
            itemstack.func_190918_g(1);
         }
      }

      return EnumActionResult.SUCCESS;
   }

   public ActionResult<ItemStack> func_77659_a(World p_77659_1_, EntityPlayer p_77659_2_, EnumHand p_77659_3_) {
      if(p_77659_2_.func_184613_cA()) {
         ItemStack itemstack = p_77659_2_.func_184586_b(p_77659_3_);
         if(!p_77659_1_.field_72995_K) {
            EntityFireworkRocket entityfireworkrocket = new EntityFireworkRocket(p_77659_1_, itemstack, p_77659_2_);
            p_77659_1_.func_72838_d(entityfireworkrocket);
            if(!p_77659_2_.field_71075_bZ.field_75098_d) {
               itemstack.func_190918_g(1);
            }
         }

         return new ActionResult(EnumActionResult.SUCCESS, p_77659_2_.func_184586_b(p_77659_3_));
      } else {
         return new ActionResult(EnumActionResult.PASS, p_77659_2_.func_184586_b(p_77659_3_));
      }
   }
}
