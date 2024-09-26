package com.blockbuster.app.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Getter
@Setter
public class MovieDto {
    private String title;
    private String genre;
    private String director;
    private LocalDateTime releasedAt;
    private String rate;
    private LocalTime duration;
    private Integer stock;
}
