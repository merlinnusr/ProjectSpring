package com.blockbuster.app.dtos;

import com.blockbuster.app.models.Movie;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Data
@Getter
@Setter
public class RentDto {
    private Long userId;
    private List<Long> movies;
    private BigDecimal amount;
}
