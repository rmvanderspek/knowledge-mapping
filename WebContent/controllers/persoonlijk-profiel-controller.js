angular.module("Knowl").controller("PersoonlijkProfielCtrl", ["$scope", "$routeParams", "ProfilesService", "$rootScope", "$interval",
    function($scope, $routeParams, ProfilesService, $rootScope, $interval) {
		$scope.allProfiles;
		$scope.profiles;

		// Load data
		$scope.loadData = function() {
			$scope.allProfiles = ProfilesService.getAllProfiles();
			$scope.profiles = ProfilesService.getProfiles();
		}
		
		// Check if the data is loaded otherwise keep checking until data is loaded
		if($rootScope.loaded) {
			console.log("hier");
			$scope.loadData();
		}else {
			$rootScope.$on("loadedEvent", function(event, data) {
				$interval(function() {
					$scope.loadData();
					
				}, 100, 10);
			});
		}
		
		
		
		$scope.popAddCompetence = false;
		$scope.popAddKnowledgeProfile = false;
		
		$scope.selected = "";
		$scope.competenceName = "";
		$scope.competenceDescription = "";
		
		$scope.select = function(index) {

			if($rootScope.loaded) {
				$scope.selected = $scope.allProfiles[index].name;
			}
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