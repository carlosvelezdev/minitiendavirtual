package com.cavs.minitiendavirtual.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.cavs.minitiendavirtual.domain.Producto;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements ProductoService {

    private List<Producto> productos = new ArrayList<>(Arrays.asList(
            new Producto(1, "Televisor", 1200000.0, 10),
            new Producto(2, "DVD", 500000.0, 4),
            new Producto(3, "Nevera", 2500000.0, 20)
    )
    );

    @Override
    public List<Producto> getProductos() {
        return productos;
    }

    @Override
    public Producto getProductoById(Integer id) {
        return productos.stream()
                .filter(producto -> producto.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    @Override
    public Producto createProducto(Producto producto) {
        productos.add(producto);
        return producto;
    }

    @Override
    public Producto updateProducto(Producto producto) {
        Optional<Producto> existenteOpt = productos.stream()
                .filter(p -> p.getId().equals(producto.getId()))
                .findFirst();

        if (existenteOpt.isEmpty()) {
            throw new RuntimeException("Producto no encontrado para actualizar");
        }

        Producto existente = existenteOpt.get();
        existente.setNombre(producto.getNombre());
        existente.setPrecio(producto.getPrecio());
        existente.setStock(producto.getStock());
        return existente;
    }

    @Override
    public void deleteProducto(Producto producto) {
        boolean eliminado = productos.removeIf(p -> p.getId().equals(producto.getId()));
        if (!eliminado) {
            throw new RuntimeException("Producto no encontrado para eliminar");
        }
    }

}
