angular.module("Knowl").controller("NavCtrl",
	function($scope) {
	$scope.link0 = "active";
	$scope.link1 = "";
	$scope.link2 = "";
	
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