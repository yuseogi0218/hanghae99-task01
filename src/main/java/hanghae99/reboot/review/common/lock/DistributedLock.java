package hanghae99.reboot.review.common.lock;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributedLock {

    String key();
    TimeUnit timeUnit() default TimeUnit.SECONDS;
    long waitTime() default 5L; // 락을 기다리는 시간 - default : 5초
    long leaseTime() default 3L; // 락을 기다리는 시간 - default : 3초
}
