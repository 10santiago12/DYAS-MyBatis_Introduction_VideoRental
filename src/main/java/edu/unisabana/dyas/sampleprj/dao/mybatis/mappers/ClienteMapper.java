package edu.unisabana.dyas.sampleprj.dao.mybatis.mappers;

import edu.unisabana.dyas.samples.entities.Cliente;
import edu.unisabana.dyas.samples.entities.ItemRentado;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClienteMapper {

    /**
     * Consultar todos los clientes
     * @return lista de clientes
     */
    public List<Cliente> consultarClientes();

    /**
     * Consultar un cliente por su documento
     * @param id documento del cliente
     * @return Cliente encontrado
     */
    public Cliente consultarCliente(@Param("id") int id);

    /**
     * Agregar un item rentado a un cliente
     * @param id documento del cliente
     * @param idit id del item
     * @param fechainicio fecha de inicio de la renta
     * @param fechafin fecha de fin de la renta
     */
    public void agregarItemRentadoACliente(
            @Param("id") int id,
            @Param("idit") int idit,
            @Param("fechainicio") java.sql.Date fechainicio,
            @Param("fechafin") java.sql.Date fechafin
    );
}
