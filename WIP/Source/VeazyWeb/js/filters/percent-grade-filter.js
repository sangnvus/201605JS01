;(function() {
	var percentGrade = function() {
		return function(input) {
			if (input == null || typeof input !== 'number') {
				return;
			}

			var percentGrade = input.toFixed(2) * 100 + '%';

			return percentGrade;
		};
	};

	angular.module('veazyFilters').filter('percentGrade', percentGrade);
})();