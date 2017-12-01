angular.module('AppMain')
        .factory('utils', function ($rootScope) {
            return {
                // Converte um objeto para uma string no padrão de parametros de url
                obj2url: function (data) {
                    var url = '';
                    if (typeof data === "object" && !Array.isArray(data)) {
                        for (attr in data) {
                            if (typeof data[attr] !== "object") {
                                if (url.length > 0) {
                                    url += '&';
                                }
                                url += attr + '=' + data[attr];
                            }
                        }
                    }
                    return url;
                },
            };
        });
