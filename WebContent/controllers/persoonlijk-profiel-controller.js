angular.module("Knowl").controller("PersoonlijkProfielCtrl", ["$scope", "$routeParams", "ProfilesService",
    function($scope, $routeParams, ProfilesService) {
		$scope.profiles = ProfilesService.getProfiles();
		
		if($routeParams.id !== null) {
			$scope.profileDetails = $scope.profiles[$routeParams.id];
		}
		
		$scope.getCompetences = function(profile_id) {
			return ProfilesService.getCompetences(profile_id);
		}
		
		$scope.getBarClass = function(level) {
			if(level < 30) {
				return "progress-bar progress-bar-danger progress-bar-striped";
			} else if(level < 60) {
				return "progress-bar progress-bar-warning progress-bar-striped";
			} else if(level < 90) {
				return "progress-bar progress-bar-info progress-bar-striped";
			} else {
				return "progress-bar progress-bar-success progress-bar-striped";
			}
		}
		
	} 
]);