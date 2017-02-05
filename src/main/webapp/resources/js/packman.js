/**
 * Created by ujjsingh on 11/23/15.
 */
var packman = angular.module("packman", ['ngRoute']);

packman.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
        when('/', {
            templateUrl: 'shipmentlist.html',
            controller: 'shipmentListController'
        }).
        when('/shipmentdetails', {
            templateUrl: 'shipmentdetails.html',
            controller: 'shipmentDetailsController'
        }).
        otherwise({
            redirectTo: '/'
        });
    }]);