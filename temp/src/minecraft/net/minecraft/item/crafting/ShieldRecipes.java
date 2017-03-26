package net.minecraft.item.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class ShieldRecipes {
   public void func_185256_a(CraftingManager p_185256_1_) {
      p_185256_1_.func_92103_a(new ItemStack(Items.field_185159_cQ), new Object[]{"WoW", "WWW", " W ", Character.valueOf('W'), Blocks.field_150344_f, Character.valueOf('o'), Items.field_151042_j});
      p_185256_1_.func_180302_a(new ShieldRecipes.Decoration());
   }

   static class Decoration implements IRecipe {
      private Decoration() {
      }

      public boolean func_77569_a(InventoryCrafting p_77569_1_, World p_77569_2_) {
         ItemStack itemstack = ItemStack.field_190927_a;
         ItemStack itemstack1 = ItemStack.field_190927_a;

         for(int i = 0; i < p_77569_1_.func_70302_i_(); ++i) {
            ItemStack itemstack2 = p_77569_1_.func_70301_a(i);
            if(!itemstack2.func_190926_b()) {
               if(itemstack2.func_77973_b() == Items.field_179564_cE) {
                  if(!itemstack1.func_190926_b()) {
                     return false;
                  }

                  itemstack1 = itemstack2;
               } else {
                  if(itemstack2.func_77973_b() != Items.field_185159_cQ) {
                     return false;
                  }

                  if(!itemstack.func_190926_b()) {
                     return false;
                  }

                  if(itemstack2.func_179543_a("BlockEntityTag") != null) {
                     return false;
                  }

                  itemstack = itemstack2;
               }
            }
         }

         if(!itemstack.func_190926_b() && !itemstack1.func_190926_b()) {
            return true;
         } else {
            return false;
         }
      }

      public ItemStack func_77572_b(InventoryCrafting p_77572_1_) {
         ItemStack itemstack = ItemStack.field_190927_a;
         ItemStack itemstack1 = ItemStack.field_190927_a;

         for(int i = 0; i < p_77572_1_.func_70302_i_(); ++i) {
            ItemStack itemstack2 = p_77572_1_.func_70301_a(i);
            if(!itemstack2.func_190926_b()) {
               if(itemstack2.func_77973_b() == Items.field_179564_cE) {
                  itemstack = itemstack2;
               } else if(itemstack2.func_77973_b() == Items.field_185159_cQ) {
                  itemstack1 = itemstack2.func_77946_l();
               }
            }
         }

         if(itemstack1.func_190926_b()) {
            return itemstack1;
         } else {
            NBTTagCompound nbttagcompound = itemstack.func_179543_a("BlockEntityTag");
            NBTTagCompound nbttagcompound1 = nbttagcompound == null?new NBTTagCompound():nbttagcompound.func_74737_b();
            nbttagcompound1.func_74768_a("Base", itemstack.func_77960_j() & 15);
            itemstack1.func_77983_a("BlockEntityTag", nbttagcompound1);
            return itemstack1;
         }
      }

      public int func_77570_a() {
         return 2;
      }

      public ItemStack func_77571_b() {
         return ItemStack.field_190927_a;
      }

      public NonNullList<ItemStack> func_179532_b(InventoryCrafting p_179532_1_) {
         NonNullList<ItemStack> nonnulllist = NonNullList.<ItemStack>func_191197_a(p_179532_1_.func_70302_i_(), ItemStack.field_190927_a);

         for(int i = 0; i < nonnulllist.size(); ++i) {
            ItemStack itemstack = p_179532_1_.func_70301_a(i);
            if(itemstack.func_77973_b().func_77634_r()) {
               nonnulllist.set(i, new ItemStack(itemstack.func_77973_b().func_77668_q()));
            }
         }

         return nonnulllist;
      }
   }
}
