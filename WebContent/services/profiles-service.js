//Service to return an array of profiles

angular.module("Knowl").service("ProfilesService", ["$http", 
    function($http) {
		var profiles = [];
		var user = "hli24213";

		var competences = [];
		var profilecompetences = [];
		var usercompetences = [];
		
		
		
			 $http({
				 method : 'GET', 
				 url : "resources/getuserprofile/",
				 params: {"userid" : user }})
			 	.success(function(data, status) {
			 		profiles = data.profile;
			 		return data;
			 })
			 	.error(function(data, status) {
			 		alert("Error");
			 });
			
			 $http({
				 method : 'GET', 
				 url : "resources/getcompetences/"})
			 	.success(function(data, status) {
			 		competences = data.profile;
			 		console.log(competences);
			 		return data;
			 })
			 	.error(function(data, status) {
			 		alert("Error");
			 });
			
			 $http({
				 method : 'GET', 
				 url : "resources/getprofilecompetences/"})
			 	.success(function(data, status) {
			 		profilecompetences = data.profile;
			 		console.log(profilecompetences);
			 		return data;
			 })
			 	.error(function(data, status) {
			 		alert("Error");
			 });
			 
			 $http({
				 method : 'GET', 
				 url : "resources/getusercompetences/",
				 params: {"userid" : user }})
			 	.success(function(data, status) {
			 		usercompetences = data.profile;
			 		console.log(usercompetences);
			 		return data;
			 })
			 	.error(function(data, status) {
			 		alert("Error");
			 });

			 

			 
		// Mock array part II
		if(competences.length === 0) {
			competences = [ {
				name : "OCA",
				description: "Het behalen van OCA certificering.",
				profile_id : 1,
				level : 100
			}, {
				name : "OCP",
				description: "Het behalen van OCP certificering.",
				profile_id : 1,
				level : 100
			}, {
				name : "REST",
				description: "Kunnen werken met REST.",
				profile_id : 1,
				level : 60
			}, {
				name : "XML",
				description: "Kunnen werken met XML.",
				profile_id : 1,
				level : 20
			}, {
				name : "Leiderschap",
				description: "Het kunnen geven van leiding aan groepen.",
				profile_id : 8,
				level : 80
			}, {
				name : "SCRUM/Agile",
				description: "Het kunnen werken met SCRUM.",
				profile_id : 1,
				level : 40
			} ];
			
	//		 As long as this doesn't work return a mock-array
	//		 $http({method : 'GET', url : "resources/personalcompetences?userid=3333"})
	//		 .success(function(data, status) {
	//		 return data;
	//		 })
	//		 .error(function(data, status) {
	//		 alert("Error");
	//		 });
			
			
		}
		
		return {
	        getProfiles: function() {
	        	return profiles;
	        },
			getCompetences: function(profile_id) {
				return competences;
			},
			getProfileCompetences: function() {
				return profilecompetences;
			},
			getUserCompetences: function() {
				return usercompetences;
			}
	    };
	}
]);