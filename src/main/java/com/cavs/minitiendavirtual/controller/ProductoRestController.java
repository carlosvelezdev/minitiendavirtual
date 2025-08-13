package com.cavs.minitiendavirtual.controller;

import java.util.List;

import com.cavs.minitiendavirtual.domain.Producto;
import com.cavs.minitiendavirtual.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productos")
public class ProductoRestController {

    @Autowired
    private ProductoService productosService;

    @GetMapping
    public ResponseEntity<?> getProductos(){
        List<Producto> productos = productosService.getProductos();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductosById(@PathVariable Integer id){
        Producto producto = productosService.getProductoById(id);
        if (producto != null) {
            return ResponseEntity.ok(producto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productosService.createProducto(producto);
        return ResponseEntity.ok(nuevoProducto);
    }

    @PutMapping
    public ResponseEntity<?> updateProducto(@RequestBody Producto producto) {
        try {
            Producto actualizado = productosService.updateProducto(producto);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Integer id) {
        Producto producto = productosService.getProductoById(id);
        if (producto != null) {
            productosService.deleteProducto(producto);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
