angular.module('AppMain')
        .factory('pedido', function ($rootScope, $localStorage) {
            var $storage = $localStorage;

            $rootScope.$watch('shoppingList', function () {
                console.log($rootScope.shoppingList);
            });

            var userId = '';
            if ('user' in $rootScope) {
                userId = $rootScope.user.id ? $rootScope.user.id : '';
            }

            console.log('shoppingList', 'shoppingList' in $storage, typeof $storage.shoppingList == 'object');

            if ('shoppingList' in $storage && typeof $storage.shoppingList == 'object') {
                $rootScope.shoppingList = $storage.shoppingList;
            } else {
                $rootScope.shoppingList = {
                    products: [],
                    id: userId + '-' + Math.random()
                };
            }

            return {
                list: function () {
                    var products = [];
                    if (Array.isArray($rootScope.shoppingList.products)) {
                        products = $rootScope.shoppingList.products;
                    }
                    return products;
                },
                add: function (product) {
                    console.log('add', product);
                    if ('id' in product) {
                        console.log(product)
                        product.quantidade = 1;
                        $rootScope.shoppingList.products.push(product);
                        $storage.shoppingList = $rootScope.shoppingList;
                    } else {
                        alert("Não foi possível adicionar esse produto, houve um erro!");
                        console.log("O produto adicionado não tinha ID");
                    }

                },
                update: function (index, newProductValues) {
                    if (typeof $rootScope.shoppingList.products[index] != 'undefined') {
                        var product = $rootScope.shoppingList.products[index];
                        var properties = Object.keys(newProductValues);
                        for (var property in properties) {
                            if (product[property] != newProductValues[property]) {
                                product[property] = newProductValues[property];
                            }
                        }
                        $rootScope.shoppingList.product[index] = product;
                    } else {
                        alert("Não foi possível atualizar este produto");
                    }
                    $storage.shoppingList = $rootScope.shoppingList;
                },
                delete: function (index) {
                    console.log('delete', index);
                    if (typeof $rootScope.shoppingList.products[index] != 'undefined') {
                        $rootScope.shoppingList.products.splice(index, 1);
                    }
                    $storage.shoppingList = $rootScope.shoppingList;
                },
                clear: function () {
                    $rootScope.shoppingList.products = [];
                    $storage.shoppingList = $rootScope.shoppingList;
                }
            };
        });
