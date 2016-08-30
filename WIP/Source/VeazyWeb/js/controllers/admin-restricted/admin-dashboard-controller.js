;(function() {
	'use strict';
	var adminDashboadCtrl = function($scope, $state, veazyConfig, ChartService) {
		var CODE = veazyConfig.CODE;
		ChartService.getSystemStats().then(function(response) {
			switch (response.code) {
				case CODE.SUCCESS: {
					$scope.stats = response.data;
					break;
				}
				case CODE.UNAUTHORIZED: {
					$state.go('login');
					break;
				}
				case CODE.NO_PERMISSION: {
					$state.go('forbidden');
					break;
				}
				default: {

				}
			}
		});
	};

	adminDashboadCtrl.$inject = ['$scope', '$state', 'veazyConfig', 'ChartService'];
	angular.module('veazyControllers').controller('adminDashboadCtrl', adminDashboadCtrl);
})();