package edu.unisabana.dyas.samples.services.client;

import edu.unisabana.dyas.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.unisabana.dyas.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.unisabana.dyas.sampleprj.dao.mybatis.mappers.TipoItemMapper;
import edu.unisabana.dyas.samples.entities.Cliente;
import edu.unisabana.dyas.samples.entities.Item;
import edu.unisabana.dyas.samples.entities.TipoItem;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisExample {

    public static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        try {
            String resource = "mybatis-config.xml";
            Reader reader = Resources.getResourceAsReader(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            throw new RuntimeException(e.getCause());
        }
        return sqlSessionFactory;
    }

    public static void main(String[] args) throws SQLException {
        SqlSessionFactory sessionfact = getSqlSessionFactory();

        try (SqlSession sqlss = sessionfact.openSession()) {

            ClienteMapper cm = sqlss.getMapper(ClienteMapper.class);
            ItemMapper im = sqlss.getMapper(ItemMapper.class);
            TipoItemMapper tim = sqlss.getMapper(TipoItemMapper.class);

            System.out.println("========= CONSULTANDO CLIENTES =========");
            List<Cliente> clientes = cm.consultarClientes();
            for (Cliente c : clientes) {
                System.out.println(c);
            }

            System.out.println("\n========= CONSULTANDO CLIENTE POR ID =========");
            Cliente c1 = cm.consultarCliente(12345); // ejemplo de documento
            System.out.println(c1);

            System.out.println("\n========= CONSULTANDO TIPOS DE ITEMS =========");
            List<TipoItem> tipos = tim.getTiposItems();
            for (TipoItem t : tipos) {
                System.out.println(t);
            }

            System.out.println("\n========= INSERTANDO NUEVO ITEM =========");
            TipoItem tipo = new TipoItem(1, "Videojuego"); // suponer que existe tipo con id=1
            Item nuevo = new Item(tipo, 99, "Halo", "Shooter Sci-Fi", new Date(),
                    3000, "Digital", "FPS");
            im.insertarItem(nuevo);
            sqlss.commit();
            System.out.println("Item insertado: " + nuevo);

            System.out.println("\n========= CONSULTANDO ITEMS =========");
            List<Item> items = im.consultarItems();
            for (Item it : items) {
                System.out.println(it);
            }

            System.out.println("\n========= PROBANDO AGREGAR ITEM RENTADO =========");
            java.sql.Date inicio = new java.sql.Date(System.currentTimeMillis());
            java.sql.Date fin = new java.sql.Date(System.currentTimeMillis() + 5 * 24 * 60 * 60 * 1000L);
            cm.agregarItemRentadoACliente(12345, 99, inicio, fin);
            sqlss.commit();
            System.out.println("Item rentado agregado al cliente con doc=12345");

        }
    }
}