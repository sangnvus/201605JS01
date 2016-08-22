;(function() {
	var lessonListCtrl = function($scope, $state, $filter, FileUploader, ngDialog, LessonService, veazyConfig) {
		var CODE = veazyConfig.CODE;
		// $scope.uploader = new FileUploader({
		// 	url: 'http://163.44.172.52:8080/Veazy/uploadfile',
		// 	method: 'POST',
		// 	withCredentials: true
		// });

		LessonService.getAllLessons().then(function(response) {
			switch (response.code) {
				case CODE.SUCCESS: {
					// deferred.resolve(response);

					$scope.lessonList = response.data;
					$scope.filteredLessonList = $scope.lessonList;

					//pagination
					$scope.totalItems = $scope.lessonList.length;
					$scope.currentPage = 1;
					$scope.numPerPage = 5;

					$scope.paginate = function(value) {
						var begin, end, index;
						begin = ($scope.currentPage - 1) * $scope.numPerPage;
						end = begin + $scope.numPerPage;
						index = $scope.lessonList.indexOf(value);
						return (begin <= index && index < end);
					};
					break;
				}
				case CODE.UNAUTHORIZED: {
					$state.go('login');
					break;
				}
				default: {

				}
			}
		}, function() {

		});

		$scope.levels = veazyConfig.levels.slice(0);
		$scope.levels.splice(0, 0, {
			id: 0,
			name: 'ALL_LEVELS'
		});
		$scope.selectedLevel = $scope.levels[0];

		$scope.filter = function() {
			var courseId = $scope.selectedLevel.id;

			$scope.filteredLessonList = $filter('lesson')($scope.lessonList, courseId);
		};

		$scope.openConfirmDeleteDialog = function(lesson) {
			ngDialog.open({
				template: 'partials/content-editor-restricted/delete-lesson.html',
				className: 'ngdialog-theme-default delete-lesson-dialog',
				showClose: true,
				closeByDocument: false,
				width: 500,
				controller: 'deleteLessonDialogCtrl',
				data: {
					lesson: lesson,
					lessonList: $scope.lessonList
				}
			});
		};
	};

	lessonListCtrl.$inject = ['$scope', '$state', '$filter', 'FileUploader', 'ngDialog', 'LessonService', 'veazyConfig'];
	angular.module('veazyControllers').controller('lessonListCtrl', lessonListCtrl);
})();