package edu.ecm.blog.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author damien bourdette
 * @version \$Revision$
 */
@Aspect
@Component
public class TraceAspect {
   @Around("within(org.hibernate..*)")
   public Object trace(ProceedingJoinPoint pjp) throws Throwable {
      long t1 = System.currentTimeMillis();

      Object value = pjp.proceed();

      long t2 = System.currentTimeMillis();

      System.out.println("Executed " + pjp.getSignature() + " in " +
         (t2 - t1) + "ms");

      return value;
   }
}
