package edu.unisabana.dyas.sampleprj.dao.mybatis.mappers;

import edu.unisabana.dyas.samples.entities.TipoItem;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TipoItemMapper {

    /**
     * Consultar todos los tipos de items
     * @return lista de tipos
     */
    public List<TipoItem> getTiposItems();

    /**
     * Consultar un tipo de item por id
     * @param id id del tipo
     * @return TipoItem encontrado
     */
    public TipoItem getTipoItem(@Param("id") int id);

    /**
     * Agregar un nuevo tipo de item
     * @param des descripcion del tipo
     */
    public void addTipoItem(@Param("des") String des);
}