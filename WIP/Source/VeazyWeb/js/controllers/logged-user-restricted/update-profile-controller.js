;(function() {
	'use strict';
	var updateProfileCtrl = function($scope, $state, $translate, UserService, ngDialog, veazyConfig, localStorageService) {
		var CODE = veazyConfig.CODE;
		$scope.REGULATIONS = veazyConfig.REGULATIONS;

		//date picker config
		$scope.vm = {
			options: {
			    widgetPositioning: {
					horizontal: 'left',
					vertical: 'bottom'
				}
			}
		};
		var options = $scope.vm.options;
		var dob = $scope.ngDialogData.user.dob;
		var currentLang = $translate.use();
		options.defaultDate = dob ? moment(dob) : moment();

		if (currentLang === 'en') {
			options.locale = 'en-au';
			options.format = 'YYYY/MM/DD';
		} else if (currentLang === 'ja') {
			options.locale = 'ja';
			options.format = 'YYYY年MM月DD日';
		}

		//click update profile
		$scope.updateProfile = function() {
			var user = {
				firstName: $scope.firstName,
				lastName: $scope.lastName,
				dob: $scope.vm.date.valueOf(),
				hobby: $scope.hobby,
				address: $scope.address,
				bio: $scope.bio,
				website: $scope.website
			};

			UserService.updateProfile(user).then(function(response) {
				switch (response.code) {
					case CODE.SUCCESS: {
						ngDialog.close();
						$state.reload();
						break;
					}
					case CODE.UNAUTHORIZED: {
						$state.go('login');
						localStorageService.remove('username');
						break;
					}
					default: {
						//handling other case
					}
				}
			}, function(reject) {
				//handling error
			});

			// console.log($scope.vm.date.valueOf());
			// user.firstName = $scope.firstName;
			// user.lastName = $scope.lastName;
			// user.dob = $scope.vm.date.valueOf();
			// user.hobby = $scope.hobby;
			// user.address = $scope.address;
			// user.bio = $scope.bio;
			// user.website = $scope.website;

			// user.$updateProfile(function(response) {
			// 	console.log(response);
			// 	switch (response.code) {
			// 		case CODE.SUCCESS: {
			// 			ngDialog.close();
			// 			$state.reload();
			// 		}
			// 	}
			// }, function() {

			// });
		};

		$scope.closeDialog = function() {
			ngDialog.close();
		};
	};

	updateProfileCtrl.$inject = ['$scope', '$state', '$translate', 'UserService', 'ngDialog', 'veazyConfig', 'localStorageService'];
	angular.module('veazyControllers').controller('updateProfileCtrl', updateProfileCtrl);
})();