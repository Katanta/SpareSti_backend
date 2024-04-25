package org.ntnu.idi.idatt2106.sparesti.sparestibackend.dto.goal;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

public record GoalUpdateDTO(
        @NotEmpty(message = "Title cannot be empty") @NotBlank(message = "Title cannot be blank")
                String title,
        @PositiveOrZero(message = "Saved amount cannot be negative") BigDecimal saved,
        @Positive(message = "Target amount cannot be less than or equal to zero") BigDecimal target,
        String description,
        @Future(message = "Due date cannot be in the past or now") ZonedDateTime due)
        implements Serializable {}
