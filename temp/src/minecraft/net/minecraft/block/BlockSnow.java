package net.minecraft.block;

import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSnow extends Block {
   public static final PropertyInteger field_176315_a = PropertyInteger.func_177719_a("layers", 1, 8);
   protected static final AxisAlignedBB[] field_185702_b = new AxisAlignedBB[]{new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.0D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)};

   protected BlockSnow() {
      super(Material.field_151597_y);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176315_a, Integer.valueOf(1)));
      this.func_149675_a(true);
      this.func_149647_a(CreativeTabs.field_78031_c);
   }

   public AxisAlignedBB func_185496_a(IBlockState p_185496_1_, IBlockAccess p_185496_2_, BlockPos p_185496_3_) {
      return field_185702_b[((Integer)p_185496_1_.func_177229_b(field_176315_a)).intValue()];
   }

   public boolean func_176205_b(IBlockAccess p_176205_1_, BlockPos p_176205_2_) {
      return ((Integer)p_176205_1_.func_180495_p(p_176205_2_).func_177229_b(field_176315_a)).intValue() < 5;
   }

   public boolean func_185481_k(IBlockState p_185481_1_) {
      return ((Integer)p_185481_1_.func_177229_b(field_176315_a)).intValue() == 8;
   }

   @Nullable
   public AxisAlignedBB func_180646_a(IBlockState p_180646_1_, IBlockAccess p_180646_2_, BlockPos p_180646_3_) {
      int i = ((Integer)p_180646_1_.func_177229_b(field_176315_a)).intValue() - 1;
      float f = 0.125F;
      AxisAlignedBB axisalignedbb = p_180646_1_.func_185900_c(p_180646_2_, p_180646_3_);
      return new AxisAlignedBB(axisalignedbb.field_72340_a, axisalignedbb.field_72338_b, axisalignedbb.field_72339_c, axisalignedbb.field_72336_d, (double)((float)i * 0.125F), axisalignedbb.field_72334_f);
   }

   public boolean func_149662_c(IBlockState p_149662_1_) {
      return false;
   }

   public boolean func_149686_d(IBlockState p_149686_1_) {
      return false;
   }

   public boolean func_176196_c(World p_176196_1_, BlockPos p_176196_2_) {
      IBlockState iblockstate = p_176196_1_.func_180495_p(p_176196_2_.func_177977_b());
      Block block = iblockstate.func_177230_c();
      return block != Blocks.field_150432_aD && block != Blocks.field_150403_cj?(iblockstate.func_185904_a() == Material.field_151584_j?true:(block == this && ((Integer)iblockstate.func_177229_b(field_176315_a)).intValue() == 8?true:iblockstate.func_185914_p() && iblockstate.func_185904_a().func_76230_c())):false;
   }

   public void func_189540_a(IBlockState p_189540_1_, World p_189540_2_, BlockPos p_189540_3_, Block p_189540_4_, BlockPos p_189540_5_) {
      this.func_176314_e(p_189540_2_, p_189540_3_, p_189540_1_);
   }

   private boolean func_176314_e(World p_176314_1_, BlockPos p_176314_2_, IBlockState p_176314_3_) {
      if(!this.func_176196_c(p_176314_1_, p_176314_2_)) {
         this.func_176226_b(p_176314_1_, p_176314_2_, p_176314_3_, 0);
         p_176314_1_.func_175698_g(p_176314_2_);
         return false;
      } else {
         return true;
      }
   }

   public void func_180657_a(World p_180657_1_, EntityPlayer p_180657_2_, BlockPos p_180657_3_, IBlockState p_180657_4_, @Nullable TileEntity p_180657_5_, ItemStack p_180657_6_) {
      func_180635_a(p_180657_1_, p_180657_3_, new ItemStack(Items.field_151126_ay, ((Integer)p_180657_4_.func_177229_b(field_176315_a)).intValue() + 1, 0));
      p_180657_1_.func_175698_g(p_180657_3_);
      p_180657_2_.func_71029_a(StatList.func_188055_a(this));
   }

   public Item func_180660_a(IBlockState p_180660_1_, Random p_180660_2_, int p_180660_3_) {
      return Items.field_151126_ay;
   }

   public int func_149745_a(Random p_149745_1_) {
      return 0;
   }

   public void func_180650_b(World p_180650_1_, BlockPos p_180650_2_, IBlockState p_180650_3_, Random p_180650_4_) {
      if(p_180650_1_.func_175642_b(EnumSkyBlock.BLOCK, p_180650_2_) > 11) {
         this.func_176226_b(p_180650_1_, p_180650_2_, p_180650_1_.func_180495_p(p_180650_2_), 0);
         p_180650_1_.func_175698_g(p_180650_2_);
      }

   }

   public boolean func_176225_a(IBlockState p_176225_1_, IBlockAccess p_176225_2_, BlockPos p_176225_3_, EnumFacing p_176225_4_) {
      if(p_176225_4_ == EnumFacing.UP) {
         return true;
      } else {
         IBlockState iblockstate = p_176225_2_.func_180495_p(p_176225_3_.func_177972_a(p_176225_4_));
         return iblockstate.func_177230_c() == this && ((Integer)iblockstate.func_177229_b(field_176315_a)).intValue() >= ((Integer)p_176225_1_.func_177229_b(field_176315_a)).intValue()?false:super.func_176225_a(p_176225_1_, p_176225_2_, p_176225_3_, p_176225_4_);
      }
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return this.func_176223_P().func_177226_a(field_176315_a, Integer.valueOf((p_176203_1_ & 7) + 1));
   }

   public boolean func_176200_f(IBlockAccess p_176200_1_, BlockPos p_176200_2_) {
      return ((Integer)p_176200_1_.func_180495_p(p_176200_2_).func_177229_b(field_176315_a)).intValue() == 1;
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      return ((Integer)p_176201_1_.func_177229_b(field_176315_a)).intValue() - 1;
   }

   protected BlockStateContainer func_180661_e() {
      return new BlockStateContainer(this, new IProperty[]{field_176315_a});
   }
}
