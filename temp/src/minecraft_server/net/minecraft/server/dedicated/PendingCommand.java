package net.minecraft.server.dedicated;

import net.minecraft.command.ICommandSender;

public class PendingCommand {
   public final String field_73702_a;
   public final ICommandSender field_73701_b;

   public PendingCommand(String p_i1491_1_, ICommandSender p_i1491_2_) {
      this.field_73702_a = p_i1491_1_;
      this.field_73701_b = p_i1491_2_;
   }
}
