angular.module("Knowl").controller("NavCtrl",
	function($scope) {
		$scope.link0 = "active";
		$scope.link1 = "";
		$scope.link2 = "";
		
		$scope.managerNavigation = "";
		
		// Change rol defined in main-controller
		$scope.changeRole = function(argument) {
			$scope.$parent.role = argument;
		}
		
		// Add navigation option if user is a manager
		$scope.showIfManager = function() {
			if($scope.$parent.isManager()) {
				$scope.managerNavigation='<li role="presentation" ng-class="link2" ng-click="activate(2)"><a href="">Kennisprofielen</a></li>');
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
		}
	}
);