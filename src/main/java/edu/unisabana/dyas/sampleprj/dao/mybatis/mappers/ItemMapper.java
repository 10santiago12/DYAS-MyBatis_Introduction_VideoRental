package edu.unisabana.dyas.sampleprj.dao.mybatis.mappers;

import edu.unisabana.dyas.samples.entities.Item;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItemMapper {

    /**
     * Consultar un item por id
     * @param id id del item
     * @return Item encontrado
     */
    public Item consultarItem(@Param("id") int id);

    /**
     * Consultar todos los items
     * @return lista de items
     */
    public List<Item> consultarItems();

    /**
     * Insertar un nuevo item
     * @param item objeto Item
     */
    public void insertarItem(@Param("item") Item item);
}