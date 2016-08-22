;(function() {
	var flexSlider = function() {
		return {
			restrict: 'A',
			link: function(scope, element, attrs) {
				$(element).flexslider({
					directionNav: false,
					controlNav: false,
					slideshowSpeed: 4000
				});
			}
		}
	};

	angular.module('veazyDirectives').directive('flexSlider', flexSlider);
})();