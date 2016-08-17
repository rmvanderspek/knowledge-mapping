//Service to return an array of profiles

angular.module("Knowl").service("ProfilesService", ["$http", "$rootScope",
    function($http, $rootScope) {
		var profiles = [];
		var allProfiles = [];
		var user = "";

		var competences = [];
		var profilecompetences = [];
		var usercompetences = [];
		var users = [];
		var usersWithProfiles = [];
		var allUserCompetences = [];
		var allUserAvailability = [];
		
		
		connect = function(username){
			if(user === "" || username != null) {
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
			 		if(data != null){
			 			profiles = data.profile;
			 		}
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
			 		if (data != null){
				 		usercompetences = data.profile;
			 		}
			 		return data;
			 })
			 	.error(function(data, status) {
			 		alert("Error");
			 });
			 
			 $http({
				 method : 'GET',
				 url : "resources/getusers/",
			 })
			 .success(function(data, status){
				 users = data.profile;
				 return data;
			 })
			 .error(function(data, status){
				 alert("Error");
			 });
			 
			 $http({
				 method : 'GET',
				 url : "resources/getuserswithprofiles"
			 })
			 .success(function(data, status){
				 usersWithProfiles = data;
				 return data;
			 })
			 .error(function(data, status){
				 alert("Error");
			 });
			 
			 $http({
				 method: 'GET',
				 url : "resources/getallusercompetences"
			 })
			 .success(function(data, status){
				 allUserCompetences = data.profile;
			 })
			 .error(function(data, status){
				 alert("Error");
			 });
			 
			 $http({
				 method: 'GET',
				 url : "resources/getallavailability"
			 })
			 .success(function(data, status){
				 allUserAvailability = data.profile;
			 })
			 .error(function(data, status){
				 alert("Error");
			 });
			 
		
			 $rootScope.loaded = true;
			 $rootScope.$broadcast("loadedEvent", "data");
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
				 .success(function(data, status){
				 connect();
				 return data;
			 })
			 	.error(function(data, status) {
			 		alert("Error");
			 });
		};
		
		saveCompetence = function(object, username){
			 $http({
				 method : 'POST', 
				 url : "resources/addcompetence/",
				 dataType : "json",
				 contentType : "application/json; charset=utf-8",
				 params : {"userid" : username },
				 data : {data : object}
				    })
			 	.success(function(data, status) {
			 		connect();
			 		return data;
			 })
			 	.error(function(data, status) {
			 		alert("Error");
			 });
		};
		
		changeCompetenceProfile = function(competenceid, profileid){
			$http({
				method 	: 'POST',
				url 	: "resources/changecompetenceprofile/",
				dataType : "json",
				contentType : "application/json; charset = utf-8",
				data : {"competenceid" : competenceid,
						"profileid" : profileid}			
			})
			.success(function(data, status){
				connect();
				return true;
			})
			.error(function(data, status){
				alert("Error");
			});
		};
		
		changeCompetenceName = function(competenceid, newName){
			$http({
				method 	:  'POST',
				url 	: 	"resources/changecompetencename/",
				dataType : "json",
				contentType : "application/json; charset = utf-8",
				data: {"competenceid" : competenceid,
					"competencename" : newName}
			})
			.success(function(data, status){
				connect();
			})
			.error(function(data, status){
				alert("Error");
			});
		};
		
		changeCompetenceDescription = function(competenceid, newDescription){
			$http({
				method	:	'POST',
				url		: 	"resources/changecompetencedescription/",
				dataType : "json",
				contentType : "application/json; charset = utf-8",
				data 	: {"competenceid" : competenceid,
					"competencedescription" : newDescription}
			})
			.success(function(data, status){
				connect();
			})
			.error(function(data, status){
				alert("Error");
			});
		};
				 
		saveDate = function(date){
			$http({
				method : 'POST',
				url	: "resources/savedate/",
				dataType : "json",
				contentType : "application/json; charset = utf-8",
				 data : {"userid" : user,
					 "date" : date}

			})
			.success(function(data, status){
				connect();
			})
			.error(function(data, status){
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
			},
			getUsers: function(){
				return users;
			},
			getUsersWithProfiles: function(){
				return usersWithProfiles;
			},
			getAllUserCompetences: function(){
				return allUserCompetences;
			},
			getUserAvailability: function(){
				return allUserAvailability;
			}
		
		};
	}
]);