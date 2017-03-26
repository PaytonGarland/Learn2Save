package net.minecraft.item;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class ItemEnchantedBook extends Item
{
    /**
     * Checks isDamagable and if it cannot be stacked
     */
    public boolean isItemTool(ItemStack stack)
    {
        return false;
    }

    /**
     * Return an item rarity from EnumRarity
     */
    public EnumRarity getRarity(ItemStack stack)
    {
        return this.getEnchantments(stack).hasNoTags() ? super.getRarity(stack) : EnumRarity.UNCOMMON;
    }

    public NBTTagList getEnchantments(ItemStack stack)
    {
        NBTTagCompound nbttagcompound = stack.getTagCompound();
        return nbttagcompound != null && nbttagcompound.hasKey("StoredEnchantments", 9) ? (NBTTagList)nbttagcompound.getTag("StoredEnchantments") : new NBTTagList();
    }

    /**
     * Adds an stored enchantment to an enchanted book ItemStack
     */
    public void addEnchantment(ItemStack stack, EnchantmentData enchantment)
    {
        NBTTagList nbttaglist = this.getEnchantments(stack);
        boolean flag = true;

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);

            if (Enchantment.getEnchantmentByID(nbttagcompound.getShort("id")) == enchantment.enchantmentobj)
            {
                if (nbttagcompound.getShort("lvl") < enchantment.enchantmentLevel)
                {
                    nbttagcompound.setShort("lvl", (short)enchantment.enchantmentLevel);
                }

                flag = false;
                break;
            }
        }

        if (flag)
        {
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.setShort("id", (short)Enchantment.getEnchantmentID(enchantment.enchantmentobj));
            nbttagcompound1.setShort("lvl", (short)enchantment.enchantmentLevel);
            nbttaglist.appendTag(nbttagcompound1);
        }

        if (!stack.hasTagCompound())
        {
            stack.setTagCompound(new NBTTagCompound());
        }

        stack.getTagCompound().setTag("StoredEnchantments", nbttaglist);
    }

    /**
     * Returns the ItemStack of an enchanted version of this item.
     */
    public ItemStack getEnchantedItemStack(EnchantmentData data)
    {
        ItemStack itemstack = new ItemStack(this);
        this.addEnchantment(itemstack, data);
        return itemstack;
    }
}
