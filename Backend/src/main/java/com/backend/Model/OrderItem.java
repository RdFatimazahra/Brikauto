package com.backend.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Setter @Getter
@RequiredArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order ;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "piece_id")
    private Piece piece;
    private Integer quantity ;
    private Double price ;
}
