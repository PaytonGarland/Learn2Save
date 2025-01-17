package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.realms.RealmsSimpleScrolledSelectionList;
import net.minecraft.util.math.MathHelper;

public class GuiSimpleScrolledSelectionListProxy extends GuiSlot {
   private final RealmsSimpleScrolledSelectionList field_178050_u;

   public GuiSimpleScrolledSelectionListProxy(RealmsSimpleScrolledSelectionList p_i45525_1_, int p_i45525_2_, int p_i45525_3_, int p_i45525_4_, int p_i45525_5_, int p_i45525_6_) {
      super(Minecraft.func_71410_x(), p_i45525_2_, p_i45525_3_, p_i45525_4_, p_i45525_5_, p_i45525_6_);
      this.field_178050_u = p_i45525_1_;
   }

   protected int func_148127_b() {
      return this.field_178050_u.getItemCount();
   }

   protected void func_148144_a(int p_148144_1_, boolean p_148144_2_, int p_148144_3_, int p_148144_4_) {
      this.field_178050_u.selectItem(p_148144_1_, p_148144_2_, p_148144_3_, p_148144_4_);
   }

   protected boolean func_148131_a(int p_148131_1_) {
      return this.field_178050_u.isSelectedItem(p_148131_1_);
   }

   protected void func_148123_a() {
      this.field_178050_u.renderBackground();
   }

   protected void func_180791_a(int p_180791_1_, int p_180791_2_, int p_180791_3_, int p_180791_4_, int p_180791_5_, int p_180791_6_) {
      this.field_178050_u.renderItem(p_180791_1_, p_180791_2_, p_180791_3_, p_180791_4_, p_180791_5_, p_180791_6_);
   }

   public int func_178048_e() {
      return this.field_148155_a;
   }

   public int func_178047_f() {
      return this.field_148162_h;
   }

   public int func_178049_g() {
      return this.field_148150_g;
   }

   protected int func_148138_e() {
      return this.field_178050_u.getMaxPosition();
   }

   protected int func_148137_d() {
      return this.field_178050_u.getScrollbarPosition();
   }

   public void func_178039_p() {
      super.func_178039_p();
   }

   public void func_148128_a(int p_148128_1_, int p_148128_2_, float p_148128_3_) {
      if(this.field_178041_q) {
         this.field_148150_g = p_148128_1_;
         this.field_148162_h = p_148128_2_;
         this.func_148123_a();
         int i = this.func_148137_d();
         int j = i + 6;
         this.func_148121_k();
         GlStateManager.func_179140_f();
         GlStateManager.func_179106_n();
         Tessellator tessellator = Tessellator.func_178181_a();
         VertexBuffer vertexbuffer = tessellator.func_178180_c();
         int k = this.field_148152_e + this.field_148155_a / 2 - this.func_148139_c() / 2 + 2;
         int l = this.field_148153_b + 4 - (int)this.field_148169_q;
         if(this.field_148165_u) {
            this.func_148129_a(k, l, tessellator);
         }

         this.func_148120_b(k, l, p_148128_1_, p_148128_2_);
         GlStateManager.func_179097_i();
         this.func_148136_c(0, this.field_148153_b, 255, 255);
         this.func_148136_c(this.field_148154_c, this.field_148158_l, 255, 255);
         GlStateManager.func_179147_l();
         GlStateManager.func_187428_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE);
         GlStateManager.func_179118_c();
         GlStateManager.func_179103_j(7425);
         GlStateManager.func_179090_x();
         int i1 = this.func_148135_f();
         if(i1 > 0) {
            int j1 = (this.field_148154_c - this.field_148153_b) * (this.field_148154_c - this.field_148153_b) / this.func_148138_e();
            j1 = MathHelper.func_76125_a(j1, 32, this.field_148154_c - this.field_148153_b - 8);
            int k1 = (int)this.field_148169_q * (this.field_148154_c - this.field_148153_b - j1) / i1 + this.field_148153_b;
            if(k1 < this.field_148153_b) {
               k1 = this.field_148153_b;
            }

            vertexbuffer.func_181668_a(7, DefaultVertexFormats.field_181709_i);
            vertexbuffer.func_181662_b((double)i, (double)this.field_148154_c, 0.0D).func_187315_a(0.0D, 1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            vertexbuffer.func_181662_b((double)j, (double)this.field_148154_c, 0.0D).func_187315_a(1.0D, 1.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            vertexbuffer.func_181662_b((double)j, (double)this.field_148153_b, 0.0D).func_187315_a(1.0D, 0.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            vertexbuffer.func_181662_b((double)i, (double)this.field_148153_b, 0.0D).func_187315_a(0.0D, 0.0D).func_181669_b(0, 0, 0, 255).func_181675_d();
            tessellator.func_78381_a();
            vertexbuffer.func_181668_a(7, DefaultVertexFormats.field_181709_i);
            vertexbuffer.func_181662_b((double)i, (double)(k1 + j1), 0.0D).func_187315_a(0.0D, 1.0D).func_181669_b(128, 128, 128, 255).func_181675_d();
            vertexbuffer.func_181662_b((double)j, (double)(k1 + j1), 0.0D).func_187315_a(1.0D, 1.0D).func_181669_b(128, 128, 128, 255).func_181675_d();
            vertexbuffer.func_181662_b((double)j, (double)k1, 0.0D).func_187315_a(1.0D, 0.0D).func_181669_b(128, 128, 128, 255).func_181675_d();
            vertexbuffer.func_181662_b((double)i, (double)k1, 0.0D).func_187315_a(0.0D, 0.0D).func_181669_b(128, 128, 128, 255).func_181675_d();
            tessellator.func_78381_a();
            vertexbuffer.func_181668_a(7, DefaultVertexFormats.field_181709_i);
            vertexbuffer.func_181662_b((double)i, (double)(k1 + j1 - 1), 0.0D).func_187315_a(0.0D, 1.0D).func_181669_b(192, 192, 192, 255).func_181675_d();
            vertexbuffer.func_181662_b((double)(j - 1), (double)(k1 + j1 - 1), 0.0D).func_187315_a(1.0D, 1.0D).func_181669_b(192, 192, 192, 255).func_181675_d();
            vertexbuffer.func_181662_b((double)(j - 1), (double)k1, 0.0D).func_187315_a(1.0D, 0.0D).func_181669_b(192, 192, 192, 255).func_181675_d();
            vertexbuffer.func_181662_b((double)i, (double)k1, 0.0D).func_187315_a(0.0D, 0.0D).func_181669_b(192, 192, 192, 255).func_181675_d();
            tessellator.func_78381_a();
         }

         this.func_148142_b(p_148128_1_, p_148128_2_);
         GlStateManager.func_179098_w();
         GlStateManager.func_179103_j(7424);
         GlStateManager.func_179141_d();
         GlStateManager.func_179084_k();
      }
   }
}
