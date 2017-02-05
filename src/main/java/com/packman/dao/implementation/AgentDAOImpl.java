package com.packman.dao.implementation;

import com.packman.dao.daointerfaces.AgentDAO;
import com.packman.exception.GenericDAOException;
import com.packman.model.Agent;
import com.packman.model.Shipment;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AgentDAO Implementation.
 * <p>
 * Created by RushikeshParadkar on 11/19/15.
 */
@Repository("AgentDAO")
public class AgentDAOImpl extends GenericDAOImpl<Agent, Long> implements AgentDAO {

    private static final String TRANS_NULL = "Connection to Database Failed: Transaction could not be started.";
    private static final String GEN_DB_ERROR = "Generic Database Error: ";
    private Transaction transaction;
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean addAgent(Agent agent) {
        super.add(agent);
        return true;
    }

    @Override
    public Agent getAgentById(Long id) {
        return super.find(id);
    }

    public List<Agent> getAgentList() throws Exception {
        return super.getAll();
    }

    @Override
    public boolean deleteAgent(Long agentId) {
        Agent agent;
        agent = getAgentById(agentId);
        agent.setActive(false);
        updateAgent(agent);
        return true;
    }

    public boolean saveOrUpdateAgent(Agent agent) {
        super.saveOrUpdate(agent);
        return true;
    }

    @Override
    public boolean updateAgent(Agent agent) {
        super.update(agent);
        return true;
    }
}
