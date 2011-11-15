package edu.ecm.blog.ejb;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * @author damien bourdette
 * @version \$Revision$
 */
@Singleton
public class MoreSeriousEJB {
    @EJB
    private HelloEJB helloEJB;

    public String hello(String name) {
        return helloEJB.hello(name);
    }

    private int count = 0;

    @Schedule(hour = "*", minute = "*", second = "*/5")
    public void endlessly() {
        System.out.println(helloEJB.hello("" + count));

        count++;
    }

    @Asynchronous
    public void startSomethingLong() {

    }

}
