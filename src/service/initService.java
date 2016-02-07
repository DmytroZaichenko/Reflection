package service;

import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;

@Target({METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface InitService {
}
