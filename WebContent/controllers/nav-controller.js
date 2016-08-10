angular.module("Knowl").controller("NavCtrl", ["$scope", "$rootScope", 
	function($scope, $rootScope) {
		$scope.link0 = "active";
		$scope.link1 = "";
		$scope.link2 = "";
		$scope.link3 = "";
		
		
		$scope.managerNavigation = "";
		
		// Change rol defined in main-controller
		$scope.changeRole = function(argument) {
			$scope.$parent.role = argument;
		}
		
		// Add navigation option if user is a manager
		$scope.setManagerMenu = function() {
			if($scope.$parent.isManager()) {
				$scope.managerNavigation = 'Kennisprofielen';
				$scope.managerNavigation2 = 'Medewerkesprofielen';
			} else {
				$scope.managerNavigation = "";
				$scope.managerNavigation2 = "";
			}
		}
	
		// Change class to active when clicked
		$rootScope.activate = function(option) {
			$scope.link0 = "";
			$scope.link1 = "";
			$scope.link2 = "";
			$scope.link3 = "";
			
			if(option === 0) {
				$scope.link0 = "active";
			} else if(option === 1) {
				$scope.link1 = "active";
			} else if(option === 2) {
				$scope.link2 = "active";
			} else if(option === 3) {
				$scope.link3 = "active";
			}
			
			
		}
		
	}
]);