;(function() {
	var fileInput = function() {
		return {
			restrict: 'A',
			link: function(scope, element, attrs) {
				$(element).fileinput({showUpload: false, showRemove: false});
			}
		}
	};

	angular.module('veazyDirectives').directive('fileInput', fileInput);
})();