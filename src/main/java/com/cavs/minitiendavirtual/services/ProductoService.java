package com.cavs.minitiendavirtual.services;

import com.cavs.minitiendavirtual.domain.Producto;

import java.util.List;

public interface ProductoService {

    public List<Producto> getProductos();
    public Producto getProductoById(Integer id);
    public Producto createProducto(Producto producto);
    public Producto updateProducto(Producto producto);
    public void deleteProducto(Producto producto);
}
