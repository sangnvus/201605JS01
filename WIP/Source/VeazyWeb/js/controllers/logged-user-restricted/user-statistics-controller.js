;(function() {
	'use strict';
	var userStatisticsCtrl = function($scope, User, veazyConfig, UserService) {
		var CODE = veazyConfig.CODE;

		// $scope.skillData = [
		// 	[65, 59, 90, 81, 0]
		// ];

		// $scope.testGradeLabels = ['Test 1', 'Test 2', 'Test3', 'Test 4', 'Test 5', 'Test 6', 'Test 7', 'Test 8', 'Test 9', 'Test 10'];
		// $scope.testGradeData = [
		// 	[65, 59, 48, 81, 56, 55, 40, 78, 100, 57]
		// ];
		
		$scope.levelLabels = ['Beginner', 'Upper-beginner', 'Intermediate', 'Upper-intermediate', 'Advanced', 'Master'];
		$scope.levelData = [
			[65, 59, 80, 81, 92, 55, 40]
		];
		// $scope.onClick = function (points, evt) {
		// 	console.log(points, evt);
		// };
		// $scope.datasetOverride = [{ yAxisID: 'y-axis-1' }, { yAxisID: 'y-axis-2' }];
		// $scope.options = {
		// 	scales: {
		// 		yAxes: [{
		// 			id: 'y-axis-1',
		// 			type: 'linear',
		// 			display: true,
		// 			position: 'left'
		// 		}, {
		// 			id: 'y-axis-2',
		// 			type: 'linear',
		// 			display: true,
		// 			position: 'right'
		// 		}
		// 	]}
		// };

		//get stats of skills
		UserService.getSkillStats().then(function(response) {
			console.log(response);
			switch (response.code) {
				case CODE.SUCCESS: {
					var data = response.data;
					$scope.skillLabels =['Vocabulary', 'Grammar', 'Listening', 'Reading', 'Writing'];
					$scope.skillData = [
						[data.vocabulary, data.grammar, data.listening, data.reading, 9]
					];
				}

				default: {
					//handling other case
				}
			}
		}, function(reject) {
			//handling error
		});

		//get marks of 10 recently exams
		UserService.getExamMarkStats(10).then(function(response) {
			console.log(response);
			switch (response.code) {
				case CODE.SUCCESS: {
					var data = response.data;
					var length = response.data.length;

					$scope.testGradeLabels = [];
					for (var i = 0; i < length; i++) {
						$scope.testGradeLabels.push('Test' + (i + 1));
					}

					$scope.skillData = [response.data];
				}

				default: {
					//handling other case
				}
			}
		}, function(reject) {
			//handling error
		});

		//get marks of test based on level
		UserService.getLevelStats().then(function(response) {
			console.log(response);
			switch (response.code) {
				case CODE.SUCCESS: {
					// var data = response.data;
					// var length = response.data.length;

					// $scope.testGradeLabels = [];
					// for (var i = 0; i < length; i++) {
					// 	$scope.testGradeLabels.push('Test' + (i + 1));
					// }

					// $scope.skillData = [response.data];
				}

				default: {
					//handling other case
				}
			}
		}, function(reject) {

		});
	};

	userStatisticsCtrl.$inject = ['$scope', 'User', 'veazyConfig', 'UserService'];
	angular.module('veazyControllers').controller('userStatisticsCtrl', userStatisticsCtrl);
})();