angular.module("Knowl").controller("PersoonlijkProfielCtrl", ["$scope", "$routeParams", "ProfilesService",
    function($scope, $routeParams, ProfilesService) {
		$scope.profiles = ProfilesService.getProfiles();
		$scope.popAddCompetence = false;
		
		$scope.showAddCompetence = function() {
			$scope.popAddCompetence = !$scope.popAddCompetence;
		}
		
		// Save a new competence --> needs to be implemented
		$scope.save = function() {
			$scope.newCompetenceName = "";
			$scope.newCompetenceDescription = "";
			$scope.popAddCompetence = false;
		};
		
	} 
]);