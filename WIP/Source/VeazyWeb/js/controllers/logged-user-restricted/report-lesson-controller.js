;(function() {
	'use strict';
	var reportLessonCtrl = function($scope, veazyConfig, ngDialog, LessonService) {
		var CODE = veazyConfig.CODE;
		$scope.reportSent = false;

		//click send report button
		$scope.submitReport = function() {
			var lessonId = $scope.ngDialogData.lessonId;
			var content = $scope.content;

			//call service
			LessonService.report(lessonId, content).then(function(response) {
				console.log(response);
				switch (response.code) {
					case CODE.SUCCESS: {
						$scope.reportSent = true;
						break;
					}

					default: {
						//handling error
					}
				}
			}, function(reject) {
				//handling error
			});
		};

		//click cancel button
		$scope.closeDialog = function() {
			ngDialog.close();
		};
	};

	reportLessonCtrl.$inject = ['$scope', 'veazyConfig', 'ngDialog', 'LessonService'];
	angular.module('veazyControllers').controller('reportLessonCtrl', reportLessonCtrl);
})();