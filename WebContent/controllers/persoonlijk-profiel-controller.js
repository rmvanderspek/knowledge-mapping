angular.module("Knowl").controller("PersoonlijkProfielCtrl",
		[ "$scope", "$http", "$routeParams", function($scope, $http, $routeParams) {
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
					description: "Een Java ontwikkelaar houd zich bezig met het ontwikkelen van de business logica  " +
							"van applicaties (back-end).  Hij of zij is bovendien " +
							"verantwoordelijk voor het onderhoud en het testen van de applicatie en het " +
							"optimaliseren hiervan.",
					id : 0
				}, {
					name : "Front-end developer",
					description: "Een Front-end developer " +
							"is verantwoordelijk is voor de technische kant van de visuele (voor)kant " +
							"van een applicatie, website of programma. Hij of zij zorgt voor de verbinding tussen " +
							"front-end (en het gebruik daarvan) en de back-end (waar de business logica plaatsvindt).",
					id : 1
				} ];
				
			};
			$scope.profiles = $scope.getProfiles();
			
			$scope.profileDetails = $scope.profiles[$routeParams.id];

		} ]);