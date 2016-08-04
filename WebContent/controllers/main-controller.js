angular.module("Knowl").controller("MainCtrl", ["$scope", "$location", "$rootScope", "$window", "ProfilesService",
	function($scope, $location, $rootScope, $window, ProfilesService) {
		$scope.role = "Medewerker";
<<<<<<< HEAD
		$scope.username = "hli24213";
=======
		$scope.username = "rsp21473";
>>>>>>> 8a7a9b8dc1724fb1cd7babb2ae4a08b6911f6f7e
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