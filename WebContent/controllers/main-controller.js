angular.module("Knowl").controller("MainCtrl", 
	function($scope) {
		$scope.role = "Medewerker";
		
		$scope.isManager = function() {
			if($scope.role === "Manager") {
				return true;
			} else {
				return false;
			}
		}			
	}
);