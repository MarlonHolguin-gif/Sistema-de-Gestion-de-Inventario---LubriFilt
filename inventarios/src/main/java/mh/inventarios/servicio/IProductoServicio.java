package mh.inventarios.servicio;

import mh.inventarios.modelo.Producto;

import java.util.List;


public interface IProductoServicio {
    List<Producto> listarProductos();
    Producto buscarProductoPorId(String idProducto);

    Producto guardarProducto(Producto producto);

    void eliminarProductoporId(String idProducto);
}
