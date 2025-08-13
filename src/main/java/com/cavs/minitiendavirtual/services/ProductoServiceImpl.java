package com.cavs.minitiendavirtual.services;

import java.util.List;
import java.util.stream.Collectors;

import com.cavs.minitiendavirtual.domain.Producto;
import com.cavs.minitiendavirtual.persistence.entities.ProductoEntity;
import com.cavs.minitiendavirtual.persistence.repositories.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductosRepository productosRepository;

    @Override
    public List<Producto> getProductos() {
        return productosRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Producto getProductoById(Integer id) {
        return productosRepository.findById(id)
                .map(this::mapToDto)
                .orElse(null); // puedes lanzar excepción si lo prefieres
    }

    @Override
    public Producto createProducto(Producto producto) {
        // No asignamos ID para que Hibernate lo trate como nuevo
        producto.setId(null);
        ProductoEntity saved = productosRepository.save(mapToEntity(producto));
        return mapToDto(saved);
    }

    @Override
    public Producto updateProducto(Producto producto) {
        if (producto.getId() == null) {
            throw new RuntimeException("ID requerido para actualizar producto");
        }

        ProductoEntity existente = productosRepository.findById(producto.getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado para actualizar"));

        existente.setNombre(producto.getNombre());
        existente.setPrecio(producto.getPrecio());
        existente.setStock(producto.getStock());

        ProductoEntity updated = productosRepository.save(existente);
        return mapToDto(updated);
    }

    @Override
    public void deleteProducto(Producto producto) {
        if (producto.getId() != null && productosRepository.existsById(producto.getId())) {
            productosRepository.deleteById(producto.getId());
        } else {
            throw new RuntimeException("Producto no encontrado para eliminar");
        }
    }

    // Métodos auxiliares
    private Producto mapToDto(ProductoEntity entity) {
        Producto dto = new Producto();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setPrecio(entity.getPrecio());
        dto.setStock(entity.getStock());
        return dto;
    }

    private ProductoEntity mapToEntity(Producto product) {
        ProductoEntity productoEntity = new ProductoEntity();
        // No seteamos ID si es null (creación)
        if (product.getId() != null) {
            productoEntity.setId(product.getId());
        }
        productoEntity.setNombre(product.getNombre());
        productoEntity.setPrecio(product.getPrecio());
        productoEntity.setStock(product.getStock());
        return productoEntity;
    }
}
