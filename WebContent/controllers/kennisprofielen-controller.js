angular.module("Knowl").controller("KennisProfielenCtrl", ["$scope", "$location", "$rootScope", "ProfilesService",                                  
	function($scope, $location, $rootScope, ProfilesService) {
	$scope.profiles = ProfilesService.getAllProfiles();
	$scope.showCompetences = [];
	$scope.selectedProfile = {};
	$scope.newName = "";
	$scope.selectedOption = 0;
	
	$scope.showChangeName = false;
	$scope.showChangeDesc = false;
	$scope.moveProfile = false;
	$scope.copyProfile = false;
	$scope.deleteComp = false;
	
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
							if(parseInt(competences[j].id) === parseInt(profileCompetences[i].competences) && parseInt(profileCompetences[i].profile_id) === parseInt(profileid)){
								showCompetences.push({
									 name : competences[j].name,
									 description : competences[j].description,
									 id : parseInt(competences[j].id)
									});
							}
						}
					}
			return showCompetences;
		};
		
		$scope.fillShowCompetences = function(){
			for (var i = 0; i < $scope.profiles.length; i++){
				var id = $scope.profiles[i].prof_comp_table_id;

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
			$scope.fillShowCompetences();
		};
		
		$scope.saveNewName = function(competenceid, newName){
			changeCompetenceName(competenceid, newName);
		};
		
		$scope.saveNewDescription = function(competenceid, newDescription){
			changeCompetenceDescription(competenceid, newDescription);
		};
		
		$scope.change = function(){
			if(parseInt($scope.selectedOption) === 1){
				$scope.showChangeName = true;
				$scope.showChangeDesc = false;
				$scope.moveProfile = false;
				$scope.copyProfile = false;
				$scope.deleteComp = false;
			} 
			else if ($scope.selectedOption === 2){
				$scope.showChangeName = false;
				$scope.showChangeDesc = true;
				$scope.moveProfile = false;
				$scope.copyProfile = false;
				$scope.deleteComp = false;
			} 
			else if ($scope.selectedOption === 3){
				$scope.showChangeName = false;
				$scope.showChangeDesc = false;
				$scope.moveProfile = true;
				$scope.copyProfile = false;
				$scope.deleteComp = false;
			} 
			else if ($scope.selectedOption === 4){
				$scope.showChangeName = false;
				$scope.showChangeDesc = false;
				$scope.moveProfile = false;
				$scope.copyProfile = true;
				$scope.deleteComp = false;
			} 
			else if ($scope.selectedOption === 5){
				$scope.showChangeName = false;
				$scope.showChangeDesc = false;
				$scope.moveProfile = false;
				$scope.copyProfile = false;
				$scope.deleteComp = true;
			} 
			else {
				$scope.showChangeName = false;
				$scope.showChangeDesc = false;
				$scope.moveProfile = false;
				$scope.copyProfile = false;
				$scope.deleteComp = false;
			}
		};
	}
]);
