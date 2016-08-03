//Service to return an array of profiles

angular.module("Knowl").service("ProfilesService", ["$http", 
    function($http) {
		var profiles = [];
		
		// Mock array
//		if(profiles.length === 0) {
//			profiles = [ {
//				name : "Java Engineer",
//				description: "Een Java ontwikkelaar houd zich bezig met het ontwikkelen van de business logica  " +
//						"van applicaties (back-end).  Hij of zij is bovendien " +
//						"verantwoordelijk voor het onderhoud en het testen van de applicatie en het " +
//						"optimaliseren hiervan.",
//				id : 0
//			}, {
//				name : "Front-end developer",
//				description: "Een Front-end developer " +
//						"is verantwoordelijk is voor de technische kant van de visuele (voor)kant " +
//						"van een applicatie, website of programma. Hij of zij zorgt voor de verbinding tussen " +
//						"front-end (en het gebruik daarvan) en de back-end (waar de business logica plaatsvindt).",
//				id : 1
//			} ];
			
//			 As long as this doesn't work return a mock-array
		
		
			 $http({method : 'GET', url : "resources/profiles"})
			 	.success(function(data, status) {
			 		profiles = data.profile;
			 		console.log(profiles);
			 		//return data;
			 })
			 	.error(function(data, status) {
			 		alert("Error");
			 });
			
			
		
		
		return {
	        getProfiles: function() {
	        	return profiles;
	        }
	    };
	}
]);