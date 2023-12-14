package com.app.FamilyForeCast;

import org.springframework.data.annotation.Id;
record CashCard(@Id Long id, Double amount, String owner) {
}
