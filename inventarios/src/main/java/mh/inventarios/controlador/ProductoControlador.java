package mh.inventarios.controlador;

import mh.inventarios.excepcion.RecursoNoEncontradoExcepcion;
import mh.inventarios.modelo.Producto;
import mh.inventarios.servicio.ProductoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("LubriFilt-app")//http://localhost:8080/LubriFilt-app
@CrossOrigin(value = "http://localhost:4200") //Puerto por default de Angular

public class ProductoControlador {
    private static final Logger logger = LoggerFactory.getLogger(ProductoControlador.class);

    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping("/productos") //http://localhost:8080/LubriFilt-app/productos
    public List<Producto> obtenerProductos(){
        List<Producto> productos = this.productoServicio.listarProductos();
        logger.info("Productos obtenidos");
        productos.forEach(producto -> logger.info(producto.toString()));
        return productos;
    }

    @PostMapping("/productos")
    public Producto agregarProducto(@RequestBody Producto producto){
        logger.info("Producto a agregar: " + producto);
        return this.productoServicio.guardarProducto(producto);
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(
            @PathVariable String id
    ){
        Producto producto = this.productoServicio.buscarProductoPorId(id);
        if(producto != null){
            return ResponseEntity.ok(producto);
        }else{
            throw new RecursoNoEncontradoExcepcion("No se encontró el id" + id);
        }
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> actualizarProducto(
            @PathVariable String id,
            @RequestBody Producto productoRecibido
    ){
        Producto producto = this.productoServicio.buscarProductoPorId(id);
        producto.setNombreProducto(productoRecibido.getNombreProducto());
        producto.setPrecioProducto(productoRecibido.getPrecioProducto());
        producto.setCostoProducto(productoRecibido.getCostoProducto());
        producto.setPorcentajeutilidad(productoRecibido.getPorcentajeutilidad());
        producto.setExistenciaProducto(productoRecibido.getExistenciaProducto());

        //Guardamos la informacion
        this.productoServicio.guardarProducto(producto);
        return ResponseEntity.ok(producto);
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarProducto(@PathVariable String id){
        Producto producto = this.productoServicio.buscarProductoPorId(id);
        if(producto==null){
            throw new RecursoNoEncontradoExcepcion(("No se encontro el id" + id));
        }
        this.productoServicio.eliminarProductoporId(producto.getIdProducto());
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
