package edu.ecm.blog.aop;

import javax.inject.Inject;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import edu.ecm.blog.annotation.FlushCache;
import edu.ecm.blog.annotation.UseCache;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

/**
 * @author damien bourdette
 * @version \$Revision$
 */
@Aspect
@Component
public class CacheAspect {
    @Inject
    private Ehcache postsCache;

    @Around("@annotation(useCache)")
    public Object cache(ProceedingJoinPoint pjp, UseCache useCache) throws Throwable {
        String key = buildKey(pjp);

        System.out.println("request with key " + key);

        Element element = postsCache.get(key);

        if (element != null) {
            System.out.println("in cache");

            return element.getValue();
        }

        Object value = pjp.proceed();

        element = new Element(key, value);

        System.out.println("put in cache");

        postsCache.put(element);

        return value;
    }

    @Around("@annotation(flushCache)")
    public Object flush(ProceedingJoinPoint pjp, FlushCache flushCache) throws Throwable {
        System.out.println("flush cache");

        postsCache.removeAll();

        return pjp.proceed();
    }

    private String buildKey(ProceedingJoinPoint pjp) {
        StringBuilder builder = new StringBuilder();

        builder.append(pjp.getSignature().getName());

        for (Object arg : pjp.getArgs()) {
            builder.append("-");

            if (arg == null) {
                builder.append("null");
            } else {
                builder.append(arg.toString());
            }
        }

        return builder.toString();
    }
}
