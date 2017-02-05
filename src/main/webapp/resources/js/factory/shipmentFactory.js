/**
 * Created by ujjsingh on 12/2/15.
 */

packman.factory("shipmentFactory", ['$http', function($http) {

    var factoryInstance = {};
    var shipmentId = "";

    factoryInstance.getShipmentById = function(shipmentId) {
        return $http({
            method: 'GET',
            url:'/shipments/'+shipmentId
        });
    };

    factoryInstance.setShipmentId = function(id) {
        shipmentId = id;
    };

    factoryInstance.getShipmentId = function() {
        return shipmentId;
    };

    factoryInstance.saveShipment = function(shipment) {
        return $http({
            method: 'POST',
            url:'/shipments/save',
            data: shipment
        });
    };

    factoryInstance.updateShipment = function(shipment) {
        return $http({
            method: 'POST',
            url:'/shipments/update',
            data: shipment
        });
    };

    factoryInstance.deleteShipment = function(shipmentId) {
        return $http({
            method: 'DELETE',
            url:'/shipments/'+shipmentId
        });
    };

    factoryInstance.getShipmentListBySender = function(senderId) {
        return $http({
            method: 'GET',
            url:'/shipments/user/'+senderId+'/sentshipments'
        });
    };

    factoryInstance.getShipmentListByReceiver = function(receiverId) {
        return $http({
            method: 'GET',
            url:'/shipments/user/'+receiverId+'/incomingshipments'
        });
    };

    factoryInstance.getShipmentListByStatus = function(userId, status) {
        return $http({
            method: 'GET',
            url:'/shipments/user/'+userId+'/status/'+status
        });
    };

    factoryInstance.requestPickup = function(userId, shipmentId) {
        return $http({
            method: 'GET',
            url:'/shipments/user/'+userId+'/requestpickup/'+shipmentId
        });
    };

    factoryInstance.cancelPickup = function(userId, shipmentId) {
        return $http({
            method: 'GET',
            url:'/shipments/user/'+userId+'/cancelpickup/'+shipmentId
        });
    };

    factoryInstance.reschedulePickup = function(userId, shipmentId, pickupTime) {
        return $http({
            method: 'GET',
            url:'/shipments/user/'+userId+'/reschedule/'+shipmentId+'/time/'+pickupTime
        });
    };

    return factoryInstance;
}]);