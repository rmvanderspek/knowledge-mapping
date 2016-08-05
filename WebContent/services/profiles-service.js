//Service to return an array of profiles

angular.module("Knowl").service("ProfilesService", ["$http", "$rootScope",
    function($http, $rootScope) {
		var profiles = [];
		var allProfiles = [];
		var user = "";

		var competences = [];
		var profilecompetences = [];
		var usercompetences = [];
		
		
		connect = function(username){
			if(user === "") {
				user = username;
			}
			$http({
				 method : 'GET', 
				 url : "resources/profiles/"})
			 	.success(function(data, status) {
			 		allProfiles = data.profile;
			 		return data;
			 })
			 	.error(function(data, status) {
			 		alert("Error");
			 });
			
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
			 		return data;
			 })
			 	.error(function(data, status) {
			 		alert("Error");
			 });
			 
			 $rootScope.loaded = true;
			 $rootScope.$broadcast("loadedEvent", "data");

		};
		

		save = function(array, username){
			 
			 var promise = $http({

				 method : 'POST', 
				 url : "resources/saveusercompetences/",
				 dataType : "json",
				 contentType : "application/json; charset=utf-8",
				 params : {"userid" : username },
				 data : {data : array}
				    });
			 
			 promise.error(function(data, status) {
			 		alert("Error");
			 });

			 return promise;
			 
		};
		
		saveCompetence = function(object, username){
			//console.log(array);
			 $http({
				 method : 'POST', 
				 url : "resources/addcompetence/",
				 dataType : "json",
				 contentType : "application/json; charset=utf-8",
				 params : {"userid" : username },
				 data : {data : object}
				    })
			 	.success(function(data, status) {
			 		console.log(data);
			 		return data;
			 })
			 	.error(function(data, status) {
			 		alert("Error");
			 });
		};
			 
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
			},
			getAllProfiles: function() {
				return allProfiles;
			}
	    };
	}
]);