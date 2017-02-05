package com.packman.service.implementation;

import com.packman.Util.enums.NotificationMessage;
import com.packman.Util.enums.ShipmentStatus;
import com.packman.Util.notificationManager.NotificationHub;
import com.packman.dao.implementation.AgentDAOImpl;
import com.packman.exception.PackmanException;
import com.packman.exception.RestErrorMessages;
import com.packman.model.Agent;
import com.packman.model.Shipment;
import com.packman.service.serviceinterfaces.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sujaysudheendra on 11/22/15.
 */

//TODO: request process error how to handle that, should that be catched, which message to diplay?
@Service
public class AgentServiceImpl implements AgentService {

    @Autowired
    ShipmentServiceImpl shipmentService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    AgentDAOImpl agentDAO;

    @Autowired
    NotificationHub notifier;

    @Override
    public void verifyAgent() {
        //TODO
    }

    @Override
    public void loginAgent() {
        //TODO
    }

    @Override
    public void logoutAgent() {
        //TODO
    }

    @Override
    public boolean checkAgentAvailability() {
        //TODO
        return false;
    }

    @Override
    public void authenticateAgent() {
        //TODO
    }

    @Override
    public void updateAgent(Agent agent) {
        try {
            agentDAO.updateAgent(agent);
        } catch (Exception ex) {
            throw new PackmanException(RestErrorMessages.AGENT_UPDATE_FAILED, ex);
        }
    }

    @Override
    public void createAgent(Agent agent) {

        try {
            agentDAO.addAgent(agent);
        }
        catch(Exception ex) {
            throw new PackmanException(RestErrorMessages.DUPLICATE_AGENT, ex);
        }
    }

    @Override
    public void deleteAgent(Long id) {
        try {
            agentDAO.deleteAgent(id);
        } catch (Exception ex) {
            throw new PackmanException(RestErrorMessages.AGENT_DELETE_FAILED, ex);
        }
    }

    @Override
    public Long assignAgent(Agent agent, Shipment shipmentAccepted) {
        Long assignedAgentId = -1L;
        shipmentAccepted.setStatus(ShipmentStatus.ASSIGNED);
        agent.getAssignedShipments().add(shipmentAccepted);
        try {
            // TODO: How to rollback in this and what exception to throw
            shipmentService.updateShipment(shipmentAccepted);
            agentDAO.updateAgent(agent);
        } catch (Exception ex) {
            throw new PackmanException(RestErrorMessages.REQUEST_PROCESS_ERROR);
        }
        assignedAgentId = agent.getAgentId();
        notifyAssignedAgent(agent, shipmentAccepted, NotificationMessage.PICKUPASSIGNED);
        return assignedAgentId;
    }

    @Override
    public void receiveAgentResponse(Long agentId, Long shipmentId) {
        Agent agent;
        Shipment shipmentAccepted;
        try {
            shipmentAccepted = shipmentService.getShipmentByShipmentId(shipmentId);
            checkNullAndThrow(shipmentAccepted, RestErrorMessages.REQUEST_PROCESS_ERROR);
        } catch(Exception ex) {
            // TODO : message ?
            throw new PackmanException(RestErrorMessages.AGENT_RESPONSE_FAILED, ex);
        }
        if (shipmentAccepted.getStatus().equals(ShipmentStatus.READY.getStatus())) {
            try {
                agent = agentDAO.getAgentById(agentId);
                checkNullAndThrow(agent, RestErrorMessages.REQUEST_PROCESS_ERROR);
                Long assignedAgentId = assignAgent(agent, shipmentAccepted);
                if (assignedAgentId != -1) {
                    userService.notifyUser(shipmentAccepted, NotificationMessage.PICKUPASSIGNED);
                }
            } catch (Exception ex) {
                throw new PackmanException(RestErrorMessages.AGENT_RESPONSE_FAILED, ex);
                //e.printStackTrace();
            }
        }
    }

    //TODO: generalize notifyAgent method to handle any kind of notification

    @Override
    public void notifyAllAgents(Shipment shipment) {
        //get agents by zoneId
        //construct message
        //send message

        //TODO: get the reg id's which should be stored in the database when user register's
        List<String> regIds = new ArrayList<>();
        Map<String,String> mapss = new HashMap<String, String>();
        NotificationHub.sendNotification(regIds, mapss);
    }

    @Override
    public void notifyAssignedAgent(Agent agent, Shipment shipment, NotificationMessage message) {
        String notificationMessage = constructNotificationMessage(message);
        List<Agent> agents = new ArrayList<>();
        agents.add(agent);
        notifier.sendNotificationToAgents(agents, notificationMessage);

    }

    @Override
    public List<Shipment> getAgentShipmentsByStatus(Long agentId, ShipmentStatus status) {
        // TODO: better way may be to query db
        Agent agent;
        try {
            agent = agentDAO.getAgentById(agentId);
            checkNullAndThrow(agent, RestErrorMessages.REQUEST_PROCESS_ERROR);
        } catch (Exception ex) {
            throw new PackmanException(RestErrorMessages.AGENT_SHIPMENT_BY_STATUS_FAILED, ex);
        }
        List<Shipment> assignedShipments = (List<Shipment>) agent.getAssignedShipments();
        List<Shipment> shipmentsByStatus = new ArrayList<Shipment>();
        for (Shipment currShipment : assignedShipments) {
            if (currShipment.getStatus().equals(status.getStatus())) {
                shipmentsByStatus.add(currShipment);
            }
        }
        return shipmentsByStatus;
    }

    @Override
    public boolean removeShipment(Agent agent, Shipment shipment) {
        try {
            agent.getAssignedShipments().remove(shipment);
            agentDAO.updateAgent(agent);
            return true;
        } catch (Exception ex) {
            throw new PackmanException(RestErrorMessages.AGENT_DELETE_FAILED, ex);
        }
    }

    @Override
    public Agent getAgentById(Long id){
        Agent agent = agentDAO.getAgentById(id);
        checkNullAndThrow(agent, RestErrorMessages.NO_AGENT_FOUND);
        return agent;
    }


    private List<Agent> getAgentsByZoneId() {
        //TODO: complete the logic
        return null;
    }

    private String constructNotificationMessage(NotificationMessage status) {
        String notificationMessage = "";
        switch (status) {
            case PICKUPASSIGNED:
                notificationMessage = "Thank you for accepting the shipment. The following shipment has been assigned to you";
                break;
            case CANCELPICKUP:
                notificationMessage = "The shipment assigned to you has been cancelled by user";
                break;
        }
        return notificationMessage;
    }

    private void checkNullAndThrow(Object obj, RestErrorMessages errorMessage){
        if(obj == null) {
            throw new PackmanException(errorMessage);
        }
    }

}
