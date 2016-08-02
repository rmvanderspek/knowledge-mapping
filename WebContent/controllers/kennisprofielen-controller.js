angular.module("Knowl").controller("KennisProfielenCtrl", ["$scope", "$location", "$controller", "$timeout",
	function($scope, $location, $controller, $timeout) {
		// Forward if user is not a manager
		if(!$scope.$parent.isManager()) {

			$location.path("/");
			var navCtrlProxy = $scope.$new();
			$controller("NavCtrl", {$scope : navCtrlProxy});
			navCtrlProxy.activate(0);
			
		}
	}
]);
