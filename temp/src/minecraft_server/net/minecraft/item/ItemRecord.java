package net.minecraft.item;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.block.BlockJukebox;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemRecord extends Item {
   private static final Map<SoundEvent, ItemRecord> field_150928_b = Maps.<SoundEvent, ItemRecord>newHashMap();
   private final SoundEvent field_185076_b;
   private final String field_185077_c;

   protected ItemRecord(String p_i46742_1_, SoundEvent p_i46742_2_) {
      this.field_185077_c = "item.record." + p_i46742_1_ + ".desc";
      this.field_185076_b = p_i46742_2_;
      this.field_77777_bU = 1;
      this.func_77637_a(CreativeTabs.field_78026_f);
      field_150928_b.put(this.field_185076_b, this);
   }

   public EnumActionResult func_180614_a(EntityPlayer p_180614_1_, World p_180614_2_, BlockPos p_180614_3_, EnumHand p_180614_4_, EnumFacing p_180614_5_, float p_180614_6_, float p_180614_7_, float p_180614_8_) {
      IBlockState iblockstate = p_180614_2_.func_180495_p(p_180614_3_);
      if(iblockstate.func_177230_c() == Blocks.field_150421_aI && !((Boolean)iblockstate.func_177229_b(BlockJukebox.field_176432_a)).booleanValue()) {
         if(!p_180614_2_.field_72995_K) {
            ItemStack itemstack = p_180614_1_.func_184586_b(p_180614_4_);
            ((BlockJukebox)Blocks.field_150421_aI).func_176431_a(p_180614_2_, p_180614_3_, iblockstate, itemstack);
            p_180614_2_.func_180498_a((EntityPlayer)null, 1010, p_180614_3_, Item.func_150891_b(this));
            itemstack.func_190918_g(1);
            p_180614_1_.func_71029_a(StatList.field_188092_Z);
         }

         return EnumActionResult.SUCCESS;
      } else {
         return EnumActionResult.PASS;
      }
   }

   public EnumRarity func_77613_e(ItemStack p_77613_1_) {
      return EnumRarity.RARE;
   }
}
