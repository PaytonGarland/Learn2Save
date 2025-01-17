package net.minecraft.world;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Explosion {
   private final boolean field_77286_a;
   private final boolean field_82755_b;
   private final Random field_77290_i;
   private final World field_77287_j;
   private final double field_77284_b;
   private final double field_77285_c;
   private final double field_77282_d;
   private final Entity field_77283_e;
   private final float field_77280_f;
   private final List<BlockPos> field_77281_g;
   private final Map<EntityPlayer, Vec3d> field_77288_k;

   public Explosion(World p_i45752_1_, Entity p_i45752_2_, double p_i45752_3_, double p_i45752_5_, double p_i45752_7_, float p_i45752_9_, List<BlockPos> p_i45752_10_) {
      this(p_i45752_1_, p_i45752_2_, p_i45752_3_, p_i45752_5_, p_i45752_7_, p_i45752_9_, false, true, p_i45752_10_);
   }

   public Explosion(World p_i45753_1_, Entity p_i45753_2_, double p_i45753_3_, double p_i45753_5_, double p_i45753_7_, float p_i45753_9_, boolean p_i45753_10_, boolean p_i45753_11_, List<BlockPos> p_i45753_12_) {
      this(p_i45753_1_, p_i45753_2_, p_i45753_3_, p_i45753_5_, p_i45753_7_, p_i45753_9_, p_i45753_10_, p_i45753_11_);
      this.field_77281_g.addAll(p_i45753_12_);
   }

   public Explosion(World p_i45754_1_, Entity p_i45754_2_, double p_i45754_3_, double p_i45754_5_, double p_i45754_7_, float p_i45754_9_, boolean p_i45754_10_, boolean p_i45754_11_) {
      this.field_77290_i = new Random();
      this.field_77281_g = Lists.<BlockPos>newArrayList();
      this.field_77288_k = Maps.<EntityPlayer, Vec3d>newHashMap();
      this.field_77287_j = p_i45754_1_;
      this.field_77283_e = p_i45754_2_;
      this.field_77280_f = p_i45754_9_;
      this.field_77284_b = p_i45754_3_;
      this.field_77285_c = p_i45754_5_;
      this.field_77282_d = p_i45754_7_;
      this.field_77286_a = p_i45754_10_;
      this.field_82755_b = p_i45754_11_;
   }

   public void func_77278_a() {
      Set<BlockPos> set = Sets.<BlockPos>newHashSet();
      int i = 16;

      for(int j = 0; j < 16; ++j) {
         for(int k = 0; k < 16; ++k) {
            for(int l = 0; l < 16; ++l) {
               if(j == 0 || j == 15 || k == 0 || k == 15 || l == 0 || l == 15) {
                  double d0 = (double)((float)j / 15.0F * 2.0F - 1.0F);
                  double d1 = (double)((float)k / 15.0F * 2.0F - 1.0F);
                  double d2 = (double)((float)l / 15.0F * 2.0F - 1.0F);
                  double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                  d0 = d0 / d3;
                  d1 = d1 / d3;
                  d2 = d2 / d3;
                  float f = this.field_77280_f * (0.7F + this.field_77287_j.field_73012_v.nextFloat() * 0.6F);
                  double d4 = this.field_77284_b;
                  double d6 = this.field_77285_c;
                  double d8 = this.field_77282_d;

                  for(float f1 = 0.3F; f > 0.0F; f -= 0.22500001F) {
                     BlockPos blockpos = new BlockPos(d4, d6, d8);
                     IBlockState iblockstate = this.field_77287_j.func_180495_p(blockpos);
                     if(iblockstate.func_185904_a() != Material.field_151579_a) {
                        float f2 = this.field_77283_e != null?this.field_77283_e.func_180428_a(this, this.field_77287_j, blockpos, iblockstate):iblockstate.func_177230_c().func_149638_a((Entity)null);
                        f -= (f2 + 0.3F) * 0.3F;
                     }

                     if(f > 0.0F && (this.field_77283_e == null || this.field_77283_e.func_174816_a(this, this.field_77287_j, blockpos, iblockstate, f))) {
                        set.add(blockpos);
                     }

                     d4 += d0 * 0.30000001192092896D;
                     d6 += d1 * 0.30000001192092896D;
                     d8 += d2 * 0.30000001192092896D;
                  }
               }
            }
         }
      }

      this.field_77281_g.addAll(set);
      float f3 = this.field_77280_f * 2.0F;
      int k1 = MathHelper.func_76128_c(this.field_77284_b - (double)f3 - 1.0D);
      int l1 = MathHelper.func_76128_c(this.field_77284_b + (double)f3 + 1.0D);
      int i2 = MathHelper.func_76128_c(this.field_77285_c - (double)f3 - 1.0D);
      int i1 = MathHelper.func_76128_c(this.field_77285_c + (double)f3 + 1.0D);
      int j2 = MathHelper.func_76128_c(this.field_77282_d - (double)f3 - 1.0D);
      int j1 = MathHelper.func_76128_c(this.field_77282_d + (double)f3 + 1.0D);
      List<Entity> list = this.field_77287_j.func_72839_b(this.field_77283_e, new AxisAlignedBB((double)k1, (double)i2, (double)j2, (double)l1, (double)i1, (double)j1));
      Vec3d vec3d = new Vec3d(this.field_77284_b, this.field_77285_c, this.field_77282_d);

      for(int k2 = 0; k2 < list.size(); ++k2) {
         Entity entity = (Entity)list.get(k2);
         if(!entity.func_180427_aV()) {
            double d12 = entity.func_70011_f(this.field_77284_b, this.field_77285_c, this.field_77282_d) / (double)f3;
            if(d12 <= 1.0D) {
               double d5 = entity.field_70165_t - this.field_77284_b;
               double d7 = entity.field_70163_u + (double)entity.func_70047_e() - this.field_77285_c;
               double d9 = entity.field_70161_v - this.field_77282_d;
               double d13 = (double)MathHelper.func_76133_a(d5 * d5 + d7 * d7 + d9 * d9);
               if(d13 != 0.0D) {
                  d5 = d5 / d13;
                  d7 = d7 / d13;
                  d9 = d9 / d13;
                  double d14 = (double)this.field_77287_j.func_72842_a(vec3d, entity.func_174813_aQ());
                  double d10 = (1.0D - d12) * d14;
                  entity.func_70097_a(DamageSource.func_94539_a(this), (float)((int)((d10 * d10 + d10) / 2.0D * 7.0D * (double)f3 + 1.0D)));
                  double d11 = d10;
                  if(entity instanceof EntityLivingBase) {
                     d11 = EnchantmentProtection.func_92092_a((EntityLivingBase)entity, d10);
                  }

                  entity.field_70159_w += d5 * d11;
                  entity.field_70181_x += d7 * d11;
                  entity.field_70179_y += d9 * d11;
                  if(entity instanceof EntityPlayer) {
                     EntityPlayer entityplayer = (EntityPlayer)entity;
                     if(!entityplayer.func_175149_v() && (!entityplayer.func_184812_l_() || !entityplayer.field_71075_bZ.field_75100_b)) {
                        this.field_77288_k.put(entityplayer, new Vec3d(d5 * d10, d7 * d10, d9 * d10));
                     }
                  }
               }
            }
         }
      }

   }

   public void func_77279_a(boolean p_77279_1_) {
      this.field_77287_j.func_184148_a((EntityPlayer)null, this.field_77284_b, this.field_77285_c, this.field_77282_d, SoundEvents.field_187539_bB, SoundCategory.BLOCKS, 4.0F, (1.0F + (this.field_77287_j.field_73012_v.nextFloat() - this.field_77287_j.field_73012_v.nextFloat()) * 0.2F) * 0.7F);
      if(this.field_77280_f >= 2.0F && this.field_82755_b) {
         this.field_77287_j.func_175688_a(EnumParticleTypes.EXPLOSION_HUGE, this.field_77284_b, this.field_77285_c, this.field_77282_d, 1.0D, 0.0D, 0.0D, new int[0]);
      } else {
         this.field_77287_j.func_175688_a(EnumParticleTypes.EXPLOSION_LARGE, this.field_77284_b, this.field_77285_c, this.field_77282_d, 1.0D, 0.0D, 0.0D, new int[0]);
      }

      if(this.field_82755_b) {
         for(BlockPos blockpos : this.field_77281_g) {
            IBlockState iblockstate = this.field_77287_j.func_180495_p(blockpos);
            Block block = iblockstate.func_177230_c();
            if(p_77279_1_) {
               double d0 = (double)((float)blockpos.func_177958_n() + this.field_77287_j.field_73012_v.nextFloat());
               double d1 = (double)((float)blockpos.func_177956_o() + this.field_77287_j.field_73012_v.nextFloat());
               double d2 = (double)((float)blockpos.func_177952_p() + this.field_77287_j.field_73012_v.nextFloat());
               double d3 = d0 - this.field_77284_b;
               double d4 = d1 - this.field_77285_c;
               double d5 = d2 - this.field_77282_d;
               double d6 = (double)MathHelper.func_76133_a(d3 * d3 + d4 * d4 + d5 * d5);
               d3 = d3 / d6;
               d4 = d4 / d6;
               d5 = d5 / d6;
               double d7 = 0.5D / (d6 / (double)this.field_77280_f + 0.1D);
               d7 = d7 * (double)(this.field_77287_j.field_73012_v.nextFloat() * this.field_77287_j.field_73012_v.nextFloat() + 0.3F);
               d3 = d3 * d7;
               d4 = d4 * d7;
               d5 = d5 * d7;
               this.field_77287_j.func_175688_a(EnumParticleTypes.EXPLOSION_NORMAL, (d0 + this.field_77284_b) / 2.0D, (d1 + this.field_77285_c) / 2.0D, (d2 + this.field_77282_d) / 2.0D, d3, d4, d5, new int[0]);
               this.field_77287_j.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, d3, d4, d5, new int[0]);
            }

            if(iblockstate.func_185904_a() != Material.field_151579_a) {
               if(block.func_149659_a(this)) {
                  block.func_180653_a(this.field_77287_j, blockpos, this.field_77287_j.func_180495_p(blockpos), 1.0F / this.field_77280_f, 0);
               }

               this.field_77287_j.func_180501_a(blockpos, Blocks.field_150350_a.func_176223_P(), 3);
               block.func_180652_a(this.field_77287_j, blockpos, this);
            }
         }
      }

      if(this.field_77286_a) {
         for(BlockPos blockpos1 : this.field_77281_g) {
            if(this.field_77287_j.func_180495_p(blockpos1).func_185904_a() == Material.field_151579_a && this.field_77287_j.func_180495_p(blockpos1.func_177977_b()).func_185913_b() && this.field_77290_i.nextInt(3) == 0) {
               this.field_77287_j.func_175656_a(blockpos1, Blocks.field_150480_ab.func_176223_P());
            }
         }
      }

   }

   public Map<EntityPlayer, Vec3d> func_77277_b() {
      return this.field_77288_k;
   }

   @Nullable
   public EntityLivingBase func_94613_c() {
      return this.field_77283_e == null?null:(this.field_77283_e instanceof EntityTNTPrimed?((EntityTNTPrimed)this.field_77283_e).func_94083_c():(this.field_77283_e instanceof EntityLivingBase?(EntityLivingBase)this.field_77283_e:null));
   }

   public void func_180342_d() {
      this.field_77281_g.clear();
   }

   public List<BlockPos> func_180343_e() {
      return this.field_77281_g;
   }
}
