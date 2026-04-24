package com.senai.conta_bancaria.application.dto.Saque;

import jakarta.validation.constraints.NotNull;

public record SaqueRequestDTO(
        @NotNull
        Long valorSaque
) {
}
