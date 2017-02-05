/**
 * Created by ujjsingh on 12/7/15.
 */
packman.factory("userFactory", ['$http', function($http) {

    var factoryInstance = {};

    factoryInstance.getUserById = function(userId) {
        return $http({
            method: 'GET',
            url:'/person/user/'+userId
        });
    };

    factoryInstance.saveUser = function(user) {
        return $http({
            method: 'POST',
            url:'/person/user/save',
            data: user
        });
    };

    factoryInstance.updateUser = function(user) {
        return $http({
            method: 'POST',
            url:'/person/user/update',
            data: user
        });
    };

    factoryInstance.removeUser = function(userId) {
        return $http({
            method: 'DELETE',
            url:'/person/user/'+userId
        });
    };

    return factoryInstance;
    
}]);