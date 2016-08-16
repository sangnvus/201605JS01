;(function() {
	'use strict';
	var lessonDetailCtrl = function($scope, $sce, veazyConfig, ngDialog, getLesson, UserService) {
		var CODE = veazyConfig.CODE;

		//do not show Report hyperlink if user is not logged in
		$scope.isLoggedIn = false;

		//check if user is logged in
		UserService.login().then(function(response) {
			switch (response.code) {
				case CODE.SUCCESS: {
					//logged in, then show Report hyperlink
					$scope.isLoggedIn = true;
					break;
				}

				default: {
					$scope.isLoggedIn = false;
				}
			}
		}, function() {
			$scope.isLoggedIn = false;
		});

		//lesson data
		$scope.lesson = getLesson.data;

		//for binding html string
		$scope.trustAsHtml = function(str) {
			return $sce.trustAsHtml(str);
		};

		//open Report Dialog
		$scope.openReportDialog = function(question) {
			ngDialog.open({
				template: 'partials/logged-user-restricted/report-lesson-dialog.html',
				className: 'ngdialog-theme-default report-dialog',
				controller: 'reportLessonCtrl',
				showClose: true,
				closeByDocument: false,
				width: 400,
				data: {
					lessonId: $scope.lesson.lessonId
				}
			});
		};



		// $scope.openReportDialog = function() {
		// 	ngDialog.open({
		// 		template: 'partials/search-lesson-dialog.html',
		// 		className: 'ngdialog-theme-default search-lesson-dialog',
		// 		// plain: true,
		// 		showClose: true,
		// 		closeByDocument: false,
		// 		width: 700,
		// 		controller: 'searchLessonCtrl',
		// 		data: {
		// 			id: 1,
		// 			choice: {
		// 				selectable: true,
		// 				name: 'Haha'
		// 			}
		// 		}
		// 	});
		// };

		$scope.searchLesson = function() {
			ngDialog.open({
				template: 'partials/search-lesson-dialog.html',
				className: 'ngdialog-theme-default search-lesson-dialog',
				// plain: true,
				showClose: true,
				closeByDocument: false,
				width: 700,
				controller: 'searchLessonCtrl',
				data: {
					id: 1,
					choice: {
						selectable: true,
						name: 'Haha'
					}
				}
			});
		};
	};

	lessonDetailCtrl.$inject = ['$scope', '$sce', 'veazyConfig', 'ngDialog', 'getLesson', 'UserService'];

	angular.module('veazyControllers').controller('lessonDetailCtrl', lessonDetailCtrl);
})();