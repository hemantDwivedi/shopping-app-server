package com.dev.shoppingapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Coupon {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Integer couponId;
    @JsonProperty("OFF5")
    private Integer off5;
    @JsonProperty("OFF10")
    private Integer off10;
    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}
