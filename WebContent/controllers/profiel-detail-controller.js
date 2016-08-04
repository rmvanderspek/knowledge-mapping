angular.module("Knowl").controller("ProfielDetailCtrl", ["$scope", "$routeParams", "ProfilesService",
    function($scope, $routeParams, ProfilesService) {
		
		$scope.id = parseInt($routeParams.id);
		
		$scope.profiles = ProfilesService.getProfiles();
		$scope.competences = ProfilesService.getCompetences();
		$scope.profileCompetences = ProfilesService.getProfileCompetences();
		$scope.userCompetences = ProfilesService.getUserCompetences();
		
		console.log($scope.profileCompetences);
		console.log($scope.competences);
		console.log($scope.userCompetences);
		
		console.log($scope.profiles );

		$scope.getProfileDetails = function() {
			for(var i = 0; i < $scope.profiles.length; i++) {
				if(parseInt($scope.profiles[i].id) === $scope.id) {
					return $scope.profiles[i];;
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
			 console.log($scope.userCompetences.length);
			 for(var i = 0; i < $scope.userCompetences.length; i++){
				 console.log("i step " + i);
				 for(var j = 0; j < $scope.profileCompetences.length; j++){
					 console.log("j step " + j);
					 if(parseInt($scope.userCompetences[i].competenceId) === parseInt($scope.profileCompetences[j].competenceId)){
						 var competence = $scope.selectCompetence($scope.userCompetences[i].competenceId);
						 console.log("EQUAL");
						 showcompetences.push({
							 name : competence.name,
							 description : competence.description,
							 level : $scope.userCompetences[i].competenceLevel,
							 id : $scope.userCompetences[i].competenceId
							 });
						 console.log(showcompetences);
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
		};
	} 
]);