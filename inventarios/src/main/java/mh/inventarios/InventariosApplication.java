package mh.inventarios;

import mh.inventarios.modelo.Producto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventariosApplication.class, args);

		//Prueba de Lombok
		Producto producto = new Producto();
		producto.setIdProducto("JD571014");
		producto.setNombreProducto("Filtro de aceite Pulsar NS200");
		producto.setPrecioProducto(12000.0);
		producto.setCostoProducto(7000.0);
		producto.setPorcentajeutilidad(42.0);
		producto.setExistenciaProducto(10);

		System.out.println(producto);

	}

}
