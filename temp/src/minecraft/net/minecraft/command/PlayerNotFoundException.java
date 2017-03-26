package net.minecraft.command;

import net.minecraft.command.CommandException;

public class PlayerNotFoundException extends CommandException {
   public PlayerNotFoundException(String p_i47330_1_) {
      super(p_i47330_1_, new Object[0]);
   }

   public PlayerNotFoundException(String p_i1362_1_, Object... p_i1362_2_) {
      super(p_i1362_1_, p_i1362_2_);
   }
}
