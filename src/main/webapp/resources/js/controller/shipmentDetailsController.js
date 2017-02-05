/**
 * Created by ujjsingh on 12/2/15.
 */
packman.controller("shipmentDetailsController", function(shipmentFactory, $scope) {

    $scope.shipment = {};

    $scope.getShipment = function(){
        shipmentFactory.getShipmentById(shipmentFactory.getShipmentId())
            .success(function (data){
                $scope.shipment = data;
            })
            .error(function (data){
                // Set error message
            });
    };

    $scope.getShipment();

});