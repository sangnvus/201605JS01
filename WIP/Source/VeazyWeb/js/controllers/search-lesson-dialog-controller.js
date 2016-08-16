;(function() {
	'use strict';

	var searchLessonCtrl = function($scope, $location, ngDialog) {
		//Handle data
		console.log($scope.ngDialogData)
		$scope.closeDialog = function() {
			// $location.path('/courselist/lessondetail');
			ngDialog.close();
		};
	};

	searchLessonCtrl.$inject = ['$scope', '$location', 'ngDialog'];


	angular.module('veazyControllers').controller('searchLessonCtrl', searchLessonCtrl);
})();