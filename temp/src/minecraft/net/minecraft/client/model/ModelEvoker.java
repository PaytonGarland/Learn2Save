package net.minecraft.client.model;

import net.minecraft.client.model.ModelIllager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityEvoker;
import net.minecraft.util.math.MathHelper;

public class ModelEvoker extends ModelIllager {
   public ModelEvoker(float p_i47228_1_) {
      super(p_i47228_1_, 0.0F, 64, 64);
   }

   public void func_78088_a(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
      super.func_78088_a(p_78088_1_, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_);
      EntityEvoker entityevoker = (EntityEvoker)p_78088_1_;
      if(entityevoker.func_190749_o()) {
         this.field_191223_g.func_78785_a(p_78088_7_);
         this.field_191224_h.func_78785_a(p_78088_7_);
      } else {
         this.field_191219_c.func_78785_a(p_78088_7_);
      }

   }

   public void func_78087_a(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
      super.func_78087_a(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
      this.field_191223_g.field_78798_e = 0.0F;
      this.field_191223_g.field_78800_c = -5.0F;
      this.field_191224_h.field_78798_e = 0.0F;
      this.field_191224_h.field_78800_c = 5.0F;
      this.field_191223_g.field_78795_f = MathHelper.func_76134_b(p_78087_3_ * 0.6662F) * 0.25F;
      this.field_191224_h.field_78795_f = MathHelper.func_76134_b(p_78087_3_ * 0.6662F) * 0.25F;
      this.field_191223_g.field_78808_h = 2.3561945F;
      this.field_191224_h.field_78808_h = -2.3561945F;
      this.field_191223_g.field_78796_g = 0.0F;
      this.field_191224_h.field_78796_g = 0.0F;
   }
}
