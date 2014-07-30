var profile = angular.module('EstimationCalibrator',['ui.bootstrap', 'ngRoute']);

profile.directive('mdLang', function() {
	return {
		restrict: 'E',
		templateUrl: '/html/language.html',
		scope: {language: '=', fireSelectEvent: '=onSelect'},
		controller: function($scope, $http) {
			$http.get('/language').success(function(data, status, headers, config){
				$scope.languages = data;
			});
			$scope.onSelect = function(language) {
				$scope.status.isopen = false;
				$scope.language = language;
				if ($scope.fireSelectEvent) {
					$scope.fireSelectEvent(language);
				}
			}
		}
	}
});

profile.directive('mdQuestionEditForm', function(){
	return {
		restrict: 'E',
		templateUrl: '/html/question-edit-form.html',
		scope: {question: '=', fireSaveEvent: '=onSave'},
		controller: function($scope, $http, $log) {
			var baselineQuestion;
			
			function rebaseline() {
				baselineQuestion = angular.copy($scope.question);
				$scope.isDirty = false;
			}
			
			function save() {
				$http.put('/question', $scope.question)
					.success(function(newQuestion) {
						if (!$scope.question.questionId) {
							$scope.fireSaveEvent(newQuestion);
						}
						$scope.question = newQuestion;
				})
				.error(function() {
					alert("blah");
				});
			}
			
			function newQuestion() {
				var newQuestion = angular.copy($scope.question);
				newQuestion.questionId = null;
				newQuestion.prose = "";
				newQuestion.exactAnswer = "";
				$scope.question = newQuestion;
			}
			
			function updateDirtyFlag() {
				if (angular.equals($scope.question, baselineQuestion)) {
					$scope.isDirty = false;
				} else {
					$scope.isDirty = true;
				}
			}

			$scope.save = save;
			$scope.newQuestion = newQuestion;
			
			$scope.$watch('question', rebaseline);
			$scope.$watch('question', updateDirtyFlag, true);
		}
	}
});

profile.directive('mdQuestionList', function(){
	return {
		restrict: 'E',
		templateUrl: '/html/question-list.html',
		scope: {questions: '=', fireSelectEvent: '=onSelect'},
		controller: function($scope, $http) {
			$scope.onSelect = function(question) {
				$scope.fireSelectEvent(question);
			};
			$scope.onDelete = function(question) {
				var request = {method: "DELETE", url: "/question/"+question.questionId, headers: {}};
				request.headers[_csrfHeaderName] = _csrfToken;
				$http( request )
					.success(function(){var n = $scope.questions.indexOf(question); $scope.questions.splice(n,1);})
					.error(function(){alert("Failed to delete.")});
			};
		}
	}
});

profile.controller('EstimatorCtrl', function($scope, $http, $location) {
	$http.defaults.headers.post[_csrfHeaderName] = _csrfToken;
	$http.defaults.headers.put[_csrfHeaderName] = _csrfToken;
	
	$scope._csrfParameterName = _csrfParameterName;
	$scope._csrfHeaderName = _csrfHeaderName;
	$scope._csrfToken = _csrfToken;
	
	function setEstimatorLanguage(language) {
		$http.put('estimator/'+$scope.estimator.username+'/language', language)
			.error(function(){alert("failed to set language");});
	}
	$scope.setLanguage = setEstimatorLanguage;

	$scope.addQuestion = function(question) {
		$scope.yourQuestions.push(question);
	}
	
	$scope.selectQuestion = function(question) {
		$scope.question = question;
	}
	
	$http.get('/estimator/current')
		.success(function(estimator) {
			$scope.estimator = estimator;
		})
		.error(function(){alert("Couldn't load estimator.")});
	
	$scope.$watch("estimator.username", function() {
		if ($scope.estimator) {
			$http.get('/question', {'params': {'username':$scope.estimator.username}})
				.success(function(data){$scope.yourQuestions = data;})
				.error(function(){alert("bad question")});
			$scope.question = { prose: '', exactAnswer: '', ownerUsername: $scope.estimator.username, language: $scope.estimator.language };
		}
	});
});

profile.config(['$routeProvider',
                    function($routeProvider) {
                      $routeProvider.
                        when('/', {
                          templateUrl: '/html/estimator-view.html',
                          controller: 'EstimatorCtrl'
                        }).
                        otherwise({
                          redirectTo: '/'
                        });
                    }]);