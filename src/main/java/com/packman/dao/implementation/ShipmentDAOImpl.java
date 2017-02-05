package com.packman.dao.implementation;

import com.packman.dao.daointerfaces.ShipmentDAO;
import com.packman.exception.GenericDAOException;
import com.packman.model.Shipment;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ShipmentDAO Implementation.
 * <p>
 * Created by mlshah on 11/21/15.
 */
@Repository
public class ShipmentDAOImpl extends GenericDAOImpl<Shipment, Long> implements ShipmentDAO {

    // i18n Strings
    private static final String TRANS_NULL = "Connection to Database Failed: Transaction could not be started.";
    private static final String GEN_DB_ERROR = "Generic Database Error: ";

    public boolean addShipment(Shipment shipment) throws Exception {
        super.add(shipment);
        return true;
    }

    public Shipment getShipmentById(Long id) throws Exception {
        return super.find(id);
    }

    public List<Shipment> getShipmentList(Long senderId) throws Exception {
        return super.getAll();
    }

    public boolean delete(Shipment shipment) throws Exception {
        super.remove(shipment);
        return true;
    }

    public boolean saveOrUpdateShipment(Shipment shipment) {
        super.saveOrUpdate(shipment);
        return true;
    }

    public boolean updateShipment(Shipment shipment) {
        super.update(shipment);
        return true;
    }


    public List<Shipment> getShipmentListBySenderId(Long senderId) {
        Transaction transaction = null;
        List<Shipment> shipmentList;
        try {
            transaction = super.currentSession().beginTransaction();
            if (transaction == null) {
                throw new GenericDAOException(TRANS_NULL);
            }
            Criteria shipmentSlection = super.currentSession().createCriteria(Shipment.class);
            shipmentSlection.add(Restrictions.eq("sender.userId", senderId));
            shipmentList = (List<Shipment>) shipmentSlection.list();
            if(!transaction.wasCommitted())
            transaction.commit();

        } catch (final HibernateException he) {
            if (transaction != null) {
                // Transaction has to be rolled back when exception is thrown
                transaction.rollback();
            }
            throw new GenericDAOException(GEN_DB_ERROR);
        }
        super.currentSession().close();
        return shipmentList;
    }

    /***
     * @param receiverId
     * @return
     */
    public List<Shipment> getShipmentListByReceiverId(Long receiverId) {
        Transaction transaction = null;
        List<Shipment> shipmentList;
        try {
            transaction = super.currentSession().beginTransaction();
            if (transaction == null) {
                throw new GenericDAOException(TRANS_NULL);
            }
            Criteria shipmentSlection = super.currentSession().createCriteria(Shipment.class);
            shipmentSlection.add(Restrictions.eq("receiver.userId", receiverId));
            shipmentList = (List<Shipment>) shipmentSlection.list();
            if(!transaction.wasCommitted())
            transaction.commit();

        } catch (final HibernateException he) {
            if (transaction != null) {
                // Transaction has to be rolled back when exception is thrown
                transaction.rollback();
            }
            throw new GenericDAOException(GEN_DB_ERROR);
        }
        super.currentSession().close();
        return shipmentList;
    }

    public List<Shipment> getShipmentListByStatus(Long userId, String status) {
        Transaction transaction = null;
        List<Shipment> shipmentList;
        try {
            transaction = super.currentSession().beginTransaction();
            if (transaction == null) {
                throw new GenericDAOException(TRANS_NULL);
            }
            Criteria shipmentSlection = super.currentSession().createCriteria(Shipment.class);
            shipmentSlection.add(
                    Restrictions.and(Restrictions.eq("sender.userId", userId),
                            Restrictions.eq("status", status))
            );
            shipmentList = (List<Shipment>) shipmentSlection.list();
            if(!transaction.wasCommitted())
            transaction.commit();

        } catch (final HibernateException he) {
            if (transaction != null) {
                // Transaction has to be rolled back when exception is thrown
                transaction.rollback();
            }
            throw new GenericDAOException(GEN_DB_ERROR);
        }
        super.currentSession().close();
        return shipmentList;
    }
}
