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

            System.out.println("\n========= DOCUMENTOS EXISTENTES =========");
            List<Cliente> todosClientes = cm.consultarClientes();
            for (Cliente c : todosClientes) {
                System.out.println("Documento: " + c.getDocumento() + ", Nombre: " + c.getNombre());
            }

            System.out.println("\n========= CONSULTANDO CLIENTE POR ID =========");
            if (!todosClientes.isEmpty()) {
                long primerDocumento = todosClientes.get(0).getDocumento();
                System.out.println("Consultando cliente con documento: " + primerDocumento);
                Cliente c1 = cm.consultarCliente((int) primerDocumento);
                System.out.println(c1);
            } else {
                System.out.println("No hay clientes en la BD");
            }

            System.out.println("\n========= CONSULTANDO TIPOS DE ITEMS =========");
            List<TipoItem> tipos = tim.getTiposItems();
            for (TipoItem t : tipos) {
                System.out.println(t);
            }

            System.out.println("\n========= INSERTANDO NUEVO ITEM =========");
            // Usar tipo que existe en tu BD (basado en el output anterior)
            TipoItem tipo = new TipoItem(2, "Mueble"); // 2 = Mueble según tu output
            Item nuevo = new Item();
            nuevo.setTipo(tipo);
            nuevo.setNombre("Halo");
            nuevo.setDescripcion("Shooter Sci-Fi");
            nuevo.setFechaLanzamiento(new Date());
            nuevo.setTarifaxDia(3000);
            nuevo.setFormatoRenta("Digital");
            nuevo.setGenero("FPS");

            im.insertarItem(nuevo);
            sqlss.commit();
            System.out.println("Item insertado con id generado: " + nuevo.getId());

            System.out.println("\n========= CONSULTANDO ITEMS =========");
            List<Item> items = im.consultarItems();
            for (Item it : items) {
                System.out.println(it);
            }

            System.out.println("\n========= PROBANDO AGREGAR ITEM RENTADO =========");
            java.sql.Date inicio = new java.sql.Date(System.currentTimeMillis());
            java.sql.Date fin = new java.sql.Date(System.currentTimeMillis() + 5L * 24 * 60 * 60 * 1000);

            // Usar un documento que realmente exista (basado en el output anterior)
            if (!todosClientes.isEmpty()) {
                long documentoExistente = todosClientes.get(0).getDocumento();
                System.out.println("Agregando item rentado al cliente con documento: " + documentoExistente);
                cm.agregarItemRentadoACliente((int) documentoExistente, nuevo.getId(), inicio, fin);
                sqlss.commit();
                System.out.println("Item rentado agregado al cliente con doc=" + documentoExistente + " (itemId=" + nuevo.getId() + ")");

                System.out.println("\n========= CLIENTE ACTUALIZADO =========");
                Cliente clienteActualizado = cm.consultarCliente((int) documentoExistente);
                System.out.println(clienteActualizado);
            } else {
                System.out.println("No hay clientes para agregar items rentados");
            }

            // Consulta adicional para ver todos los clientes con sus items rentados
            System.out.println("\n========= TODOS LOS CLIENTES CON ITEMS RENTADOS =========");
            List<Cliente> clientesFinal = cm.consultarClientes();
            for (Cliente c : clientesFinal) {
                System.out.println(c);
                if (c.getRentados() != null && !c.getRentados().isEmpty()) {
                    System.out.println("  Items rentados:");
                    for (int i = 0; i < c.getRentados().size(); i++) {
                        System.out.println("    " + (i + 1) + ". " + c.getRentados().get(i));
                    }
                } else {
                    System.out.println("  Sin items rentados");
                }
                System.out.println("---");
            }
        }
    }
}