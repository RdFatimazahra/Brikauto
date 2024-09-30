package com.backend.Repository;

import com.backend.Model.PanierItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PanierItemRepository extends JpaRepository<PanierItem, Long> {
    
}
