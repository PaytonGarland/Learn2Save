package net.minecraft.client.model;

import net.minecraft.client.model.ModelIllager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityVindicator;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

public class ModelVindicator extends ModelIllager {
   public ModelVindicator(float p_i47222_1_) {
      this(p_i47222_1_, 0.0F, 64, 64);
   }

   public ModelVindicator(float p_i47223_1_, float p_i47223_2_, int p_i47223_3_, int p_i47223_4_) {
      super(p_i47223_1_, p_i47223_2_, p_i47223_3_, p_i47223_4_);
   }

   public void func_78088_a(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
      super.func_78088_a(p_78088_1_, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_);
      EntityVindicator entityvindicator = (EntityVindicator)p_78088_1_;
      if(entityvindicator.func_190639_o()) {
         this.field_191223_g.func_78785_a(p_78088_7_);
         this.field_191224_h.func_78785_a(p_78088_7_);
      } else {
         this.field_191219_c.func_78785_a(p_78088_7_);
      }

   }

   public void func_78087_a(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
      super.func_78087_a(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
      float f = MathHelper.func_76126_a(this.field_78095_p * 3.1415927F);
      float f1 = MathHelper.func_76126_a((1.0F - (1.0F - this.field_78095_p) * (1.0F - this.field_78095_p)) * 3.1415927F);
      this.field_191223_g.field_78808_h = 0.0F;
      this.field_191224_h.field_78808_h = 0.0F;
      this.field_191223_g.field_78796_g = 0.15707964F;
      this.field_191224_h.field_78796_g = -0.15707964F;
      if(((EntityLivingBase)p_78087_7_).func_184591_cq() == EnumHandSide.RIGHT) {
         this.field_191223_g.field_78795_f = -1.8849558F + MathHelper.func_76134_b(p_78087_3_ * 0.09F) * 0.15F;
         this.field_191224_h.field_78795_f = -0.0F + MathHelper.func_76134_b(p_78087_3_ * 0.19F) * 0.5F;
         this.field_191223_g.field_78795_f += f * 2.2F - f1 * 0.4F;
         this.field_191224_h.field_78795_f += f * 1.2F - f1 * 0.4F;
      } else {
         this.field_191223_g.field_78795_f = -0.0F + MathHelper.func_76134_b(p_78087_3_ * 0.19F) * 0.5F;
         this.field_191224_h.field_78795_f = -1.8849558F + MathHelper.func_76134_b(p_78087_3_ * 0.09F) * 0.15F;
         this.field_191223_g.field_78795_f += f * 1.2F - f1 * 0.4F;
         this.field_191224_h.field_78795_f += f * 2.2F - f1 * 0.4F;
      }

      this.field_191223_g.field_78808_h += MathHelper.func_76134_b(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
      this.field_191224_h.field_78808_h -= MathHelper.func_76134_b(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
      this.field_191223_g.field_78795_f += MathHelper.func_76126_a(p_78087_3_ * 0.067F) * 0.05F;
      this.field_191224_h.field_78795_f -= MathHelper.func_76126_a(p_78087_3_ * 0.067F) * 0.05F;
   }
}
