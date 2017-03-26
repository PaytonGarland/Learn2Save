package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelEvoker;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.ResourceLocation;

public class RenderEvoker extends RenderLiving<EntityMob> {
   private static final ResourceLocation field_191338_a = new ResourceLocation("textures/entity/illager/evoker.png");

   public RenderEvoker(RenderManager p_i47207_1_) {
      super(p_i47207_1_, new ModelEvoker(0.0F), 0.5F);
   }

   protected ResourceLocation func_110775_a(EntityMob p_110775_1_) {
      return field_191338_a;
   }

   protected void func_77041_b(EntityMob p_77041_1_, float p_77041_2_) {
      float f = 0.9375F;
      GlStateManager.func_179152_a(0.9375F, 0.9375F, 0.9375F);
   }
}
