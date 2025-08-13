package com.cavs.minitiendavirtual.persistence.repositories;

import com.cavs.minitiendavirtual.persistence.entities.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductosRepository extends JpaRepository<ProductoEntity, Integer> {

}
