package com.backend.Model;

import com.backend.enums.OrderStatues;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "customer_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    private OrderStatues orderStatus;

    private BigDecimal totalPrice;

    @ManyToOne(cascade = CascadeType.ALL)
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;


}
