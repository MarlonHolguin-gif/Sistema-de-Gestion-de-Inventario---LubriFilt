package mh.inventarios.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    String idProducto;
    String nombreProducto;
    Double precioProducto;
    Double costoProducto;
    Double porcentajeutilidad;
    Integer existenciaProducto;


}
