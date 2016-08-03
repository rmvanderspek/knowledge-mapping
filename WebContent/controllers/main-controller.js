angular.module("Knowl").controller("MainCtrl", ["$scope", "$location", "$rootScope", "$window", "ProfilesService",
	function($scope, $location, $rootScope, $window, ProfilesService) {
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