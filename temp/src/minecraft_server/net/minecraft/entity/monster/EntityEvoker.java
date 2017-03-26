package net.minecraft.entity.monster;

import com.google.common.base.Predicate;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityVex;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityEvokerFangs;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

public class EntityEvoker extends EntityMob {
   protected static final DataParameter<Byte> field_190760_a = EntityDataManager.<Byte>func_187226_a(EntityEvoker.class, DataSerializers.field_187191_a);
   private int field_190761_b;
   private int field_190762_c;
   private EntitySheep field_190763_bw;

   public EntityEvoker(World p_i47287_1_) {
      super(p_i47287_1_);
      this.func_70105_a(0.6F, 1.95F);
      this.field_70728_aV = 10;
   }

   protected void func_184651_r() {
      super.func_184651_r();
      this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
      this.field_70714_bg.func_75776_a(1, new EntityEvoker.AICastingSpell());
      this.field_70714_bg.func_75776_a(2, new EntityAIAvoidEntity(this, EntityPlayer.class, 8.0F, 0.6D, 1.0D));
      this.field_70714_bg.func_75776_a(4, new EntityEvoker.AISummonSpell());
      this.field_70714_bg.func_75776_a(5, new EntityEvoker.AIAttackSpell());
      this.field_70714_bg.func_75776_a(6, new EntityEvoker.AIWololoSpell());
      this.field_70714_bg.func_75776_a(8, new EntityAIWander(this, 0.6D));
      this.field_70714_bg.func_75776_a(9, new EntityAIWatchClosest(this, EntityPlayer.class, 3.0F, 1.0F));
      this.field_70714_bg.func_75776_a(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[]{EntityEvoker.class}));
      this.field_70715_bh.func_75776_a(2, (new EntityAINearestAttackableTarget(this, EntityPlayer.class, true)).func_190882_b(300));
      this.field_70715_bh.func_75776_a(3, (new EntityAINearestAttackableTarget(this, EntityVillager.class, false)).func_190882_b(300));
      this.field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, false));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.5D);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(12.0D);
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(24.0D);
   }

   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_187214_a(field_190760_a, Byte.valueOf((byte)0));
   }

   public static void func_190759_b(DataFixer p_190759_0_) {
      EntityLiving.func_189752_a(p_190759_0_, EntityEvoker.class);
   }

   public void func_70037_a(NBTTagCompound p_70037_1_) {
      super.func_70037_a(p_70037_1_);
      this.field_190761_b = p_70037_1_.func_74762_e("SpellTicks");
   }

   public void func_70014_b(NBTTagCompound p_70014_1_) {
      super.func_70014_b(p_70014_1_);
      p_70014_1_.func_74768_a("SpellTicks", this.field_190761_b);
   }

   public EnumCreatureAttribute func_70668_bt() {
      return EnumCreatureAttribute.ILLAGER;
   }

   protected ResourceLocation func_184647_J() {
      return LootTableList.field_191185_au;
   }

   public boolean func_190749_o() {
      return this.field_70170_p.field_72995_K?((Byte)this.field_70180_af.func_187225_a(field_190760_a)).byteValue() > 0:this.field_190761_b > 0;
   }

   public void func_190753_a(int p_190753_1_) {
      this.field_70180_af.func_187227_b(field_190760_a, Byte.valueOf((byte)p_190753_1_));
   }

   private int func_190755_di() {
      return this.field_190761_b;
   }

   protected void func_70619_bc() {
      super.func_70619_bc();
      if(this.field_190761_b > 0) {
         --this.field_190761_b;
      }

   }

   public void func_70071_h_() {
      super.func_70071_h_();
      if(this.field_70170_p.field_72995_K && this.func_190749_o()) {
         int i = ((Byte)this.field_70180_af.func_187225_a(field_190760_a)).byteValue();
         double d0 = 0.7D;
         double d1 = 0.5D;
         double d2 = 0.2D;
         if(i == 2) {
            d0 = 0.4D;
            d1 = 0.3D;
            d2 = 0.35D;
         } else if(i == 1) {
            d0 = 0.7D;
            d1 = 0.7D;
            d2 = 0.8D;
         }

         float f = this.field_70761_aq * 0.017453292F + MathHelper.func_76134_b((float)this.field_70173_aa * 0.6662F) * 0.25F;
         float f1 = MathHelper.func_76134_b(f);
         float f2 = MathHelper.func_76126_a(f);
         this.field_70170_p.func_175688_a(EnumParticleTypes.SPELL_MOB, this.field_70165_t + (double)f1 * 0.6D, this.field_70163_u + 1.8D, this.field_70161_v + (double)f2 * 0.6D, d0, d1, d2, new int[0]);
         this.field_70170_p.func_175688_a(EnumParticleTypes.SPELL_MOB, this.field_70165_t - (double)f1 * 0.6D, this.field_70163_u + 1.8D, this.field_70161_v - (double)f2 * 0.6D, d0, d1, d2, new int[0]);
      }

   }

   public boolean func_184191_r(Entity p_184191_1_) {
      return p_184191_1_ == null?false:(p_184191_1_ == this?true:(super.func_184191_r(p_184191_1_)?true:(p_184191_1_ instanceof EntityVex?this.func_184191_r(((EntityVex)p_184191_1_).func_190645_o()):(p_184191_1_ instanceof EntityLivingBase && ((EntityLivingBase)p_184191_1_).func_70668_bt() == EnumCreatureAttribute.ILLAGER?this.func_96124_cp() == null && p_184191_1_.func_96124_cp() == null:false))));
   }

   protected SoundEvent func_184639_G() {
      return SoundEvents.field_191243_bm;
   }

   protected SoundEvent func_184615_bR() {
      return SoundEvents.field_191245_bo;
   }

   protected SoundEvent func_184601_bQ() {
      return SoundEvents.field_191246_bp;
   }

   private void func_190748_a(@Nullable EntitySheep p_190748_1_) {
      this.field_190763_bw = p_190748_1_;
   }

   @Nullable
   private EntitySheep func_190751_dj() {
      return this.field_190763_bw;
   }

   class AIAttackSpell extends EntityEvoker.AIUseSpell {
      private AIAttackSpell() {
         super(null);
      }

      protected int func_190869_f() {
         return 40;
      }

      protected int func_190872_i() {
         return 100;
      }

      protected void func_190868_j() {
         EntityLivingBase entitylivingbase = EntityEvoker.this.func_70638_az();
         double d0 = Math.min(entitylivingbase.field_70163_u, EntityEvoker.this.field_70163_u);
         double d1 = Math.max(entitylivingbase.field_70163_u, EntityEvoker.this.field_70163_u) + 1.0D;
         float f = (float)MathHelper.func_181159_b(entitylivingbase.field_70161_v - EntityEvoker.this.field_70161_v, entitylivingbase.field_70165_t - EntityEvoker.this.field_70165_t);
         if(EntityEvoker.this.func_70068_e(entitylivingbase) < 9.0D) {
            for(int i = 0; i < 5; ++i) {
               float f1 = f + (float)i * 3.1415927F * 0.4F;
               this.func_190876_a(EntityEvoker.this.field_70165_t + (double)MathHelper.func_76134_b(f1) * 1.5D, EntityEvoker.this.field_70161_v + (double)MathHelper.func_76126_a(f1) * 1.5D, d0, d1, f1, 0);
            }

            for(int k = 0; k < 8; ++k) {
               float f2 = f + (float)k * 3.1415927F * 2.0F / 8.0F + 1.2566371F;
               this.func_190876_a(EntityEvoker.this.field_70165_t + (double)MathHelper.func_76134_b(f2) * 2.5D, EntityEvoker.this.field_70161_v + (double)MathHelper.func_76126_a(f2) * 2.5D, d0, d1, f2, 3);
            }
         } else {
            for(int l = 0; l < 16; ++l) {
               double d2 = 1.25D * (double)(l + 1);
               int j = 1 * l;
               this.func_190876_a(EntityEvoker.this.field_70165_t + (double)MathHelper.func_76134_b(f) * d2, EntityEvoker.this.field_70161_v + (double)MathHelper.func_76126_a(f) * d2, d0, d1, f, j);
            }
         }

      }

      private void func_190876_a(double p_190876_1_, double p_190876_3_, double p_190876_5_, double p_190876_7_, float p_190876_9_, int p_190876_10_) {
         BlockPos blockpos = new BlockPos(p_190876_1_, p_190876_7_, p_190876_3_);
         boolean flag = false;
         double d0 = 0.0D;

         while(true) {
            if(!EntityEvoker.this.field_70170_p.func_175677_d(blockpos, true) && EntityEvoker.this.field_70170_p.func_175677_d(blockpos.func_177977_b(), true)) {
               if(!EntityEvoker.this.field_70170_p.func_175623_d(blockpos)) {
                  IBlockState iblockstate = EntityEvoker.this.field_70170_p.func_180495_p(blockpos);
                  AxisAlignedBB axisalignedbb = iblockstate.func_185890_d(EntityEvoker.this.field_70170_p, blockpos);
                  if(axisalignedbb != null) {
                     d0 = axisalignedbb.field_72337_e;
                  }
               }

               flag = true;
               break;
            }

            blockpos = blockpos.func_177977_b();
            if(blockpos.func_177956_o() < MathHelper.func_76128_c(p_190876_5_) - 1) {
               break;
            }
         }

         if(flag) {
            EntityEvokerFangs entityevokerfangs = new EntityEvokerFangs(EntityEvoker.this.field_70170_p, p_190876_1_, (double)blockpos.func_177956_o() + d0, p_190876_3_, p_190876_9_, p_190876_10_, EntityEvoker.this);
            EntityEvoker.this.field_70170_p.func_72838_d(entityevokerfangs);
         }

      }

      protected SoundEvent func_190871_k() {
         return SoundEvents.field_191247_bq;
      }

      protected int func_190870_l() {
         return 2;
      }
   }

   class AICastingSpell extends EntityAIBase {
      public AICastingSpell() {
         this.func_75248_a(3);
      }

      public boolean func_75250_a() {
         return EntityEvoker.this.func_190755_di() > 0;
      }

      public void func_75249_e() {
         super.func_75249_e();
         EntityEvoker.this.func_190753_a(EntityEvoker.this.field_190762_c);
         EntityEvoker.this.field_70699_by.func_75499_g();
      }

      public void func_75251_c() {
         super.func_75251_c();
         EntityEvoker.this.func_190753_a(0);
      }

      public void func_75246_d() {
         if(EntityEvoker.this.func_70638_az() != null) {
            EntityEvoker.this.func_70671_ap().func_75651_a(EntityEvoker.this.func_70638_az(), (float)EntityEvoker.this.func_184649_cE(), (float)EntityEvoker.this.func_70646_bf());
         } else if(EntityEvoker.this.func_190751_dj() != null) {
            EntityEvoker.this.func_70671_ap().func_75651_a(EntityEvoker.this.func_190751_dj(), (float)EntityEvoker.this.func_184649_cE(), (float)EntityEvoker.this.func_70646_bf());
         }

      }
   }

   class AISummonSpell extends EntityEvoker.AIUseSpell {
      private AISummonSpell() {
         super(null);
      }

      public boolean func_75250_a() {
         if(!super.func_75250_a()) {
            return false;
         } else {
            int i = EntityEvoker.this.field_70170_p.func_72872_a(EntityVex.class, EntityEvoker.this.func_174813_aQ().func_186662_g(16.0D)).size();
            return EntityEvoker.this.field_70146_Z.nextInt(8) + 1 > i;
         }
      }

      protected int func_190869_f() {
         return 100;
      }

      protected int func_190872_i() {
         return 340;
      }

      protected void func_190868_j() {
         for(int i = 0; i < 3; ++i) {
            BlockPos blockpos = (new BlockPos(EntityEvoker.this)).func_177982_a(-2 + EntityEvoker.this.field_70146_Z.nextInt(5), 1, -2 + EntityEvoker.this.field_70146_Z.nextInt(5));
            EntityVex entityvex = new EntityVex(EntityEvoker.this.field_70170_p);
            entityvex.func_174828_a(blockpos, 0.0F, 0.0F);
            entityvex.func_180482_a(EntityEvoker.this.field_70170_p.func_175649_E(blockpos), (IEntityLivingData)null);
            entityvex.func_190658_a(EntityEvoker.this);
            entityvex.func_190651_g(blockpos);
            entityvex.func_190653_a(20 * (30 + EntityEvoker.this.field_70146_Z.nextInt(90)));
            EntityEvoker.this.field_70170_p.func_72838_d(entityvex);
         }

      }

      protected SoundEvent func_190871_k() {
         return SoundEvents.field_191248_br;
      }

      protected int func_190870_l() {
         return 1;
      }
   }

   abstract class AIUseSpell extends EntityAIBase {
      protected int field_190873_b;
      protected int field_190874_c;

      private AIUseSpell() {
      }

      public boolean func_75250_a() {
         return EntityEvoker.this.func_70638_az() == null?false:(EntityEvoker.this.func_190749_o()?false:EntityEvoker.this.field_70173_aa >= this.field_190874_c);
      }

      public boolean func_75253_b() {
         return EntityEvoker.this.func_70638_az() != null && this.field_190873_b > 0;
      }

      public void func_75249_e() {
         this.field_190873_b = this.func_190867_m();
         EntityEvoker.this.field_190761_b = this.func_190869_f();
         this.field_190874_c = EntityEvoker.this.field_70173_aa + this.func_190872_i();
         EntityEvoker.this.func_184185_a(this.func_190871_k(), 1.0F, 1.0F);
         EntityEvoker.this.field_190762_c = this.func_190870_l();
      }

      public void func_75246_d() {
         --this.field_190873_b;
         if(this.field_190873_b == 0) {
            this.func_190868_j();
            EntityEvoker.this.func_184185_a(SoundEvents.field_191244_bn, 1.0F, 1.0F);
         }

      }

      protected abstract void func_190868_j();

      protected int func_190867_m() {
         return 20;
      }

      protected abstract int func_190869_f();

      protected abstract int func_190872_i();

      protected abstract SoundEvent func_190871_k();

      protected abstract int func_190870_l();
   }

   public class AIWololoSpell extends EntityEvoker.AIUseSpell {
      final Predicate<EntitySheep> field_190879_a = new Predicate<EntitySheep>() {
         public boolean apply(EntitySheep p_apply_1_) {
            return p_apply_1_.func_175509_cj() == EnumDyeColor.BLUE;
         }
      };

      public AIWololoSpell() {
         super(null);
      }

      public boolean func_75250_a() {
         if(EntityEvoker.this.func_70638_az() != null) {
            return false;
         } else if(EntityEvoker.this.func_190749_o()) {
            return false;
         } else if(EntityEvoker.this.field_70173_aa < this.field_190874_c) {
            return false;
         } else if(!EntityEvoker.this.field_70170_p.func_82736_K().func_82766_b("mobGriefing")) {
            return false;
         } else {
            List<EntitySheep> list = EntityEvoker.this.field_70170_p.<EntitySheep>func_175647_a(EntitySheep.class, EntityEvoker.this.func_174813_aQ().func_72314_b(16.0D, 4.0D, 16.0D), this.field_190879_a);
            if(list.isEmpty()) {
               return false;
            } else {
               EntityEvoker.this.func_190748_a((EntitySheep)list.get(EntityEvoker.this.field_70146_Z.nextInt(list.size())));
               return true;
            }
         }
      }

      public boolean func_75253_b() {
         return EntityEvoker.this.func_190751_dj() != null && this.field_190873_b > 0;
      }

      public void func_75251_c() {
         super.func_75251_c();
         EntityEvoker.this.func_190748_a((EntitySheep)null);
      }

      protected void func_190868_j() {
         EntitySheep entitysheep = EntityEvoker.this.func_190751_dj();
         if(entitysheep != null && entitysheep.func_70089_S()) {
            entitysheep.func_175512_b(EnumDyeColor.RED);
         }

      }

      protected int func_190867_m() {
         return 40;
      }

      protected int func_190869_f() {
         return 60;
      }

      protected int func_190872_i() {
         return 140;
      }

      protected SoundEvent func_190871_k() {
         return SoundEvents.field_191249_bs;
      }

      protected int func_190870_l() {
         return 3;
      }
   }
}
