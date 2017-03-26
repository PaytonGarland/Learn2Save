package net.minecraft.util;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import javax.annotation.Nullable;
import org.apache.logging.log4j.Logger;

public class Util {
   @Nullable
   public static <V> V func_181617_a(FutureTask<V> p_181617_0_, Logger p_181617_1_) {
      try {
         p_181617_0_.run();
         return p_181617_0_.get();
      } catch (ExecutionException executionexception) {
         p_181617_1_.fatal((String)"Error executing task", (Throwable)executionexception);
      } catch (InterruptedException interruptedexception) {
         p_181617_1_.fatal((String)"Error executing task", (Throwable)interruptedexception);
      }

      return (V)null;
   }

   public static <T> T func_184878_a(List<T> p_184878_0_) {
      return (T)p_184878_0_.get(p_184878_0_.size() - 1);
   }
}
