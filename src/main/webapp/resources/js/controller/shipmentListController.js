/**
 * Created by ujjsingh on 12/2/15.
 */
packman.controller("shipmentListController", function (shipmentFactory, $scope) {

    $scope.shipmentList = [];
    $scope.shipment = {};
    $scope.senderId = "1";

    $scope.getShipmentById = function (shipmentId) {
        $scope.shipment = data;
        shipmentFactory.setShipmentId($scope.senderId);
    };

    $scope.getShipmentListBySender = function () {
        shipmentFactory.getShipmentListBySender($scope.senderId)
            .success(function (data) {
                $scope.shipmentList = data;
            })
            .error(function (data) {
                // Set error message
            });
    };

    $scope.getShipmentListByReceiver = function () {
        shipmentFactory.getShipmentListByReceiver($scope.receiverId)
            .success(function (data) {
                $scope.shipmentList = data;
            })
            .error(function (data) {
                // Set error message
            });
    };

    $scope.getShipmentListByStatus = function () {
        shipmentFactory.getShipmentListByStatus($scope.user.userId, $scope.status)
            .success(function (data) {
                // Set data
            })
            .error(function (data) {
                // Set error message
            });
    };

    $scope.setShipmentId = function (shipmentId) {
        shipmentFactory.setShipmentId(shipmentId);
    }

    $scope.saveShipment = function () {
        shipmentFactory.saveShipment($scope.shipment)
            .success(function (data) {
                // Set data
            })
            .error(function (data) {
                // Set error message
            });
    };

    $scope.updateShipment = function () {
        shipmentFactory.updateShipment($scope.shipment)
            .success(function (data) {
                // Set data
            })
            .error(function (data) {
                // Set error message
            });
    };

    $scope.deleteShipment = function () {
        shipmentFactory.deleteShipment($scope.shipment.shipmentId)
            .success(function (data) {
                // Set data
            })
            .error(function (data) {
                // Set error message
            });
    };

    $scope.requestPickup = function () {
        shipmentFactory.requestPickup($scope.user.userId, $scope.shipment.shipmentId)
            .success(function (data) {
                // Set data
            })
            .error(function (data) {
                // Set error message
            });
    };

    $scope.cancelPickup = function () {
        shipmentFactory.cancelPickup($scope.user.userId, $scope.shipment.shipmentId)
            .success(function (data) {
                // Set data
            })
            .error(function (data) {
                // Set error message
            });
    };

    $scope.reschedulePickup = function () {
        shipmentFactory.reschedulePickup(userId, shipmentId, pickupTime)
            .success(function (data) {
                // Set data
            })
            .error(function (data) {
                // Set error message
            });
    };

    $scope.getShipmentListBySender();
    $scope.getShipmentListByReceiver();

});