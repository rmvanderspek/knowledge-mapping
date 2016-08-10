angular.module("Knowl").controller("MainCtrl", ["$scope", "$location", "$rootScope", "$window", "ProfilesService", 
	function($scope, $location, $rootScope, $window, $ProfilesService) {
		$scope.role = "Medewerker";

		$scope.username = "";

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