package edu.unisabana.dyas.sampleprj.dao.mybatis.mappers;

import edu.unisabana.dyas.samples.entities.Item;
import java.util.List;

public interface ItemMapper {

    /**
     * Consultar un item por id
     * @param id id del item
     * @return Item encontrado
     */
    public Item consultarItem(int id);

    /**
     * Consultar todos los items
     * @return lista de items
     */
    public List<Item> consultarItems();

    /**
     * Insertar un nuevo item
     * @param item objeto Item
     */
    public void insertarItem(Item item);
}