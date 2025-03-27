package matheusosses.aluraflix.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Set;

public class HexOrColorValidator implements ConstraintValidator<HexOrColor, String> {
    private static final Set<String> CORES = Set.of(
            "red", "blue", "green", "yellow", "black", "white", "gray", "purple", "orange", "pink", "brown"
    );

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value.matches("^#?([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$")) {
            return true;
        }

        return CORES.contains(value.toLowerCase());
    }
}

