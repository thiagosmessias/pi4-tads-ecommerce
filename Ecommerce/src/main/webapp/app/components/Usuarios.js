angular.module('AppMain')
        .controller('UserController', function ($rootScope, api, utils) {
            /**
             * Get the object
             */
            this.data = {};

            this.save = function () {
                api.call('/ServletUsuario', 'post', this.data)
                        .then(function (response) {
                            if (response.status == 200 || response.status == 201) {
                                alert("Salvo com sucesso");
                            }
                        }, function (response) {
                            alert("Algum erro ocorreu");
                            console.log(response);
                        });
            };

            this.get = function () {
                api.call('/ServletUsuario', 'get', this.data)
                        .then(function (response) {
                            if (response.status === 200) {
                                this.data = response.data;
                                $rootScope.user = response.data;
                            }
                        }, function (response) {
                            alert("Algum erro ocorreu");
                            console.log(response);
                        });
            };

            if ('user' in $rootScope) {
                this.get();
            }
        });
