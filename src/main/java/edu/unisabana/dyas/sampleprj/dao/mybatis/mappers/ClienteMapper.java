package edu.unisabana.dyas.sampleprj.dao.mybatis.mappers;

import edu.unisabana.dyas.samples.entities.Cliente;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClienteMapper {

    /**
     * Consultar todos los clientes
     * @return lista de clientes
     */
    List<Cliente> consultarClientes();

    /**
     * Consultar un cliente por su documento
     * @param id documento del cliente
     * @return Cliente encontrado
     */
    Cliente consultarCliente(@Param("id") long id);

    /**
     * Agregar un item rentado a un cliente
     * @param id documento del cliente
     * @param idit id del item
     * @param fechainicio fecha de inicio de la renta
     * @param fechafin fecha de fin de la renta
     */
    void agregarItemRentadoACliente(
            @Param("id") long id,
            @Param("idit") int idit,
            @Param("fechainicio") java.sql.Date fechainicio,
            @Param("fechafin") java.sql.Date fechafin
    );
}
