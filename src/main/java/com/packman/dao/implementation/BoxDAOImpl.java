package com.packman.dao.implementation;

import com.packman.dao.daointerfaces.BoxDAO;
import com.packman.model.Box;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BoxDAO Implementation.
 * <p>
 * Created by RushikeshParadkar on 11/19/15.
 */
@Repository("BoxDAO")
public class BoxDAOImpl extends GenericDAOImpl<Box, Long> implements BoxDAO {

    public boolean addBox(Box box) throws Exception {
        super.add(box);
        return true;
    }

    public Box getBoxById(Long id) throws Exception {
        return super.find(id);
    }

    public List<Box> getBoxList() throws Exception {
        return super.getAll();
    }

    public boolean delete(Box box) throws Exception {
        super.remove(box);
        return true;
    }

    public boolean saveOrUpdateBox(Box box) {
        super.saveOrUpdate(box);
        return true;
    }

    public boolean updateBox(Box box) {
        super.update(box);
        return true;
    }
}
