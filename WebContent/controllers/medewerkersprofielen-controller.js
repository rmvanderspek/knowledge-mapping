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
					 if(parseInt($scope.userCompetences[i].comp_id) === parseInt($scope.profileCompetences[j].competences) 
							 && parseInt($scope.profileCompetences[j].profile_id) === profileid 
							 && $scope.userCompetences[i].user_id === userid){
						 
						 var competence = $scope.selectCompetence($scope.userCompetences[i].comp_id);
						 
						 showcompetences.push({
							 name : competence.name,
							 description : competence.description,
							 level : parseInt($scope.userCompetences[i].comp_level),
							 id : parseInt($scope.userCompetences[i].comp_id)
							 });
					 }
				 }
			 }
			 return showcompetences;
		};
	
		
		for(var i = 0; i < $scope.users.length; i++){
			userid = $scope.users[i].userid;
			profiles = $scope.profiles[userid];
			
			profileswithcompetences = [];
			
			for(var j = 0; j < $scope.profiles[userid].length; j++){
				competences = $scope.getCompetences($scope.profiles[userid][j].prof_comp_table_id, userid);
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
				if($scope.availability[k].user_id === userid){
					availableDate = $scope.availability[k].available_date;
					var dateParts = availableDate.split("/");
					var date = new Date(dateParts[2], (dateParts[1] -1), dateParts[0]);
					
					if(new Date > date){
						availableDate = "Beschikbaar!";
					} 
				}
			}
			
			$scope.usersWithProfiles.push({
					firstname : $scope.users[i].firstname,
					lastname : $scope.users[i].lastname,
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
