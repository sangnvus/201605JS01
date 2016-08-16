;(function() {
	var veazyFooter = function() {
		return {
			restrict: 'E',
			templateUrl: 'partials/footer.html'
		};
	};

	veazyFooter.$inject = [];
	angular.module('veazyDirectives').directive('veazyFooter', veazyFooter);
})();