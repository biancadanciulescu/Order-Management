package DataAccess;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


//import connection.ConnectionFactory;
import Connection.ConnectionFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Furnizează o implementare generică pentru Obiectele de Acces la Date (DAO).

 */

public class AbstractDAO<T> {

    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    public int ok; //indentificator pt tipul de tabel

    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private String createSelectAllQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        return sb.toString();

    }

    /**
     * Returnează o listă de toate obiectele de tip T din baza de date.
     */
    public List<T> findAll() {
        // TODO:
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * Returnează un obiect de tip T din baza de date pe baza ID-ului specificat.
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            List<T> resultList = createObjects(resultSet);

            if (!resultList.isEmpty()) {
                return resultList.get(0);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return null;
    }

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance;
                if(ok == 1) {
                    instance = (T) ctor.newInstance(0, null, null);

                    }else if(ok == 2){
                    instance = (T) ctor.newInstance(0, null, 0, 0f);
                }else{
                    instance = (T) ctor.newInstance(0, 0, 0, 0, 0);
                }

                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    private String createInsertQuery() {
        //
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append(" (");
        Field[] fields = type.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) { ////
            sb.append(fields[i].getName());
            if (i < fields.length - 1) {
                sb.append(", ");
            }
        }
        sb.append(") VALUES (");
        for (int i = 0; i < fields.length; i++) {
            sb.append("?");
            if (i < fields.length - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * Inserează un obiect nou de tip T în baza de date.
     */
    public T insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createInsertQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            int i = 1;
            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(t);
                statement.setObject(i, value); //inlocuiesc ? cu i si value
                i++;
            }

            int rowsInserted = statement.executeUpdate(); // nr de interogari inserate

            if (rowsInserted > 0) {
                resultSet = statement.getGeneratedKeys(); //obtin cheile generate
                if (resultSet.next()) { //daca am cel putin o cheie
                    int id = resultSet.getInt(1); //iau prima cheie generata
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor("id", type);
                    Method method = propertyDescriptor.getWriteMethod(); //obtine metoda setter
                    method.invoke(t, id); //apel setter
                    return t;
                }
            }
        } catch (SQLException | IllegalAccessException | IntrospectionException | InvocationTargetException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Actualizează un obiect de tip T în baza de date.
     */

    public T update(T t) {
        // TODO:
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getConnection();

            String tableName = type.getSimpleName();
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE ");
            sb.append(tableName);
            sb.append(" SET ");

            Field[] fields = type.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                String fieldName = field.getName();
                if (fieldName.equals("id")) {
                    continue; // trec peste id
                }
                sb.append(fieldName);
                sb.append(" = ?");
                if (i < fields.length - 1) {
                    sb.append(", ");
                }
            }

            sb.append(" WHERE id = ?");

            statement = connection.prepareStatement(sb.toString());

            // setez valorile pt SET
            int paramIndex = 1;
            for (Field field : fields) {
                String fieldName = field.getName();
                if (fieldName.equals("id")) {
                    continue;
                }
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                Method method = propertyDescriptor.getReadMethod();
                Object value = method.invoke(t);
                statement.setObject(paramIndex++, value);
            }

            // setez val pt update
            PropertyDescriptor idPropertyDescriptor = new PropertyDescriptor("id", type);
            Method idMethod = idPropertyDescriptor.getReadMethod();
            Object idValue = idMethod.invoke(t);
            statement.setObject(paramIndex, idValue);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                LOGGER.log(Level.WARNING, type.getName() + "DAO:update failed, no rows affected.");
                return null;
            }

            return t;

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } catch (IntrospectionException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } catch (IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } catch (InvocationTargetException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return null;
    }

    /**
     * Șterge un obiect de tip T din baza de date.
     */

    public boolean delete(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getConnection();

            String tableName = type.getSimpleName();
            String idFieldName = "id";
            String query = "DELETE FROM " + tableName + " WHERE " + idFieldName + " = ?";
            //
            statement = connection.prepareStatement(query);


            PropertyDescriptor idPropertyDescriptor = new PropertyDescriptor(idFieldName, type);
            Method idMethod = idPropertyDescriptor.getReadMethod();
            Object idValue = idMethod.invoke(t);

            statement.setObject(1, idValue);

            int affectedRows = statement.executeUpdate();

            if(affectedRows > 0)
                return true;

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } catch (IntrospectionException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } catch (IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } catch (InvocationTargetException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return false;
    }


    /**
     * Creează și returnează un tabel JTable pe baza listei de obiecte de tip T.
     */

    public JTable createTable(List<T> t) throws IllegalAccessException  {
        List<T> dataList = findAll();


        T firstElement = dataList.get(0);  // iau numele coloanelor de la primu element
        Field[] fields = firstElement.getClass().getDeclaredFields();

        String[] columnNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            columnNames[i] = fields[i].getName();
        }


        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0); //creez tabelul

        // adaug datele
        for (T data : dataList) {
            Object[] rowData = new Object[fields.length];
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                try {
                    rowData[i] = fields[i].get(data);
                } catch (IllegalAccessException e) {
                    LOGGER.log(Level.WARNING, type.getName() + "DAO:getTable " + e.getMessage());
                }
            }
            tableModel.addRow(rowData);
        }

        //creez jtable u
        JTable table = new JTable(tableModel);

        return table;
    }

}
