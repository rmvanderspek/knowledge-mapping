angular.module("Knowl").controller("PersoonlijkProfielCtrl", ["$scope", "$routeParams", "ProfilesService",
    function($scope, $routeParams, ProfilesService) {
		$scope.profiles = ProfilesService.getProfiles();
		
		if($routeParams.id !== null) {
			$scope.profileDetails = $scope.profiles[$routeParams.id];
		}
		

	} 
]);