package net.minecraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityShulkerBox;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockShulkerBox extends BlockContainer {
   public static final PropertyEnum<EnumFacing> field_190957_a = PropertyDirection.func_177714_a("facing");
   private final EnumDyeColor field_190958_b;

   public BlockShulkerBox(EnumDyeColor p_i47248_1_) {
      super(Material.field_151576_e, MapColor.field_151660_b);
      this.field_190958_b = p_i47248_1_;
      this.func_149647_a(CreativeTabs.field_78031_c);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_190957_a, EnumFacing.UP));
   }

   public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
      return new TileEntityShulkerBox(this.field_190958_b);
   }

   public boolean func_149662_c(IBlockState p_149662_1_) {
      return false;
   }

   public boolean func_176214_u(IBlockState p_176214_1_) {
      return true;
   }

   public boolean func_149686_d(IBlockState p_149686_1_) {
      return false;
   }

   public EnumBlockRenderType func_149645_b(IBlockState p_149645_1_) {
      return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
   }

   public boolean func_180639_a(World p_180639_1_, BlockPos p_180639_2_, IBlockState p_180639_3_, EntityPlayer p_180639_4_, EnumHand p_180639_5_, EnumFacing p_180639_6_, float p_180639_7_, float p_180639_8_, float p_180639_9_) {
      if(p_180639_1_.field_72995_K) {
         return true;
      } else if(p_180639_4_.func_175149_v()) {
         return true;
      } else {
         TileEntity tileentity = p_180639_1_.func_175625_s(p_180639_2_);
         if(tileentity instanceof TileEntityShulkerBox) {
            EnumFacing enumfacing = (EnumFacing)p_180639_3_.func_177229_b(field_190957_a);
            boolean flag;
            if(((TileEntityShulkerBox)tileentity).func_190591_p() == TileEntityShulkerBox.AnimationStatus.CLOSED) {
               AxisAlignedBB axisalignedbb = field_185505_j.func_72321_a((double)(0.5F * (float)enumfacing.func_82601_c()), (double)(0.5F * (float)enumfacing.func_96559_d()), (double)(0.5F * (float)enumfacing.func_82599_e())).func_191195_a((double)enumfacing.func_82601_c(), (double)enumfacing.func_96559_d(), (double)enumfacing.func_82599_e());
               flag = !p_180639_1_.func_184143_b(axisalignedbb.func_186670_a(p_180639_2_.func_177972_a(enumfacing)));
            } else {
               flag = true;
            }

            if(flag) {
               p_180639_4_.func_71029_a(StatList.field_191272_ae);
               p_180639_4_.func_71007_a((IInventory)tileentity);
            }

            return true;
         } else {
            return false;
         }
      }
   }

   public IBlockState func_180642_a(World p_180642_1_, BlockPos p_180642_2_, EnumFacing p_180642_3_, float p_180642_4_, float p_180642_5_, float p_180642_6_, int p_180642_7_, EntityLivingBase p_180642_8_) {
      return this.func_176223_P().func_177226_a(field_190957_a, p_180642_3_);
   }

   protected BlockStateContainer func_180661_e() {
      return new BlockStateContainer(this, new IProperty[]{field_190957_a});
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      return ((EnumFacing)p_176201_1_.func_177229_b(field_190957_a)).func_176745_a();
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      EnumFacing enumfacing = EnumFacing.func_82600_a(p_176203_1_);
      return this.func_176223_P().func_177226_a(field_190957_a, enumfacing);
   }

   public void func_176208_a(World p_176208_1_, BlockPos p_176208_2_, IBlockState p_176208_3_, EntityPlayer p_176208_4_) {
      TileEntityShulkerBox tileentityshulkerbox = (TileEntityShulkerBox)p_176208_1_.func_175625_s(p_176208_2_);
      tileentityshulkerbox.func_190579_a(p_176208_4_.field_71075_bZ.field_75098_d);
      tileentityshulkerbox.func_184281_d(p_176208_4_);
   }

   public void func_180653_a(World p_180653_1_, BlockPos p_180653_2_, IBlockState p_180653_3_, float p_180653_4_, int p_180653_5_) {
   }

   public void func_180633_a(World p_180633_1_, BlockPos p_180633_2_, IBlockState p_180633_3_, EntityLivingBase p_180633_4_, ItemStack p_180633_5_) {
      if(p_180633_5_.func_82837_s()) {
         TileEntity tileentity = p_180633_1_.func_175625_s(p_180633_2_);
         if(tileentity instanceof TileEntityShulkerBox) {
            ((TileEntityShulkerBox)tileentity).func_190575_a(p_180633_5_.func_82833_r());
         }
      }

   }

   public void func_180663_b(World p_180663_1_, BlockPos p_180663_2_, IBlockState p_180663_3_) {
      TileEntity tileentity = p_180663_1_.func_175625_s(p_180663_2_);
      if(tileentity instanceof TileEntityShulkerBox) {
         TileEntityShulkerBox tileentityshulkerbox = (TileEntityShulkerBox)tileentity;
         if(!tileentityshulkerbox.func_190590_r() && tileentityshulkerbox.func_190582_F()) {
            ItemStack itemstack = new ItemStack(Item.func_150898_a(this));
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound.func_74782_a("BlockEntityTag", ((TileEntityShulkerBox)tileentity).func_190580_f(nbttagcompound1));
            itemstack.func_77982_d(nbttagcompound);
            if(tileentityshulkerbox.func_145818_k_()) {
               itemstack.func_151001_c(tileentityshulkerbox.func_70005_c_());
               tileentityshulkerbox.func_190575_a("");
            }

            func_180635_a(p_180663_1_, p_180663_2_, itemstack);
         }

         p_180663_1_.func_175666_e(p_180663_2_, p_180663_3_.func_177230_c());
      }

      super.func_180663_b(p_180663_1_, p_180663_2_, p_180663_3_);
   }

   public EnumPushReaction func_149656_h(IBlockState p_149656_1_) {
      return EnumPushReaction.DESTROY;
   }

   public AxisAlignedBB func_185496_a(IBlockState p_185496_1_, IBlockAccess p_185496_2_, BlockPos p_185496_3_) {
      TileEntity tileentity = p_185496_2_.func_175625_s(p_185496_3_);
      return tileentity instanceof TileEntityShulkerBox?((TileEntityShulkerBox)tileentity).func_190584_a(p_185496_1_):field_185505_j;
   }

   public boolean func_149740_M(IBlockState p_149740_1_) {
      return true;
   }

   public int func_180641_l(IBlockState p_180641_1_, World p_180641_2_, BlockPos p_180641_3_) {
      return Container.func_94526_b((IInventory)p_180641_2_.func_175625_s(p_180641_3_));
   }

   public ItemStack func_185473_a(World p_185473_1_, BlockPos p_185473_2_, IBlockState p_185473_3_) {
      ItemStack itemstack = super.func_185473_a(p_185473_1_, p_185473_2_, p_185473_3_);
      TileEntityShulkerBox tileentityshulkerbox = (TileEntityShulkerBox)p_185473_1_.func_175625_s(p_185473_2_);
      NBTTagCompound nbttagcompound = tileentityshulkerbox.func_190580_f(new NBTTagCompound());
      if(!nbttagcompound.func_82582_d()) {
         itemstack.func_77983_a("BlockEntityTag", nbttagcompound);
      }

      return itemstack;
   }

   public static Block func_190952_a(EnumDyeColor p_190952_0_) {
      switch(p_190952_0_) {
      case WHITE:
         return Blocks.field_190977_dl;
      case ORANGE:
         return Blocks.field_190978_dm;
      case MAGENTA:
         return Blocks.field_190979_dn;
      case LIGHT_BLUE:
         return Blocks.field_190980_do;
      case YELLOW:
         return Blocks.field_190981_dp;
      case LIME:
         return Blocks.field_190982_dq;
      case PINK:
         return Blocks.field_190983_dr;
      case GRAY:
         return Blocks.field_190984_ds;
      case SILVER:
         return Blocks.field_190985_dt;
      case CYAN:
         return Blocks.field_190986_du;
      case PURPLE:
      default:
         return Blocks.field_190987_dv;
      case BLUE:
         return Blocks.field_190988_dw;
      case BROWN:
         return Blocks.field_190989_dx;
      case GREEN:
         return Blocks.field_190990_dy;
      case RED:
         return Blocks.field_190991_dz;
      case BLACK:
         return Blocks.field_190975_dA;
      }
   }

   public static ItemStack func_190953_b(EnumDyeColor p_190953_0_) {
      return new ItemStack(func_190952_a(p_190953_0_));
   }

   public IBlockState func_185499_a(IBlockState p_185499_1_, Rotation p_185499_2_) {
      return p_185499_1_.func_177226_a(field_190957_a, p_185499_2_.func_185831_a((EnumFacing)p_185499_1_.func_177229_b(field_190957_a)));
   }

   public IBlockState func_185471_a(IBlockState p_185471_1_, Mirror p_185471_2_) {
      return p_185471_1_.func_185907_a(p_185471_2_.func_185800_a((EnumFacing)p_185471_1_.func_177229_b(field_190957_a)));
   }
}
