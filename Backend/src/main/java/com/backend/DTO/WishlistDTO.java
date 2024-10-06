package com.backend.DTO;

import lombok.Data;

import java.util.Set;

@Data
public class WishlistDTO {
    private Long id;
    private Long userId;
    private Set<PieceDto> pieces;
}
