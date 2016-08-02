angular.module("Knowl").controller("MainCtrl", ["$scope", "$location", "$rootScope", "$window",
	function($scope, $location, $rootScope, $window) {
		$scope.role = "Medewerker";
		
		$scope.isManager = function() {
			if($scope.role === "Manager") {
				return true;
			} else {
				return false;
			}
		}	
		
		
	}
]);