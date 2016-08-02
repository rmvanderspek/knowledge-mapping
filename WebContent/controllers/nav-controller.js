angular.module("Knowl").controller("NavCtrl", ["$scope", "$timeout", 
	function($scope, $timeout) {
		$scope.link0 = "active";
		$scope.link1 = "";
		$scope.link2 = "";
		
		
		$scope.managerNavigation = "";
		
		// Change rol defined in main-controller
		$scope.changeRole = function(argument) {
			$scope.$parent.role = argument;
		}
		
		// Add navigation option if user is a manager
		$scope.setManagerMenu = function() {
			if($scope.$parent.isManager()) {
				$scope.managerNavigation = 'Kennisprofielen';
				
			} else {
				$scope.managerNavigation = "";
			}
		}
	
		// Change class to active when clicked
		$scope.activate = function(option) {
			$scope.link0 = "";
			$scope.link1 = "";
			$scope.link2 = "";
			
			if(option === 0) {
				$scope.link0 = "active";
			} else if(option === 1) {
				$scope.link1 = "active";
			} else if(option === 2) {
				$scope.link2 = "active";
			}
			$timeout(function() {
				$scope.$apply();
			});
			console.log($scope.link0);
			
		}
		
		$scope.refresh = function() {
			console.log($scope.link0);
		}
		
	}
]);