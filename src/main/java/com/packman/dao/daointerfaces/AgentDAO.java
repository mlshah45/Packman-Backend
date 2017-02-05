package com.packman.dao.daointerfaces;

import com.packman.model.Agent;
import com.packman.model.Shipment;

/**
 * DAO Class for Agent Entity.
 * <p>
 * Created by RushikeshParadkar on 11/19/15.
 */
public interface AgentDAO extends GenericDAO<Agent, Long> {

    //TODO: Currently uses the generic methods. Add Agent Specific methods for future implementation.

    boolean addAgent(Agent agent);

    Agent getAgentById(Long agentId);

    boolean deleteAgent(Long id);

    boolean updateAgent(Agent agent);
}


