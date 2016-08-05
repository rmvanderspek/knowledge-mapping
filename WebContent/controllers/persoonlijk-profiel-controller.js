angular.module("Knowl").controller("PersoonlijkProfielCtrl", ["$scope", "$routeParams", "ProfilesService",
    function($scope, $routeParams, ProfilesService) {
		$scope.profiles = ProfilesService.getProfiles();
		$scope.popAddCompetence = false;
		$scope.popAddKnowledgeProfile = false;
		$scope.allProfiles = ProfilesService.getAllProfiles();
		$scope.selected = "";
		$scope.competenceName = "";
		$scope.competenceDescription = "";
		
		$scope.select = function(index) {
			$scope.selected = $scope.allProfiles[index].name;
		};
		
		$scope.showAddCompetence = function() {
			$scope.popAddKnowledgeProfile = false; // Make sure the other is closed
			$scope.popAddCompetence = !$scope.popAddCompetence;
		};
		
		$scope.showAddKnowledgeProfile = function() {
			$scope.popAddCompetence = false; // Make sure the other is closed
			$scope.popAddKnowledgeProfile = !$scope.popAddKnowledgeProfile;
		};
		
		// Save a new competence --> needs to be implemented
		$scope.save = function() {
			saveCompetence({name : $scope.competenceName, description : $scope.competenceDescription}, $scope.username);
			$scope.newCompetenceName = "";
			$scope.newCompetenceDescription = "";
			$scope.popAddCompetence = false;
		};
		
	} 
]);