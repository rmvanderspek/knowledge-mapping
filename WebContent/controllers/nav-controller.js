angular.module("Knowl").controller("NavCtrl",
	function($scope) {
	$scope.link0 = "active";
	$scope.link1 = "";
	
		$scope.activate = function(option) {
			$scope.link0 = "";
			$scope.link1 = "";
			
			if(option === 0) {
				$scope.link0 = "active";
			} else if(option === 1) {
				$scope.link1 = "active";
			}
		}
	}
);