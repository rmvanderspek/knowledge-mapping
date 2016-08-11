angular.module("Knowl").controller("MedewerkersProfielenCtrl", ["$scope", "$location", "$rootScope", "ProfilesService",                                  
	function($scope, $location, $rootScope, ProfilesService) {

		$scope.users = ProfilesService.getUsers();
		$scope.profiles = ProfilesService.getUsersWithProfiles();
		$scope.usersWithProfiles = [];
		$scope.userCompetences = ProfilesService.getAllUserCompetences();
		$scope.profileCompetences = ProfilesService.getProfileCompetences();
		$scope.competences = ProfilesService.getCompetences();
		$scope.availability = ProfilesService.getUserAvailability();

		$scope.selectCompetence = function(id){
			for(var i = 0; i < $scope.competences.length; i++){
				if($scope.competences[i].id === id){
					return $scope.competences[i];
				}
			}
		};
		
		$scope.getCompetences = function(profileid, userid){
			var showcompetences = [];
			 for(var i = 0; i < $scope.userCompetences.length; i++){
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
				competences = $scope.getCompetences($scope.profiles[userid][j].profileCompetenceTableId, userid);
				total = competences.length;
				levelTotal = 0;
				
				for(var y = 0; y < competences.length; y++){
					levelTotal = levelTotal + competences[y].level;
				}
				
				profileswithcompetences.push({
					profilename : profiles[j].name,
					profileLevel : (levelTotal / total).toFixed(0),
					competences : competences
				});
			}		
			
			availableDate = "";
			for(var k = 0; k < $scope.availability.length; k++){
				if($scope.availability[k].userid === userid){
					availableDate = $scope.availability[k].dateString;
					var dateParts = availableDate.split("/");
					var date = new Date(dateParts[2], (dateParts[1] -1), dateParts[0]);
					
					if(new Date > date){
						console.log(true)
						availableDate = "Beschikbaar!";
					} 
				}
			}
			
			$scope.usersWithProfiles.push({
					firstname : $scope.users[i].firstName,
					lastname : $scope.users[i].lastName,
					available:	availableDate,
					profiles : profileswithcompetences						
				});
		};
		
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
		
		$scope.showName = function(name, level) {
			if(level > 30) {
				return name + ":";
			} else {
				return "";
			}
		};
		
		$scope.showHeadingTitle = function(name, level) {
			if(level <= 30) {
				return name;
			} else {
				return "";
			}
		};
		
	}
]);
