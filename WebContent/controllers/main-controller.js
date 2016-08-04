angular.module("Knowl").controller("MainCtrl", ["$scope", "$location", "$rootScope", "$window",
	function($scope, $location, $rootScope, $window) {
		$scope.role = "Medewerker";
		$scope.username = "rsp21473";
		$scope.isManager = function() {
			if($scope.role === "Manager") {
				return true;
			} else {
				return false;
			}
		};
		
		$scope.connect = function(){
			connect($scope.username);
		};
		
		
	}
]);