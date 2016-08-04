angular.module("Knowl").controller("ProfielDetailCtrl", ["$scope", "$routeParams", "ProfilesService", "$location",
    function($scope, $routeParams, ProfilesService, $location) {
		$scope.profiles = ProfilesService.getProfiles();
		$scope.id = parseInt($routeParams.id);
		
		$scope.competences = ProfilesService.getCompetences($scope.id);
		
		$scope.getProfileDetails = function() {
			for(var i = 0; i < $scope.profiles.length; i++) {
				if(parseInt($scope.profiles[i].id) === $scope.id) {
					return $scope.profiles[i];
				}
			}
		}
		
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