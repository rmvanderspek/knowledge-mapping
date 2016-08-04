angular.module("Knowl").controller("ProfielDetailCtrl", ["$scope", "$routeParams", "ProfilesService", "$location",
    function($scope, $routeParams, ProfilesService, $location) {
		$scope.profiles = ProfilesService.getProfiles();
		$scope.id = parseInt($routeParams.id);
		
		$scope.profiles = ProfilesService.getProfiles();
		$scope.competences = ProfilesService.getCompetences();
		$scope.profileCompetences = ProfilesService.getProfileCompetences();
		$scope.userCompetences = ProfilesService.getUserCompetences();

		$scope.getProfileDetails = function() {
			for(var i = 0; i < $scope.profiles.length; i++) {
				if(parseInt($scope.profiles[i].id) === $scope.id) {
					return $scope.profiles[i];
				}
			}
		};
		
		$scope.selectCompetence = function(id){
			for(var i = 0; i < $scope.competences.length; i++){
				if($scope.competences[i].id === id){
					return $scope.competences[i];
				}
			}
		}
		
		$scope.getCompetences = function(){
			var showcompetences = [];
			 
			 for(var i = 0; i < $scope.userCompetences.length; i++){
				 
				 for(var j = 0; j < $scope.profileCompetences.length; j++){
					 
					 if(parseInt($scope.userCompetences[i].competenceId) === parseInt($scope.profileCompetences[j].competenceId)){
						 var competence = $scope.selectCompetence($scope.userCompetences[i].competenceId);
						 
						 showcompetences.push({
							 name : competence.name,
							 description : competence.description,
							 level : $scope.userCompetences[i].competenceLevel,
							 id : $scope.userCompetences[i].competenceId
							 });
						 
					 }
				 }
			 }
			 return showcompetences;
		}
		
		$scope.showCompetences = $scope.getCompetences();
	
		
		$scope.profileDetails = $scope.getProfileDetails();
		
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
		
		$scope.showName = function(name, level) {
			if(level > 30) {
				return name + ":";
			} else {
				return "";
			}
		}
		
		$scope.showHeadingTitle = function(name, level) {
			if(level <= 30) {
				return name;
			} else {
				return "";
			}
		}
		
		$scope.decrease = function(id) {
			for(var i = 0; i < $scope.competences.length; i++) {
				if(parseInt(id) === $scope.competences[i].comp_id) {
					if($scope.competences[i].level >= 5) {
						$scope.competences[i].level -= 5;
					}
					return;
				}
			}
		}
		
		$scope.increase = function(id) {
			for(var i = 0; i < $scope.competences.length; i++) {
				if(parseInt(id) === $scope.competences[i].comp_id) {
					if($scope.competences[i].level <= 95) {
						$scope.competences[i].level += 5;
					}
					return;
				}
			}
		}
		
		// Navigation
		$scope.navigate = {};
		
		$scope.navigate.back = function() {
			$location.path("persoonlijk-profiel");
		}
	} 
]);