package net.minecraft.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

public class ModelIllager extends ModelBase {
   public ModelRenderer field_191217_a;
   public ModelRenderer field_191218_b;
   public ModelRenderer field_191219_c;
   public ModelRenderer field_191220_d;
   public ModelRenderer field_191221_e;
   public ModelRenderer field_191222_f;
   public ModelRenderer field_191223_g;
   public ModelRenderer field_191224_h;

   public ModelIllager(float p_i47227_1_, float p_i47227_2_, int p_i47227_3_, int p_i47227_4_) {
      this.field_191217_a = (new ModelRenderer(this)).func_78787_b(p_i47227_3_, p_i47227_4_);
      this.field_191217_a.func_78793_a(0.0F, 0.0F + p_i47227_2_, 0.0F);
      this.field_191217_a.func_78784_a(0, 0).func_78790_a(-4.0F, -10.0F, -4.0F, 8, 10, 8, p_i47227_1_);
      this.field_191222_f = (new ModelRenderer(this)).func_78787_b(p_i47227_3_, p_i47227_4_);
      this.field_191222_f.func_78793_a(0.0F, p_i47227_2_ - 2.0F, 0.0F);
      this.field_191222_f.func_78784_a(24, 0).func_78790_a(-1.0F, -1.0F, -6.0F, 2, 4, 2, p_i47227_1_);
      this.field_191217_a.func_78792_a(this.field_191222_f);
      this.field_191218_b = (new ModelRenderer(this)).func_78787_b(p_i47227_3_, p_i47227_4_);
      this.field_191218_b.func_78793_a(0.0F, 0.0F + p_i47227_2_, 0.0F);
      this.field_191218_b.func_78784_a(16, 20).func_78790_a(-4.0F, 0.0F, -3.0F, 8, 12, 6, p_i47227_1_);
      this.field_191218_b.func_78784_a(0, 38).func_78790_a(-4.0F, 0.0F, -3.0F, 8, 18, 6, p_i47227_1_ + 0.5F);
      this.field_191219_c = (new ModelRenderer(this)).func_78787_b(p_i47227_3_, p_i47227_4_);
      this.field_191219_c.func_78793_a(0.0F, 0.0F + p_i47227_2_ + 2.0F, 0.0F);
      this.field_191219_c.func_78784_a(44, 22).func_78790_a(-8.0F, -2.0F, -2.0F, 4, 8, 4, p_i47227_1_);
      this.field_191219_c.func_78784_a(44, 22).func_78790_a(4.0F, -2.0F, -2.0F, 4, 8, 4, p_i47227_1_);
      this.field_191219_c.func_78784_a(40, 38).func_78790_a(-4.0F, 2.0F, -2.0F, 8, 4, 4, p_i47227_1_);
      this.field_191220_d = (new ModelRenderer(this, 0, 22)).func_78787_b(p_i47227_3_, p_i47227_4_);
      this.field_191220_d.func_78793_a(-2.0F, 12.0F + p_i47227_2_, 0.0F);
      this.field_191220_d.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i47227_1_);
      this.field_191221_e = (new ModelRenderer(this, 0, 22)).func_78787_b(p_i47227_3_, p_i47227_4_);
      this.field_191221_e.field_78809_i = true;
      this.field_191221_e.func_78793_a(2.0F, 12.0F + p_i47227_2_, 0.0F);
      this.field_191221_e.func_78790_a(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i47227_1_);
      this.field_191223_g = (new ModelRenderer(this, 40, 46)).func_78787_b(p_i47227_3_, p_i47227_4_);
      this.field_191223_g.func_78790_a(-3.0F, -2.0F, -2.0F, 4, 12, 4, p_i47227_1_);
      this.field_191223_g.func_78793_a(-5.0F, 2.0F + p_i47227_2_, 0.0F);
      this.field_191224_h = (new ModelRenderer(this, 40, 46)).func_78787_b(p_i47227_3_, p_i47227_4_);
      this.field_191224_h.field_78809_i = true;
      this.field_191224_h.func_78790_a(-1.0F, -2.0F, -2.0F, 4, 12, 4, p_i47227_1_);
      this.field_191224_h.func_78793_a(5.0F, 2.0F + p_i47227_2_, 0.0F);
   }

   public void func_78088_a(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
      this.func_78087_a(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
      this.field_191217_a.func_78785_a(p_78088_7_);
      this.field_191218_b.func_78785_a(p_78088_7_);
      this.field_191220_d.func_78785_a(p_78088_7_);
      this.field_191221_e.func_78785_a(p_78088_7_);
   }

   public void func_78087_a(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
      this.field_191217_a.field_78796_g = p_78087_4_ * 0.017453292F;
      this.field_191217_a.field_78795_f = p_78087_5_ * 0.017453292F;
      this.field_191219_c.field_78797_d = 3.0F;
      this.field_191219_c.field_78798_e = -1.0F;
      this.field_191219_c.field_78795_f = -0.75F;
      this.field_191220_d.field_78795_f = MathHelper.func_76134_b(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_ * 0.5F;
      this.field_191221_e.field_78795_f = MathHelper.func_76134_b(p_78087_1_ * 0.6662F + 3.1415927F) * 1.4F * p_78087_2_ * 0.5F;
      this.field_191220_d.field_78796_g = 0.0F;
      this.field_191221_e.field_78796_g = 0.0F;
   }

   public ModelRenderer func_191216_a(EnumHandSide p_191216_1_) {
      return p_191216_1_ == EnumHandSide.LEFT?this.field_191224_h:this.field_191223_g;
   }
}
