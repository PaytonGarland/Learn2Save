package net.minecraft.block;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLilyPad extends BlockBush {
   protected static final AxisAlignedBB field_185523_a = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.09375D, 0.9375D);

   protected BlockLilyPad() {
      this.func_149647_a(CreativeTabs.field_78031_c);
   }

   public void func_185477_a(IBlockState p_185477_1_, World p_185477_2_, BlockPos p_185477_3_, AxisAlignedBB p_185477_4_, List<AxisAlignedBB> p_185477_5_, @Nullable Entity p_185477_6_, boolean p_185477_7_) {
      if(!(p_185477_6_ instanceof EntityBoat)) {
         func_185492_a(p_185477_3_, p_185477_4_, p_185477_5_, field_185523_a);
      }

   }

   public void func_180634_a(World p_180634_1_, BlockPos p_180634_2_, IBlockState p_180634_3_, Entity p_180634_4_) {
      super.func_180634_a(p_180634_1_, p_180634_2_, p_180634_3_, p_180634_4_);
      if(p_180634_4_ instanceof EntityBoat) {
         p_180634_1_.func_175655_b(new BlockPos(p_180634_2_), true);
      }

   }

   public AxisAlignedBB func_185496_a(IBlockState p_185496_1_, IBlockAccess p_185496_2_, BlockPos p_185496_3_) {
      return field_185523_a;
   }

   protected boolean func_185514_i(IBlockState p_185514_1_) {
      return p_185514_1_.func_177230_c() == Blocks.field_150355_j || p_185514_1_.func_185904_a() == Material.field_151588_w;
   }

   public boolean func_180671_f(World p_180671_1_, BlockPos p_180671_2_, IBlockState p_180671_3_) {
      if(p_180671_2_.func_177956_o() >= 0 && p_180671_2_.func_177956_o() < 256) {
         IBlockState iblockstate = p_180671_1_.func_180495_p(p_180671_2_.func_177977_b());
         Material material = iblockstate.func_185904_a();
         return material == Material.field_151586_h && ((Integer)iblockstate.func_177229_b(BlockLiquid.field_176367_b)).intValue() == 0 || material == Material.field_151588_w;
      } else {
         return false;
      }
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      return 0;
   }
}
