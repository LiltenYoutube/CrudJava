
package DAO;

import DTO.Datos;

/**
 *
 * @author root
 */
public interface DAOinterface {
    
    //el parametro datos es el nombre de nuestra tabla
    public void insertar(Datos datos);

    public void select(Datos datos);

    public void update(Datos datos);

    public void delete(Datos datos);
}
