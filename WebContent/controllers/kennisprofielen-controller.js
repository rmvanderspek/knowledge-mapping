angular.module("Knowl").controller("KennisProfielenCtrl", ["$scope", "$location", "$rootScope",
	function($scope, $location, $rootScope) {
		// Forward if user is not a manager
		if(!$scope.$parent.isManager()) {
			
			$location.path("/");
			$rootScope.activate(0); // Activate 'Home' button
			
		} else {
			$rootScope.activate(2); // Activate 'Kennis Profiel' button
		}
	}
]);
