;(function() {
	'use strict';
	angular.module('veazyControllers').controller('questionDetailController', ['$scope', function($scope) {
		$scope.questionVersions = [{
			id: 1,
			state: 'Published'
		}, {
			id: 2,
			state: 'Reviewing'
		}, {
			id: 3,
			state: 'Updating'
		}];

		$scope.questionVersion = $scope.questionVersions[0];

		$scope.questionDetailVersions = [{
			type: 'シングル質問',
			level: 'マスター',
			skill: '語彙',
			content: '雷神の隣で同行してるのは何？',
			answers: ['1番の解答', '2番の解答', '3番の解答', '4番の解答']
		}, {
			type: 'シングル質問',
			level: 'マスター',
			skill: '語彙',
			content: '鎌鼬はいつも何匹で出現してる？',
			answers: ['1番の解答', '2番の解答', '3番の解答', '4番の解答', '5番の解答']
		}, {
			type: 'シングル質問',
			level: 'マスター',
			skill: '文法',
			content: '雷神の隣で同行してるのは何？',
			answers: ['1番の解答', '2番の解答', '3番の解答', '4番の解答']
		}];


		$scope.questionDetail = $scope.questionDetailVersions[$scope.questionVersion.id - 1];

		$scope.changeVersion = function() {
			$scope.questionDetail = $scope.questionDetailVersions[$scope.questionVersion.id - 1];
			console.log($scope.questionVersion.id - 1);
		};
	}]);
})();