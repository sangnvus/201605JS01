;(function() {
	editorSideBarCtrl = function($scope, $location) {
		$scope.showAddLessonPage = function() {
			console.log('here')
			$location.path('/addlesson')
		};

		$scope.showAddQuestionPage = function() {
			$location.path('/editor/addquestion');
		};
	};

	editorSideBarCtrl.$inject = ['$scope', '$location'];

	angular.module('veazyControllers').controller('editorSideBarCtrl', editorSideBarCtrl);
})();