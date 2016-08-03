angular.module("Knowl").controller("ProfielDetailCtrl", ["$scope", "$routeParams", "ProfilesService",
    function($scope, $routeParams, ProfilesService) {
		$scope.profiles = ProfilesService.getProfiles();
		$scope.id = $routeParams.id;
		$scope.profileDetails = [];
		$scope.competences = ProfilesService.getCompetences($scope.id);
		
		console.log($scope.profiles);
		for(var i = 0; i < $scope.profiles.length; i++) {
			if($scope.profiles[i].id == $scope.id) {
				$scope.profileDetails = $scope.profiles[i];
				return;
			}
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