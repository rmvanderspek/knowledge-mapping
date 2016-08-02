var app = angular.module("Knowl", ["ngRoute"]);

function routeConfig($routeProvider, $locationProvider){
	$routeProvider.when('/', 
		{
			templateUrl: "views/home.html",
			controller: "HomeCtrl"
		}	
	).when('/persoonlijk-profiel',
		{
			templateUrl: "views/persoonlijk-profiel.html",
			controller: "PersoonlijkProfielCtrl"
		}
	).when('/kennisprofielen',
		{
			templateUrl: "views/kennisprofielen.html",
			controller: "KennisProfielenCtrl"
		}
	).otherwise(
		{
			redirectTo: "/"
		});
	
	$locationProvider.html5Mode({enabled: true, requireBase: false});
};

// Bind the route configuration to the app module
app.config(routeConfig);






