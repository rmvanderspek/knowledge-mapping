//Service to return an array of profiles

angular.module("Knowl").service("ProfilesService", ["$http", 
    function($http) {
		var profiles = [];
		var allProfiles = [];
		var user = "rsp21473";

		var competences = [];
		var profilecompetences = [];
		var usercompetences = [];
		
		
		connect = function(username){
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
				 params: {"userid" : username }})
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
				 params: {"userid" : username }})
			 	.success(function(data, status) {
			 		usercompetences = data.profile;
			 		
			 		return data;
			 })
			 	.error(function(data, status) {
			 		alert("Error");
			 });

		};

		save = function(array, username){
			 $http({
				 method : 'POST', 
				 url : "resources/saveusercompetences/",
				 dataType : "json",
				 contentType : "application/json; charset=utf-8",
				 params : {"userid" : username },
				 data : {data : array}
				    })
			 	.success(function(data, status) {
			 		console.log(data);
			 		return data;
			 })
			 	.error(function(data, status) {
			 		alert("Error");
			 });
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