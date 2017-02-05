package com.packman.service.serviceinterfaces;

import com.packman.Util.enums.NotificationMessage;
import com.packman.Util.enums.ShipmentStatus;
import com.packman.exception.PackmanException;
import com.packman.model.Agent;
import com.packman.model.Shipment;

import java.util.List;

/**
 * Created by sujaysudheendra on 11/22/15.
 */
public interface AgentService {
    void createAgent(Agent agent);

    void verifyAgent();

    void updateAgent(Agent agent);

    void authenticateAgent();

    void deleteAgent(Long id);

    Agent getAgentById(Long id);

    void loginAgent();

    void logoutAgent();

    boolean checkAgentAvailability();

    Long assignAgent(Agent agent, Shipment shipment);

    void receiveAgentResponse(Long agentId, Long shipmentId);

    void notifyAllAgents(Shipment shipment);

    void notifyAssignedAgent(Agent agent, Shipment shipment, NotificationMessage message);

    List<Shipment> getAgentShipmentsByStatus(Long agentId, ShipmentStatus status);

    boolean removeShipment(Agent agent, Shipment shipment);
}
