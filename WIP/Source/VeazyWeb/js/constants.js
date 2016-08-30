;(function() {
	var app = angular.module('veazyApp');

	app.constant('veazyConfig', {
		CODE: {
			BEGINNER_LEVEL: 1,
			UPPER_BEGINNER_LEVEL: 2,
			INTERMEDIATE_LEVEL: 3,
			UPPER_INTERMEDIATE_LEVEL: 4,
			ADVANCED_LEVEL: 5,
			MASTER_LEVEL: 6,
			SINGLE_QUESTION_TYPE: 1,
			GROUP_QUESTION_TYPE: 2,
			VOCABULARY_SKILL: 1,
			GRAMMAR_SKILL: 2,
			LISTENING_SKILL: 3,
			READING_SKILL: 4,

			ADMIN: 1,
			EDITOR: 2,
			USER: 3,

			SUCCESS: 200,
			UNAUTHORIZED: 401,
			NO_PERMISSION: 403,
			INCORRECT_USERNAME_PASSWORD: 470,
			// INVALID_EMAIL: 410,
			// DUPPLICATED_EMAIL: 411,
			// DUPPLICATED_USERNAME: 413,
			BAD_REQUEST: 500
		},
		levels: [{
			id: 1,
			name: 'BEGINNER_LEVEL'
		}, {
			id: 2,
			name: 'UPPER_BEGINNER_LEVEL'
		}, {
			id: 3,
			name: 'INTERMEDIATE_LEVEL'
		}, {
			id: 4,
			name: 'UPPER_INTERMEDIATE_LEVEL'
		}, {
			id: 5,
			name: 'ADVANCED_LEVEL'
		}, {
			id: 6,
			name: 'MASTER_LEVEL'
		}],
		questionTypes: [{
			id: 1,
			name: 'SINGLE_QUESTION_TYPE'
		}, {
			id: 2,
			name: 'GROUP_QUESTION_TYPE'
		}],
		testSkills: [{
			id: 1,
			name: 'VOCABULARY_SKILL'
		}, {
			id: 2,
			name: 'GRAMMAR_SKILL'
		}, {
			id: 3,
			name: 'LISTENING_SKILL'
		}, {
			id: 4,
			name: 'READING_SKILL'
		}],
		lessonStates: [{
			id: 1,
			name: 'Published'
		}, {
			id: 2,
			name: 'Draft'
		}],
		roles: [{
			id: 1, 
			name: 'ADMIN'
		}, {
			id: 2, 
			name: 'EDITOR'
		}, {
			id: 3, 
			name: 'USER'
		}],
		statuses: [{
			id: 1,
			name: 'BANNED'
		}, {
			id: 2,
			name: 'ACTIVE'
		}],
		REGULATIONS: {
			USERNAME_MIN_LENGTH: 6,
			USERNAME_MAX_LENGTH: 30,
			PASSWORD_MIN_LENGTH: 6,
			PASSWORD_MAX_LENGTH: 32,
			MIN_QUESTION_NUMBER_IN_EXAM: 5
		},
		MESSAGE: {
			470: 'INCORRECT_USERNAME_PASSWORD',
			410: 'INVALID_EMAIL',
			411: 'DUPPLICATED_EMAIL',
			413: 'DUPPLICATED_USERNAME',
			500: 'BAD_REQUEST'
		}
	});
})();