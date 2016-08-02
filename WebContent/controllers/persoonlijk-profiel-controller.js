angular.module("Knowl").controller("PersoonlijkProfielCtrl",
		[ "$scope", "$http", function($scope) {
			$scope.getProfiles = function() {
//				 As long as this doesn't work return a mock-array
//				 $http({method : 'GET', url : "resources/personalprofiles"})
//				 .success(function(data, status) {
//				 return data;
//				 })
//				 .error(function(data, status) {
//				 alert("Error");
//				 return null;
//				 });

				// Mock array
				return [ {
					name : "Java Engineer",
					id : 0
				}, {
					name : "Front-end developer",
					id : 1
				} ];
				
			};
			$scope.profiles = $scope.getProfiles();

		} ]);