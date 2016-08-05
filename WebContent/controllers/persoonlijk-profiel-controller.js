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
			$scope.loadData();
		}else {
			$rootScope.$on("loadedEvent", function(event, data) {
				$interval(function() {
					$scope.loadData();
					
				}, 100, 7);
			});
		}
		
		
		
		$scope.popAddCompetence = false;
		$scope.popAddKnowledgeProfile = false;
		
		$scope.selected = "";
		
		$scope.select = function(index) {
			if($scope.loaded) {
				$scope.selected = $scope.allProfiles[index].name;
			}
		}
		
		$scope.showAddCompetence = function() {
			$scope.popAddKnowledgeProfile = false; // Make sure the other is closed
			$scope.popAddCompetence = !$scope.popAddCompetence;
		}
		
		$scope.showAddKnowledgeProfile = function() {
			$scope.popAddCompetence = false; // Make sure the other is closed
			$scope.popAddKnowledgeProfile = !$scope.popAddKnowledgeProfile;
		}
		
		// Save a new competence --> needs to be implemented
		$scope.save = function() {
			$scope.newCompetenceName = "";
			$scope.newCompetenceDescription = "";
			$scope.popAddCompetence = false;
		};
		
		
	} 
]);