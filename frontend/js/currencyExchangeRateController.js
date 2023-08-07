angular.module('currencyExchangeRateApp')
    .controller('currencyExchangeRateController', function($scope, currencyExchangeRateService) {
        $scope.requestAuthor;
        $scope.currency;
        $scope.itemsPerPage = 5;
        $scope.currentPage = -1;

        $scope.getCurrencyRequests = function(page, size) {
            currencyExchangeRateService.getCurrencyRequests(page, size).then(
                function successCallback(response) {
                    $scope.currenciesData = response.data;
                },
                function errorCallback(response) {
                    alert("API Doesn't response")
                });
        }

        $scope.getCurrentExchangeRate = function () {
            currencyExchangeRateService.getCurrentCurrencyExchangeRate($scope.currency, $scope.requestAuthor).then(
                function successCallback(response) {
                    console.log(response);
                    $scope.updateData(0)
                    alert("Exchange rate for: " + $scope.currency + " is " + response.data.value);
                },
                function errorCallback(response) {
                    alert(response.data.errorMessage);
                }
            )

        }

        $scope.formatDate = function(inputDate) {
            const date = new Date(inputDate);
            const year = date.getFullYear().toString().padStart(4, '0');
            const month = (date.getMonth() + 1).toString().padStart(2, '0');
            const day = date.getDate().toString().padStart(2, '0');
            const hours = date.getHours().toString().padStart(2, '0');
            const minutes = date.getMinutes().toString().padStart(2, '0');
            const seconds = date.getSeconds().toString().padStart(2, '0');
            return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
        }

        $scope.changeItemsPerPage = function() {
            $scope.currentPage = 0;
            this.getCurrencyRequests($scope.currentPage, $scope.itemsPerPage)
        };

        $scope.updateData = function(page) {
            $scope.currentPage = page;
            this.getCurrencyRequests($scope.currentPage, $scope.itemsPerPage);
        }
    });
