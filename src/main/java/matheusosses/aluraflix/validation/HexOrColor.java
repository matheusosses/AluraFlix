package matheusosses.aluraflix.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = HexOrColorValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface HexOrColor {
    String message() default "O valor deve ser um hexadecimal válido ou uma cor nomeada";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

