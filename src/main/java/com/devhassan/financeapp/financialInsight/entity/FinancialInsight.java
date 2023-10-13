package com.devhassan.financeapp.financialInsight.entity;

import com.devhassan.financeapp.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "financial_insight")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class FinancialInsight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(name = "insight_type", nullable = false)
    private String insightType;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

}
