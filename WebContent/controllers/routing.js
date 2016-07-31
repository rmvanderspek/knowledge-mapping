var app = angular.module("Knowl", ["ngRoute"]);

function emailRouteConfig($routeProvider, $locationProvider){
	$routeProvider.when('/', 
		{
			templateUrl: "views/home.html",
			controller: "HomeCtrl"
		}
	
	).otherwise(
		{
			redirectTo: "/"
		});
	$locationProvider.html5Mode({enabled: true, requireBase: false});
};

// Bind the route configuration to the app module
app.config(emailRouteConfig);






