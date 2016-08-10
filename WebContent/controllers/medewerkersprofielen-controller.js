angular.module("Knowl").controller("MedewerkersProfielenCtrl", ["$scope", "$location", "$rootScope", "ProfilesService",                                  
	function($scope, $location, $rootScope, ProfilesService) {

		$scope.users = ProfilesService.getUsers();
		$scope.profiles = ProfilesService.getUsersWithProfiles();
		$scope.usersWithProfiles = [];
		$scope.userCompetences = ProfilesService.getAllUserCompetences();
		$scope.profileCompetences = ProfilesService.getProfileCompetences();
		$scope.competences = ProfilesService.getCompetences();

		$scope.selectCompetence = function(id){
			for(var i = 0; i < $scope.competences.length; i++){
				if($scope.competences[i].id === id){
					return $scope.competences[i];
				}
			}
		};
		
		$scope.getCompetences = function(profileid, userid){
			var showcompetences = [];
			 console.log($scope.userCompetences)
			 console.log($scope.profileCompetences)
			 for(var i = 0; i < $scope.userCompetences.length; i++){
				// console.log("dit " + $scope.userCompetences[i].competenceId + " plus " + $scope.profileCompetences[i].competenceId + " en " + $scope.profileCompetences[i].profileId + " plus " + profileid)
				 for(var j = 0; j < $scope.profileCompetences.length; j++){
					 if(parseInt($scope.userCompetences[i].competenceId) === parseInt($scope.profileCompetences[j].competenceId) 
							 && parseInt($scope.profileCompetences[j].profileId) === profileid 
							 && $scope.userCompetences[i].personId === userid){
						 
						 var competence = $scope.selectCompetence($scope.userCompetences[i].competenceId);
						 
						 showcompetences.push({
							 name : competence.name,
							 description : competence.description,
							 level : parseInt($scope.userCompetences[i].competenceLevel),
							 id : parseInt($scope.userCompetences[i].competenceId)
							 });
					 }
				 }
			 }
			 return showcompetences;
		};
		
		for(var i = 0; i < $scope.users.length; i++){
			userid = $scope.users[i].userId;

			profiles = $scope.profiles[userid];
			profileswithcompetences = [];
			
			for(var j = 0; j < $scope.profiles[userid].length; j++){
				profileswithcompetences.push({
					profilename : profiles[j].name, 
					competences : $scope.getCompetences($scope.profiles[userid][j].profileCompetenceTableId, userid)
				})
			}
			console.log(profileswithcompetences)
			
			$scope.usersWithProfiles.push({
					firstname : $scope.users[i].firstName,
					lastname : $scope.users[i].lastName,
					
					profiles : profileswithcompetences
							
				});
		}

		console.log($scope.usersWithProfiles)
		
		
	//	$scope.userCompetences =  ProfilesService.getUserCompetences();

		
	}
]);
