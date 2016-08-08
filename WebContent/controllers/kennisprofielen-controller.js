angular.module("Knowl").controller("KennisProfielenCtrl", ["$scope", "$location", "$rootScope", "ProfilesService",                                  
	function($scope, $location, $rootScope, ProfilesService) {
	$scope.profiles = ProfilesService.getAllProfiles();
	$scope.showCompetences = [];
	$scope.selectedProfile = {};
	
	isLoaded = false;
		// Forward if user is not a manager
	competences = ProfilesService.getCompetences();
	profileCompetences = ProfilesService.getProfileCompetences();
		if(!$scope.$parent.isManager()) {
			
			$location.path("/");
			$rootScope.activate(0); // Activate 'Home' button
			
		} else {
			$rootScope.activate(2); // Activate 'Kennis Profiel' button
		}
		
		$scope.getCompetencesOfProfile = function(profileid){
				showCompetences = [];
					for (var j = 0; j < competences.length; j++){			
						for (var i = 0; i < profileCompetences.length; i++){
							if(parseInt(competences[j].id) === parseInt(profileCompetences[i].competenceId) && parseInt(profileCompetences[i].profileId) === parseInt(profileid)){
								showCompetences.push({
									 name : competences[j].name,
									 description : competences[j].description,
									 id : parseInt(competences[i].id)
									});
							}
						}
					}
			return showCompetences;
		};
		
		$scope.fillShowCompetences = function(){
			for (var i = 0; i < $scope.profiles.length; i++){
				var id = $scope.profiles[i].profileCompetenceTableId;
				$scope.showCompetences.push({id : id,
					array : $scope.getCompetencesOfProfile(id)});
			}
		};
		
		$scope.returnCompetences = function(profileid){
			showCompetences = [];
			for (var i = 0; i < $scope.showCompetences.length; i++){
				if (parseInt($scope.showCompetences[i].id) === parseInt(profileid)){
					showCompetences = $scope.showCompetences[i].array;
				}
			}
			return showCompetences;
		};

		$scope.saveProfile = function(profileid, competenceid){
			console.log(profileid + "  -  " + competenceid)
			var success = changeCompetenceProfile(competenceid, profileid);

			$scope.fillShowCompetences();
		};
	}
]);
