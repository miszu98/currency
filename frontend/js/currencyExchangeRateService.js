angular.module('currencyExchangeRateApp')
    .service('currencyExchangeRateService', function($http) {
        const currencyExchangeRateApiBaseUrl = 'http://localhost:8080/api/v1/currencies/';
        this.getCurrencyRequests = function(page, size) {
            return $http.get(currencyExchangeRateApiBaseUrl + "requests?page=" + page + "&size=" + size + "&sort=date,desc");
        }
        this.getCurrentCurrencyExchangeRate = function(currencyCode, authorRequest) {
            return $http.post(currencyExchangeRateApiBaseUrl + "get-current-currency-value", {
                "currency": currencyCode,
                "requestAuthor": authorRequest
            });
        }
    });
