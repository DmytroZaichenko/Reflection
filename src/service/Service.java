package service;

import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;


@Target({TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {

}
